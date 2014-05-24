package helianthus.core.web;

import helianthus.core.bean.PathMappingResultBean;
import helianthus.core.util.Context;
import helianthus.core.util.ContextUtils;
import helianthus.core.util.PathHandler;
import helianthus.core.web.workflow.WorkFlowContext;
import helianthus.core.web.workflow.WorkFlowFactory;
import helianthus.core.web.workflow.WorkFlowStep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * Entry point for the Helianthus app
 *
 * Date: 5/11/14
 * Time: 12:07 PM
 * @author jsanca
 */
public class HelianthusServlet extends HttpServlet {

    public static enum Method { POST, GET, PUT, DELETE };


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.POST);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.PUT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.DELETE);
    }

    protected void doAction (
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final Method method) {

        Context context = null;
        PathMappingResultBean pathMappingResultBean = null;
        PathHandler pathHandler = null;
        LinkedHashSet<WorkFlowStep> workFlow = null;
        WorkFlowFactory workFlowFactory = null;

        try {

            context = ContextUtils.getContext(req);

            if (null != context) {

                pathHandler =
                        (PathHandler)context.getBean("pathHandler");

                workFlowFactory =
                        (WorkFlowFactory)context.getBean("workFlowFactory");

                if ((null != pathHandler) && (null != workFlowFactory)) {

                    pathMappingResultBean =
                        pathHandler.parsePath(req.getServletPath());

                    if (null != pathMappingResultBean) {

                        workFlow =
                                workFlowFactory.createWorkFlow
                                        (pathMappingResultBean);

                        if (null != workFlow) {

                            req.setAttribute
                                    (WorkFlowContext.PATH_MAPPING_RESULT_BEAN_KEY,
                                            pathMappingResultBean);
                            this.executeWorkFlow(req, resp, workFlow, context);
                        }
                    }
                }
            }
        } catch (Exception e) {

            // todo: error handling.
        }
    } // doAction.

    private void executeWorkFlow(final HttpServletRequest req,
                                 final HttpServletResponse resp,
                                 final LinkedHashSet<WorkFlowStep> workFlow,
                                 final Context context) {

        final WorkFlowContext workFlowContext =
                new WorkFlowContext();

        workFlowContext.put
                (WorkFlowContext.REQUEST_KEY, req);
        workFlowContext.put
                (WorkFlowContext.RESPONSE_KEY, resp);
        workFlowContext.put
                (WorkFlowContext.CONTEXT_KEY, context);

        for (WorkFlowStep step : workFlow) {

            if (!step.doStep(workFlowContext)) {

                break;
            }
        }
    } // executeWorkFlow.

} // E:O:F:HelianthusServlet.
