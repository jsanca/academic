package cr.prodigious.dao.impl;

import cr.prodigious.bean.SkillCategoryBean;
import cr.prodigious.dao.base.FileSystemJsonDAO;
import cr.prodigious.dao.SkillCategoryDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Json File System Skill Category DAO implementation
 * @author jsanca
 */
public class FileSystemJsonSkillCategoryDAOImpl extends FileSystemJsonDAO implements SkillCategoryDAO {

    private String fileName = null;

    private static Class<? extends ArrayList> arrayListClass =
            new ArrayList<SkillCategoryBean>().getClass();

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    /**
     * Update the list of skills
     *
     * @param skillCategoryBeans
     * @return boolean
     */
    @Override
    public boolean update(List<SkillCategoryBean> skillCategoryBeans) {

        boolean success = false;

        ArrayList<SkillCategoryBean> skillCategoryBeanList = null;

        if (skillCategoryBeans instanceof ArrayList) {

            skillCategoryBeanList =
                    (ArrayList) skillCategoryBeans;
        } else {

            skillCategoryBeanList =
                    new ArrayList<>(skillCategoryBeans);
        }

        success =
                this.insertOrUpdate(this.fileName, skillCategoryBeanList);

        return success;
    }

    @Override
    public List<SkillCategoryBean> get() {

        return this.get(this.fileName, arrayListClass);
    }
} // E:O:F:FileSystemJsonSkillCategoryDAOImpl.
