package controllers;

import cr.prodigious.Capability;
import play.*;
import play.mvc.*;

import scala.collection.JavaConverters;
import views.html.*;

import cr.prodigious.CapabilityService;
import cr.prodigious.impl.CapabilityServiceImpl;

import java.util.List;

/**
 *http://localhost:9000/helloWorld
 */
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    // http://localhost:9000/helloWorld
    public static Result helloWorld() {

        final CapabilityService capabilityService =
                new CapabilityServiceImpl();

        final List<Capability> capabilityList =
                capabilityService.getCapabilityList();


        return ok(helloworld.render(
                "Hola muchachos!", capabilityList));
    }


}
