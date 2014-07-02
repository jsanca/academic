package exercises;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *
 * Date: 7/2/14
 * Time: 12:00 AM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class SimplePrettyByteFormatterTest {

    @Test
    public void formatTest() throws IOException {

       final PrettyByteFormatter prettyByteFormatter =
               new SimplePrettyByteFormatter();

        int test1 = 341;
        //int test1 = 2000000000;
        int test2 = 34200;
        int test3 = 5910000;
        int test4 = 1000000000;

        String testResult =
                prettyByteFormatter.format(test1);

        assertEquals("341B", testResult);

        testResult =
                prettyByteFormatter.format(test2);

        assertEquals("34.2K", testResult);

        testResult =
                prettyByteFormatter.format(test3);

        assertEquals("5.91M", testResult);

        testResult =
                prettyByteFormatter.format(test4);

        assertEquals("1G", testResult);
    } // formatTest.

} // E:O:F:SimplePrettyByteFormatterTest.
