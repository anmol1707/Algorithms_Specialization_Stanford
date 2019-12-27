package Course1;

import Helpers.RandomizedHelpers;

import java.util.Arrays;

public class LinearTimeSelection {

    private RandomizedHelpers helpers;
    private final int ELEMENTS_PER_GROUP = 5;

    public LinearTimeSelection() {
        this.helpers = new RandomizedHelpers();
    }

    // average runtime - O(n), worst case runtime -  O(n^2)
    public int randomizedSelection(int[] arr, int i) {
        int length = arr.length;
        return processInputRandomly(arr, i, 0, length - 1);
    }

    private int processInputRandomly(int[] arr, int i, int start, int end) {
        if(start == end) {
            return arr[start];
        }

        int pivot = helpers.getRandomBetweenNumbers(start, end);
        pivot = helpers.partition(arr, start, end, pivot);
        int pivotInRange = pivot - start;

        if(pivotInRange == i) {
            return arr[pivot];
        } else if (pivotInRange < i) {
            return processInputRandomly(arr, i - (pivotInRange + 1), pivot + 1, end);
        } else {
            return processInputRandomly(arr, i , start, pivot - 1);
        }
    }

    // Runtime - O(n)
    public int deterministicSelection(int[] arr, int i) {
        int length = arr.length;
        return processInputDeterministically(arr, i, 0, length - 1);
    }

    private int processInputDeterministically(int[] arr, int i, int start, int end) {
        if(start == end) {
            return arr[start];
        }

        int[] middleElements = getMiddleElements(arr, start, end);
        int length = middleElements.length;

        int pivotVal = deterministicSelection(middleElements, (length / 2) - 1);
        int pivot = getElementIndex(arr, start, end, pivotVal);
        pivot = helpers.partition(arr, start, end, pivot);
        int pivotInRange = pivot - start;

        if(pivotInRange == i) {
            return arr[pivot];
        } else if (pivotInRange < i) {
            return processInputDeterministically(arr, i - (pivotInRange + 1), pivot + 1, end);
        } else {
            return processInputDeterministically(arr, i , start, pivot - 1);
        }
    }

    private int getElementIndex(int[] arr, int start, int end, int elem) {
        for(int i = start; i <= end; i++) {
            if(arr[i] == elem) {
                return i;
            }
        }
        return -1;
    }

    private int[] getMiddleElements(int[] arr, int start, int end) {
        int length = end - start + 1;
        int groups = length / ELEMENTS_PER_GROUP;
        if(length % ELEMENTS_PER_GROUP != 0) {
            groups++;
        }
        int elementsPadded = 0;

        int[][] arrayGroups = new int[groups][ELEMENTS_PER_GROUP];
        for(int i = start; i < start + (groups * ELEMENTS_PER_GROUP); i++) {
            int elemNumber = i - start;
            int groupNum = elemNumber / ELEMENTS_PER_GROUP;
            int posInGroup = elemNumber % ELEMENTS_PER_GROUP;

            if(i > end) {
                arrayGroups[groupNum][posInGroup] = Integer.MAX_VALUE;
                elementsPadded++;
            } else {
                arrayGroups[groupNum][posInGroup] = arr[i];
            }
        }

        int[] middleElements = new int[groups];

        for(int i = 0; i < groups; i++) {
            Arrays.sort(arrayGroups[i]);
            int actualElementsInGroup = i == groups - 1 ? ELEMENTS_PER_GROUP - elementsPadded : ELEMENTS_PER_GROUP;
            middleElements[i] = arrayGroups[i][actualElementsInGroup / 2];
        }

        return middleElements;
    }
}
