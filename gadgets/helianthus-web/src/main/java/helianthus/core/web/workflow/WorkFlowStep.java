package helianthus.core.web.workflow;

import java.io.Serializable;

/**
 * Defines a single work flow step
 *
 * Date: 5/13/14
 * Time: 10:38 PM
 * @author jsanca
 */
public interface WorkFlowStep extends Serializable {

    /**
     * Runs just a step as part of a workflow, returns false if you want to stop the workflow.
     * Otherwise true will continue with the next step.
     *
     * @param workFlowContext WorkFlowContext
     * @return boolean true in case you want to chain to the next step
     */
    public boolean doStep (WorkFlowContext workFlowContext);
} // E:O:F:WorkFlowStep.
