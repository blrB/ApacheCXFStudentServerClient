package by.bsuir.aipos.cxfclient;

import by.bsuir.aipos.model.StudentXML;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.StringJoiner;

public class StudentTableModel implements TableModel {

    private final static int FIRST_NAME = 0;
    private final static int LAST_NAME = 1;
    private final static int MIDDLE_NAME = 2;
    private final static int DATE_OF_BIRTH = 3;
    private final static int ADDRESS = 4;
    private final static int GROUP = 5;
    private static final int COLUMN_COUNT = 6;
    private final static String FIRST_NAME_COLUMN = "FIRST NAME";
    private final static String LAST_NAME_COLUMN = "LAST NAME";
    private final static String MIDDLE_NAME_COLUMN = "MIDDLE NAME";
    private final static String DATE_OF_BIRTH_COLUMN = "DATE OF BIRTH";
    private final static String ADDRESS_COLUMN = "ADDRESS";
    private final static String GROUP_COLUMN = "GROUP";
    private List<StudentXML> students;

    public StudentTableModel(List<StudentXML> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case FIRST_NAME:
                return FIRST_NAME_COLUMN;
            case LAST_NAME:
                return LAST_NAME_COLUMN;
            case MIDDLE_NAME:
                return MIDDLE_NAME_COLUMN;
            case DATE_OF_BIRTH:
                return DATE_OF_BIRTH_COLUMN;
            case ADDRESS:
                return ADDRESS_COLUMN;
            case GROUP:
                return GROUP_COLUMN;
            default:
                try {
                    throw new IllegalArgumentException("Column " + Integer.toString(columnIndex) + " not find");
                } catch (IllegalArgumentException e) {
                    MainWindow.logger.trace(e);
                }
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case FIRST_NAME:
                return String.class;
            case LAST_NAME:
                return String.class;
            case MIDDLE_NAME:
                return String.class;
            case DATE_OF_BIRTH:
                return String.class;
            case ADDRESS:
                return String.class;
            case GROUP:
                return StringJoiner.class;
            default:
                try {
                    throw new IllegalArgumentException("Column " + Integer.toString(columnIndex) + " not find");
                } catch (IllegalArgumentException e) {
                    MainWindow.logger.trace(e);
                }
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudentXML studentXML = students.get(rowIndex);
        switch (columnIndex) {
            case FIRST_NAME:
                return studentXML.getFirstName();
            case LAST_NAME:
                return studentXML.getLastName();
            case MIDDLE_NAME:
                return studentXML.getMiddleName();
            case DATE_OF_BIRTH:
                return studentXML.getDateOfBirth();
            case ADDRESS:
                return studentXML.getHomeAddress();
            case GROUP:
                return studentXML.getStudentGroupXML().getName();
            default:
                try {
                    throw new IllegalArgumentException("Column " + Integer.toString(columnIndex) + " not find");
                } catch (IllegalArgumentException e) {
                    MainWindow.logger.trace(e);
                }
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
    }
}
