package cr.prodigious.service;

import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.RegionBean;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;

import java.io.Serializable;
import java.util.List;

/**
 * Facade service for the team web site
 * @author jsanca
 */
public interface TeamWebSiteFacadeService extends Serializable {

    WorkBean getWork();

    void storeWork (WorkBean workBean);

    CasesBean getCases();

    void storeCases (CasesBean casesBean);

    boolean removeCases ();

    boolean restoreCases (String fileName);

    TeamBean getTeam();

    TeamBean getTeam(Long teamId);

    Person getPerson(Long teamId, String personEmail);

    void storeTeam (TeamBean teamBean);

    boolean removeTeam (Long teamId);

    boolean restoreTeam (final Long teamId, String fileName);

    boolean backMe ();

    boolean closeMe();

    void storeManagers(List<ManagerBean> managers);

    List<ManagerBean> getManagers ();

    void storeRegions(List<RegionBean> regions);

    List<RegionBean> getRegions ();

} // E:O:F:TeamWebSiteFacadeService.
