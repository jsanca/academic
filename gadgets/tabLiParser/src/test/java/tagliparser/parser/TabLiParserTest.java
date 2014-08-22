package tagliparser.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import tagliparser.bean.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Test
 * Date: 8/21/14
 * Time: 6:22 PM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class TabLiParserTest {

    @Test
    public void parserTest1() throws IOException {

        final TabLiParser tabLiParser =
                TabLiParser.INSTANCE;

        final String file = "test1.txt";
        InputStream stream = null;

        stream =
                this.getClass()
                        .getClassLoader().
                        getResourceAsStream(file);

        final List<Item<String>> items =
                tabLiParser.parser(stream);

        org.junit.Assert.assertNotNull(items);
        org.junit.Assert.assertTrue(items.size() == 7);

        org.junit.Assert.assertTrue("A".equals(items.get(0).getValue()));
        org.junit.Assert.assertTrue("B".equals(items.get(1).getValue()));
        org.junit.Assert.assertTrue("C".equals(items.get(2).getValue()));
        org.junit.Assert.assertTrue("D".equals(items.get(3).getValue()));
        org.junit.Assert.assertTrue("E".equals(items.get(4).getValue()));
        org.junit.Assert.assertTrue("F".equals(items.get(5).getValue()));
        org.junit.Assert.assertTrue("J".equals(items.get(6).getValue()));

    } // parser.


    @Test
    public void parserTest2() throws IOException {

        final TabLiParser tabLiParser =
                TabLiParser.INSTANCE;

        final String file = "test2.txt";
        InputStream stream = null;

        stream =
                this.getClass()
                        .getClassLoader().
                        getResourceAsStream(file);

        final List<Item<String>> items =
                tabLiParser.parser(stream);

        org.junit.Assert.assertNotNull(items);
        org.junit.Assert.assertTrue(items.size() == 6);

        org.junit.Assert.assertTrue("A".equals(items.get(0).getValue()));

        org.junit.Assert.assertTrue(items.get(0).getList().size() == 2);
        org.junit.Assert.assertTrue("A1".equals(items.get(0).getList().get(0).getValue()));
        org.junit.Assert.assertTrue("A2".equals(items.get(0).getList().get(1).getValue()));

        org.junit.Assert.assertTrue("B".equals(items.get(1).getValue()));


        org.junit.Assert.assertTrue("C".equals(items.get(2).getValue()));

        org.junit.Assert.assertTrue(items.get(2).getList().size() == 2);
        org.junit.Assert.assertTrue("C1".equals(items.get(2).getList().get(0).getValue()));
        org.junit.Assert.assertTrue("C2".equals(items.get(2).getList().get(1).getValue()));
        org.junit.Assert.assertTrue(items.get(2).getList().get(1).getList().size() == 1);
        org.junit.Assert.assertTrue("C3".equals(items.get(2).getList().get(1).getList().get(0).getValue()));


        org.junit.Assert.assertTrue("D".equals(items.get(3).getValue()));

        org.junit.Assert.assertTrue(items.get(3).getList().size() == 2);
        org.junit.Assert.assertTrue("D1".equals(items.get(3).getList().get(0).getValue()));
        org.junit.Assert.assertTrue("D2".equals(items.get(3).getList().get(1).getValue()));
        org.junit.Assert.assertTrue(items.get(3).getList().get(1).getList().size() == 1);
        org.junit.Assert.assertTrue("D21".equals(items.get(3).getList().get(1).getList().get(0).getValue()));
        org.junit.Assert.assertTrue(items.get(3).getList().get(1).getList().get(0).getList().size() == 1);
        org.junit.Assert.assertTrue("D211".equals(items.get(3).getList().get(1).getList().get(0).getList().get(0).getValue()));


        org.junit.Assert.assertTrue("E".equals(items.get(4).getValue()));

        org.junit.Assert.assertTrue(items.get(5).getList().size() == 1);
        org.junit.Assert.assertTrue("F".equals(items.get(5).getValue()));

        org.junit.Assert.assertTrue(items.get(5).getList().get(0).getList().size() == 1);
        org.junit.Assert.assertTrue("F1".equals(items.get(5).getList().get(0).getValue()));

        org.junit.Assert.assertTrue(items.get(5).getList().get(0).getList().get(0).getList().size() == 1);
        org.junit.Assert.assertTrue("F2".equals(items.get(5).getList().get(0).getList().get(0).getValue()));

        org.junit.Assert.assertTrue(items.get(5).getList().get(0).getList().get(0).getList().get(0).getList().size() == 1);
        org.junit.Assert.assertTrue("F3".equals(items.get(5).getList().get(0).getList().get(0).getList().get(0).getValue()));

        org.junit.Assert.assertTrue(items.get(5).getList().get(0).getList().get(0).getList().get(0).getList().get(0).getList().size() == 1);
        org.junit.Assert.assertTrue("F4".equals(items.get(5).getList().get(0).getList().get(0).getList().get(0).getList().get(0).getValue()));

        org.junit.Assert.assertTrue("F5".equals(items.get(5).getList().get(0).getList().get(0).getList().get(0).getList().get(0).getList().get(0).getValue()));

    } // parser.

} // E:O:F:TabLiParserTest.
