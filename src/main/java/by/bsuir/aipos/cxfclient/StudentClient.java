package by.bsuir.aipos.cxfclient;

import by.bsuir.aipos.cxflib.StudentWebService;
import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentClient extends Thread {

    private static final QName SERVICE_NAME
            = new QName("http://cxflib.aipos.bsuir.by/", "StudentWebService");
    private StudentWebService studentWebService;
    private final MainWindow mainWindow;
    private int port;
    private String host;


    public StudentClient(String host, int port, MainWindow mainWindow) {
        this.host = host;
        this.port = port;
        this.mainWindow = mainWindow;
    }

    public void run() {
        MainWindow.logger.info("Start client");
        try {
            Service service = Service.create(new URL("http://" + host + ":" + port + "/student?wsdl"), SERVICE_NAME);
            studentWebService = service.getPort(StudentWebService.class);
            mainWindow.updateTable();
        } catch (Exception e) {
            e.printStackTrace();
            MainWindow.logger.error("Error in StudentClient run() ");
            MainWindow.logger.trace(e);
            System.exit(-1);
        }
    }

    public StudentXML saveStudent(StudentXML student) {
        try {
            student = studentWebService.saveStudent(student);
        } catch (Exception e) {
            MainWindow.logger.error("Error in saveStudent");
            MainWindow.logger.trace(e);
        }
        return student;
    }

    public StudentXML getStudent(long id) {
        StudentXML student = new StudentXML();
        try {
            student = studentWebService.getStudent(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in getStudent");
            MainWindow.logger.trace(e);
        }
        return student;
    }

    public void deleteStudent(long id) {
        try {
            studentWebService.deleteStudent(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in deleteStudent");
            MainWindow.logger.trace(e);
        }
    }

    public List<StudentXML> getAllStudent(){
        List<StudentXML> student = new ArrayList<>();
        try {
            student = Arrays.asList(studentWebService.getAllStudent());
        } catch (Exception e) {
            MainWindow.logger.error("Error in getAllStudent");
            MainWindow.logger.trace(e);
        }
        return student;
    }

    public StudentGroupXML saveStudentGroup(StudentGroupXML studentGroup) {
        try {
            studentGroup = studentWebService.saveStudentGroup(studentGroup);
        } catch (Exception e) {
            MainWindow.logger.error("Error in saveStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroup;
    }

    public StudentGroupXML getStudentGroup(long id) {
        StudentGroupXML studentGroup = new StudentGroupXML();
        try {
            studentGroup = studentWebService.getStudentGroup(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in getStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroup;
    }

    public StudentGroupXML getStudentGroupByName(String name) {
        StudentGroupXML studentGroup = new StudentGroupXML();
        try {
            studentGroup = studentWebService.getStudentGroupByName(name);
        } catch (Exception e) {
            MainWindow.logger.error("Error in getStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroup;
    }

    public void deleteStudentGroup(long id) {
        try {
            studentWebService.deleteStudentGroup(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in deleteStudentGroup");
            MainWindow.logger.trace(e);
        }
    }

    public List<StudentGroupXML> getAllStudentGroup() {
        List<StudentGroupXML> studentGroups = new ArrayList<>();
        try {
            studentGroups = Arrays.asList(studentWebService.getAllStudentGroup());
        } catch (Exception e) {
            MainWindow.logger.error("Error in getAllStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroups;
    }

}
