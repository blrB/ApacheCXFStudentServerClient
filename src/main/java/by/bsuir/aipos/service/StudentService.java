package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import javax.jws.WebService;
import java.util.List;

public interface StudentService{
    /**
     * Save student
     *
     * @param student student to save
     * @return saved student
     */
    Student save(Student student);

    /**
     * Get student by identifier
     *
     * @param id identifier of student
     * @return found student
     */
    Student get(long id);

    /**
     * Delete student by identifier
     *
     * @param id identifier of student to delete
     */
    void delete(long id);

    /**
     * Get all student
     *
     * @return list of students
     */
    List<Student> getAll();
}