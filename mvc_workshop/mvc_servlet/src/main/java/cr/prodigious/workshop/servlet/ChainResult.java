package cr.prodigious.workshop.servlet;

import cr.prodigious.workshop.servlet.view.ViewResult;

import java.io.Serializable;

/**
 * Encapsulates the Chain Result for a pre and post conditions.
 *
 * In case you return stop as a state, you might add a view result object in case
 * you want to do something instead of continue the workflow.
 *
 * Date: 4/2/14
 * Time: 4:20 PM
 * @author jsanca
 */
public class ChainResult implements Serializable {

    public static enum ChainType { STOP, CONTINUE };

    private ChainType type = null;

    private ViewResult viewResult = null;

    public ChainType getType() {
        return type;
    }

    public ChainResult setType(ChainType type) {
        this.type = type;
        return this;
    }

    public ViewResult getViewResult() {
        return viewResult;
    }

    public void setViewResult(ViewResult viewResult) {
        this.viewResult = viewResult;
    }
} // E:O:F:ChainResult.
