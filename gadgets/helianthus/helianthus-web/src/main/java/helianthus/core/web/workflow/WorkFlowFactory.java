package helianthus.core.web.workflow;

import helianthus.core.bean.PathMappingResultBean;
import helianthus.core.marshall.MarshallFormatFactory;
import helianthus.core.web.workflow.step.FormatterWorkFlowStep;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Based on a path mapping result bean returns a linked set of a work flow steps.
 *
 * Date: 5/13/14
 * Time: 11:00 PM
 * @author jsanca
 */
public class WorkFlowFactory implements Serializable {

    private MarshallFormatFactory marshallFormatFactory;

    private WorkFlowStep operationRunnerWorkFlowStep;

    public LinkedHashSet<WorkFlowStep> createWorkFlow
            (final PathMappingResultBean pathMappingResultBean) {

        final LinkedHashSet<WorkFlowStep> workFlow =
                new LinkedHashSet<WorkFlowStep>();

        if (null != pathMappingResultBean) {

            workFlow.add(this.createWorkFlowStepOperationProcess
                    (pathMappingResultBean.getOperationId()));

            if (null != pathMappingResultBean.getFormat()) {

                workFlow.add(this.createWorkFlowStepFormatter
                        (pathMappingResultBean.getFormat()));
            } else {

                workFlow.add(this.createWorkFlowStepFormatter
                        ("html"));
            }
        }

        return workFlow;
    } // createWorkFlow.

    protected WorkFlowStep createWorkFlowStepFormatter (final String formatter) {

        return new FormatterWorkFlowStep
                    (this.marshallFormatFactory.
                            getMarshallFormatter(formatter));
    } // createWorkFlowStepFormatter.

    protected WorkFlowStep createWorkFlowStepOperationProcess (final String operationId) {

        return this.operationRunnerWorkFlowStep;
    } // createWorkFlowStepOperationProcess.

    public void setMarshallFormatFactory(MarshallFormatFactory marshallFormatFactory) {

        this.marshallFormatFactory = marshallFormatFactory;
    } // setMarshallFormatFactory.

    public void setOperationRunnerWorkFlowStep(WorkFlowStep operationRunnerWorkFlowStep) {

        this.operationRunnerWorkFlowStep = operationRunnerWorkFlowStep;
    }
} // E:O:F:WorkFlowFactory.
