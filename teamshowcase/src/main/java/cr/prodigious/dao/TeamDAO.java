package cr.prodigious.dao;

import cr.prodigious.bean.team.TeamBean;

/**
 * Team DAO
 * @author jsanca
 */
public class TeamDAO extends BaseDAO<TeamBean> {

    @Override
    protected int getDepth() {

        return 8;
    }

    @Override
    protected Class<TeamBean> getEntityClass() {

        return TeamBean.class;
    } // getEntityClass.
} // E:O:F:TeamDAO.
