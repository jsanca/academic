
import jsanca.artjava.cap2.DividedByZeroException;
import jsanca.artjava.cap2.NonExpressionDefinedException;
import jsanca.artjava.cap2.Parser;
import jsanca.artjava.cap2.SintaxErrorException;
import jsanca.artjava.cap2.UnbalanceParenthesesException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

/**
 * Parser test
 * Date: 8/12/14
 * Time: 5:21 PM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class ParserTest {

    @Test
    public void invalidExpressionsTest() throws IOException {

        double result = 0;

        try {

            result = Parser.eval(null);
            org.junit.Assert.fail("Should show NonExpressionDefinedException");
        } catch (NonExpressionDefinedException e) {

            // (Y) ok
        }

        try {

            result = Parser.eval("");
            org.junit.Assert.fail("Should show NonExpressionDefinedException");
        } catch (NonExpressionDefinedException e) {

            // (Y) ok
        }

        try {

            result = Parser.eval("*24-6");
            org.junit.Assert.fail("Should show SintaxErrorException");
        } catch (SintaxErrorException e) {

            // (Y) ok
        }

        try {

            result = Parser.eval("(2*1)(45-6+1)");
            org.junit.Assert.fail("Should show SintaxErrorException");
        } catch (SintaxErrorException e) {

            // (Y) ok
        }

        try {

            result = Parser.eval("(2*1)+(45-6+1");
            org.junit.Assert.fail("Should show UnbalanceParenthesesException");
        } catch (UnbalanceParenthesesException e) {

            // (Y) ok
        }

        try {

            result = Parser.eval("(2*1)+(45-6+1)+1024/0");
            org.junit.Assert.fail("Should show DividedByZeroException");

        } catch (DividedByZeroException ex) {

            // (Y) ok
        }

    } // invalidExpressionsTest


    @Test
    public void validExpressionsTest() throws IOException {

        double result = 0;
        double expectedResult = 4.0;

        result = Parser.eval("2+2");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 10;
        result = Parser.eval("12-2");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = -2;
        result = Parser.eval("10-12");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 6;
        result = Parser.eval("2*3");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 6;
        result = Parser.eval("2*3");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 4;
        result = Parser.eval("12/3");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 8;
        result = Parser.eval("2^3");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = 110;
        result = Parser.eval("(2^3) + 10 * (100*0.10) + 2");
        org.junit.Assert.assertTrue(expectedResult == result);

        expectedResult = -110;
        result = Parser.eval("-1*(2^3) + -10 * (100*0.10) - 2");
        org.junit.Assert.assertTrue(expectedResult == result);

    } // invalidExpressionsTest
} // ParserTest.
