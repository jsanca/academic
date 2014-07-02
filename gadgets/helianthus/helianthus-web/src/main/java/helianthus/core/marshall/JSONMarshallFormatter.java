package helianthus.core.marshall;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import helianthus.core.bean.TableResultBean;

import java.io.OutputStream;

/**
 * Json formatter
 * Date: 5/11/14
 * Time: 1:09 PM
 * @author jsanca
 */
public class JSONMarshallFormatter implements MarshallFormatter {

    protected static XStream xstream = //new XStream(new JettisonMappedXmlDriver());
            new XStream(new JsonHierarchicalStreamDriver());

    /**
     * Process the response
     *
     * @param outputStream    OutputStream
     * @param tableResultBean TableResultBean
     */
    @Override
    public void process(
            final OutputStream outputStream,
            final TableResultBean tableResultBean) {


        xstream.toXML(tableResultBean, outputStream);
    } // process.

    /**
     * Add an Alias to the mapper
     * @param nodeName   String
     * @param clazz      Class
     * @param fieldName String
     */
    public static void addAlias (final String nodeName,
                                 final Class clazz,
                                 final String fieldName) {

        xstream.aliasField(nodeName, clazz, fieldName);
    } // addAlias.

    /**
     * Add an Alias to the mapper
     * @param nodeName   String
     * @param clazz      Class
     */
    public static void addAlias (String nodeName, Class clazz) {

        xstream.alias(nodeName, clazz);
    } // addAlias.


} // E:O:F:JsonMarshallFormatter.
