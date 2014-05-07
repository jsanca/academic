package cr.prodigious.workshop.servlet;

import java.io.Serializable;

/**
 * Custom Wrapper for the response
 *
 * Date: 4/2/14
 * Time: 4:30 PM
 * @author jsanca
 */
public interface Response extends Serializable {


    /**
     * Show the error page associated or the error page if there is not any associated.
     * @param errorCode Integer
     */
    void error(Integer errorCode);

    /**
     * Do a forward based on the path
     * @param path String
     */
    void forward(String path);

    /**
     * Do a redirect
     * @param path String
     */
    void redirect(String path);
} // Response.
