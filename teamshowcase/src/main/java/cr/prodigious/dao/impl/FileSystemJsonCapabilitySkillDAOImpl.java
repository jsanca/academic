package cr.prodigious.dao.impl;

import cr.prodigious.bean.CapabilitySkillsBean;
import cr.prodigious.bean.SkillCategoryBean;
import cr.prodigious.dao.CapabilitySkillDAO;
import cr.prodigious.dao.FileSystemJsonDAO;
import cr.prodigious.dao.SkillCategoryDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Json File System Capability Skill Category DAO implementation
 * @author jsanca
 */
public class FileSystemJsonCapabilitySkillDAOImpl extends FileSystemJsonDAO implements CapabilitySkillDAO {

    private String fileName = null;

    private static Class<? extends ArrayList> arrayListClass =
            new ArrayList<CapabilitySkillsBean>().getClass();

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    @Override
    public boolean update(List<CapabilitySkillsBean> capabilitySkillsBeans) {

        boolean success = false;

        ArrayList<CapabilitySkillsBean> skillCategoryBeanList = null;

        if (skillCategoryBeanList instanceof ArrayList) {

            skillCategoryBeanList =
                    (ArrayList) capabilitySkillsBeans;
        } else {

            skillCategoryBeanList =
                    new ArrayList<>(capabilitySkillsBeans);
        }

        success =
                this.insertOrUpdate(this.fileName, skillCategoryBeanList);

        return success;
    }

    @Override
    public List<CapabilitySkillsBean> get() {

        return this.get(this.fileName, arrayListClass);
    }
} // E:O:F:FileSystemJsonCapabilitySkillDAOImpl.
