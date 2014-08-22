package tagliparser.converter;

/**
 * Simple trim string converter
 *
 * Date: 8/21/14
 * Time: 5:22 PM
 * @author jsanca
 */
public class StringConverter implements Converter<String> {

    public final static StringConverter INSTANCE = new StringConverter();

    private final static String EMPTY = "";

    /**
     * Convert a line into a T object, null if it is a blank line
     *
     * @param line String
     * @return T
     */
    @Override
    public String convert(String line) {

        String rline = EMPTY;

        if (null != line) {

            rline = line.trim();

            if (0 == rline.length()) { //blank line

                rline = null;
            }
        }

        return rline;
    } // convert
} // E:O:F:StringConverter.
