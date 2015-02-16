package cr.prodigious.dao.impl;

import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.dao.base.BaseDAO;

/**
 * Cases DAO
 * @author jsanca
 */
public class Db4oCasesDAOImpl extends BaseDAO<CasesBean> {

    @Override
    protected int getDepth() {
        return 5;
    }

    @Override
    protected Class<CasesBean> getEntityClass() {

        return CasesBean.class;
    } // getEntityClass.
} // E:O:F:CasesDAO.
