package tagliparser.converter;

import java.io.Serializable;

/**
 *
 * Convert from a string line to a T object
 *
 * Date: 8/21/14
 * Time: 4:54 PM
 * @author jsanca
 */
public interface Converter<T> extends Serializable {

    /**
     * Convert a line into a T object
     * @param line String
     * @return T
     */
    public abstract T convert(String line);

} // E:O:F:Converter.
