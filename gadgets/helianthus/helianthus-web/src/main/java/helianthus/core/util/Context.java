package helianthus.core.util;

import javax.servlet.ServletContext;
import java.io.Serializable;

/**
 * Abstraction for application context.
 *
 * Date: 5/11/14
 * Time: 1:19 PM
 * @author jsanca
 */
public interface Context extends Serializable {

    /**
     * Init the current context
     * @param servletContext ServletContext
     */
    public abstract void init (ServletContext servletContext);

    /**
     * Get the bean associated to the name
     * @param name String
     * @return Object
     */
    public abstract Object getBean (String name);
} // E:O:F:Context.
