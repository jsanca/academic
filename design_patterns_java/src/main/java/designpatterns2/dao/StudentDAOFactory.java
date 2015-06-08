package designpatterns2.dao;


import designpatterns2.dao.impl.JDBCStudentDAOImpl;
import designpatterns2.dao.impl.JsonStudentDAOImpl;

/**
 * Factory for DAO
 *
 * http://www.allapplabs.com/java_design_patterns/creational_patterns.htm
 * @author jsanca
 */
public class StudentDAOFactory {

    private Integer defaultImplementation = JDBC_DAO;

    public static final Integer JSON_DAO = 1;
    public static final Integer JDBC_DAO = 2;

    public StudentDAO create () {

        return create(defaultImplementation);
    }

    public StudentDAO create (int type) {

        StudentDAO studentDAO = null;

        if (type == JDBC_DAO) {

            studentDAO =
                    new JDBCStudentDAOImpl();
        } else {

            studentDAO =
                    new JsonStudentDAOImpl();
        }

        return studentDAO;
    }
}

/***
 *
 *
 * LookAndF abstract = LookAndFactory.create (LookAndFactory.UBUNTU_ALGO);
 *
 *
 * TreeView treeview = abstract.createTreeView ();
 *
 *
 *
 */
