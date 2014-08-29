package monentrevue.search;

import monentrevue.bean.Interview;

import java.io.Serializable;
import java.util.List;

/**
 * This service handle the searching capabilities for one or more interviews
 *
 * In addition it provides the possibility to index an interview.
 *
 * Date: 8/27/14
 * Time: 9:29 AM
 * @author jsanca
 */
public interface InterviewSearchService extends Serializable {

    /**
     * Do the index of an interview.
     * Returns an Interview Index Key to recovery later the interview
     * In addition the current interview will be ready to be queried.
     *
     * @param interview   Interview
     * @return InterviewIndexKey
     */
    public abstract void indexInterview (Interview interview);


    /**
     * Do a search over the current session indexes
     *
     * The interview objects returned will contain only the information stored in the index,
     * probably not all of them.
     *
     * @param searchTerm String
     * @return List of Interview
     */
    public abstract List<Interview> search(String searchTerm);
} // E:O:F:InterviewSearchService.
