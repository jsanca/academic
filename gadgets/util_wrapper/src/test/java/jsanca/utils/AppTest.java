package jsanca.utils;

import jsanca.utils.strategy.WriterInputStreamStrategy;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException {


        InputStream inputStream = null;
        Writer writer = new StringWriter();
        InputStream inputStreamWrapper = null;

        inputStream = new ByteArrayInputStream(BIG_STRING.getBytes());
        inputStreamWrapper =
                new InputStreamWrapper(inputStream, new WriterInputStreamStrategy(writer, false));

        copy(new InputStreamReader(inputStreamWrapper), System.out);

        assertTrue(BIG_STRING.equals(writer.toString()));
    }

    public static int copy(Reader input, PrintStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(Reader input, PrintStream output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.print(new String(buffer, 0, n));
            count += n;
        }
        return count;
    }

    private static final int DEFAULT_BUFFER_SIZE = 256;

    private static final String BIG_STRING = "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed " +
            "this is an example of a big string to test this input stream wrapper basically the string read must be the same of a string printed ";
}
