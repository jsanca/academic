package cr.prodigious;

import java.io.Serializable;

/**
 * A Capability :)
 *
 * Date: 4/8/14
 * Time: 6:45 PM
 * @author jsanca
 */
public class Capability implements Serializable {

    private String name = null;
    private String title = null;
    private String group = null;
    private String technology = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
} // E:O:F:Capability.
