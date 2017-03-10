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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({StudentWebServiceImpl.class, ConverterForStudentXMLAndORM.class,
        StudentServiceImpl.class, StudentGroupServiceImpl.class})
public class StudentWebServiceImplTest {

    private StudentServiceImpl studentService;
    private StudentGroupServiceImpl studentGroupService;
    private StudentWebService studentWebService;
    private Student student;
    private StudentXML studentXML;
    private StudentGroup studentGroup;
    private StudentGroupXML studentGroupXML;

    @Before
    public void init() throws Exception {
        Logger logger = mock(Logger.class);
        PowerMockito.mockStatic(StudentWebServiceImpl.class);
        PowerMockito.mockStatic(StudentServiceImpl.class);
        studentService = mock(StudentServiceImpl.class);
        Mockito.when(StudentWebServiceImpl.getLogger()).thenReturn(logger);
        Mockito.when(StudentWebServiceImpl.getStudentService()).thenReturn(studentService);
        PowerMockito.mockStatic(StudentGroupServiceImpl.class);
        studentGroupService = mock(StudentGroupServiceImpl.class);
        Mockito.when(StudentWebServiceImpl.getStudentGroupService()).thenReturn(studentGroupService);
        studentXML = new StudentXML();
        student = new Student();
        studentGroup = new StudentGroup();
        studentGroupXML = new StudentGroupXML();
        PowerMockito.mockStatic(ConverterForStudentXMLAndORM.class);
        Mockito.when(ConverterForStudentXMLAndORM.convert(student)).thenReturn(studentXML);
        Mockito.when(ConverterForStudentXMLAndORM.convert(studentXML)).thenReturn(student);
        Mockito.when(ConverterForStudentXMLAndORM.convert(studentGroup)).thenReturn(studentGroupXML);
        Mockito.when(ConverterForStudentXMLAndORM.convert(studentGroupXML)).thenReturn(studentGroup);
        PowerMockito.whenNew(StudentServiceImpl.class).withNoArguments()
                .thenReturn(studentService);
        PowerMockito.whenNew(StudentGroupServiceImpl.class).withNoArguments()
                .thenReturn(studentGroupService);
        studentWebService = new StudentWebServiceImpl();
    }


    @Test
    public void saveStudent() throws Exception {
        studentWebService.saveStudent(studentXML);
        verify(studentService).save(student);
    }
    @Test
    public void getStudent() throws Exception {
        studentWebService.getStudent(1);
        verify(studentService).get(1);
    }

    @Test
    public void deleteStudent() throws Exception {
        studentWebService.deleteStudent(1);
        verify(studentService).delete(1);
    }

    @Test
    public void getAllStudent() throws Exception {
        studentWebService.getAllStudent();
        verify(studentService).getAll();
    }

    @Test
    public void saveStudentGroup() throws Exception {
        studentWebService.saveStudentGroup(studentGroupXML);
        verify(studentGroupService).save(studentGroup);
    }

    @Test
    public void getStudentGroup() throws Exception {
        studentWebService.getStudentGroup(1);
        verify(studentGroupService).get(1);
    }

    @Test
    public void getStudentGroupByName() throws Exception {
        studentWebService.getStudentGroupByName("421702");
        verify(studentGroupService).get("421702");
    }

    @Test
    public void deleteStudentGroup() throws Exception {
        studentWebService.deleteStudentGroup(1);
        verify(studentGroupService).delete(1);
    }

    @Test
    public void getAllStudentGroup() throws Exception {
        studentWebService.getAllStudentGroup();
        verify(studentGroupService).getAll();
    }

}