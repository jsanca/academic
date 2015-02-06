package designpatterns.dao;

import designpatterns.vo.dto.StudentDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Defines a CRUD for a student, depending on the implementation it might be a data base, xml, json, no-sql, etc.
 *
 * http://www.corej2eepatterns.com/DataAccessObject.htm
 *
 * @author jsanca
 */
public interface StudentDAO extends Serializable {

    /**
     * Get a list with all the students
     * @return  List
     */
    public List<StudentDTO> getAll();

    /**
     * Try to find a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     *
     * @param number String
     * @return  StudentDTO
     */
    public StudentDTO searchByNumber(String number);

    /**
     * Try to find and remove a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     * If there is any issue in the process a CanNotRemoveException will be released.
     *
     * @param number String
     * @return boolean true if was successfully deleted
     */
    public boolean remove(String number);

    /**
     * Try to find and update a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     * If there is any issue in the process a CanNotUpdateException will be released.
     *
     * @param studentDTO StudentDTO
     * @return  boolean true if was successfully updated
     */
    public boolean update (StudentDTO studentDTO);

    /**
     * Creates a new student
     * @param studentDTO StudentDTO
     * @return boolean true if was successfully created
     */
    public boolean create (StudentDTO studentDTO);

} // E:O:F:StudentDAO.
