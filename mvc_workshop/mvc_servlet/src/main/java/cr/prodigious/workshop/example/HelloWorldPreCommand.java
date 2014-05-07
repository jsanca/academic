package cr.prodigious.workshop.example;

import cr.prodigious.workshop.servlet.ChainResult;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;
import cr.prodigious.workshop.servlet.controller.PreconditionCommand;

/**
 * Hello World Pre conditions command ;)
 *
 * Date: 4/2/14
 * Time: 7:58 PM
 * @author jsanca
 */
public class HelloWorldPreCommand implements PreconditionCommand {


    /**
     * Execute the preconditions, it will return a ChainResult that determine the next step
     *
     * @param req  Request
     * @param resp Response
     * @return ChainResult
     */
    @Override
    public ChainResult execute(Request req, Response resp) {

        req.set("precondition", "This is a precondition");

        return new ChainResult().setType(ChainResult.ChainType.CONTINUE);
    }
} // E:O:F:HelloWorldController.
