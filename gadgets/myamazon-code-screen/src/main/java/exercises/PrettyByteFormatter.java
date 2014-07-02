package exercises;

import java.io.Serializable;

/**
 * Defines a Pretty Byte formatter, based on an integer value (Number of bytes)
 * convert to a string easy to read for human being
 *
 * Examples:
 *
 * 341B = 341B
 o 34200B = 34.2K
 o 5910000B = 5.91M
 o 1000000000B = 1G
 *
 * Note: For this problem, 1000 bytes = 1 KB, and so on
 *
 * Date: 7/1/14
 * Time: 11:57 PM
 * @author jsanca
 */
public interface PrettyByteFormatter extends Serializable {

    /**
     * Format the bytes to a human being readable String
     * @param bytes  Integer
     * @return String
     */
    public abstract String format (final Integer bytes);
} // E:O:F:PrettyByteFormatter.
