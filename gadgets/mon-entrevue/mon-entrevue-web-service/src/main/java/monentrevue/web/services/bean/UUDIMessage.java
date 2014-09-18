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

    private String uuid;

    public UUDIMessage() {

        super();
    }

    public UUDIMessage(String uudi) {
        this.uuid = uudi;
    }

    @XmlAttribute
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
} // E:O:F:UUDIMessage.
