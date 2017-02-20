package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.model.StudentXML;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface StudentWebService {

    Student saveStudent(Student student);
    Student getStudent(long id);
    void deleteStudent(long id);
    StudentXML[] getAllStudent();

    StudentGroup saveStudentGroup(StudentGroup group);
    StudentGroup getStudentGroup(long id);
    StudentGroup getStudentGroupByName(String name);
    void deleteStudentGroup(long id);
    StudentGroup[] getAllStudentGroup();
}
