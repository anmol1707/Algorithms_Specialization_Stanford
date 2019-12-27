package Course1.Week1;

import Helpers.GeneralHelpers;

public class MergeSort {

    public MergeSort() {

    }

    // runtime - O(n.log(n))
    public void sortArray(int[] arr) {
        int length = arr.length;
        processInput(arr, 0, length - 1);
    }

    private void processInput(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = (end + start) / 2;
        processInput(arr, start, middle);
        processInput(arr, middle + 1, end);

        int[] leftArr = GeneralHelpers.copyElements(arr, start, middle);
        int[] rightArr = GeneralHelpers.copyElements(arr, middle + 1, end);
        mergeParts(arr, leftArr, rightArr, start);
    }

    private void mergeParts(int[] arr, int[] leftArr, int[] rightArr, int start) {
        int x = 0, y = 0, z = start;

        while (x < leftArr.length && y < rightArr.length) {
            if (leftArr[x] <= rightArr[y]) {
                arr[z] = leftArr[x++];
            } else {
                arr[z] = rightArr[y++];
            }
            z++;
        }

        while (x < leftArr.length) {
            arr[z++] = leftArr[x++];
        }

        while (y < rightArr.length) {
            arr[z++] = rightArr[y++];
        }
    }
}
