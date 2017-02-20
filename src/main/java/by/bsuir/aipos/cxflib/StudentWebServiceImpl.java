package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
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
    private StudentGroupService studentGroupService = new StudentGroupServiceImpl();
    private StudentService studentService = new StudentServiceImpl();

    @Override
    public Student saveStudent(Student student) {
        logger.info("Save student " + student.getLastName());
        return studentService.save(student);
    }

    @Override
    public Student getStudent(long id) {
        logger.info("Get student " + id);
        return studentService.get(id);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Delete student " + id);
        studentService.delete(id);
    }

    @Override
    public StudentXML[] getAllStudent(){
        logger.info("Get all students");
        List<Student> list = studentService.getAll();
        List<StudentXML> listXml = new ArrayList<>();
        for (Student student: list) {
            listXml.add(new StudentXML(
                    student.getFirstName(),
                    student.getLastName(),
                    student.getMiddleName(),
                    student.getDateOfBirth().toString(),
                    student.getHomeAddress(),
                    student.getStudentGroup().getName()
                    ));
        }
        return listXml.toArray(new StudentXML[listXml.size()]);
    }

    @Override
    public StudentGroup saveStudentGroup(StudentGroup group) {
        logger.info("Save group " + group.getName());
        return studentGroupService.save(group);
    }

    @Override
    public StudentGroup getStudentGroup(long id) {
        logger.info("Get student group " + id);
        return studentGroupService.get(id);
    }

    @Override
    public StudentGroup getStudentGroupByName(String name) {
        logger.info("Get student group " + name);
        return studentGroupService.get(name);
    }

    @Override
    public void deleteStudentGroup(long id) {
        logger.info("Delete student group " + id);
        studentGroupService.delete(id);
    }

    @Override
    public StudentGroup[] getAllStudentGroup() {
        logger.info("Get all student");
        List<StudentGroup> list = studentGroupService.getAll();
        return list.toArray(new StudentGroup[list.size()]);
    }
}
