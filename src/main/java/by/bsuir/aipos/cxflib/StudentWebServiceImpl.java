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

    private static Logger logger = Logger.getLogger(StudentWebServiceImpl.class);
    private static StudentGroupService studentGroupService = new StudentGroupServiceImpl();
    private static StudentService studentService = new StudentServiceImpl();

    public static Logger getLogger() {
        return logger;
    }

    public static StudentGroupService getStudentGroupService() {
        return studentGroupService;
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    @Override
    public StudentXML saveStudent(StudentXML studentXML) {
        logger.info("Save student " + studentXML.getLastName());
        Student student = studentService.save(ConverterForStudentXMLAndORM.convert(studentXML));
        studentXML.setId(student.getId());
        return studentXML;
    }

    @Override
    public StudentXML getStudent(long id) {
        logger.info("Get student " + id);
        StudentXML studentXML = ConverterForStudentXMLAndORM.convert(studentService.get(id));
        return studentXML;
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Delete student " + id);
        studentService.delete(id);
    }

    @Override
    public StudentXML[] getAllStudent(){
        logger.info("Get all students");
        List<Student> students = studentService.getAll();
        List<StudentXML> studentsXML  = new ArrayList<>();
        students.forEach(student -> {
            StudentXML studentXML = ConverterForStudentXMLAndORM.convert(student);
            studentsXML.add(studentXML);
        });
        return studentsXML.toArray(new StudentXML[studentsXML.size()]);
    }

    @Override
    public StudentGroupXML saveStudentGroup(StudentGroupXML group) {
        logger.info("Save group " + group.getName());
        StudentGroup studentGroup = studentGroupService.save(ConverterForStudentXMLAndORM.convert(group));
        group.setId(studentGroup.getId());
        return group;
    }

    @Override
    public StudentGroupXML getStudentGroup(long id) {
        logger.info("Get student group " + id);
        StudentGroupXML studentGroupXML = ConverterForStudentXMLAndORM.convert(studentGroupService.get(id));
        return studentGroupXML;
    }

    @Override
    public StudentGroupXML getStudentGroupByName(String name) {
        logger.info("Get student group " + name);
        StudentGroupXML studentGroupXML = ConverterForStudentXMLAndORM.convert(studentGroupService.get(name));
        return studentGroupXML;
    }

    @Override
    public void deleteStudentGroup(long id) {
        logger.info("Delete student group " + id);
        studentGroupService.delete(id);
    }

    @Override
    public StudentGroupXML[] getAllStudentGroup() {
        logger.info("Get all student");
        List<StudentGroup> studentGroups = studentGroupService.getAll();
        List<StudentGroupXML> studentGroupsXML  = new ArrayList<>();
        studentGroups.forEach(group -> {
            studentGroupsXML.add(ConverterForStudentXMLAndORM.convert(group));
        });
        return studentGroupsXML.toArray(new StudentGroupXML[studentGroupsXML.size()]);
    }
}
