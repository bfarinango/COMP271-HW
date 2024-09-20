
import java.util.Arrays;
public class GradeSchoolMultiplication {

    private static final int DEFAULT_BASE = 10;

    public static void main(String[] args) {
        int [] firstArr = {1, 2, 3};
        int [] secondArr = {4, 5, 6};
        System.out.print(Arrays.toString(multiply(firstArr, secondArr)));
    }

    /**
     * Multiplies two integer numbers represented as arrays of single digits and returns their product as a single digit array.
     *
     * @param x    An array of integers representing the digits of the first number.
     * @param y    An array of integers representing the digits of the second number.

     * @return     A single digit array of integers representing the digits of the product of x and y.
     * 
     */
    public static int[] multiply (final int[] x, final int[] y, final int base) {
        int xLen = x.length;
        int yLen = y.length;

        // Initializes result array with the max possible length of the product
        int[] result = new int[xLen + yLen];

        // Iterates through x array from right to left
        for (int i = xLen - 1; i >= 0; i--) {
            //iterates through y array from right to left
            for (int j = yLen - 1; j >= 0; j--) {
                // multiplies each pair of digits
                int product = x[i] * y[j];
                // adds product to existing value in the result array for carrying over
                int sum = result[i + j + 1] + product;

                // Stores the ones digit of the sum in current position
                result[i + j + 1] = sum % base;
                // carries over the tens digit to next position
                result[i + j] += sum / base;
            }
        }

        // removes leading zeros
        int start = 0;
        // Finds the index of the first non-zero digit
        while (start < result.length - 1 && result[start] == 0) {
            start++;
        }

        // creates new array with correct size without leading 0s
        int[] trimmedResult = new int[result.length - start];
        // Copies significant digits to new array
        for (int i = 0; i < trimmedResult.length; i++) {
            trimmedResult[i] = result[start + i];
        }

        return trimmedResult;
    }


    public static int[] multiply(final int[] x, final int[] y) {
        return multiply(x, y, DEFAULT_BASE);
    } 
}