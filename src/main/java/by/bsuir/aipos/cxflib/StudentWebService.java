package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;

import javax.jws.WebService;

@WebService
public interface StudentWebService {

    StudentXML saveStudent(StudentXML student);
    StudentXML getStudent(long id);
    void deleteStudent(long id);
    StudentXML[] getAllStudent();

    StudentGroupXML saveStudentGroup(StudentGroupXML group);
    StudentGroupXML getStudentGroup(long id);
    StudentGroupXML getStudentGroupByName(String name);
    void deleteStudentGroup(long id);
    StudentGroupXML[] getAllStudentGroup();
}
