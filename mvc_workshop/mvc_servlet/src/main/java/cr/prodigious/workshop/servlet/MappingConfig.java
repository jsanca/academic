package cr.prodigious.workshop.servlet;

import cr.prodigious.workshop.servlet.controller.Controller;
import cr.prodigious.workshop.servlet.controller.PostconditionCommand;
import cr.prodigious.workshop.servlet.controller.PreconditionCommand;

import java.io.Serializable;

/**
 * Encapsulate a single config for a mapping.
 *
 * Date: 4/2/14
 * Time: 4:08 PM
 * @author jsanca
 */
public class MappingConfig implements Serializable {

    // the url mapping
    private String mapping = null;

    // the main class to handle the action
    private Class<? extends Controller> controllerClass = null;

    // in case you need preconditions
    private Class<? extends PreconditionCommand> preconditionCommandClass = null;

    // in case you need postconditions.
    private Class<? extends PostconditionCommand> postconditionCommandClass = null;

    public MappingConfig() {
    }

    public MappingConfig(String mapping, Class<Controller> controllerClass) {
        this.mapping = mapping;
        this.controllerClass = controllerClass;
    }

    public MappingConfig(final String mapping,
                         final Class<? extends Controller> controllerClass,
                         final Class<? extends PreconditionCommand> preconditionCommandClass,
                         final Class<? extends PostconditionCommand> postconditionCommandClass) {
        this.mapping = mapping;
        this.controllerClass = controllerClass;
        this.preconditionCommandClass = preconditionCommandClass;
        this.postconditionCommandClass = postconditionCommandClass;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public Class<? extends Controller> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<? extends Controller> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Class<? extends PreconditionCommand> getPreconditionCommandClass() {
        return preconditionCommandClass;
    }

    public void setPreconditionCommandClass(Class<? extends PreconditionCommand> preconditionCommandClass) {
        this.preconditionCommandClass = preconditionCommandClass;
    }

    public Class<? extends PostconditionCommand> getPostconditionCommandClass() {
        return postconditionCommandClass;
    }

    public void setPostconditionCommandClass(Class<? extends PostconditionCommand> postconditionCommandClass) {
        this.postconditionCommandClass = postconditionCommandClass;
    }
} // E:O:F:MappingConfig.
