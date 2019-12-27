package Course1.Week2;

public class MatrixMultiplication {

    // runtime - O(n^3) assuming both matrices have dimensions n x n
    public int[][] matrixMultiplicationSlow(int[][] arr1, int[][] arr2) {
        if (arr1[0].length != arr2.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }

        int[][] result = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                for (int k = 0; k < arr2[0].length; k++) {
                    result[i][k] += arr1[i][j] * arr2[j][k];
                }
            }
        }

        return result;
    }

    // runtime - O(n^2.8) assuming both matrices have dimensions n x n
    public int[][] strassenMatrixMultiplication(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr1[0].length || arr1[0].length != arr2.length || arr2.length != arr2[0].length || !isPowerOfTwo(arr1.length)) {
            throw new IllegalArgumentException("Matrices cannot be multiplied. Only square matrices allowed with dimensions n x n where n is a power of 2!");
        }

        int n = arr1.length;
        int[][] result = new int[n][n];

        if (n == 1) {
            result[0][0] = arr1[0][0] * arr2[0][0];
            return result;
        }

        int[][] A = new int[n / 2][n / 2];
        int[][] B = new int[n / 2][n / 2];
        int[][] C = new int[n / 2][n / 2];
        int[][] D = new int[n / 2][n / 2];

        int[][] E = new int[n / 2][n / 2];
        int[][] F = new int[n / 2][n / 2];
        int[][] G = new int[n / 2][n / 2];
        int[][] H = new int[n / 2][n / 2];

        splitMatrix(arr1, A, 0, 0);
        splitMatrix(arr1, B, 0, n/2);
        splitMatrix(arr1, C, n/2, 0);
        splitMatrix(arr1, D, n/2, n/2);

        splitMatrix(arr2, E, 0, 0);
        splitMatrix(arr2, F, 0, n/2);
        splitMatrix(arr2, G, n/2, 0);
        splitMatrix(arr2, H, n/2, n/2);

        int[][] P1 = strassenMatrixMultiplication(A, add(F, H, -1));
        int[][] P2 = strassenMatrixMultiplication(add(A, B, 1), H);
        int[][] P3 = strassenMatrixMultiplication(add(C, D, 1), E);
        int[][] P4 = strassenMatrixMultiplication(D, add(G, E, -1));
        int[][] P5 = strassenMatrixMultiplication(add(A, D, 1), add(E, H, 1));
        int[][] P6 = strassenMatrixMultiplication(add(B, D, -1), add(G, H, 1));
        int[][] P7 = strassenMatrixMultiplication(add(A, C, -1), add(E, F, 1));

        int[][] topLeftResult = add(add(P5, add(P4, P6, 1), 1), P2, -1);
        int[][] topRightResult = add(P1, P2, 1);
        int[][] bottomLeftResult = add(P3, P4, 1);
        int[][] bottomRightResult = add(add(P1, P5, 1), add(P3, P7, 1), -1);

        mergeResults(result, topLeftResult, 0, 0);
        mergeResults(result, topRightResult, 0, n/2);
        mergeResults(result, bottomLeftResult, n/2, 0);
        mergeResults(result, bottomRightResult, n/2, n/2);

        return result;
    }

    private void mergeResults(int[][] parent, int[][] child, int parentRowStart, int parentColumnStart) {
        int n = child.length;
        for(int i = 0; i < n;i ++) {
            for(int j = 0; j < n; j++) {
                parent[parentRowStart + i][parentColumnStart + j] = child[i][j];
            }
        }
    }

    private int[][] add(int[][] arr1, int[][] arr2, int signForMatrix2) {
        int n = arr1.length;
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                result[i][j] = arr1[i][j] + (signForMatrix2 * arr2[i][j]);
            }
        }

        return result;
    }

    private void splitMatrix(int[][] parent, int[][] child, int parentRowStart, int parentColumnStart) {
        int n = child.length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                child[i][j] = parent[parentRowStart + i][parentColumnStart + j];
            }
        }
    }

    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] arr2 = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};

        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        matrixMultiplication.matrixMultiplicationSlow(arr1, arr2);
        matrixMultiplication.strassenMatrixMultiplication(arr1, arr2);
    }
}
