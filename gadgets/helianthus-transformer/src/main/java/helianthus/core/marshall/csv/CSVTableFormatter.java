package helianthus.core.marshall.csv;

import helianthus.core.FormatterException;
import helianthus.core.marshall.html.config.HTMLConfig;
import helianthus.core.util.BeanUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * CSV Table Formatter formatter based on open csv
 *
 * Date: 5/11/14
 * Time: 1:09 PM
 * @author jsanca
 */
public class CSVTableFormatter implements CSVFormatter {

    private HashMap<HTMLConfig, String> configMap =
            new HashMap<HTMLConfig, String>();

    public CSVTableFormatter() {
    }

    public CSVTableFormatter(final HashMap<HTMLConfig, String> configMap) {

        this.configMap.putAll(configMap);
    }

    /**
     * Transform an object to a table.
     *
     * @param outputStream    OutputStream
     * @param bean Object
     */
    @Override
    public void formatter(
            final OutputStream outputStream,
            final Object bean) {

        this.formatter(new BufferedWriter
                (new OutputStreamWriter(outputStream)), bean);
    } // formatter.

    /**
     * formatter an object
     *
     * @param writer Writer
     * @param bean   Object
     */
    @Override
    public void formatter(
            final Writer writer,
            final Object bean)  {

        final Map<String, String> beanDescriptionMap =
                BeanUtils.INSTANCE.describeBean(bean);

        String tableHeaderPropertyName = null;
        String tableBodyPropertyName = null;

        try {

            tableHeaderPropertyName =
                    this.configMap.get(HTMLConfig.TABLE_HEADER);

            tableBodyPropertyName =
                    this.configMap.get(HTMLConfig.TBODY);

            this.beginTable(writer);

            this.processHeader
                    (tableHeaderPropertyName,
                            bean, writer);

            this.processBody(tableBodyPropertyName,
                    bean, writer);

            this.endTable(writer);
        } catch (Exception e) {

            throw new FormatterException(e);
        }
    } // formatter.

    protected void processBody(
            final String tableBodyPropertyName,
            final Object bean,
            final Writer writer) throws IOException {

        Collection collectionBody = null;
        Collection collectionRow  = null;

        if (null != tableBodyPropertyName) {

            collectionBody =
                    BeanUtils.INSTANCE.
                            getPropertyCollectionBean
                                    (bean, tableBodyPropertyName);

            for(Object rowItem : collectionBody) {

                this.beginTableRow(writer);

                collectionRow =
                        (Collection)rowItem;

                this.processBodyRow (collectionRow, writer);

                this.endTableRow(writer);
            }
        }
    } // processBody.

    protected void processBodyRow
            (final Collection collectionRow,
             final Writer writer) throws IOException {

        Object tdObject = null;
        final String tdProperty =
                this.configMap.get(HTMLConfig.TD);

        for (Object tdObjectItem : collectionRow) {

            tdObject = tdObjectItem;

            if (null != tdProperty) {

                tdObject =
                        BeanUtils.INSTANCE.getPropertyBean
                                (tdObject, tdProperty);

                if (null == tdObject) {

                    tdObject = tdObjectItem;
                }
            }

            this.processBodyRow(tdObject, writer);
        }
    } // processBodyRow.

    protected void processBodyRow(
            final Object tdObject, final Writer writer) throws IOException {

        this.beginTableData(writer);

        if (null != tdObject) {

            writer.write(tdObject.toString());
        }

        this.endTableData(writer);
    } // processBodyRow.

    protected void processHeader(
            final String tableHeaderPropertyName,
            final Object bean,
            final Writer writer) throws IOException {

        Object thObject = null;
        Collection collection = null;
        final String thProperty =
                this.configMap.get(HTMLConfig.TH);

        if (null != tableHeaderPropertyName) {

            collection =
                    BeanUtils.INSTANCE.getPropertyCollectionBean
                            (bean, tableHeaderPropertyName);

            if (null != collection) {

                this.beginTableRow(writer);

                for (Object thObjectItem : collection) {

                    thObject = thObjectItem;

                    if (null != thProperty) {

                        thObject =
                            BeanUtils.INSTANCE.getPropertyBean
                                    (thObject, thProperty);

                        if (null == thObject) {

                            thObject = thObjectItem;
                        }
                    }

                    this.processHeader(thObject, writer);
                }

                this.endTableRow(writer);
            }
        }
    } // processHeader.

    protected void processHeader(final Object thObject, final Writer writer) throws IOException {

        this.beginTableHeader(writer);

        if (null != thObject) {

            writer.write(thObject.toString());
        }

        this.endTableHeader(writer);
    } // processHeader.

    protected void beginTableData(final Writer writer) throws IOException {

        writer.write("<td>");

    } // beginTableData.

    protected void endTableData(final Writer writer) throws IOException {

        writer.write("</td>");
    } // endTableData.

    protected void beginTableRow(final Writer writer) throws IOException {

        writer.write("<tr>");

    } // beginTableRow.

    protected void endTableRow(final Writer writer) throws IOException {

        writer.write("</tr>");
    } // beginTableRow.

    protected void beginTableHeader(final Writer writer) throws IOException {

        writer.write("<th>");

    } // beginTableHeader.

    protected void endTableHeader(final Writer writer) throws IOException {

        writer.write("</th>");
    } // endTableHeader.

    protected void beginTable(final Writer writer) throws IOException {

        writer.write("<table>");

    } // beginTable.

    protected void endTable(final Writer writer) throws IOException {

        writer.write("</table>");
    } // endTable.

} // E:O:F:JsonMarshallFormatter.
