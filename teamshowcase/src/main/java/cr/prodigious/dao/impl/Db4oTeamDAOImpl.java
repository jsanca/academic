package cr.prodigious.dao.impl;

import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.dao.base.BaseDAO;

/**
 * Team DAO
 * @author jsanca
 */
public class Db4oTeamDAOImpl extends BaseDAO<TeamBean> {

    @Override
    protected int getDepth() {

        return 8;
    }

    @Override
    protected Class<TeamBean> getEntityClass() {

        return TeamBean.class;
    } // getEntityClass.
} // E:O:F:TeamDAO.
