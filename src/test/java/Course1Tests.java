import Course1.CountInversions;
import Course1.KaratsubaMultiplication;
import Course1.MatrixMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course1Tests {

    @Test
    public void karatsubaMultiplicationTest() throws FileNotFoundException {
        InputStream inputStream = HelperFunctions.readFileAsStream("Course1TestResources/KaratsubaMultiplicationInput.txt");
        Scanner in = new Scanner(inputStream);
        KaratsubaMultiplication multiplication = new KaratsubaMultiplication();

        while (in.hasNextLine()) {
            String[] input = in.nextLine().split(",");

            BigInteger result = multiplication.doKaratsuba(input[0], input[1]);
            Assert.assertEquals(input[2], result.toString());
        }
    }

    @Test
    public void countInversionsTest() throws FileNotFoundException {
        InputStream inputStream = HelperFunctions.readFileAsStream("Course1TestResources/CountInversionsTest.txt");
        Scanner in = new Scanner(inputStream);
        CountInversions countInversions = new CountInversions();

        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(2407905288L, countInversions.countInversionsFast(arr));

        // to avoid referencing issues as countInversionsFast sorts the array while counting inversions
        arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(2407905288L, countInversions.countInversionsSlow(arr));
    }

    @Test
    public void MatrixMultiplicationTest() {
        int[][] arr1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] arr2 = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};

        int[][] expectedResult = {{250, 260, 270, 280}, {618, 644, 670, 696}, {986, 1028, 1070, 1112}, {1354, 1412, 1470, 1528}};

        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        Assert.assertArrayEquals(expectedResult, matrixMultiplication.matrixMultiplicationSlow(arr1, arr2));
        Assert.assertArrayEquals(expectedResult, matrixMultiplication.strassenMatrixMultiplication(arr1, arr2));
    }
}
