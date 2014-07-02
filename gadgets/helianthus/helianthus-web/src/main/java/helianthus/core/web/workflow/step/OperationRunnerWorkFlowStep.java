package helianthus.core.web.workflow.step;

import helianthus.core.HelianthusService;
import helianthus.core.bean.PathMappingResultBean;
import helianthus.core.bean.TableResultBean;
import helianthus.core.util.OperationMappingHelper;
import helianthus.core.web.workflow.WorkFlowContext;
import helianthus.core.web.workflow.WorkFlowStep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Execute an operation id workflow step
 *
 * Date: 5/19/14
 * Time: 6:10 PM
 * @author jsanca
 */
public class OperationRunnerWorkFlowStep implements WorkFlowStep {

    private HelianthusService helianthusService;

    private OperationMappingHelper operationMappingHelper;

    public OperationRunnerWorkFlowStep() {

    }

    /**
     * Runs just a step as part of a workflow, returns false if you want to stop the workflow.
     * Otherwise true will continue with the next step.
     *
     * @param workFlowContext WorkFlowContext
     * @return boolean true in case you want to chain to the next step
     */
    @Override
    public boolean doStep(final WorkFlowContext workFlowContext) {

        boolean continueStep = true;

        final HttpServletRequest request =
                (HttpServletRequest)workFlowContext.get
                        (WorkFlowContext.REQUEST_KEY);

        final HttpServletResponse response =
                (HttpServletResponse)workFlowContext.get
                        (WorkFlowContext.RESPONSE_KEY);

        TableResultBean tableResultBean = null;
        PathMappingResultBean pathMappingResultBean = null;

        try {

            pathMappingResultBean =
                    (PathMappingResultBean)request
                        .getAttribute(WorkFlowContext.PATH_MAPPING_RESULT_BEAN_KEY);

            if (null != pathMappingResultBean) {

                tableResultBean = this.helianthusService.executeOperation
                        (pathMappingResultBean.getOperationId(),
                                this.getRequestQueryStringParameters
                                        (pathMappingResultBean, request));

                request.setAttribute
                        (WorkFlowContext.RESULT_BEAN_KEY,
                                tableResultBean);
            }
        } catch (Exception e) {

            continueStep = false;
        }

        return continueStep;
    } // doStep.

    private Object [] getRequestQueryStringParameters(
            final PathMappingResultBean pathMappingResultBean,
            final HttpServletRequest request) {

        final String [] parameterList =
            this.operationMappingHelper.getParameterList
                    (pathMappingResultBean.getOperationId());

        final ArrayList<String> paramValues =
                new ArrayList<String>();

        for (String paramName : parameterList) {

            if (null != request.getParameter(paramName)) {

                paramValues.add(request.getParameter(paramName));
            }
        }

        return paramValues.toArray();
    } // getRequestQueryStringParameters.

    public void setHelianthusService(HelianthusService helianthusService) {

        this.helianthusService = helianthusService;
    }

    public void setOperationMappingHelper(OperationMappingHelper operationMappingHelper) {
        this.operationMappingHelper = operationMappingHelper;
    }
} // E:O:F:FormatterWorkFlowStep.
