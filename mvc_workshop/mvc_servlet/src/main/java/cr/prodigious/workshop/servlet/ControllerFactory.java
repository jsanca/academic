package cr.prodigious.workshop.servlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  This class defines with Controller si associated to the current request, depending on
 *
 * Date: 4/2/14
 * Time: 4:26 PM
 * @author jsanca
 */
public class ControllerFactory implements Serializable {

    public Map<String, MappingConfig> mappingConfigMap = null;

    public ControllerFactory(final ArrayList<MappingConfig> mappingConfigCollection) {

        Iterator<MappingConfig> iterator =
                mappingConfigCollection.iterator();

        MappingConfig mappingConfig = null;

        this.mappingConfigMap = new HashMap<String, MappingConfig>();

        while (iterator.hasNext()) {

            mappingConfig =
                    iterator.next();

            this.mappingConfigMap.put
                    (mappingConfig.getMapping(), mappingConfig);
        }
    } // ControllerFactory.

    /**
     * Creates the instance to handle the workflow depending on the config.
     * The method by the moment it is not useful for anything but could be to create controller
     * based on the method called for instance (RESTFUL Controller bla bla bla).
     *
     * @param req Request
     * @param resp  Response
     * @param method Method
     * @return  MappingResult
     */
    public MappingResult create
            (final Request req,
             final Response resp,
             final FrontControllerServlet.Method method) {


        MappingResult mappingResult = null;

        final String requestPath = req.getPath();

        final MappingConfig mappingConfig =
                this.mathMapping (requestPath);

        if (null != mappingConfig) {

            if (null != mappingConfig.getControllerClass()) {

                mappingResult =
                        new MappingResult();

                mappingResult.setController
                        (this.createClass(mappingConfig.getControllerClass()));

                if (null != mappingConfig.getPreconditionCommandClass()) {

                    mappingResult.setPreconditionCommand
                            (this.createClass(mappingConfig.getPreconditionCommandClass()));
                }

                if (null != mappingConfig.getPostconditionCommandClass()) {

                    mappingResult.setPostconditionCommand
                            (this.createClass(mappingConfig.getPostconditionCommandClass()));
                }
            }
        }

        return mappingResult;
    } // create.

    private <T> T createClass(final Class<T> controllerClass) {

        T instance = null;

        try {

            instance = controllerClass.newInstance();
        } catch (InstantiationException e) {

            // todo: report something
            instance = null;
        } catch (IllegalAccessException e) {

            // todo: report something
            instance = null;
        }

        return instance;
    } // createClass.

    private MappingConfig mathMapping(final String requestPath) {

        MappingConfig mappingConfig = null;

        // just a simple match in a map :D by now

        mappingConfig =
                this.mappingConfigMap.get(requestPath);

        return mappingConfig;
    } // mathMapping.
} // E:O:F:ControllerFactory.
