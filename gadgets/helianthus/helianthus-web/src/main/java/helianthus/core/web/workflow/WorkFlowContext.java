package helianthus.core.web.workflow;

import java.util.HashMap;

/**
 * Just a context to store the stuff between work flow steps
 *
 * Date: 5/13/14
 * Time: 10:41 PM
 */
public class WorkFlowContext extends HashMap<String, Object> {

    public static final String REQUEST_KEY = "request";
    public static final String RESPONSE_KEY = "response";
    public static final String CONTEXT_KEY = "context";
    public static final String RESULT_BEAN_KEY = "resultBean";
    public static final String PATH_MAPPING_RESULT_BEAN_KEY = "pathMappingResultBean";

} // E:O:F:WorkFlowContext.
