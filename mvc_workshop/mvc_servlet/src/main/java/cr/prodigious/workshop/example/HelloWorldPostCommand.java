package cr.prodigious.workshop.example;

import cr.prodigious.workshop.servlet.ChainResult;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;
import cr.prodigious.workshop.servlet.controller.PostconditionCommand;

/**
 * Hello World post conditions command ;)
 *
 * Date: 4/2/14
 * Time: 7:58 PM
 * @author jsanca
 */
public class HelloWorldPostCommand implements PostconditionCommand {


    /**
     * Execute the post-conditions, it will return a ChainResult that determine the next step
     *
     * @param req  Request
     * @param resp Response
     * @return ChainResult
     */
    @Override
    public ChainResult execute(Request req, Response resp) {

        req.set("postcondition", "This is a postcondition");

        return new ChainResult().setType(ChainResult.ChainType.CONTINUE);
    }
} // E:O:F:HelloWorldController.
