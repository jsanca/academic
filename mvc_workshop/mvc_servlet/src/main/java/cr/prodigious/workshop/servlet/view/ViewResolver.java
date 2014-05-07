package cr.prodigious.workshop.servlet.view;

import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;

import java.io.Serializable;

/**
 * Results the View
 *
 * Date: 4/2/14
 * Time: 4:58 PM
 * @author jsanca
 */
public interface ViewResolver extends Serializable {

    /**
     * Do the resolver.
     * @param viewResult ViewResult
     * @param request  Request
     * @param response Response
     */
    public abstract void doResolver (ViewResult viewResult, Request request, Response response);
} // E:O:F:ViewResolver.
