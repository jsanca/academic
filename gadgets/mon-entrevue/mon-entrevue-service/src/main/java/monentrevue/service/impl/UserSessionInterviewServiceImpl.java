package monentrevue.service.impl;

import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.search.impl.SessionMemoryInterviewSearchService;
import monentrevue.service.UserSessionInterviewService;
import org.apache.commons.lang.math.RandomUtils;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Default implementation
 *
 * Date: 9/3/14
 * Time: 4:15 PM
 * @author jsanca
 */
public class UserSessionInterviewServiceImpl implements UserSessionInterviewService {

    private SessionMemoryInterviewSearchService sessionMemoryInterviewSearchService;

    private volatile Interview interview = null;

    private int indexIterator = 0;

    public UserSessionInterviewServiceImpl() {

         this.sessionMemoryInterviewSearchService =
                 new SessionMemoryInterviewSearchService();
    }

    public UserSessionInterviewServiceImpl(Interview interview) {

        this.sessionMemoryInterviewSearchService =
                new SessionMemoryInterviewSearchService();
        this.setInterview(interview);
    }

    public void setInterview(Interview interview) {

        this.interview = interview;

        this.interview.indexQuestionsById();
        this.interview.indexQuestionsByType();

        this.sessionMemoryInterviewSearchService.indexInterview(interview);
    }

    /**
     * Get all the questions for the current interview
     *
     * @return List of questions
     */
    @Override
    public List<Question> all() {

        return this.interview.getQuestions();
    }

    /**
     * Do a search based on the term in the param
     *
     * @param searchTerm String
     * @return List of questions
     */
    @Override
    public List<Question> search(String searchTerm) {

        return this.sessionMemoryInterviewSearchService.search(searchTerm);
    }

    /**
     * Do a find by id
     *
     * @param interviewNameId String
     * @return Question
     */
    @Override
    public Question findBy(String interviewNameId) {

        return this.interview.findById(interviewNameId);
    }

    /**
     * You can use this method to get one by one the questions
     *
     * @return Question
     */
    @Override
    public Question findNext() {

        Question question = null;

        if (this.indexIterator < this.all().size()) {

            question =
                    this.interview.getQuestions().get
                            (this.indexIterator++);
        }

        return question;
    }

    /**
     * You can use this method to get a random question
     *
     * @return Question
     */
    @Override
    public Question random() {

        int index =
                RandomUtils.nextInt
                        (this.interview.getQuestions().size() - 1);

        Question question = null;


        question = this.interview.getQuestions().get(index);

        return question;
    } // random.

    /**
     * Find Questions by type
     *
     * @param questionType String
     * @return Question List
     */
    @Override
    public List<Question> findByType(final String questionType) {

        return this.interview.findByType(questionType);
    } // findByType.

    /**
     * Get all the question types
     *
     * @return Set of questions.
     */
    @Override
    public Set<String> getQuestionTypes() {

        return new TreeSet<String>(this.interview.getQuestionTypes());
    }

} // E:O:F:UserSessionInterviewServiceImpl.
