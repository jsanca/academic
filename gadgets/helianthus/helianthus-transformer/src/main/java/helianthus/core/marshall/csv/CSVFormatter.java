package helianthus.core.marshall.csv;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

/**
 * CSV Formatter
 *
 * Date: 5/11/14
 * Time: 1:01 PM
 * @author jsanca
 */
public interface CSVFormatter extends Serializable {

    /**
     * formatter an object
     * @param outputStream  OutputStream
     * @param bean  Object
     */
    void formatter(OutputStream outputStream, Object bean);

    /**
     * formatter an object
     * @param writer  Writer
     * @param bean  Object
     */
    void formatter(Writer writer, Object bean);

} // E:O:F:HTMLFormatter.
