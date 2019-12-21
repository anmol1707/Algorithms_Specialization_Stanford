package Course1;

public class CountInversions {

    // runtime - O(n^2)
    public long countInversionsSlow(int[] arr) {
        int length = arr.length;
        long result = 0L;

        for(int i = 0; i < length; i++) {
            for(int j = i + 1; j < length; j++) {
                if(arr[i] > arr[j]) {
                    result++;
                }
            }
        }

        return result;
    }

    public long countInversionsFast(int[] arr) {
        return processInputFast(arr, 0, arr.length - 1);
    }

    // runtime - O(n.log(n))
    private long processInputFast(int[] arr, int start, int end) {
        if(start >= end) {
            return 0;
        }

        int middle = (start + end) / 2;

        // count all inversions present strictly in the left half
        long val1 = processInputFast(arr, start, middle);
        // count all inversions present strictly in the right half
        long val2 = processInputFast(arr, middle + 1, end);

        int[] leftArr = new int[middle - start + 1];
        int[] rightArr = new int[end - middle];

        for(int i = start; i <= middle; i++) {
            leftArr[i - start] = arr[i];
        }
        for(int i = middle + 1; i <= end; i++) {
            rightArr[i - (middle + 1)] = arr[i];
        }

        // count all inversions split between left and right halves
        long val3 = mergeAndCount(arr, leftArr, rightArr, start);
        return val1 + val2 + val3;
    }

    private long mergeAndCount(int[] arr, int[] leftArr, int[] rightArr, int start) {
        int x = 0, y = 0, z = start;
        long result = 0L;

        while(x < leftArr.length && y < rightArr.length) {
            if(leftArr[x] <= rightArr[y]) {
                arr[z] = leftArr[x++];
            } else {
                arr[z] = rightArr[y++];
                result += (leftArr.length - x);
            }
            z++;
        }

        while(x < leftArr.length) {
            arr[z++] = leftArr[x++];
        }

        while(y < rightArr.length) {
            arr[z++] = rightArr[y++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {5,1,3,2,4,6};

        CountInversions countInversions = new CountInversions();
        System.out.println(countInversions.countInversionsFast(arr));
    }

}
