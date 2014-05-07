package cr.prodigious.workshop.example;

import cr.prodigious.CapabilityService;
import cr.prodigious.impl.CapabilityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation for the example, just overrides the factory method of the controller in order to init with a hardcore mappings.=
 *
 * http://localhost:8080/workshop-simple/helloworld.do
 * http://localhost:8080/workshop-simple/goodbye.do
 *
 * Date: 4/2/14
 * Time: 7:27 PM
 * @author jsanca
 */
public class WorkshopFrontControllerServlet extends HttpServlet {

    private CapabilityService capabilityService = null;

    public void init() throws ServletException {

        this.capabilityService = new CapabilityServiceImpl();
    } // init

    @Override
    protected void doGet
            (final HttpServletRequest request,
             final HttpServletResponse response)
            throws ServletException, IOException {

        final String path = request.getRequestURI()
                .substring(request.getContextPath().length());

        if ("/helloworld.do".equalsIgnoreCase(path)) {


            request.setAttribute("message", "Hello Prodigious CR");
            request.setAttribute("capabilityList",
                    this.capabilityService.getCapabilityList());

            request.getRequestDispatcher("helloworld.jsp")
                    .forward(request, response);
        } else if ("/goodbye.do".equalsIgnoreCase(path)) {

            response.getWriter().write("Good Bye Guys");
        }
    } // doGet.
} // E:O:F:WorkshopFrontControllerServlet.
