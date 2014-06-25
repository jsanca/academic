package helianthus.core.marshall;

import helianthus.core.MarshallFormatterException;
import helianthus.core.bean.TableResultBean;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Binary formatter based on the standard java serialization.
 *
 * Date: 6/18/14
 * Time: 11:01 PM
 * @author jsanca
 */
public class BinaryMarshallFormater implements MarshallFormatter  {


    /**
     * Process the response
     *
     * @param outputStream    OutputStream
     * @param tableResultBean TableResultBean
     */
    @Override
    public void process(final OutputStream outputStream,
                        final TableResultBean tableResultBean) {


        ObjectOutputStream objectStream = null;

        try {

            objectStream =
                    new ObjectOutputStream(outputStream);

            objectStream.writeObject(tableResultBean);
            objectStream.flush();
        } catch (IOException e) {

            throw new MarshallFormatterException(e);
        }
    } // process.
} // E:O:F:BinaryMarshallFormater.
