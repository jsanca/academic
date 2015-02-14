package cr.prodigious.service;

import cr.prodigious.bean.CapabilityBean;
import cr.prodigious.bean.CapabilitySkillsBean;
import cr.prodigious.bean.Configuration;
import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.RegionBean;
import cr.prodigious.bean.SkillCategoryBean;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.dao.BackupDAOHelper;
import cr.prodigious.dao.CapabilityPositionsDAO;
import cr.prodigious.dao.CapabilitySkillDAO;
import cr.prodigious.dao.CasesDAO;
import cr.prodigious.dao.DataBase;
import cr.prodigious.dao.ManagerDAO;
import cr.prodigious.dao.RegionDAO;
import cr.prodigious.dao.SkillCategoryDAO;
import cr.prodigious.dao.TeamDAO;
import cr.prodigious.dao.WorkDAO;
import cr.prodigious.helper.JsonHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Bizness team show service facade
 * @author jsanca
 */
// TODO:implement cache
// TODO:implement MBeanService to invalidate caches by hand in the JConsole
public class TeamWebSiteFacadeServiceImpl implements TeamWebSiteFacadeService {

    private DataBase dataBase = null;

    private WorkDAO workDAO = null;

    private CasesDAO casesDAO = null;

    private TeamDAO teamDAO = null;

    private BackupDAOHelper xmlBackupDAOHelper = null;

    private ManagerDAO managerDAO = null;

    private RegionDAO regionDAO = null;

    private CapabilityPositionsDAO capabilityPositionsDAO;

    private SkillCategoryDAO skillCategoryDAO;

    private CapabilitySkillDAO capabilitySkillDAO;

    private boolean backModeOn = false;

    public void setCapabilitySkillDAO(CapabilitySkillDAO capabilitySkillDAO) {
        this.capabilitySkillDAO = capabilitySkillDAO;
    }

    public void setSkillCategoryDAO(SkillCategoryDAO skillCategoryDAO) {
        this.skillCategoryDAO = skillCategoryDAO;
    }

    public void setCapabilityPositionsDAO(CapabilityPositionsDAO capabilityPositionsDAO) {
        this.capabilityPositionsDAO = capabilityPositionsDAO;
    }

    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void setWorkDAO(WorkDAO workDAO) {
        this.workDAO = workDAO;
    }

    public void setCasesDAO(CasesDAO casesDAO) {
        this.casesDAO = casesDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public void setXmlBackupDAOHelper(BackupDAOHelper xmlBackupDAOHelper) {
        this.xmlBackupDAOHelper = xmlBackupDAOHelper;
    }

    public void setBackModeOn(boolean backModeOn) {
        this.backModeOn = backModeOn;
    }

    public boolean isBackModeOn() {
        return backModeOn;
    }

    @Override
    public WorkBean getWork() {

        return this.workDAO.selectById(Configuration.DEFAULT_ID);
    }

    @Override
    public void storeWork(final WorkBean workBean) {

        // TODO: this most be an aspect and a message
        if (this.isBackModeOn()) {

            this.xmlBackupDAOHelper.storeBackup(workBean);
        }

        this.workDAO.insertOrUpdate(workBean);
    }

    @Override
    public CasesBean getCases() {

        return this.casesDAO.selectById(Configuration.DEFAULT_ID);
    }

    @Override
    public void storeCases(final CasesBean casesBean) {

        // TODO: this most be an aspect and a message
        if (this.isBackModeOn()) {

            this.xmlBackupDAOHelper.storeBackup(casesBean);
        }

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
    public Person getPerson(final Long teamId, final String personEmail) {

        TeamBean teamBean = null;
        Person person = null;

        teamBean =
                this.getTeam(teamId);

        if ((null != teamBean)
                && (null != teamBean.getPerson())) {



            person =
                (Person)CollectionUtils.find
                        (teamBean.getPerson(), new Predicate() {
                    @Override
                    public boolean evaluate(Object object) {

                        return Person.class.cast(object)
                                .getEmail().equals(personEmail);
                    }
                });
        }

        return person;
    }

    @Override
    public void storeTeam(final TeamBean teamBean) {

        // TODO: this most be an aspect and a message
        if (this.isBackModeOn()) {

            this.xmlBackupDAOHelper.storeBackup(teamBean);
        }

        this.teamDAO.insertOrUpdate(teamBean);
    }

    @Override
    public boolean backMe() {

        return this.dataBase.backupMe();
    }

    @Override
    public boolean closeMe() {

        return this.dataBase.closeMe();
    }

    @Override
    public void storeManagers(final List<ManagerBean> managers) {

        this.managerDAO.update(managers);
    }

    @Override
    public List<ManagerBean> getManagers() {

        List<ManagerBean> managerBeans =
                this.managerDAO.get();

        if (null == managerBeans) {

            managerBeans =
                    new ArrayList<>();
        }

        return managerBeans;
    }

    @Override
    public void storeRegions(final List<RegionBean> regions) {

        this.regionDAO.update(regions);
    }

    @Override
    public List<RegionBean> getRegions() {

        List<RegionBean> regionBeans =
                this.regionDAO.get();

        if (null == regionBeans) {

            regionBeans =
                    new ArrayList<>();
        }

        return regionBeans;
    }

    @Override
    public void storeCapabilityPositions(final ArrayList<CapabilityBean> capabilities) {

        this.capabilityPositionsDAO.update(capabilities);
    }

    @Override
    public List<CapabilityBean> getCapabilityPositions() {

        List<CapabilityBean> capabilityBeans =
                this.capabilityPositionsDAO.get();

        if (null == capabilityBeans) {

            capabilityBeans =
                    new ArrayList<>();
        }

        return capabilityBeans;
    }

    @Override
    public void storeSkillCatalog(final ArrayList<SkillCategoryBean> capabilities) {

         this.skillCategoryDAO.update(capabilities);
    }

    @Override
    public List<SkillCategoryBean> getSkillCatalog() {

        List<SkillCategoryBean> skillCategoryBeans =
                this.skillCategoryDAO.get();

        if (null == skillCategoryBeans) {

            skillCategoryBeans =
                    new ArrayList<>();
        }

        return skillCategoryBeans;
    }

    @Override
    public List<CapabilitySkillsBean> getCapabilitySkills() {

        List<CapabilitySkillsBean> capabilitySkillsBeanList =
                this.capabilitySkillDAO.get();

        if (null == capabilitySkillsBeanList) {

            capabilitySkillsBeanList =
                    new ArrayList<>();
        }

        return capabilitySkillsBeanList;
    }

    @Override
    public void storeCapabilitySkills(ArrayList<CapabilitySkillsBean> capabilitySkillsBeans) {

        this.capabilitySkillDAO.update(capabilitySkillsBeans);
    }

    @Override
    public boolean removeTeam(final Long teamId) {

        final TeamBean teamBean =
                this.getTeam(teamId);

        return this.dataBase.delete(teamBean);
    }

    @Override
    public boolean restoreTeam(final Long teamId, final String fileName) {

        final TeamBean teamBean =
                this.xmlBackupDAOHelper.getTeamBeanFromBackupFile(fileName);

        this.storeTeam(teamBean);

        return false;
    }

    @Override
    public boolean removeCases() {

        final CasesBean casesBean =
                new CasesBean();

        casesBean.setId(Configuration.DEFAULT_ID);

        return this.dataBase.delete(casesBean);
    }

    @Override
    public boolean restoreCases(final String fileName) {

        CasesBean casesBean = null;
        boolean result = false;

        try {

            casesBean =
                    this.xmlBackupDAOHelper.getCasesBeanFromBackupFile(fileName);

            this.storeCases(casesBean);
            result = true;
        } catch (Exception e) {

            result = false;
        }

        return result;
    }

} // E:O:F:TeamShowCaseService.
