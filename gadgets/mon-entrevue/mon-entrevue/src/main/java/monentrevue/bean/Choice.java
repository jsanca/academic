package monentrevue.bean;

import java.io.Serializable;

/**
 * Defines an question, it has a text and a list of questions and their choices & answers
 *
 * Date: 8/21/14
 * Time: 8:07 PM
 * @author jsanca
 */
public class Choice implements Serializable {

    private String text;

    private int suggestedScore;

    private boolean valid;

    private String note;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSuggestedScore() {
        return suggestedScore;
    }

    public void setSuggestedScore(int suggestedScore) {
        this.suggestedScore = suggestedScore;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
} // Choice.
