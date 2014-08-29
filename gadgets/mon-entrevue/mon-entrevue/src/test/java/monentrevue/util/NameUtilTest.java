package monentrevue.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

/**
 * Test Name Util
 *
 * Date: 8/28/14
 * Time: 2:26 PM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class NameUtilTest {

    @Test
    public void serializerTest() throws IOException {

        final String baseName = "fileName";

        final String dateName =
                NameUtil.createDateName(baseName, ".txt");

        org.junit.Assert.assertNotNull(dateName);
    }

} // NameUtilTest.
