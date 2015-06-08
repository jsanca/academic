package designpatterns3.statemachine;

import java.io.Serializable;

/**
 * Represents a Context for the states
 * @author jsanca
 */
public interface Context extends Serializable {

    public Object get (String paramKey);

    public void   set (String paramKey, Object value);
} // E:O:F:Context.
