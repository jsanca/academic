package cr.prodigious.helper;

import java.io.Serializable;

/**
 * Conversion helper
 * @author jsanca
 */
public class ConversionHelper implements Serializable {

    /**
     * Pass to Long a non-numeric string (the string is non a number)
     * @param string String
     * @return Long
     */
    public Long toLongNonNumeric (final String string) {

        return Long.valueOf(string.hashCode());
    } // toLong

} // E:O:F:ConversionHelper.
