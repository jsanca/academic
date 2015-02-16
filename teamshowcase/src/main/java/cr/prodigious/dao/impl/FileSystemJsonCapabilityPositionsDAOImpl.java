package cr.prodigious.dao.impl;

import cr.prodigious.bean.CapabilityBean;
import cr.prodigious.dao.CapabilityPositionsDAO;
import cr.prodigious.dao.base.FileSystemJsonDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Json File System Capability positions DAO implementation
 * @author jsanca
 */
public class FileSystemJsonCapabilityPositionsDAOImpl extends FileSystemJsonDAO implements CapabilityPositionsDAO {

    private String fileName = null;

    private static Class<? extends ArrayList> arrayListClass =
            new ArrayList<CapabilityBean>().getClass();

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }


    @Override
    public boolean update(final List<CapabilityBean> capabilityPositionsBeanList) {

        boolean success = false;

        ArrayList<CapabilityBean> capabilityPositionsBeanArrayList = null;

        if (capabilityPositionsBeanList instanceof ArrayList) {

           capabilityPositionsBeanArrayList =
                   (ArrayList) capabilityPositionsBeanList;
        } else {

            capabilityPositionsBeanArrayList =
                    new ArrayList<>(capabilityPositionsBeanList);
        }

        success =
                this.insertOrUpdate(this.fileName, capabilityPositionsBeanArrayList);

        return success;
    }


    @Override
    public List<CapabilityBean> get() {

        return this.get(this.fileName, arrayListClass);
    }
} // E:O:F:FileSystemJsonCapabilityPositionsDAOImpl.
