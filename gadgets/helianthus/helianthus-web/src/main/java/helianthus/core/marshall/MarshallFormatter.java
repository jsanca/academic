package helianthus.core.marshall;

import helianthus.core.bean.TableResultBean;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * Marshall Formatter
 *
 * Date: 5/11/14
 * Time: 1:01 PM
 * @author jsanca
 */
public interface MarshallFormatter extends Serializable {

    /**
     * Process the response
     * @param outputStream  OutputStream
     * @param tableResultBean  TableResultBean
     */
    void process (OutputStream outputStream, TableResultBean tableResultBean);

} // E:O:F:MarshallFormatter.
