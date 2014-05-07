package cr.prodigious.workshop.servlet;

import cr.prodigious.workshop.servlet.controller.Controller;
import cr.prodigious.workshop.servlet.controller.PostconditionCommand;
import cr.prodigious.workshop.servlet.controller.PreconditionCommand;

import java.io.Serializable;

/**
 * Encapsulate the result for a mapping
 *
 * it contains the instance of the controller,
 * the pre and post conditions
 *
 * Date: 4/2/14
 * Time: 4:46 PM
 * @author jsanca
 */
public class MappingResult implements Serializable {

    private PreconditionCommand preconditionCommand = null;

    private Controller controller = null;

    private PostconditionCommand postconditionCommand = null;

    public PreconditionCommand getPreconditionCommand() {
        return preconditionCommand;
    }

    public void setPreconditionCommand(PreconditionCommand preconditionCommand) {
        this.preconditionCommand = preconditionCommand;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public PostconditionCommand getPostconditionCommand() {
        return postconditionCommand;
    }

    public void setPostconditionCommand(PostconditionCommand postconditionCommand) {
        this.postconditionCommand = postconditionCommand;
    }
} // E:O:F:MappingResult.
