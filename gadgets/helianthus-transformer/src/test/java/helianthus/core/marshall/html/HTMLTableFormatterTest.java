package helianthus.core.marshall.html;

import helianthus.core.marshall.html.config.HTMLConfig;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: boszdigital1
 * Date: 5/17/14
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */

public class HTMLTableFormatterTest {

    @Test
    public void formatterTest () {

        final HashMap<HTMLConfig, String> config =
                new HashMap<HTMLConfig, String>();

        config.put(HTMLConfig.TABLE_HEADER, "columns");
        config.put(HTMLConfig.TH, "name");

        config.put(HTMLConfig.TBODY, "data");
        config.put(HTMLConfig.TD, "value");

        final HTMLFormatter htmlFormatter =
                new HTMLTableFormatter(config);


        final Table table = new Table();

        final ArrayList<Column> columns =
                new ArrayList<Column>();

        Column column = new Column();
        column.setId(1);
        column.setName("Field 1");
        columns.add(column);

        column = new Column();
        column.setId(2);
        column.setName("Field 2");
        columns.add(column);

        column = new Column();
        column.setId(3);
        column.setName("Field 3");
        columns.add(column);

        final ArrayList<ArrayList<Data>> data =
                new ArrayList<ArrayList<Data>>();

        ArrayList<Data> dataArrayList =
                new ArrayList<Data>();

        Data dataItem = new Data();
        dataItem.setId(1);
        dataItem.setValue("Value 11");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(2);
        dataItem.setValue("Value 12");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(3);
        dataItem.setValue("Value 13");
        dataArrayList.add(dataItem);

        data.add(dataArrayList);

        dataArrayList =
                new ArrayList<Data>();

        dataItem = new Data();
        dataItem.setId(4);
        dataItem.setValue("Value 21");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(5);
        dataItem.setValue("Value 22");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(6);
        dataItem.setValue("Value 23");
        dataArrayList.add(dataItem);

        data.add(dataArrayList);

        dataArrayList =
                new ArrayList<Data>();

        dataItem = new Data();
        dataItem.setId(7);
        dataItem.setValue("Value 31");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(8);
        dataItem.setValue("Value 32");
        dataArrayList.add(dataItem);

        dataItem = new Data();
        dataItem.setId(9);
        dataItem.setValue("Value 33");
        dataArrayList.add(dataItem);

        data.add(dataArrayList);

        table.setColumns(columns);
        table.setData(data);

        final StringWriter writer =
                new StringWriter();

        htmlFormatter.formatter(writer, table);

        org.junit.Assert.assertEquals
                ("<table><tr><th>Field 1</th><th>Field 2</th><th>Field 3</th></tr><tr><td>Value 11</td><td>Value 12</td><td>Value 13</td></tr><tr><td>Value 21</td><td>Value 22</td><td>Value 23</td></tr><tr><td>Value 31</td><td>Value 32</td><td>Value 33</td></tr></table>",
                        writer.toString());
    }
}
