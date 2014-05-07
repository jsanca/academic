package cr.prodigious.workshop.servlet.wrapper;

import cr.prodigious.workshop.servlet.Context;
import cr.prodigious.workshop.servlet.Request;

import javax.servlet.http.HttpServletRequest;

/**
 * Default request wrapper
 *
 * Date: 4/2/14
 * Time: 5:43 PM
 * @author  jsanca
 */
public class RequestWrapper implements Request {

    protected HttpServletRequest request = null;

    protected Context context = null;

    public RequestWrapper(HttpServletRequest request) {

        this.request = request;
    }

    /**
     * Get the
     *
     * @return
     */
    @Override
    public String getPath() {

        return this.request.getRequestURI()
                .substring(this.request.getContextPath().length());
    }

    /**
     * Get App Content
     *
     * @return Context
     */
    @Override
    public Context getContext() {

        if (null == this.context) {

            this.context =
                    new ContextWrapper
                            (this.request.getSession().getServletContext());
        }

        return this.context;
    }

    /**
     * Set a value to the request
     *
     * @param key   String
     * @param value value
     */
    @Override
    public void set(final String key, final Object value) {

        this.request.setAttribute(key, value);
    }
} // E:O:F:RequestWrapper
