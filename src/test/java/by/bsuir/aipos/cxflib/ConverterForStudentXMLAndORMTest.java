package by.bsuir.aipos.cxflib;

import static org.mockito.Mockito.*;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;
import by.bsuir.aipos.service.StudentGroupService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(StudentWebServiceImpl.class)
public class ConverterForStudentXMLAndORMTest {

    private static Date date;
    private static String dateString;
    private static Student student;
    private static StudentXML studentXML;
    private static StudentGroup studentGroup;
    private static StudentGroupXML studentGroupXML;

    @BeforeClass
    public static void init() throws ParseException {
        dateString = "1996-12-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(dateString);
        studentGroup = new StudentGroup("421702");
        studentGroup.setId(1);
        student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                date,
                "Bobrujsk",
                studentGroup);
        student.setId(1);
        studentGroupXML = new StudentGroupXML(1,"421702");
        studentXML = new StudentXML(
                1,
                "Andrey",
                "Bobkov",
                "Valerievich",
                dateString,
                "Bobrujsk",
                studentGroupXML);
    }

    @Test
    public void convert() throws Exception {
        StudentGroupXML g = ConverterForStudentXMLAndORM.convert(studentGroup);
        assert (studentGroupXML.getName().equals(g.getName()));
    }

    @Test
    public void convert1() throws Exception {
        StudentGroup g = ConverterForStudentXMLAndORM.convert(studentGroupXML);
        assert (studentGroup.getId() == g.getId() && studentGroup.getName().equals(g.getName()));
    }

    @Test
    public void convert2() throws Exception {
        StudentXML s = ConverterForStudentXMLAndORM.convert(student);
        assert (studentXML.getDateOfBirth().equals(s.getDateOfBirth()));
    }

    @Test
    public void convert3() throws Exception {
        PowerMockito.mockStatic(StudentWebServiceImpl.class);
        StudentGroupService studentGroupService = mock(StudentGroupService.class);
        Mockito.when(StudentWebServiceImpl.getStudentGroupService()).thenReturn(studentGroupService);
        when(studentGroupService.get(anyString())).thenReturn(studentGroup);
        Student student = ConverterForStudentXMLAndORM.convert(studentXML);
        assert (studentXML.getLastName().equals(student.getLastName()));
    }

}