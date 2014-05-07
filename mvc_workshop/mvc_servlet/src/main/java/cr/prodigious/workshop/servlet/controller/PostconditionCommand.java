package cr.prodigious.workshop.servlet.controller;

import cr.prodigious.workshop.servlet.ChainResult;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;

import java.io.Serializable;

/**
 * Defines a single Command contract to handle the post-conditions
 *
 * Date: 4/2/14
 * Time: 4:17 PM
 * @author jsanca
 */
public interface PostconditionCommand extends Serializable {

    /**
     * Execute the post-conditions, it will return a ChainResult that determine the next step
     * @param req Request
     * @param resp Response
     * @return ChainResult
     */
    public abstract ChainResult execute(Request req, Response resp);
} // E:O:F:PreconditionCommand.
