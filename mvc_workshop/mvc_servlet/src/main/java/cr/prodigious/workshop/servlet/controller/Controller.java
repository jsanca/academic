package cr.prodigious.workshop.servlet.controller;

import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;
import cr.prodigious.workshop.servlet.view.ViewResult;

import java.io.Serializable;

/**
 * Defines a single Controller contract
 *
 * Date: 4/2/14
 * Time: 4:13 PM
 * @author jsanca
 */
public interface Controller extends Serializable {

    /**
     * Method to be implemented in order to handle an specific action for a particular Controller.
     * @param req Request
     * @param resp Response
     * @return ViewResult
     */
    public abstract ViewResult execute (Request req, Response resp);
} // E:O:F:Controller.
