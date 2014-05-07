package cr.prodigious.workshop.servlet.wrapper;

import cr.prodigious.workshop.servlet.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Default response wrapper
 *
 * Date: 4/2/14
 * Time: 5:43 PM
 * @author  jsanca
 */
public class ResponseWrapper implements Response {

    protected HttpServletResponse response = null;
    protected HttpServletRequest request = null;

    public ResponseWrapper(HttpServletResponse response,
                           HttpServletRequest request) {

        this.response = response;
        this.request = request;
    }

    @Override
    public void error(final Integer errorCode) {

        try {

            response.sendError(errorCode);
        } catch (IOException e) {

            // todo: do something with this exception???
        }
    } // error.

    /**
     * Do a forward based on the path
     *
     * @param path String
     */
    @Override
    public void forward(final String path) {

        try {

            this.request.getRequestDispatcher(path)
                    .forward(this.request, this.response);
        } catch (ServletException e) {

            // todo: do something with this exception???
        } catch (IOException e) {

            // todo: do something with this exception???
        }
    }

    /**
     * Do a redirect
     *
     * @param path String
     */
    @Override
    public void redirect(final String path) {

        try {

            this.response.sendRedirect(path);
        } catch (IOException e) {
            // todo: do something with this exception???
        }
    } // redirect.
} // E:O:F:RequestWrapper
