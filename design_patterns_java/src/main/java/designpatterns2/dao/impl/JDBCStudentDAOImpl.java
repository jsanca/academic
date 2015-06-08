package designpatterns2.dao.impl;

import designpatterns2.vo.dto.StudentDTO;
import designpatterns2.dao.StudentDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jdbc dao impl
 * @author jsanca
 */
public class JDBCStudentDAOImpl implements StudentDAO {

    private DataSource dataSource = null;

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    /**
     * Get a list with all the students
     *
     * @return List
     */
    @Override
    public List<StudentDTO> getAll() {

        Connection connection        = null;
        List<StudentDTO> studentDTOs = null;
        PreparedStatement statement  = null;
        ResultSet resultSet          = null;
        StudentDTO studentDTO        = null;

        try {

            connection = dataSource.getConnection();
            statement =
                    connection.prepareStatement("SELECT  NUMBER, NAME, BIRTH_DATE, START_DATE FROM STUDENT");

            resultSet = statement.executeQuery();

            studentDTOs =
                    new ArrayList<StudentDTO>();

            while (resultSet.next()) {

                studentDTO = new StudentDTO();
                studentDTO.setNumber(resultSet.getObject("NUMBER").toString());
                studentDTO.setName(resultSet.getObject("NAME").toString());
                studentDTO.setBirthDate(Long.parseLong(resultSet.getObject("BIRTH_DATE").toString()));
                studentDTO.setStartDate(Long.parseLong(resultSet.getObject("START_DATE").toString()));

                studentDTOs.add(studentDTO);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            if (null != resultSet) {

                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != statement) {

                try {

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {

                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentDTOs;
    }

    /**
     * Try to find a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     *
     * @param number String
     * @return StudentDTO
     */
    @Override
    public StudentDTO searchByNumber(String number) {

        Connection connection        = null;
        PreparedStatement statement  = null;
        ResultSet resultSet          = null;
        StudentDTO studentDTO        = null;

        try {

            connection = dataSource.getConnection();
            statement =
                    connection.prepareStatement("SELECT  NUMBER, NAME, BIRTH_DATE, START_DATE FROM STUDENT");

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                studentDTO = new StudentDTO();
                studentDTO.setNumber(resultSet.getObject("NUMBER").toString());
                studentDTO.setName(resultSet.getObject("NAME").toString());
                studentDTO.setBirthDate(Long.parseLong(resultSet.getObject("BIRTH_DATE").toString()));
                studentDTO.setStartDate(Long.parseLong(resultSet.getObject("START_DATE").toString()));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            if (null != resultSet) {

                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != statement) {

                try {

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {

                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentDTO;
    }

    /**
     * Try to find and remove a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     * If there is any issue in the process a CanNotRemoveException will be released.
     *
     * @param number String
     * @return boolean true if was successfully deleted
     */
    @Override
    public boolean remove(String number) {

        Connection connection        = null;
        PreparedStatement statement  = null;
        ResultSet resultSet          = null;
        boolean removed              = false;

        try {

            connection = dataSource.getConnection();
            statement =
                    connection.prepareStatement("DELETE FROM STUDENT  WHERE NUMBER = ?");

            statement.setObject(1, number);

            removed = statement.executeUpdate() > 0;
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            if (null != resultSet) {

                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != statement) {

                try {

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {

                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return removed;
    }

    /**
     * Try to find and update a Student by number, if can not be found, an
     * NotFoundStudentException will be throwed.
     * If there is any issue in the process a CanNotUpdateException will be released.
     *
     * @param studentDTO StudentDTO
     * @return boolean true if was successfully updated
     */
    @Override
    public boolean update(StudentDTO studentDTO) {

        Connection connection        = null;
        PreparedStatement statement  = null;
        ResultSet resultSet          = null;
        boolean updated              = false;
        int paramIndex = 0;

        try {

            connection = dataSource.getConnection();
            statement =
                    connection.prepareStatement("UPDATE STUDENT SET NAME = ?, BIRTH_DATE = ?, START_DATE = ?  WHERE NUMBER = ?");

            statement.setObject(paramIndex++, studentDTO.getName());
            statement.setObject(paramIndex++, studentDTO.getBirthDate());
            statement.setObject(paramIndex++, studentDTO.getStartDate());
            statement.setObject(paramIndex++, studentDTO.getNumber());

            updated = statement.executeUpdate() > 0;
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            if (null != resultSet) {

                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != statement) {

                try {

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {

                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return updated;
    }

    /**
     * Creates a new student
     *
     * @param studentDTO StudentDTO
     * @return boolean true if was successfully created
     */
    @Override
    public boolean create(StudentDTO studentDTO) {

        Connection connection        = null;
        PreparedStatement statement  = null;
        ResultSet resultSet          = null;
        boolean created              = false;
        int paramIndex = 0;

        try {

            connection = dataSource.getConnection();
            statement =
                    connection.prepareStatement("INSERT STUDENT INTO (NUMBER, NAME, BIRTH_DATE, START_DATE) VALUES(?,?,?,?");

            statement.setObject(paramIndex++, studentDTO.getNumber());
            statement.setObject(paramIndex++, studentDTO.getName());
            statement.setObject(paramIndex++, studentDTO.getBirthDate());
            statement.setObject(paramIndex++, studentDTO.getStartDate());


            created = statement.executeUpdate() > 0;
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            if (null != resultSet) {

                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != statement) {

                try {

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {

                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return created;
    }
} // E:O:F:JDBCStudentDAOImpl.
