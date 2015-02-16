package cr.prodigious.dao.impl;

import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.dao.base.BaseDAO;

/**
 * Work DAO
 * @author jsanca
 */
public class Db4oWorkDAOImpl extends BaseDAO<WorkBean> {

    @Override
    protected int getDepth() {

       return 5;
    }

    @Override
    protected Class<WorkBean> getEntityClass() {

        return WorkBean.class;
    } // getEntityClass.
} // E:O:F:WorkDAO.
