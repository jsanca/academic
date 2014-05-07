package cr.prodigious.workshop.example;

import cr.prodigious.CapabilityService;
import cr.prodigious.impl.CapabilityServiceImpl;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;
import cr.prodigious.workshop.servlet.controller.Controller;
import cr.prodigious.workshop.servlet.view.ViewResult;

/**
 * Hello World Controller ;)
 *
 * Date: 4/2/14
 * Time: 7:58 PM
 * @author jsanca
 */
public class HelloWorldController implements Controller {

    /**
     * Method to be implemented in order to handle an specific action for a particular Controller.
     *
     * @param req  Request
     * @param resp Response
     * @return ViewResult
     */
    @Override
    public ViewResult execute(Request req, Response resp) {

        req.set("message", "Hello World!");

        final CapabilityService capabilityService =
                (CapabilityService)
                        req.getContext().get("capabilityService",
                                CapabilityServiceImpl.class);

        if (null != capabilityService) {

            req.set("capabilityList", capabilityService.getCapabilityList());
        }

        return ViewResult.createForward("helloworld.jsp");
    }
} // E:O:F:HelloWorldController.
