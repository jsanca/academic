package helianthus.core.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Utils to get the context from the request.
 *
 * Date: 5/11/14
 * Time: 1:23 PM
 * @author jsanca
 */
public class ContextUtils implements Serializable {

    public static final String CONTEXT_KEY = "contextApp";

    public static final String CONTEXT_CLASS_PATH_KEY = "contextClassPath";

    public static Context getContext (final HttpServletRequest request) {

        Context context = null;
        final ServletContext servletContext =
                request.getSession().getServletContext();

        if (null == servletContext.getAttribute(CONTEXT_KEY)) {

             initContext (servletContext);
        }

        context =
            (Context)servletContext.getAttribute(CONTEXT_KEY);

        return context;
    } // getContext.

    private static void initContext(final ServletContext servletContext) {

        final String contextClassPath =
                servletContext.getInitParameter(CONTEXT_CLASS_PATH_KEY);

        final Context context =
                (Context) ReflectionUtils.INSTANCE.createInstance
                        (contextClassPath);

        if (null != context) {

            context.init(servletContext);
            servletContext.setAttribute(CONTEXT_KEY, context);
        }
    } // initContext.
} // E:O:F:ContextUtils.
