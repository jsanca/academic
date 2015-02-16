package cr.prodigious.dao.impl;

import cr.prodigious.bean.ManagerBean;
import cr.prodigious.dao.base.FileSystemJsonDAO;
import cr.prodigious.dao.ManagerDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Json File System Managers DAO implementation
 */
public class FileSystemJsonManagerDAOImpl extends FileSystemJsonDAO implements ManagerDAO {

    private String fileName = null;

    private static Class<? extends ArrayList> arrayListClass =
            new ArrayList<ManagerBean>().getClass();

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    /**
     * Updat the list of managers
     *
     * @param managerBeanList
     * @return boolean
     */
    @Override
    public boolean update(final List<ManagerBean> managerBeanList) {

        boolean success = false;

        ArrayList<ManagerBean> managerBeanArrayList = null;

        if (managerBeanList instanceof ArrayList) {

           managerBeanArrayList =
                   (ArrayList)managerBeanList;
        } else {

            managerBeanArrayList =
                    new ArrayList<>(managerBeanList);
        }

        success =
                this.insertOrUpdate(this.fileName, managerBeanArrayList);

        return success;
    }

    /**
     * Get the list of managers
     *
     * @return List
     */
    @Override
    public List<ManagerBean> get() {

        return this.get(this.fileName, arrayListClass);
    }
} // E:O:F:FileSystemJsonManagerDAOImpl.
