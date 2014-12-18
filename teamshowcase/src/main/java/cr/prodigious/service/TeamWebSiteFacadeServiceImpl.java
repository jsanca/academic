package cr.prodigious.service;

import cr.prodigious.bean.Configuration;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.dao.CasesDAO;
import cr.prodigious.dao.TeamDAO;
import cr.prodigious.dao.WorkDAO;

/**
 * Bizness team show service
 * @author jsanca
 */
// TODO:implement cache
// TODO:implement MBeanService to invalidate caches by hand in the JConsole
public class TeamWebSiteFacadeServiceImpl implements TeamWebSiteFacadeService {

    private WorkDAO workDAO = null;

    private CasesDAO casesDAO = null;

    private TeamDAO teamDAO = null;

    public void setWorkDAO(WorkDAO workDAO) {
        this.workDAO = workDAO;
    }

    public void setCasesDAO(CasesDAO casesDAO) {
        this.casesDAO = casesDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public WorkBean getWork() {

        return this.workDAO.selectById(Configuration.DEFAULT_ID);
    }

    @Override
    public void storeWork(final WorkBean workBean) {

        this.workDAO.insertOrUpdate(workBean);
    }

    @Override
    public CasesBean getCases() {

        return this.casesDAO.selectById(Configuration.DEFAULT_ID);
    }

    @Override
    public void storeCases(final CasesBean casesBean) {

        this.casesDAO.insertOrUpdate(casesBean);
    }

    @Override
    public TeamBean getTeam() {

        return this.teamDAO.selectById(Configuration.DEFAULT_ID);
    }

    @Override
    public TeamBean getTeam(final Long teamId) {

        return this.teamDAO.selectById(teamId);
    }

    @Override
    public void storeTeam(final TeamBean teamBean) {

        this.teamDAO.insertOrUpdate(teamBean);
    }

} // E:O:F:TeamShowCaseService.
