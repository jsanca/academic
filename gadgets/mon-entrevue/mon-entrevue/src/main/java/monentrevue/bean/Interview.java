package monentrevue.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines an interview, it has a title a list of questions and their choices & answers
 *
 * Date: 8/21/14
 * Time: 8:07 PM
 * @author jsanca
 */
public class Interview implements Serializable {

    private String nameId;

    private String title;

    private String note;

    private Map<String, List<Question>> questionByType;

    private Map<String, Question> questionById;

    private List<Question> questions;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {

        if (null == this.questions) {

            this.questions =
                    new ArrayList<Question>();
        }

        this.questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setQuestions(List<Question> questions, boolean doIndex) {
        this.questions = questions;

        if (doIndex) {

            this.indexQuestionsByType();
            this.indexQuestionsById();
        }
    }

    public void indexQuestionsByType () {

        this.questionByType =
                new HashMap<String, List<Question>>();

        List<Question> questionByTypeList = null;

        for (Question question : this.questions) {

            if (!this.questionByType.containsKey(question.getType())) {

                questionByTypeList = new ArrayList<Question>();
                this.questionByType.put(question.getType(), questionByTypeList);
            }

            questionByTypeList =
                    this.questionByType.get(question.getType());

            questionByTypeList.add(question);
        }
    } // indexQuestionsByType.

    public Question findById (final String questionNameId) {

        Question question = null;

        if ((null != questionNameId) &&
                (null != this.questionById)) {

            if (this.questionById.containsKey(questionNameId)) {

                question =
                        this.questionById.get(questionNameId);
            }
        }

        return question;
    }

    public List<Question> findByType (final String questionType) {

        List<Question> questionList =
                Collections.EMPTY_LIST;

        if ((null != questionType) &&
                (null != this.questionByType)) {

            if (this.questionByType.containsKey(questionType)) {

                questionList =
                        this.questionByType.get(questionType);
            }
        }

        return questionList;
    }

    public void indexQuestionsById () {

        this.questionById =
                new HashMap<String, Question>();

        for (Question question : this.questions) {

            if (null != question.getNameId()) {

                this.questionById.put
                        (question.getNameId(),
                                question);
            }
        }
    } // indexQuestionsByType
} // Interview.
