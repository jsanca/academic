package monentrevue.web.services.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Util to check authentication
 * Date: 9/5/14
 * Time: 4:38 PM
 * @author jsanca
 */
public class AuthenticationUtil {

    /**
     * Determinate if the user is currently authenticated based on an uuid
     * @param request   HttpServletRequest
     * @param uuid      String
     * @return  boolean
     */
    public static boolean isAuthenticated (final HttpServletRequest request,
                                    final String uuid) {

        HttpSession httpSession = null;

        httpSession = request.getSession();

        return null != httpSession.getAttribute(uuid);
    } // isAuthenticated


    /**
     * This one just remove the uudi and logic session from the Http Session
     * But the server session still alive
     * @param request
     * @param uuid
     */
    public static void softLogout (final HttpServletRequest request,
                                           final String uuid) {

        HttpSession httpSession = null;

        httpSession = request.getSession();

        if (null != httpSession.getAttribute(uuid)) {

            httpSession.removeAttribute(uuid);
        }
    } // softLogout

    /**
     * This one invalidate the current session, closing it and removing all the attribute inside
     * @param request
     */
    public static void hardLogout (final HttpServletRequest request) {

        HttpSession httpSession = null;

        httpSession = request.getSession();

        httpSession.invalidate();
    } // hardLogout

} // E:O:F:AuthenticationUtil.
