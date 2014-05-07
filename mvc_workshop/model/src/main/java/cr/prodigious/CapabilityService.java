package cr.prodigious;

import java.io.Serializable;
import java.util.List;

/**
 * Capability service
 *
 * Date: 4/8/14
 * Time: 6:48 PM
 * @author jsanca
 */
public interface CapabilityService extends Serializable {

    /**
     * Get the capability list
     * @return List of Capability beans
     */
    public abstract List<Capability> getCapabilityList ();
} // E:O:F:CapabilityService.
