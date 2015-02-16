package cr.prodigious.dao.impl;

import cr.prodigious.bean.RegionBean;
import cr.prodigious.dao.base.FileSystemJsonDAO;
import cr.prodigious.dao.RegionDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Json File System Region DAO implementation
 * @author jsanca
 */
public class FileSystemJsonRegionDAOImpl extends FileSystemJsonDAO implements RegionDAO {

    private String fileName = null;

    private static Class<? extends ArrayList> arrayListClass =
            new ArrayList<RegionBean>().getClass();

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }


    @Override
    public boolean update(final List<RegionBean> regionBeanList) {

        boolean success = false;

        ArrayList<RegionBean> managerBeanArrayList = null;

        if (regionBeanList instanceof ArrayList) {

           managerBeanArrayList =
                   (ArrayList)regionBeanList;
        } else {

            managerBeanArrayList =
                    new ArrayList<>(regionBeanList);
        }

        success =
                this.insertOrUpdate(this.fileName, managerBeanArrayList);

        return success;
    }


    @Override
    public List<RegionBean> get() {

        return this.get(this.fileName, arrayListClass);
    }
} // E:O:F:FileSystemJsonRegionDAOImpl.
