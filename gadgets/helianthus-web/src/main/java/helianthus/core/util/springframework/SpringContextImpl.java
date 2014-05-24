package helianthus.core.util.springframework;

import helianthus.core.util.Context;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Spring context imple
 *
 * Date: 5/11/14
 * Time: 1:28 PM
 * @author jsanca
 */
public class SpringContextImpl implements Serializable, Context {

    private static Logger logger =
            Logger.getLogger(SpringContextImpl.class.getName());

    private ApplicationContext context = null;

    /**
     * Init the current context
     *
     * @param servletContext ServletContext
     */
    @Override
    public void init(final ServletContext servletContext) {

        final String contextConfigLocation =
                servletContext.getInitParameter("contextConfigLocation");

        if (null == contextConfigLocation) {

            logger.log(Level.WARNING, "No any Context Config Load");
        } else {

            final String [] contextConfigLocationArray =
                    contextConfigLocation.split(",");

            logger.log(Level.INFO,
                    MessageFormat.format
                            ("Loading the config file {0}",
                                    contextConfigLocation));

            this.init(servletContext,
                    this.toRealPath(
                            servletContext,
                            contextConfigLocationArray
                    ));
        }
    } // init.

    private String [] toRealPath (
            final ServletContext servletContext,
            final String [] contextConfigLocationArray
            ) {

        for (int i = 0; i < contextConfigLocationArray.length; ++i) {

            contextConfigLocationArray [i] =
                    servletContext.getRealPath
                            (contextConfigLocationArray[i]);
        }

        return contextConfigLocationArray;
    } // toRealPath.

    private void init(final ServletContext servletContext,
                      final String [] contextConfigLocationArray) {


        try {

            this.context =
                    new FileSystemXmlApplicationContext
                            (contextConfigLocationArray);

        } /*catch (FileNotFoundException e) {

             logger.log(Level.INFO, e.getMessage(), e);
        } */finally {

        }
    } // init.

    /**
     * Get the bean associated to the name
     *
     * @param name String
     * @return Object
     */
    @Override
    public Object getBean(final String name) {

        return (null != this.context)?
                this.context.getBean(name):null;
    } // getBean
} // E:O:F:SpringContextImpl.
