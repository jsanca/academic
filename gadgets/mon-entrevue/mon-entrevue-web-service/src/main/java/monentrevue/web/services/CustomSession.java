package monentrevue.web.services;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Custom Session Wrapper
 * Date: 9/4/14
 * Time: 12:07 AM
 * @author jsanca
 */
public class CustomSession implements Serializable {

    public static final String USER_OBJECT = "userObject";

    public static final String USER_INTERVIEW_SERVICE = "UserSessionInterviewService";

    private HashMap<String, Object> sessionMap =
            new HashMap<String, Object>();

    public void add (final String name, final Object value) {

        this.sessionMap.put(name, value);
    }

    public Object get(final String name) {

        return this.sessionMap.get(name);
    }

    public void remove (final String name) {

        this.sessionMap.remove(name);
    }
} // E:O:F:CustomSession.
