package designpatterns3.statemachine;

import java.io.Serializable;

/**
 * Define a particular state
 * @author jsanca
 */
public interface State extends Serializable {

    /**
     * Method called when the state gets in (when this is become)
     */
    public void handleIn (Context context);

    /**
     * Method called when the state gets out (when leaves this state)
     */
    public void handleOut (Context context);

} // E:O:F:State
