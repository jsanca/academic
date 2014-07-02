package helianthus.core.access.impl.db;

import java.io.Serializable;

/**
 * Data base utils methods.
 *
 * Date: 5/6/14
 * Time: 10:33 PM
 * @author jsanca
 */
public class DataBaseUtils implements Serializable {

    /**
     * Close a list of Auto Closeable keeping the argument order
     * @param closeables AutoCloseable
     */
    public void closeQuiet (final AutoCloseable... closeables) {

        for (AutoCloseable closeable : closeables) {
            if (null != closeable) {

                try {

                    closeable.close();
                } catch (Exception e) {
                    /** quiet */
                }
            }
        }
    } // closeQuiet.
} // E:O:F:DataBaseUtils.
