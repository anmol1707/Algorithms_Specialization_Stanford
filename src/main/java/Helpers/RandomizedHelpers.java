package Helpers;

import java.util.Random;

public class RandomizedHelpers {

    private Random random;

    public RandomizedHelpers() {
        this.random = new Random();
    }

    public int partition(int[] arr, int start, int end, int pivot) {
        swap(arr, start, pivot);

        int i = start + 1;

        for (int j = start + 1; j <= end; j++) {
            if (arr[j] < arr[start]) {
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

    public int getRandomBetweenNumbers(int start, int end) {
        return this.random.nextInt(end - start + 1) + start;
    }
}
