package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.cxfserver.StudentServer;
import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterForStudentXMLAndORM {
    /**
     * Converts student to XML representation
     *
     * @param student student to convert
     * @return XML representation of student
     */
    public static StudentXML convert(Student student) {
        StudentXML studentXML = new StudentXML();
        if(student.getId() != 0) {
            studentXML.setId(student.getId());
        }
        studentXML.setFirstName(student.getFirstName());
        studentXML.setLastName(student.getLastName());
        if(student.getMiddleName() != null) {
            studentXML.setMiddleName(student.getMiddleName());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = format.format(student.getDateOfBirth());
        studentXML.setDateOfBirth(birthDate);
        studentXML.setHomeAddress(student.getHomeAddress());
        StudentGroupXML studentGroupXML = new StudentGroupXML();
        studentGroupXML.setId(student.getStudentGroup().getId());
        studentGroupXML.setName(student.getStudentGroup().getName());
        studentXML.setStudentGroupXML(studentGroupXML);
        return studentXML;
    }

    /**
     * Converts student group to XML
     *
     * @param studentGroup student group to convert
     * @return XML representation of student group
     */
    public static StudentGroupXML convert(StudentGroup studentGroup){
        StudentGroupXML studentGroupXML = new StudentGroupXML();
        if(studentGroup.getId() != 0) {
            studentGroupXML.setId(studentGroup.getId());
        }
        studentGroupXML.setName(studentGroup.getName());
        return studentGroupXML;
    }

    /**
     * Coverts XML representation of student to student
     * @param studentXML XML representation of student
     * @return converted student
     */
    public static Student convert(StudentXML studentXML) {
        Student student = new Student();
        if(studentXML.getId() != 0) {
            student.setId(studentXML.getId());
        }
        student.setFirstName(studentXML.getFirstName());
        student.setLastName(studentXML.getLastName());
        if(studentXML.getMiddleName() != null) {
            student.setMiddleName(studentXML.getMiddleName());
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = format.parse(studentXML.getDateOfBirth());
            student.setDateOfBirth(birthDate);
        } catch (ParseException e) {
            StudentServer.getLogger().error("ParseException");
            StudentServer.getLogger().trace(e);
        }
        student.setHomeAddress(studentXML.getHomeAddress());
        StudentGroup studentGroup = StudentWebServiceImpl
                .getStudentGroupService()
                .get(studentXML.getStudentGroupXML().getName());
        student.setStudentGroup(studentGroup);
        return student;
    }

    /**
     * Convert XML representation of student group to student group
     * @param studentGroupXML XML representation of student group
     * @return converted student group
     */
    public static StudentGroup convert(StudentGroupXML studentGroupXML){
        StudentGroup studentGroup = new StudentGroup();
        if(studentGroupXML.getId() != 0) {
            studentGroup.setId(studentGroupXML.getId());
        }
        studentGroup.setName(studentGroupXML.getName());
        return studentGroup;
    }

}
