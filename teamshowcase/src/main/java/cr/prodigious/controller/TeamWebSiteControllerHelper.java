package cr.prodigious.controller;

import cr.prodigious.bean.CapabilityTeamMapping;
import cr.prodigious.bean.Configuration;
import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.service.TeamWebSiteFacadeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Helper to perform some simple action for the controller
 *
 * @author jsanca
 */
@Service(value = "teamWebSiteControllerHelper")
public class TeamWebSiteControllerHelper implements Serializable {

    @Autowired
    private CapabilityTeamMapping capabilityTeamMapping = null;

    @Autowired
    private TeamWebSiteFacadeService teamWebSiteFacadeService = null;

    /**
     * Get the associated team id for the capability position.
     * @param position String capability name
     * @return Long
     */
    public Long getTeamId (final String position) {

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

    /**
     * Add a person to a particular team.
     * @param teamBean TeamBean
     * @param person   Person
     * @param teamId   Long
     * @return boolean
     */
    public boolean addNewPerson(TeamBean teamBean, final Person person,  final Long teamId) {

        boolean result = false;

        try {

            if (null == teamBean) {

                teamBean = new TeamBean();
                teamBean.setId(teamId);
            }

            if (null == teamBean.getPerson()) {

                teamBean.setPerson(new ArrayList<Person>());
            }

            // TODO: if the person already exists (based on the email)

            teamBean.getPerson().add(person);

            this.teamWebSiteFacadeService.storeTeam(teamBean);

            result = true;
        } catch (Exception e) {

            result = false;
        }

        return result;
    } // addNewPerson.

    //////////

    /**
     * Add a new case to the object
     * @param casesBean CasesBean
     * @param aCase Case
     * @return boolean
     */
    public boolean addNewCase(CasesBean casesBean, final Case aCase) {

        boolean result = false;

        try {

            if (null == casesBean) {

                casesBean = new CasesBean();
                casesBean.setId(Configuration.DEFAULT_ID);
            }

            if (null == casesBean.getCases()) {

                casesBean.setCases(new ArrayList<Case>());
            }

            casesBean.getCases().add(aCase);

            this.teamWebSiteFacadeService.storeCases(casesBean);

            result = true;
        } catch (Exception e) {

            result = false;
        }

        return result;
    } // addNewCase.


} // E:O:F:TeamWebSiteControllerHelper.
