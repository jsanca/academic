package cr.prodigious.controller;

import cr.prodigious.bean.BooleanMessage;
import cr.prodigious.bean.CapabilityBean;
import cr.prodigious.bean.CapabilityTeamMapping;
import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.PositionBean;
import cr.prodigious.bean.RegionBean;
import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.helper.CasePopulatorHelper;
import cr.prodigious.helper.JsonHelper;
import cr.prodigious.helper.PersonPopulatorHelper;
import cr.prodigious.service.TeamWebSiteFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Team Web Site Controller
 *
 * @author jsanca
 */
@RestController
@RequestMapping("/team")
public class TeamWebSiteController implements Serializable {

    private static final Class<? extends ArrayList> ARRAY_LIST_MANAGER_CLASS
            = new ArrayList<ManagerBean>().getClass();

    @Autowired
    TeamWebSiteControllerHelper teamWebSiteControllerHelper = null;

    @Autowired
    private CapabilityTeamMapping capabilityTeamMapping = null;

    @Autowired
    private TeamWebSiteFacadeService teamWebSiteFacadeService = null;

    @Autowired
    private JsonHelper jsonHelper = null;

    /*@RequestMapping(value = "/init-managers", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerBean> initManagers() {

        ArrayList<ManagerBean> managers =
                new ArrayList<>();

        ManagerBean managerBean =
                new ManagerBean();

        managerBean.setName("Carolina Chavarria");
        managerBean.setOrder(1);
        managers.add(managerBean);

        managerBean =
                new ManagerBean();
        managerBean.setName("Eduardo Morales");
        managerBean.setOrder(2);
        managers.add(managerBean);

        managerBean =
                new ManagerBean();
        managerBean.setOrder(3);
        managerBean.setName("Guido Bolaños");
        managers.add(managerBean);

        this.teamWebSiteFacadeService.storeManagers(managers);

        return this.teamWebSiteFacadeService.getManagers();
    } // allTeam    */

    @RequestMapping(value = "/capabilities/positions", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage editCapabilitiesPositions(HttpServletRequest request) {

        final BooleanMessage success =
                new BooleanMessage(false);

        InputStream inputStream = null;

        ArrayList<RegionBean> regions = null;

        try {

            inputStream =
                    request.getInputStream();

            regions =
                    this.jsonHelper.read
                            (inputStream, ARRAY_LIST_MANAGER_CLASS);

            if (null != regions) {

                this.teamWebSiteFacadeService.
                        storeRegions(regions);

                success.setValue(true);
            }
        } catch (IOException e) {

            success.setValue(false);
        }

        return success;
    } // editRegions

    @RequestMapping(value = "/capabilities/positions", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<CapabilityBean> capabilitiesPositions() {

        ArrayList<CapabilityBean> capabilities =
                new ArrayList<>();
        ArrayList<PositionBean> positionBeansBE =
                new ArrayList<>();


        CapabilityBean capabilityBean =
                new CapabilityBean();

        capabilityBean.setOrder(1);
        capabilityBean.setName("BE");

        capabilityBean.setPositions(positionBeansBE);

        PositionBean positionBean =
                new PositionBean();

        positionBean.setOrder(1);
        positionBean.setName("Software Engineer");

        positionBeansBE.add(positionBean);

        positionBean =
                new PositionBean();

        positionBean.setOrder(2);
        positionBean.setName("Senior Software Engineer");

        positionBeansBE.add(positionBean);

        capabilities.add(capabilityBean);

        return capabilities;
    } // capabilitiesPositions

    @RequestMapping(value = "/capabilities", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Set<String> capabilities() {

        return this.capabilityTeamMapping.getAllCapabilityNames();
    } // capabilities

    @RequestMapping(value = "/regions", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage editRegions(HttpServletRequest request) {

        final BooleanMessage success =
                new BooleanMessage(false);

        InputStream inputStream = null;

        ArrayList<RegionBean> regions = null;

        try {

            inputStream =
                    request.getInputStream();

            regions =
                    this.jsonHelper.read
                            (inputStream, ARRAY_LIST_MANAGER_CLASS);

            if (null != regions) {

                this.teamWebSiteFacadeService.
                        storeRegions(regions);

                success.setValue(true);
            }
        } catch (IOException e) {

            success.setValue(false);
        }

        return success;
    } // editRegions

    @RequestMapping(value = "/regions", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<RegionBean> regions() {

        return this.teamWebSiteFacadeService.getRegions();
    } // regions

    @RequestMapping(value = "/managers", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage editManagers(HttpServletRequest request) {

        final BooleanMessage success =
                new BooleanMessage(false);

        InputStream inputStream = null;

        ArrayList<ManagerBean> managers = null;

        try {

            inputStream =
                    request.getInputStream();

            managers =
                this.jsonHelper.read
                        (inputStream, ARRAY_LIST_MANAGER_CLASS);

            if (null != managers) {

                this.teamWebSiteFacadeService.
                        storeManagers(managers);

                success.setValue(true);
            }
        } catch (IOException e) {

            success.setValue(false);
        }

        return success;
    } // editManagers

    @RequestMapping(value = "/managers", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerBean> managers() {

        return this.teamWebSiteFacadeService.getManagers();
    } // managers

    //////////////

    @RequestMapping(value = "/all-team", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<TeamBean> allTeam() {

        final ArrayList<TeamBean> teamBeanList =
                 new ArrayList<>();

        final Collection<Long> idList =
                this.capabilityTeamMapping.getAllIds();

        TeamBean teamBean = null;

        if (null != idList) {

            for (Long id : idList) {

                teamBean =
                        this.teamWebSiteFacadeService
                                .getTeam(id);

                teamBeanList.add
                        (teamBean);
            }
        }

        return teamBeanList;
    } // allTeam

    @RequestMapping(value = "/person/capability/{capability}/email/{email}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Person person(@PathVariable("capability")  String capability, @PathVariable("email") String personEmail ) {

        Long teamId = null;
        TeamBean teamBean = null;
        Person person = null;

        if (null != capability) {

            teamId =
                    this.capabilityTeamMapping.map(capability.trim().toUpperCase());
        }

        if(null != teamId) {

            person =
                this.teamWebSiteFacadeService.getPerson
                        (teamId, personEmail);
        }

        return person;
    } // person


    @RequestMapping(value = "/team/{capability}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public TeamBean team(@PathVariable String capability ) {

        Long teamId = null;

        if (null != capability) {

            teamId =
                    this.capabilityTeamMapping.map(capability.trim().toUpperCase());
        }

        return (null != teamId)?
                this.teamWebSiteFacadeService.getTeam(teamId):null;
    } // team

    @RequestMapping(value = "/delete-team/{capability}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage deleteTeam(@PathVariable String capability ) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        Long teamId = null;

        if (null != capability) {

            teamId =
                    this.capabilityTeamMapping.map(capability.trim().toUpperCase());

            booleanMessage.setValue(this.teamWebSiteFacadeService.removeTeam(teamId));
        }

        return booleanMessage;
    } // deleteTeam.


    @RequestMapping(value = "/restore-team/{capability}/{filename}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage restoreTeam(@PathVariable String capability, @PathVariable String filename) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        Long teamId = null;

        if (null != capability) {

            teamId =
                    this.capabilityTeamMapping.map(capability.trim().toUpperCase());

            booleanMessage.setValue(this.teamWebSiteFacadeService.restoreTeam(teamId, filename));
        }

        return booleanMessage;
    } // restoreTeam.


    @RequestMapping(value = "/add-team", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage addTeam(@RequestParam(value = "name", required = true) final String name,
                                  @RequestParam(value = "position", required = true)   final String position,
                                  @RequestParam(value = "imageLocal", required = true) final String imageLocal,
                                  @RequestParam(value = "email", required = true)      final String email,
                                  @RequestParam(value = "regionProject", required = true)      final String regionProject,
                                  @RequestParam(value = "employeeNumber", required = true)     final String employeeNumber,
                                  @RequestParam(value = "manager", required = true)    final String manager,
                                  final HttpServletRequest request) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        final Long teamId =
                this.teamWebSiteControllerHelper.getTeamId(position);

        if (null != teamId) {

            final TeamBean teamBean =
                    this.teamWebSiteFacadeService.getTeam(teamId);

            final Person person =
                    PersonPopulatorHelper.populate(name, position,
                            imageLocal, email, regionProject,
                            employeeNumber, manager, request);

            booleanMessage.setValue
                    (this.teamWebSiteControllerHelper.addNewPerson(teamBean, person, teamId));
        }

        return booleanMessage;
    } // addWork



    ////////////////////

    @RequestMapping(value = "/cases", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public CasesBean cases( ) {

        return this.teamWebSiteFacadeService.getCases();
    } // team

    @RequestMapping(value = "/restore-cases/{filename}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage restoreCases(@PathVariable String filename) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        booleanMessage.setValue(this.teamWebSiteFacadeService.restoreCases(filename));

        return booleanMessage;
    } // deleteTeam.

    @RequestMapping(value = "/delete-cases", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage deleteCases( ) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        booleanMessage.setValue(this.teamWebSiteFacadeService.removeCases());

        return booleanMessage;
    } // deleteTeam.

    @RequestMapping(value = "/add-cases", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage addCase(@RequestParam(value = "brand", required = true)          final String brand,
                                  @RequestParam(value = "description", required = true)    final String description,
                                  @RequestParam(value = "image", required = true)          final String image,
                                  @RequestParam(value = "inputText", required = true)      final String inputText,
                                  @RequestParam(value = "excecutionTime", required = true) final String excecutionTime,
                                  final HttpServletRequest request) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        final CasesBean casesBean =
                this.teamWebSiteFacadeService.getCases();

        final Case aCase =
                CasePopulatorHelper.populate(brand, description, image, inputText, excecutionTime, request);

        booleanMessage.setValue
                (this.teamWebSiteControllerHelper.addNewCase(casesBean, aCase));

        return booleanMessage;
    } // addCase

    //////////

    @RequestMapping(value = "/work", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public WorkBean work( ) {

        return this.teamWebSiteFacadeService.getWork();
    } // team


    //////////

    @RequestMapping(value = "/backme", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage backupMe() {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        booleanMessage.setValue(this.teamWebSiteFacadeService.backMe());

        return booleanMessage;
    } // backupMe.

    @RequestMapping(value = "/closeme", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage closeMe() {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        booleanMessage.setValue(this.teamWebSiteFacadeService.closeMe());

        return booleanMessage;
    } // closeMe.

} // E:O:F:TeamWebSiteController.
