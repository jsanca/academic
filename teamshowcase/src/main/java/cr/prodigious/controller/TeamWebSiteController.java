package cr.prodigious.controller;

import cr.prodigious.bean.BooleanMessage;
import cr.prodigious.bean.CapabilityTeamMapping;
import cr.prodigious.bean.Configuration;
import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.helper.CasePopulatorHelper;
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
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Team Web Site Controller
 *
 * @author jsanca
 */
@RestController
@RequestMapping("/team")
public class TeamWebSiteController implements Serializable {

    @Autowired
    private CapabilityTeamMapping capabilityTeamMapping = null;

    @Autowired
    private TeamWebSiteFacadeService teamWebSiteFacadeService = null;

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

    @RequestMapping(value = "/cases", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public CasesBean cases( ) {

        return this.teamWebSiteFacadeService.getCases();
    } // team

    @RequestMapping(value = "/work", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public WorkBean work( ) {

        return this.teamWebSiteFacadeService.getWork();
    } // team

    @RequestMapping(value = "/add-team", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage addTeam(@RequestParam(value = "name", required = true) final String name,
                         @RequestParam(value = "position", required = true)   final String position,
                         @RequestParam(value = "imageLocal", required = true) final String imageLocal,
                         @RequestParam(value = "email", required = true)      final String email,
                         @RequestParam(value = "regionproject", required = true)      final String regionproject,
                         final HttpServletRequest request) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        final Long teamId =
                  this.getTeamId(position);

        if (null != teamId) {

            final TeamBean teamBean =
                    this.teamWebSiteFacadeService.getTeam(teamId);

            final Person person =
                    PersonPopulatorHelper.populate(name, position,
                            imageLocal, email, regionproject, request);

            booleanMessage.setValue
                    (this.addNewPerson(teamBean, person, teamId));
        }

        return booleanMessage;
    } // addWork

    private Long getTeamId (final String position) {

        Long teamId         = null;
        String positionName = null;
        String [] array     = null;

        if (null != position) {

            array = position.split("/");
            if (array.length > 1) {

                positionName =
                        array[0];

                teamId =
                        this.capabilityTeamMapping.map(positionName.trim().toUpperCase());

            }
        }

        return teamId;
    } // teamId

    private boolean addNewPerson(TeamBean teamBean, final Person person,  final Long teamId) {

        boolean result = false;

        try {

            if (null == teamBean) {

                teamBean = new TeamBean();
                teamBean.setId(teamId);
            }

            if (null == teamBean.getPerson()) {

                teamBean.setPerson(new ArrayList<Person>());
            }

            teamBean.getPerson().add(person);

            this.teamWebSiteFacadeService.storeTeam(teamBean);

            result = true;
        } catch (Exception e) {

            result = false;
        }

        return result;
    } // addNewPerson.

    @RequestMapping(value = "/add-case", method = RequestMethod.POST, produces={"application/xml", "application/json"})
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
                (this.addNewCase(casesBean, aCase));

        return booleanMessage;
    } // addCase

    private boolean addNewCase(CasesBean casesBean, final Case aCase) {

        boolean result = false;

        try {

          /*  if (null == teamBean) {

                teamBean = new TeamBean();
                teamBean.setId(Configuration.DEFAULT_ID);
            }

            if (null == teamBean.getPerson()) {

                teamBean.setPerson(new ArrayList<Person>());
            }

            teamBean.getPerson().add(person);

            this.teamWebSiteFacadeService.storeTeam(teamBean);*/

            result = true;
        } catch (Exception e) {

            result = false;
        }

        return result;
    } // addNewPerson.


} // E:O:F:TeamWebSiteController.
