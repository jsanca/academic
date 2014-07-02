package helianthus.core.marshall.tableresult;

import com.thoughtworks.xstream.XStream;
import helianthus.core.bean.TableResultBean;
import helianthus.core.marshall.XMLMarshallFormatter;

/**
 * Table Result XML Formatter
 *
 * Date: 6/1/14
 * Time: 12:13 PM
 * @author jsanca
 */
public class TableResultXMLMarshallFormatter extends XMLMarshallFormatter {

    static {

        // copy objects
        xstream.setMode(XStream.NO_REFERENCES);

        addAlias("table", TableResultBean.class);
        addAlias("columns", TableResultBean.class, "columnNames");
        addAlias("result", TableResultBean.class, "rowResultBeans");
        // todo: transform the list inside rowresultbeans to row

    }

} // E:O:F:TableResultXMLMarshallFormatter.
