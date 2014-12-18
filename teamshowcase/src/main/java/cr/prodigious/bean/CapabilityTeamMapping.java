package cr.prodigious.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Keeps the relationship between the capability names and the database ids
 * @author jsanca
 */
public class CapabilityTeamMapping implements Serializable {

    private Map<String, Long> capabilityTeamMap = null;

    public Long map (final String capability) {

        return this.capabilityTeamMap.get(capability);
    }

    public void setCapabilityTeamMap(Map<String, Long> capabilityTeamMap) {
        this.capabilityTeamMap = capabilityTeamMap;
    }
} // E:O:F:CapabilityTeamMapping.
