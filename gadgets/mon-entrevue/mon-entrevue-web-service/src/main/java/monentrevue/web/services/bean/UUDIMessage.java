package monentrevue.web.services.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * UUDI message
 *
 * Date: 9/5/14
 * Time: 6:22 PM
 * @author jsanca
 */
@XmlRootElement
public class UUDIMessage implements Serializable {

    private String uudi;

    public UUDIMessage() {

        super();
    }

    public UUDIMessage(String uudi) {
        this.uudi = uudi;
    }

    @XmlAttribute
    public String getUudi() {
        return uudi;
    }

    public void setUudi(String uudi) {
        this.uudi = uudi;
    }
} // E:O:F:UUDIMessage.
