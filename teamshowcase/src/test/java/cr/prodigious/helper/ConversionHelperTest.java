package cr.prodigious.helper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author jsanca
 */
public class ConversionHelperTest
        extends TestCase {

    public ConversionHelperTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ConversionHelperTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        final String test = "anemail@ahost.com";
        final ConversionHelper conversionHelper =
                new ConversionHelper();


        System.out.println(conversionHelper.toLongNonNumeric(test));
        //assertTrue(stringEncoder.encode(test).equals(stringEncoder.encode(test)));
        //assertFalse(stringEncoder.encode(test).equals(stringEncoder.encode("Something else")));
    }
}
