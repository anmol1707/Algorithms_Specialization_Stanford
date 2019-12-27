import Course1.*;
import Helpers.GeneralHelpers;
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
        InputStream inputStream = GeneralHelpers.readFileAsStream("Course1TestResources/KaratsubaMultiplicationInput.txt");
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
        InputStream inputStream = GeneralHelpers.readFileAsStream("Course1TestResources/CountInversionsTest.txt");
        Scanner in = new Scanner(inputStream);
        CountInversions countInversions = new CountInversions();

        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        long expectedInversions = 2407905288L;

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(expectedInversions, countInversions.countInversionsFast(arr));

        // to avoid referencing issues as countInversionsFast sorts the array while counting inversions
        arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(expectedInversions, countInversions.countInversionsSlow(arr));
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

    @Test
    public void QuickSortTest1() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr;

        QuickSort quickSort = new QuickSort(PivotType.FirstElement);
        arr = getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.LastElement);
        arr = getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.MedianOfThree);
        arr = getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.Random);
        arr = getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void QuickSortTest2() throws FileNotFoundException {
        InputStream inputStream = GeneralHelpers.readFileAsStream("Course1TestResources/QuickSortTest.txt");
        Scanner in = new Scanner(inputStream);

        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        int[] arr;

        QuickSort quickSort = new QuickSort(PivotType.FirstElement);
        arr = getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(162085, quickSort.getComparisonCount());

        quickSort.setPivotType(PivotType.LastElement);
        arr = getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(164123, quickSort.getComparisonCount());

        quickSort.setPivotType(PivotType.MedianOfThree);
        arr = getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(138382, quickSort.getComparisonCount());
    }

    @Test
    public void RandomizedSelectionTest() {
        LinearTimeSelection selection = new LinearTimeSelection();
        int[] arr;
        int actual;

        arr = getSmallArray();
        actual = selection.randomizedSelection(arr, 3);
        Assert.assertEquals(4, actual);

        arr = getSmallArray();
        actual = selection.randomizedSelection(arr, 6);
        Assert.assertEquals(7, actual);
    }

    @Test
    public void DeterministicSelectionTest() {
        LinearTimeSelection selection = new LinearTimeSelection();
        int[] arr;
        int actual;

        arr = getSmallArray();
        actual = selection.deterministicSelection(arr, 3);
        Assert.assertEquals(4, actual);

        arr = getSmallArray();
        actual = selection.deterministicSelection(arr, 6);
        Assert.assertEquals(7, actual);
    }

    private int[] getLargeArrayForSorting(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    private int[] getSmallArray() {
        return new int[]{3, 8, 2, 5, 1, 4, 7, 6};
    }
}
