package cr.prodigious.dao;

import cr.prodigious.bean.cases.CasesBean;

/**
 * Cases DAO
 * @author jsanca
 */
public class CasesDAO extends BaseDAO<CasesBean> {

    @Override
    protected int getDepth() {
        return 5;
    }

    @Override
    protected Class<CasesBean> getEntityClass() {

        return CasesBean.class;
    } // getEntityClass.
} // E:O:F:CasesDAO.
