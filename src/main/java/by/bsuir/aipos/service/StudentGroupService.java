package by.bsuir.aipos.service;

import by.bsuir.aipos.model.StudentGroup;

import javax.jws.WebService;
import java.util.List;

public interface StudentGroupService {
    /**
     * Saves student group
     *
     * @param studentGroup student group to save
     * @return saved student group
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Returns student group by group identifier
     *
     * @return student group
     */
    StudentGroup get(long id);

    /**
     * Returns student group by name
     *
     * @param name name of group
     * @return found group
     */
    StudentGroup get(String name);

    /**
     * Deletes student group with given identifier
     *
     * @param id identifier of group to delete
     */
    void delete(long id);

    /**
     * Returns list of all students groups
     *
     * @return list of all student groups
     */
    List<StudentGroup> getAll();
}
