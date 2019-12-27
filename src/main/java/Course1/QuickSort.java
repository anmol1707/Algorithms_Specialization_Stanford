package Course1;

public class QuickSort {

    private PivotType pivotType;
    private int comparisonCount;

    public QuickSort(PivotType pivotType) {
        this.pivotType = pivotType;
        this.comparisonCount = 0;
    }

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
        if(start >= end) {
            return;
        }
        this.comparisonCount += (end - start);

        int pivot = getPivot(arr, start, end);
        pivot = partition(arr, start, end, pivot);
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

                if((startVal < endVal && startVal > midVal) || (startVal < midVal && startVal > endVal)) {
                    return start;
                } else if ((endVal < startVal && endVal > midVal) || (endVal < midVal && endVal > startVal)) {
                    return end;
                } else {
                    return middleIndex;
                }
            default:
                return middleIndex;
        }
    }

    private int partition(int[] arr, int start, int end, int pivot) {
        swap(arr, start, pivot);

        int i = start + 1;

        for(int j = start + 1; j <= end; j++) {
            if(arr[j] < arr[start]) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, start, i - 1);
        return i - 1;
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
