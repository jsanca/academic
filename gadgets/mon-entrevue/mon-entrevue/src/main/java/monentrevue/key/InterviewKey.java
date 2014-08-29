package monentrevue.key;

import java.io.Serializable;

/**
 * Encapsulate the key to recovery an interview.
 * Date: 8/27/14
 * Time: 9:31 AM
 * @author jsanca
 */
public class InterviewKey implements Serializable {

    private Object key = null;


    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }
} // E:O:F:InterviewIndexKey.
