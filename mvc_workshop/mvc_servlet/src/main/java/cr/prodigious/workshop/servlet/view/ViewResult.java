package cr.prodigious.workshop.servlet.view;

import java.io.Serializable;

/**
 * Defines a generic view result abstraction
 *
 * Date: 4/2/14
 * Time: 5:04 PM
 * @author jsanca
 */
public class ViewResult implements Serializable {

    public static enum ViewType implements Serializable {

        REDIRECT(2), FORWARD(4), ERROR(8), NOTHING(16);

        private int type;

        private ViewType(int type) {

            this.type = type;
        }

        public int getType() {

            return this.type;
        }

    } // ViewType.

    private final static ViewResult NOTHING = new ViewResult();

    // this is an integer to open the possibility to be extensible
    private int type = ViewType.NOTHING.getType();

    private Object value = null;

    public ViewResult() {
    }

    protected ViewResult(ViewType type, Object value) {
        this.type = type.getType();
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Creates a redirect view result
     * @param path String
     * @return  ViewResult
     */
    public static ViewResult createRedirect (final String path) {

        return new ViewResult(ViewType.REDIRECT, path);
    } // createRedirect.

    /**
     * Creates a redirect view result
     * @param errorCode Integer
     * @return  ViewResult
     */
    public static ViewResult createError (final Integer errorCode) {

        return new ViewResult(ViewType.ERROR, errorCode);
    } // createRedirect.

    /**
     * Creates a redirect view result
     * @param path String
     * @return  ViewResult
     */
    public static ViewResult createForward (final String path) {

        return new ViewResult(ViewType.FORWARD, path);
    } // createRedirect.

    /**
     * A passive view result, this is the default and it does nothing ;)
     *
     * @return  ViewResult
     */
    public static ViewResult createPassive () {

        return NOTHING;
    } // createRedirect.
} // E:O:F:ViewResult.
