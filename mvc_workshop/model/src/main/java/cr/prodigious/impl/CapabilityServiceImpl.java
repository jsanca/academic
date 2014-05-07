package cr.prodigious.impl;

import cr.prodigious.Capability;
import cr.prodigious.CapabilityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Super Hyper Rocket science implementation
 *
 * Date: 4/8/14
 * Time: 6:49 PM
 * @author jsanca
 */
public class CapabilityServiceImpl implements CapabilityService {

    /**
     * Get the capability list
     *
     * @return List of Capability beans
     */
    @Override
    public List<Capability> getCapabilityList() {

        final List<Capability> capabilities =
                new ArrayList<Capability>();

        final Capability anibalCapability = new Capability();

        anibalCapability.setGroup("RZF");
        anibalCapability.setName("Anibal Gomez");
        anibalCapability.setTechnology("Java/Net");
        anibalCapability.setTitle("TA");
        capabilities.add(anibalCapability);

        final Capability elProfeCapability = new Capability();

        elProfeCapability.setGroup("RZF");
        elProfeCapability.setName("Daniel Pacheco");
        elProfeCapability.setTechnology("Java");
        elProfeCapability.setTitle("TA");
        capabilities.add(elProfeCapability);

        final Capability alemanCapability = new Capability();

        alemanCapability.setGroup("Digitas");
        alemanCapability.setName("Jose Aleman");
        alemanCapability.setTechnology("Java");
        alemanCapability.setTitle("TA");
        capabilities.add(alemanCapability);

        final Capability saurezCapability = new Capability();

        saurezCapability.setGroup("RZF");
        saurezCapability.setName("Jonathan Saurez");
        saurezCapability.setTechnology("Java");
        saurezCapability.setTitle("PSE");
        capabilities.add(saurezCapability);

        final Capability jsancaCapability = new Capability();

        jsancaCapability.setGroup("RZF");
        jsancaCapability.setName("Jonathan Sanchez");
        jsancaCapability.setTechnology("Java");
        jsancaCapability.setTitle("TA");
        capabilities.add(jsancaCapability);

        return capabilities;
    } // getCapabilityList.
} // E:O:F:CapabilityServiceImpl.
