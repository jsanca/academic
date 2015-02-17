package cr.prodigious.helper;

import cr.prodigious.bean.UserSessionBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Helper for common session stuff
 * @author jsanca
 */
public class SessionHelper implements Serializable {

    private static final String USER_SESSION_KEY = "userSessionKey";

    /**
     * Create a new session, setting up the session token id and the user session is assigned to the session
     * @param request
     * @param userSessionBean
     */
    public void createSession (final HttpServletRequest request,
                               final UserSessionBean userSessionBean) {

        HttpSession httpSession = null;

        httpSession = // create a new session
                request.getSession(true);

        userSessionBean.setSessionToken
                (httpSession.getId());

        httpSession.setAttribute(USER_SESSION_KEY,
                userSessionBean);

        // todo: add some other security methods such as the client ip
        // to restrict the access to the session just for particular ip?
    } // createSession.

} // E:O:F:SessionHelper
