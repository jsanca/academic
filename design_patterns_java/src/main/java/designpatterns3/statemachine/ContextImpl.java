package designpatterns3.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Default impl
 * @author jsanca
 */
public class ContextImpl implements Context {

    private Map<String, Object> contextMap =
            new HashMap<String, Object>();

    @Override
    public void set(final String paramKey, final Object value) {

        this.contextMap.put(paramKey, value);
    }

    @Override
    public Object get(final String paramKey) {

        return this.contextMap.get(paramKey);
    }
}
