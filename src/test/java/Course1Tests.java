import Course1.Week1.KaratsubaMultiplication;
import Course1.Week1.MergeSort;
import Course1.Week2.CountInversions;
import Course1.Week2.MatrixMultiplication;
import Course1.Week3.PivotType;
import Course1.Week3.QuickSort;
import Course1.Week4.ContractedVertex;
import Course1.Week4.KargerRandomContraction;
import Course1.Week4.LinearTimeSelection;
import Helpers.GeneralHelpers;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void mergeSortTest() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = GeneralHelpers.getSmallArray();

        MergeSort mergeSort = new MergeSort();
        mergeSort.sortArray(arr);

        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void countInversionsTest() throws FileNotFoundException {
        List<Integer> numbers = GeneralHelpers.readArrayFromFile("Course1TestResources/CountInversionsTest.txt");
        long expectedInversions = 2407905288L;

        CountInversions countInversions = new CountInversions();

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
        arr = GeneralHelpers.getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.LastElement);
        arr = GeneralHelpers.getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.MedianOfThree);
        arr = GeneralHelpers.getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);

        quickSort.setPivotType(PivotType.Random);
        arr = GeneralHelpers.getSmallArray();
        quickSort.sortArray(arr);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void QuickSortTest2() throws FileNotFoundException {
        List<Integer> numbers = GeneralHelpers.readArrayFromFile("Course1TestResources/QuickSortTest.txt");
        int[] arr;

        QuickSort quickSort = new QuickSort(PivotType.FirstElement);
        arr = GeneralHelpers.getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(162085, quickSort.getComparisonCount());

        quickSort.setPivotType(PivotType.LastElement);
        arr = GeneralHelpers.getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(164123, quickSort.getComparisonCount());

        quickSort.setPivotType(PivotType.MedianOfThree);
        arr = GeneralHelpers.getLargeArrayForSorting(numbers);
        quickSort.sortArray(arr);
        Assert.assertEquals(138382, quickSort.getComparisonCount());
    }

    @Test
    public void RandomizedSelectionTest() {
        LinearTimeSelection selection = new LinearTimeSelection();
        int[] arr;
        int actual;

        arr = GeneralHelpers.getSmallArray();
        actual = selection.randomizedSelection(arr, 3);
        Assert.assertEquals(4, actual);

        arr = GeneralHelpers.getSmallArray();
        actual = selection.randomizedSelection(arr, 6);
        Assert.assertEquals(7, actual);
    }

    @Test
    public void DeterministicSelectionTest() {
        LinearTimeSelection selection = new LinearTimeSelection();
        int[] arr;
        int actual;

        arr = GeneralHelpers.getSmallArray();
        actual = selection.deterministicSelection(arr, 3);
        Assert.assertEquals(4, actual);

        arr = GeneralHelpers.getSmallArray();
        actual = selection.deterministicSelection(arr, 6);
        Assert.assertEquals(7, actual);
    }

    @Test
    public void KargerRandomContractionTest1() throws FileNotFoundException {
        int minCuts = Integer.MAX_VALUE;
        Map<ContractedVertex, List<ContractedVertex>> adjacencyList = GeneralHelpers.readAdvancedDirectedGraphFromFile("Course1TestResources/KargerRandomContractionTest1.txt");
        for (int i = 0; i < 100; i++) {
            Map<ContractedVertex, List<ContractedVertex>> duplicateAdjacencyList = duplicateAdjacencyList(adjacencyList);
            KargerRandomContraction randomContraction = new KargerRandomContraction(duplicateAdjacencyList);
            int cuts = randomContraction.getMinimumCut();
            minCuts = Math.min(minCuts, cuts);
        }
        Assert.assertEquals(2, minCuts);
    }

    @Test
    public void KargerRandomContractionTest2() throws FileNotFoundException, InterruptedException {
        AtomicInteger minCuts = new AtomicInteger(Integer.MAX_VALUE);
        final Map<ContractedVertex, List<ContractedVertex>> adjacencyList = GeneralHelpers.readAdvancedDirectedGraphFromFile("Course1TestResources/KargerRandomContractionTest2.txt");
        final int numberOfProcessors = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfProcessors);

        for (int i = 0; i < numberOfProcessors * 10; i++) {
            executorService.execute(() -> {
                Map<ContractedVertex, List<ContractedVertex>> duplicateAdjacencyList = duplicateAdjacencyList(adjacencyList);
                KargerRandomContraction randomContraction = new KargerRandomContraction(duplicateAdjacencyList);
                int cuts = randomContraction.getMinimumCut();
                minCuts.set(Math.min(minCuts.get(), cuts));
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        Assert.assertEquals(17, minCuts.get());
    }

    private Map<ContractedVertex, List<ContractedVertex>> duplicateAdjacencyList(Map<ContractedVertex, List<ContractedVertex>> adjacencyList) {
        Map<ContractedVertex, List<ContractedVertex>> duplicateAdjacencyList = new HashMap<>();
        for (ContractedVertex vertex : adjacencyList.keySet()) {
            List<ContractedVertex> connections = adjacencyList.get(vertex);

            ContractedVertex dupVertex = duplicateVertex(vertex);
            List<ContractedVertex> dupConnections = duplicateConnections(connections);
            duplicateAdjacencyList.put(dupVertex, dupConnections);
        }
        return duplicateAdjacencyList;
    }

    private List<ContractedVertex> duplicateConnections(List<ContractedVertex> connections) {
        List<ContractedVertex> dupConnections = new ArrayList<>();
        for (ContractedVertex connection : connections) {
            dupConnections.add(duplicateVertex(connection));
        }
        return dupConnections;
    }

    private ContractedVertex duplicateVertex(ContractedVertex originalVertex) {
        ContractedVertex dupVertex = new ContractedVertex();
        dupVertex.addVertices(originalVertex.getVertices());
        return dupVertex;
    }
}
