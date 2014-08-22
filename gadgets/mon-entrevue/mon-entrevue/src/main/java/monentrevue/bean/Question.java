package monentrevue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines an question, it has a text and a list of questions and their choices & answers
 *
 * Date: 8/21/14
 * Time: 8:07 PM
 * @author jsanca
 */
public class Question implements Serializable {

    private String nameId;

    private String text;

    private String note;

    private String type;

    private List<Choice> choices;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
} // Question.
