package cr.prodigious.helper;

import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.dao.DataBaseHelper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author jsanca
 */
public class MD5StringEncoderImplTest
        extends TestCase {

    public MD5StringEncoderImplTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MD5StringEncoderImplTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        final String test = "Hellow World";
        final StringEncoder stringEncoder =
                new MD5StringEncoderImpl();


        assertTrue(stringEncoder.encode(test).equals(stringEncoder.encode(test)));
        assertFalse(stringEncoder.encode(test).equals(stringEncoder.encode("Something else")));
    }
}
