package exercises;

import java.io.Serializable;

/**
 * Based on an array of integers,
 * produce an array whose values are the product of every other integer excluding the current index.
 * Example:
 * [4, 3, 2, 8] -> [3*2*8, 4*2*8, 4*3*8, 4*3*2] -> [48, 64, 96, 24]
 *
 * This is a pretty basic algorithm so it takes around:
 *
 * O(n)*O(n)
 *
 * Date: 7/2/14
 * Time: 12:44 AM
 * @author jsanca
 */
public class ArrayProductPermutations implements Serializable {

    private static final int [] EMPTY_ARRAY = new int [] {};

    /**
     * Perform the actual permutation
     * @param originalArray array of integer
     * @return an array of integer permuted
     */
    public int [] doProductPermutation (final int [] originalArray) {

        int [] permutedArray = null;

        if (null == originalArray) {

            permutedArray = EMPTY_ARRAY;
        } else {

            permutedArray = new int [originalArray.length];

            for (int i = 0; i < originalArray.length; ++i) {

                this.permute (permutedArray, originalArray, i);
            }
        }

        return permutedArray;
    } // doProductPermutation.

    private void permute(final int[] permutedArray,
                         final int[] originalArray,
                         final int currentIndex) {

        permutedArray[currentIndex] = 1;

        for (int i = 0; i < originalArray.length; ++i) {

            if (currentIndex != i) {

                permutedArray[currentIndex] *= originalArray[i];
            }
        }
    } // permute.
} // E:O:F:ArrayProductPermutations.
