package monentrevue.util;

import java.util.Date;

/**
 * Encapsulate utils method for name
 *
 * Date: 8/28/14
 * Time: 9:21 AM
 * @author jsanca
 */
public class NameUtil {

    /**
     * Returns a baseName + timestap + ext
     *
     * @param baseName String base name to attach the time stamp
     * @param ext      String fileExtension must be not null.
     * @return String return a file name with a date as a posfix.
     */
    public static String createDateName (final String baseName,
                                         final String ext) {

        final String datePosFix = new Date().getTime() + ext;
        final String fileName   = new StringBuilder(baseName)
                .append("-").append(datePosFix).toString();

        return fileName;
    } // createDateName.

} // E:O:F:NameUtil.
