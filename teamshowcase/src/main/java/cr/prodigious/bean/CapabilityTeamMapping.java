package cr.prodigious.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

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

    public Collection<Long> getAllIds () {

        return capabilityTeamMap.values();
    }

    public Set<String> getAllCapabilityNames() {

        return this.capabilityTeamMap.keySet();
    }
} // E:O:F:CapabilityTeamMapping.
