package exercises;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;

/**
 * Test for ArrayProductPermutations

 * Date: 7/2/14
 * Time: 12:44 AM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class ArrayProductPermutationsTest implements Serializable {

    @Test
    public void doProductPermutationTest () {

        final ArrayProductPermutations permutations =
                new ArrayProductPermutations();

        int [] testArray = new int [] { 4, 3, 2, 8 };

        int [] resultArray =
                permutations.doProductPermutation(testArray);

        assertEquals(testArray.length, resultArray.length);

        int index = 0;

        assertEquals(3*2*8, resultArray[index++]);
        assertEquals(4*2*8  , resultArray[index++]);
        assertEquals(4*3*8, resultArray[index++]);
        assertEquals(4*3*2, resultArray[index++]);
    } // doProductPermutation.


} // E:O:F:ArrayProductPermutations.
