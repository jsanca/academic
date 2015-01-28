package cr.prodigious.dao;

import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;

/**
 * Work DAO
 * @author jsanca
 */
public class WorkDAO extends BaseDAO<WorkBean> {

    @Override
    protected int getDepth() {

       return 5;
    }

    @Override
    protected Class<WorkBean> getEntityClass() {

        return WorkBean.class;
    } // getEntityClass.
} // E:O:F:WorkDAO.
