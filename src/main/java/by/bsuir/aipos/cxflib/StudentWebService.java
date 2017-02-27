package by.bsuir.aipos.cxflib;

import by.bsuir.aipos.model.StudentGroupXML;
import by.bsuir.aipos.model.StudentXML;

import javax.jws.WebService;

@WebService
public interface StudentWebService {
    /**
     * Saves student
     *
     * @param student student to save
     * @return saved student
     */
    StudentXML saveStudent(StudentXML student);

    /**
     * Searches for student with given identifier
     * @param id identifier of student
     * @return detected student
     */
    StudentXML getStudent(long id);

    /**
     * Deletes student with given identifier
     * @param id identifier of student to delete
     */
    void deleteStudent(long id);

    /**
     * Returns all existing students
     * @return all existing students
     */
    StudentXML[] getAllStudent();

    /**
     * Saves student group
     * @param group student group to save
     * @return saved student group
     */
    StudentGroupXML saveStudentGroup(StudentGroupXML group);

    /**
     * Returns student group with given identifier
     * @param id identifier of student group
     * @return detected student group
     */
    StudentGroupXML getStudentGroup(long id);

    /**
     * Returns student group with given name
     * @param name name of student group
     * @return detected student group
     */
    StudentGroupXML getStudentGroupByName(String name);

    /**
     * Deletes student group with given identifier
     * @param id identifier of student group to delete
     */
    void deleteStudentGroup(long id);

    /**
     * Returns all existing student groups
     *
     * @return all existing student groups
     */
    StudentGroupXML[] getAllStudentGroup();
}
