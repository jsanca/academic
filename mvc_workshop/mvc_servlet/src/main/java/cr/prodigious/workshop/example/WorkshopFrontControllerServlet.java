package cr.prodigious.workshop.example;

import cr.prodigious.workshop.servlet.Context;
import cr.prodigious.workshop.servlet.FrontControllerServlet;
import cr.prodigious.workshop.servlet.MappingConfig;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation for the example, just overrides the factory method of the controller in order to init with a hardcore mappings.=
 *
 * http://localhost:8080/workshop/helloWord.do
 *
 * Date: 4/2/14
 * Time: 7:27 PM
 * @author jsanca
 */
public class WorkshopFrontControllerServlet extends FrontControllerServlet {

    public void init() throws ServletException {

        final ArrayList<MappingConfig> mappingConfigs =
                new ArrayList<MappingConfig>();

        mappingConfigs.add
                (new MappingConfig("/helloWord.do",
                        HelloWorldController.class,
                        HelloWorldPreCommand.class,
                        HelloWorldPostCommand.class
                        ));

        this.getServletContext().setAttribute
                ("mappingConfigCollection",
                        mappingConfigs);
    } // init

    @Override
    protected Object[] getFactoryParameters(final Request request, final Response response) {

        final Context context =
                request.getContext();
        final Collection<MappingConfig> mappingConfigCollection =
                (Collection<MappingConfig>)context.get("mappingConfigCollection");

        return new Object[] { mappingConfigCollection };
    }
} // E:O:F:WorkshopFrontControllerServlet.
