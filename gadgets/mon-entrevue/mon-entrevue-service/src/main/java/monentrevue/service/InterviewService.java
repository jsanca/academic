package monentrevue.service;

import monentrevue.bean.Interview;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * Date: 9/3/14
 * Time: 2:32 PM
 * @author jsanca
 */
public interface InterviewService extends Serializable {

    /**
     * Get all the interviews, if populate is true will returns the interview objects completed with all the
     * questions and info, otherwise only the necessary info to be displayed in a header or list.
     * @return List of Interview objects
     */
    public abstract List<Interview> getAll ();

    /**
     * Get a interview index by interviewNameId
     * @param interviewNameId String
     * @return Interview
     */
    public abstract Interview get (String interviewNameId);

} // E:O:F:InterviewService.
