package monentrevue.reader;

import monentrevue.bean.Interview;

import java.io.Reader;
import java.io.Serializable;

/**
 * Interface for an Interview Reader
 *
 * Basically parsers an Interview from a reader.
 *
 * Date: 8/21/14
 * Time: 11:50 PM
 * @author jsanca
 */
public interface InterviewReader extends Serializable {

    /**
     * Parse the interview from a reader.
     * @param reader
     * @return
     */
    public abstract Interview read (Reader reader);

} // E:O:F:InterviewReader.
