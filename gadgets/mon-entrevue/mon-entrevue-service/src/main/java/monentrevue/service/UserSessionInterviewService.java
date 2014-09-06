package monentrevue.service;

import monentrevue.bean.Question;

import java.io.Serializable;
import java.util.List;

/**
 * This is kind of a Facade per user session.
 * Provide a search, find by, next and random questions
 * Usually the interview is loaded for the session, so this service must be one per session.
 * Date: 9/3/14
 * Time: 4:01 PM
 * @author jsanca
 */
public interface UserSessionInterviewService extends Serializable {

    /**
     * Get all the questions for the current interview
     * @return List of questions
     */
    public List<Question> all();

    /**
     * Do a search based on the term in the param
     * @param searchTerm String
     * @return List of questions
     */
    public List<Question> search(String searchTerm);

    /**
     * Do a find by id
     *
     * @param interviewNameId String
     * @return Question
     */
    public Question findBy(String interviewNameId);

    /**
     * You can use this method to get one by one the questions
     * @return Question
     */
    public Question findNext();

    /**
     * You can use this method to get a random question
     * @return Question
     */
    public Question random();



} // E:O:F:UserSessionInterviewService.
