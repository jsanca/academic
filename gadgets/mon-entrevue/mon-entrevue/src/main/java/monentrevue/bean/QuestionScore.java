package monentrevue.bean;

import java.io.Serializable;

/**
 *  Score
 *
 * Date: 8/21/14
 * Time: 8:23 PM
 * @author jsanca
 */
public class QuestionScore implements Serializable {

    private int score;

    private String note;

    private Question question;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
} // E:O:F:InterviewScore.
