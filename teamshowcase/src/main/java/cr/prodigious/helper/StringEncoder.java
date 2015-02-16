package cr.prodigious.helper;

import java.io.Serializable;

/**
 * Depending on the algorithm will apply an encode, for instance md5 might be
 * an implementation for id.
 *
 * The idea is that
 *
 * StringEncoder.encode("Hello Word").equals (
 *  StringEncoder.encode("Hellow Word"));
 *
 * Always must be true
 *
 * @author jsanca
 */
public interface StringEncoder extends Serializable {

    /**
     * Encode a string
     * @param string unencoded string
     * @return String encoded string
     */
    public String encode (String string);
} // E:O:F:StringEncoder
