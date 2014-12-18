package cr.prodigious.service;

import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;

import java.io.Serializable;

/**
 * Facade service for the team web site
 * @author jsanca
 */
public interface TeamWebSiteFacadeService extends Serializable {

    WorkBean getWork();

    void storeWork (WorkBean workBean);

    CasesBean getCases();

    void storeCases (CasesBean casesBean);

    TeamBean getTeam();

    TeamBean getTeam(Long teamId);

    void storeTeam (TeamBean teamBean);

} // E:O:F:TeamWebSiteFacadeService.
