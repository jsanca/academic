package cr.prodigious.controller;

import cr.prodigious.bean.BooleanMessage;
import cr.prodigious.bean.CapabilityBean;
import cr.prodigious.bean.CapabilitySkillsBean;
import cr.prodigious.bean.CapabilityTeamMapping;
import cr.prodigious.bean.CreateUserResult;
import cr.prodigious.bean.LoginResult;
import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.RegionBean;
import cr.prodigious.bean.SkillBean;
import cr.prodigious.bean.SkillCategoryBean;
import cr.prodigious.bean.SkillSubCategoryBean;
import cr.prodigious.bean.UserBean;
import cr.prodigious.bean.UserSessionBean;
import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.helper.CasePopulatorHelper;
import cr.prodigious.helper.JsonHelper;
import cr.prodigious.helper.PersonPopulatorHelper;
import cr.prodigious.helper.SessionHelper;
import cr.prodigious.service.TeamWebSiteFacadeService;
import cr.prodigious.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User Controller, for Publish site user creation and login
 *
 * @author jsanca
 */
@RestController
@RequestMapping("/user")
public class UserController implements Serializable {

    @Autowired
    private SessionHelper sessionHelper = null;

    @Autowired
    private UserService userService = null;

    @Autowired
    private JsonHelper jsonHelper = null;

    // todo: eventually will have login by user name

    @RequestMapping(value = "/is-logged-in", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage isLoggedIn(
            @RequestParam(value = "email", required = true) final String email,
            final HttpServletRequest request) {

        final BooleanMessage booleanMessage =
                new BooleanMessage(false);

        final HttpSession httpSession =
                request.getSession(false);

        UserSessionBean userSessionBean = null;

        if (null != httpSession) {

            // todo: check the ip security?
            userSessionBean =
                    this.sessionHelper.getUserSessionBean(httpSession);

            if (null != userSessionBean) {

                booleanMessage.setValue
                        (userSessionBean.getUserBean().getEmail().equals(email));
            }
        }


        return booleanMessage;
    } // isLogged.

    @RequestMapping(value = "/login-by-email", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public LoginResult loginByEmail(
            @RequestParam(value = "email", required = true) final String email,
            @RequestParam(value = "password", required = true)   final String password,
            final HttpServletRequest request) {

        final LoginResult loginResult =
            new LoginResult();
        final BooleanMessage success =
            new BooleanMessage(false);
        final UserSessionBean userSessionBean =
            new UserSessionBean();

        UserBean userBean = null;

        try {

            userBean =
                    this.userService.loginByEmail(email, password);

            userSessionBean.setUserBean
                    (this.cloneUserBean(userBean));

            this.sessionHelper.createSession
                    (request, userSessionBean);

            success.setValue(true);
        } catch (Exception e) {

            success.setValue(false);
        }

        loginResult.setResult(success);

        if (success.isValue()) {

            loginResult.setUserSessionBean
                    (userSessionBean);
        }

        return loginResult;
    } // loginByEmail,

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public CreateUserResult createUser(
            @RequestParam(value = "email", required = true) final String email,
            @RequestParam(value = "password", required = true)   final String password,
            final HttpServletRequest request) {

        final CreateUserResult createUserResult =
                new CreateUserResult();
        final BooleanMessage success =
                new BooleanMessage(false);
        final UserSessionBean userSessionBean =
                new UserSessionBean();

        UserBean userBean = null;

        try {

            userBean =
                    this.userService.create(email, password);

            userSessionBean.setUserBean
                    (this.cloneUserBean(userBean));

            this.sessionHelper.createSession
                    (request, userSessionBean);

            success.setValue(true);
        } catch (Exception e) {

            success.setValue(false);
        }

        createUserResult.setResult(success);

        if (success.isValue()) {

            createUserResult.setUserSessionBean
                    (userSessionBean);
        }

        return createUserResult;
    } // createUser

    private UserBean cloneUserBean(final UserBean userBean) throws CloneNotSupportedException {

        final UserBean cloned =
                (UserBean) userBean.clone();

        cloned.setPassword(null); // for security reason
                                  // we clear the password

        return cloned;
    } // cloneUserBean

} // E:O:F:UserController.
