package by.bsuir.aipos.cxfclient;

import by.bsuir.aipos.cxfserver.StudentServer;
import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDialog {

    private long id;
    private String dialogName;
    private final static String FIRST_NAME = "FIRST NAME";
    private final static String LAST_NAME = "LAST NAME";
    private final static String MIDDLE_NAME = "MIDDLE NAME";
    private final static String DATE_OF_BIRTH = "DATE OF BIRTH";
    private final static String ADDRESS = "ADDRESS";
    private final static String GROUP = "GROUP";
    private String[] labelString = {LAST_NAME, FIRST_NAME, MIDDLE_NAME, ADDRESS};
    private String[] studentGroupArray;
    private JDialog dialog;
    private Map<String, JTextField> fieldID = new HashMap<String, JTextField>();
    private JComboBox<String> group;
    private JDateChooser jDateChooser = new JDateChooser();
    private MainWindow mainWindow;

    public StudentDialog(MainWindow mainWindow, String dialogName) {
        this.mainWindow = mainWindow;
        this.dialogName = dialogName;
        this.studentGroupArray = getArrayGroupName();
        this.group = new JComboBox(studentGroupArray);
        for (int field = 0; field < labelString.length; field++) {
            JTextField jtfField = new JTextField(30);
            fieldID.put(labelString[field], jtfField);
        }
        jDateChooser.setDateFormatString("yyyy-MM-dd");
        jDateChooser.setDate(new Date());
        jDateChooser.getDateEditor().setEnabled(false);
    }

    public void show(){
        dialog = new JDialog(new JFrame(), dialogName, true);
        createFrame();
        dialog.setLocationRelativeTo(mainWindow.getFrame());
        dialog.setSize(500,200);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public void closeDialog(){
        dialog.dispose();
        dialog = null;
    }

    private void createFrame(){
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        JLabel labelText = new JLabel(this.dialogName);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 0, 2, 1);
        for (int field = 0; field < labelString.length; field++) {
            labelText = new JLabel(labelString[field]);
            AddComponent.add(jPanelID, labelText, 0, field + 1, 1, 1);
            AddComponent.add(jPanelID, fieldID.get(labelString[field]), 1, field + 1, 1, 1);
        }
        int offset = labelString.length + 1;
        labelText = new JLabel(DATE_OF_BIRTH);
        AddComponent.add(jPanelID, labelText, 0, offset, 1, 1);
        AddComponent.add(jPanelID, jDateChooser, 1, offset, 1, 1);
        offset++;
        labelText = new JLabel(GROUP);
        AddComponent.add(jPanelID, labelText, 0, offset, 1, 1);
        AddComponent.add(jPanelID, group, 1, offset, 1, 1);
        dialog.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton(dialogName);
        okButton.addActionListener(actionEvent -> checkAndSave());
        dialog.add(okButton, BorderLayout.SOUTH);
    }

    private String[] getArrayGroupName() {
        java.util.List<StudentGroupXML> studentGroupThrifts = mainWindow.getStudentClient().getAllStudentGroup();
        List<String> groupName = studentGroupThrifts.stream()
                .map(StudentGroupXML::getName).collect(Collectors.toList());
        return groupName.toArray(new String[groupName.size()]);
    }

    private void checkAndSave(){
        if (haveEmptyField()){
            JOptionPane.showMessageDialog(dialog,
                    "Some field are empty!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saveStudent();
        }
    }

    private boolean haveEmptyField() {
        return  getTextID(FIRST_NAME).isEmpty() ||
                getTextID(LAST_NAME).isEmpty() ||
                getTextID(ADDRESS).isEmpty();
    }

    private void saveStudent(){
        StudentGroupXML group = mainWindow.getStudentClient().getStudentGroupByName
                (this.group.getSelectedItem().toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = format.format(jDateChooser.getDate());
        StudentXML student = new StudentXML(
                getTextID(FIRST_NAME),
                getTextID(LAST_NAME),
                getTextID(MIDDLE_NAME),
                birthDate,
                getTextID(ADDRESS),
                group
        );
        student.setId(id);
        mainWindow.getStudentClient().saveStudent(student);
        closeDialog();
    }

    private String getTextID(String key) {
        return fieldID.get(key).getText();
    }

    private void setTextID(String key, String value) {
        fieldID.get(key).setText(value);
    }

    public void setField(StudentXML student){
        id = student.getId();
        setTextID(FIRST_NAME, student.getFirstName());
        setTextID(LAST_NAME, student.getLastName());
        if (student.getMiddleName() != null){
            setTextID(MIDDLE_NAME, student.getMiddleName());
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = format.parse(student.getDateOfBirth());
            jDateChooser.setDate(birthDate);
        } catch (ParseException e) {
            MainWindow.getLogger().error("ParseException");
            StudentServer.getLogger().trace(e);
        }
        setTextID(ADDRESS, student.getHomeAddress());
        group.setSelectedIndex
                (Arrays.asList(studentGroupArray).indexOf(student.getStudentGroupXML().getName()));
    }

}
