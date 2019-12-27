package Course1;

import Helpers.RandomizedHelpers;

public class QuickSort {

    private PivotType pivotType;
    private int comparisonCount;
    private RandomizedHelpers helpers;

    public QuickSort() {
        this(PivotType.Random);
    }

    public QuickSort(PivotType pivotType) {
        this.pivotType = pivotType;
        this.comparisonCount = 0;
        this.helpers = new RandomizedHelpers();
    }

    // average runtime - O(n), worst case runtime -  O(n^2)
    public void sortArray(int[] arr) {
        this.comparisonCount = 0;

        int length = arr.length;
        processInput(arr, 0, length - 1);
    }

    public void setPivotType(PivotType pivotType) {
        this.pivotType = pivotType;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    private void processInput(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        this.comparisonCount += (end - start);

        int pivot = getPivot(arr, start, end);
        pivot = helpers.partition(arr, start, end, pivot);
        processInput(arr, start, pivot - 1);
        processInput(arr, pivot + 1, end);
    }

    private int getPivot(int[] arr, int start, int end) {
        int middleIndex = (start + end) / 2;
        switch (pivotType) {
            case FirstElement:
                return start;
            case LastElement:
                return end;
            case MedianOfThree:
                int startVal = arr[start];
                int endVal = arr[end];
                int midVal = arr[middleIndex];

                if ((startVal < endVal && startVal > midVal) || (startVal < midVal && startVal > endVal)) {
                    return start;
                } else if ((endVal < startVal && endVal > midVal) || (endVal < midVal && endVal > startVal)) {
                    return end;
                } else {
                    return middleIndex;
                }
            case Random:
                return helpers.getRandomBetweenNumbers(start, end);
            default:
                return middleIndex;
        }
    }
}
