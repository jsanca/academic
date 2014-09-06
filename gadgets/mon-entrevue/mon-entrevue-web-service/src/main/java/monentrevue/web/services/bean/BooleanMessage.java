package monentrevue.web.services.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * Date: 9/5/14
 * Time: 6:26 PM
 * @author jsanca
 */
@XmlRootElement
public class BooleanMessage implements Serializable {

    private boolean value;

    public BooleanMessage() {

    }

    public BooleanMessage(boolean value) {
        this.value = value;
    }

    @XmlAttribute
    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
} // E:O:F:BooleanMessage.
