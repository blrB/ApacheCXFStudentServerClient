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
    /**
     * Service name
     */
    private static final QName SERVICE_NAME
            = new QName("http://cxflib.aipos.bsuir.by/", "StudentWebService");
    /**
     * Web service for handling of students
     */
    private StudentWebService studentWebService;
    /**
     * Main window instance
     */
    private final MainWindow mainWindow;
    /**
     * Server's port
     */
    private int port;
    /**
     * Server's host address
     */
    private String host;

    /**
     * Creates StudentClient by setting port, host and main window
     *
     * @param host       server's host address
     * @param port       server's port number
     * @param mainWindow instance of main window
     */
    public StudentClient(String host, int port, MainWindow mainWindow) {
        this.host = host;
        this.port = port;
        this.mainWindow = mainWindow;
    }

    /**
     * Creates connection
     */
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

    /**
     * Saves edited/created student
     * @param student student to save
     * @return saved student
     */
    public StudentXML saveStudent(StudentXML student) {
        try {
            student = studentWebService.saveStudent(student);
        } catch (Exception e) {
            MainWindow.logger.error("Error in saveStudent");
            MainWindow.logger.trace(e);
        }
        return student;
    }

    /**
     * Gets student by id
     * @param id identifier of student to find
     * @return found student
     */
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

    /**
     * Deletes student by id
     *
     * @param id identifier of student to delete
     */
    public void deleteStudent(long id) {
        try {
            studentWebService.deleteStudent(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in deleteStudent");
            MainWindow.logger.trace(e);
        }
    }

    /**
     * Returns list of all students
     * @return list of all students
     */
    public List<StudentXML> getAllStudent() {
        List<StudentXML> student = new ArrayList<>();
        try {
            student = Arrays.asList(studentWebService.getAllStudent());
        } catch (Exception e) {
            MainWindow.logger.error("Error in getAllStudent");
            MainWindow.logger.trace(e);
        }
        return student;
    }

    /**
     * Saves student group
     *
     * @param studentGroup student group to save
     * @return saved student group
     */
    public StudentGroupXML saveStudentGroup(StudentGroupXML studentGroup) {
        try {
            studentGroup = studentWebService.saveStudentGroup(studentGroup);
        } catch (Exception e) {
            MainWindow.logger.error("Error in saveStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroup;
    }

    /**
     * Returns student group with given id
     * @param id identifier of student group
     * @return student group that has been found
     */
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

    /**
     * Returns student group with given name
     * @param name name of student group
     * @return student group that has been found
     */
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

    /**
     * Delete student group by id
     * @param id identifier of student group to delete
     */
    public void deleteStudentGroup(long id) {
        try {
            studentWebService.deleteStudentGroup(id);
        } catch (Exception e) {
            MainWindow.logger.error("Error in deleteStudentGroup");
            MainWindow.logger.trace(e);
        }
    }

    /**
     * Returns all existing student groups
     * @return all existing student groups
     */
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
