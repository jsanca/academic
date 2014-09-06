package monentrevue.util;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * File util IO operations
 *
 * Date: 9/3/14
 * Time: 3:13 PM
 * @author jsanca
 */
public class FileUtil {

    /**
     * Store the file into a directory
     * @param filePathName File this must be a complete path
     * @param inputStream  InputStream
     * @throws IOException
     */
    public static void store (final java.io.File filePathName,
                       final InputStream inputStream) throws IOException {

        OutputStream outputStream = null;

        try {

            outputStream =
                    new FileOutputStream(filePathName);

            IOUtils.
                    copy(inputStream, outputStream);
        } finally {

            IOUtils.closeQuietly(outputStream);
        }
    } // store.

} // E:O:F:FileUtil.
