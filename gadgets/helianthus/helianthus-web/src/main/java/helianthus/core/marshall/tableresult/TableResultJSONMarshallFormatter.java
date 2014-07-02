package helianthus.core.marshall.tableresult;

import com.thoughtworks.xstream.XStream;
import helianthus.core.bean.TableResultBean;
import helianthus.core.marshall.JSONMarshallFormatter;

/**
 * Table Result Json Formatter
 *
 * Date: 6/1/14
 * Time: 11:26 AM
 * @author jsanca
 */
public class TableResultJSONMarshallFormatter extends JSONMarshallFormatter {

    static {

        // copy objects
        xstream.setMode(XStream.NO_REFERENCES);

        addAlias("table", TableResultBean.class);
        addAlias("columns", TableResultBean.class, "columnNames");
        addAlias("result", TableResultBean.class, "rowResultBeans");

    }

} // E:O:F:TableResultJSONMarshallFormatter.
