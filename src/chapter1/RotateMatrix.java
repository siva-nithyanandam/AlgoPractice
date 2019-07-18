package chapter1;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees.Can you do this in place?
 *
 * Input:
 * 1,2,3,4,
 * 5,6,7,8,
 * 9,10,11,12,
 * 13,14,15,16
 *
 * Output:
 * 4,8,12,16,
 * 3,7,11,15,
 * 2,6,10,14,
 * 1,5,9,13,
 */
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] arr =
                {
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}
                };
        rotateMatrix(arr);
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println("");
        }
    }

    /**
     * In 4x4 matrix, Move (0, 0) to (3, 0), (0, 1) to (2, 0), (0, 2) to (1, 0), (0, 3) to (0, 0) and so on.
     * @param arr
     */
    private static void rotateMatrix(int[][] arr){
        int n = arr.length;
        for(int r = 0; r < n/2; r++) {
            for(int c = r; c < n-1-r; c++) {
                //Store top value
                int temp = arr[r][c];

                //Move right to top
                arr[r][c] = arr[c][n-1-r];

                //Move bottom to right
                arr[c][n-1-r] = arr[n-1-r][n-1-c];

                //Move left to bottom
                arr[n-1-r][n-1-c] = arr[n-1-c][r];

                //Move top to left
                arr[n-1-c][r] = temp;
            }
        }
    }
}
