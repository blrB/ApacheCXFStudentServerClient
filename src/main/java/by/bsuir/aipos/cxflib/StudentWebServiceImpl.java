package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;
import by.bsuir.aipos.service.StudentGroupService;
import by.bsuir.aipos.service.StudentGroupServiceImpl;
import by.bsuir.aipos.service.StudentService;
import by.bsuir.aipos.service.StudentServiceImpl;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "by.bsuir.aipos.cxflib.StudentWebService",
        serviceName = "StudentWebService")
public class StudentWebServiceImpl implements StudentWebService {
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(StudentWebServiceImpl.class);
    /**
     * Service for handling with student groups
     */
    private static StudentGroupService studentGroupService;
    /**
     * Service for handling with students
     */
    private static StudentService studentService;

    /**
     * Initialize services for handling with students and student groups
     */
    public StudentWebServiceImpl() {
        studentGroupService = new StudentGroupServiceImpl();
        studentService = new StudentServiceImpl();
    }

    /**
     * Returns logger
     *
     * @return logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Returns service for handling with student groups
     * @return service for handling with student groups
     */
    public static StudentGroupService getStudentGroupService() {
        return studentGroupService;
    }

    /**
     * Returns service for handling with student groups
     * @return service for handling with student groups
     */
    public static StudentService getStudentService() {
        return studentService;
    }

    @Override
    public StudentXML saveStudent(StudentXML studentXML) {
        logger.info("Save student " + studentXML.getLastName());
        Student student = ConverterForStudentXMLAndORM.convert(studentXML);
        getStudentService().save(student);
        studentXML.setId(student.getId());
        return studentXML;
    }

    @Override
    public StudentXML getStudent(long id) {
        logger.info("Get student " + id);
        StudentXML studentXML = ConverterForStudentXMLAndORM.convert(getStudentService().get(id));
        return studentXML;
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Delete student " + id);
        studentService.delete(id);
    }

    @Override
    public StudentXML[] getAllStudent() {
        logger.info("Get all students");
        List<Student> students = getStudentService().getAll();
        List<StudentXML> studentsXML = new ArrayList<>();
        students.forEach(student -> {
            StudentXML studentXML = ConverterForStudentXMLAndORM.convert(student);
            studentsXML.add(studentXML);
        });
        return studentsXML.toArray(new StudentXML[studentsXML.size()]);
    }

    @Override
    public StudentGroupXML saveStudentGroup(StudentGroupXML group) {
        logger.info("Save group " + group.getName());
        StudentGroup studentGroup = ConverterForStudentXMLAndORM.convert(group);
        getStudentGroupService().save(studentGroup);
        group.setId(studentGroup.getId());
        return group;
    }

    @Override
    public StudentGroupXML getStudentGroup(long id) {
        logger.info("Get student group " + id);
        StudentGroupXML studentGroupXML = ConverterForStudentXMLAndORM.convert(getStudentGroupService().get(id));
        return studentGroupXML;
    }

    @Override
    public StudentGroupXML getStudentGroupByName(String name) {
        logger.info("Get student group " + name);
        StudentGroupXML studentGroupXML = ConverterForStudentXMLAndORM.convert(getStudentGroupService().get(name));
        return studentGroupXML;
    }

    @Override
    public void deleteStudentGroup(long id) {
        logger.info("Delete student group " + id);
        getStudentGroupService().delete(id);
    }

    @Override
    public StudentGroupXML[] getAllStudentGroup() {
        logger.info("Get all student");
        List<StudentGroup> studentGroups = getStudentGroupService().getAll();
        List<StudentGroupXML> studentGroupsXML = new ArrayList<>();
        studentGroups.forEach(group -> {
            studentGroupsXML.add(ConverterForStudentXMLAndORM.convert(group));
        });
        return studentGroupsXML.toArray(new StudentGroupXML[studentGroupsXML.size()]);
    }
}
