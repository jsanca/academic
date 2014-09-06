package monentrevue.web.services.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * Date: 9/5/14
 * Time: 4:43 PM
 * @author jsanca
 */
@XmlRootElement
public class StringMessage implements Serializable {

    private String message;

    public StringMessage() {

        super();
    }

    public StringMessage(String message) {

        this.message = message;
    }

    @XmlAttribute
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
} // E:O:F:PingMessage.
