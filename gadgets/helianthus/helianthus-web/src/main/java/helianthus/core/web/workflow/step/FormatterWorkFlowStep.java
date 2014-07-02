package helianthus.core.web.workflow.step;

import helianthus.core.bean.TableResultBean;
import helianthus.core.marshall.MarshallFormatter;
import helianthus.core.web.workflow.WorkFlowContext;
import helianthus.core.web.workflow.WorkFlowStep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Execute a formatter workflow step
 *
 * Date: 5/19/14
 * Time: 6:10 PM
 * @author jsanca
 */
public class FormatterWorkFlowStep implements WorkFlowStep {

    private MarshallFormatter marshallFormatter;

    public FormatterWorkFlowStep(MarshallFormatter marshallFormatter) {

        this.marshallFormatter = marshallFormatter;
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

        OutputStream outputStream = null;
        TableResultBean tableResultBean = null;

        try {

            outputStream =
                    response.getOutputStream();

            tableResultBean =
                    (TableResultBean)request
                        .getAttribute(WorkFlowContext.RESULT_BEAN_KEY);

            this.marshallFormatter.process
                    (outputStream, tableResultBean);
        } catch (IOException e) {

            continueStep = false;
        }

        return continueStep;
    } // doStep.
} // E:O:F:FormatterWorkFlowStep.
