package monentrevue.key;

import java.io.Serializable;
import java.util.Map;

/**
 * Defines an interface to creates the interview key from a set of meta data
 *
 * Date: 8/27/14
 * Time: 9:33 AM
 * @author jsanca
 */
public interface InterviewKeyFactory extends Serializable {

    /**
     * Based on a context map creates a
     * @param contextMap
     * @return
     */
    public abstract InterviewKey createInterviewIndexKey
            (final Map<String, Object> contextMap);

} // E:O:F:InterviewIndexKeyFactory
