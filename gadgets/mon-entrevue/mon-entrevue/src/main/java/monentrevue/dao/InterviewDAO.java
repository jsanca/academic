package monentrevue.dao;

import monentrevue.bean.Interview;

import java.io.Serializable;
import java.util.List;

/**
 * Persists and recovery an interview
 *
 * Date: 8/27/14
 * Time: 10:21 AM
 * @author jsanca
 */
public interface InterviewDAO extends Serializable {

    /**
     * Get all the interviews, if populate is true will returns the interview objects completed with all the
     * questions and info, otherwise only the necessary info to be displayed in a header or list.
     * @param populate boolean true to populate the whole interview object
     * @return List of Interview objects
     */
    public abstract List<Interview> getAll (boolean populate);

    /**
     * Get a interview index by interviewNameId
     * @param interviewNameId String
     * @return Interview
     */
    public abstract Interview get (String interviewNameId);

    /**
     * Save an interview
     * @param interview Interview
     * @return String
     */
    public abstract String save(Interview interview);

    /**
     * Update an existing interview
     * @param interviewNameId String
     * @param interview String
     * @return boolean
     */
    public abstract boolean update(String interviewNameId, Interview interview);

    /**
     * Delete an existing interview
     * @param interviewNameId String
     * @return boolean
     */
    public abstract boolean delete(String interviewNameId);
} // E:O:F:InterviewDAO.
