package monentrevue.web.services;

import monentrevue.bean.User;
import monentrevue.service.UserService;
import monentrevue.web.services.bean.BooleanMessage;
import monentrevue.web.services.bean.StringMessage;
import monentrevue.web.services.bean.UUDIMessage;
import monentrevue.web.services.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.UUID;

/**
 * Provides the Authentication service.
 *
 * Date: 9/3/14
 * Time: 11:33 PM
 * @author jsanca
 */
//@WebService( serviceName = "Authentication" )
@RestController
@RequestMapping ("/auth")
public class AuthenticationWebService implements Serializable {

    @Autowired
    private UserService userService = null;

    public AuthenticationWebService() {

        super();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public StringMessage ping( )
    {
           return new StringMessage("hello word");
    }

    /**
     * Do the authentication based on the username and password
     * If the authentication is successfully returns the uuid, otherwise returns null
     * @param username String
     * @param password String
     * @return String UUID
     */
   // @WebMethod(operationName = "authenticate")
    @RequestMapping(value = "/entication", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UUDIMessage authenticate( @RequestParam(value = "u", required = true) final String username,
                                @RequestParam(value = "p", required = true) final String password,
                                final HttpServletRequest request,
                                final HttpServletResponse response)
    {

        // finally, store the username in the httpsession
        HttpSession session = null;
        String uuid = null;
        CustomSession customSession = null;

        final User user =
                this.userService.getUser(username);

        if (null != user) {

            if (user.getPassword().equals(password)) { // todo: change this in order to use a strategy

                // context.getMessageContext().put(javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY, true)
                session = request.getSession();

                uuid = UUID.randomUUID().toString();

                customSession = new CustomSession();
                customSession.add(CustomSession.USER_OBJECT,
                        user);

                session.setAttribute(uuid, customSession);
            }
        }

        return new UUDIMessage(uuid);
    } // authenticate.

    /**
     * Determine if the user still authenticated
     * @param uuid String
     * @return boolean
     */
   // @WebMethod(operationName = "stillAuthenticated")
    @RequestMapping(value = "/still", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage stillAuthenticated (@RequestParam(value = "uuid", required = true) final String uuid,
                                       final HttpServletRequest request,
                                       final HttpServletResponse response) {

        return new BooleanMessage(AuthenticationUtil.isAuthenticated(request, uuid));
    } // stillAuthenticated.


    @RequestMapping(value = "/cleanUp", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage cleanUpSession (@RequestParam(value = "uuid", required = true) final String uuid,
                                              final HttpServletRequest request,
                                              final HttpServletResponse response) {

        boolean result = false;

        try {

            AuthenticationUtil.softLogout(request, uuid);
            result = true;
        } catch (Exception e) {

            result = false;
        }

        return new BooleanMessage(result);
    } // stillAuthenticated.
} // E:O:F:AuthenticationWebService.
