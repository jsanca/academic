package helianthus.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream Utils
 *
 * Date: 5/20/14
 * Time: 7:20 PM
 * @author jsanca
 */
public class InputStreamUtils {

    /**
     * Get an input stream from class path or file system
     * @param path String
     * @return  InputStream
     * @throws IOException
     */
    public static InputStream getInputStream (final String path) throws IOException {

        InputStream inputStream = null;

        inputStream = getInputStreamFromClassPath(path);

        if (null == inputStream) {

            inputStream =
                    getInputStreamFromFileSystem(path);
        }

        return inputStream;
    } // getInputStream

    /**
     * Get an input stream from the class path
     * @param path String
     * @return InputStream
     */
    public static InputStream getInputStreamFromClassPath (final String path) {

        return InputStreamUtils.class
                         .getClassLoader()
                            .getResourceAsStream(path);
    } // getInputStreamFromClassPath.

    /**
     * Get an input stream from file system
     * @param path String
     * @return InputStream
     * @throws FileNotFoundException
     */
    public static InputStream getInputStreamFromFileSystem (final String path) throws FileNotFoundException {

        return new FileInputStream(path);
    } // getInputStreamFromClassPath.


} // E:O:F:InputStreamUtils.
