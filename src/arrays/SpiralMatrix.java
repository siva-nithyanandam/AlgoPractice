package arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        SpiralMatrix o = new SpiralMatrix();
        int[][] matrix;
        List<Integer> res;

        matrix = new int[][]{{6,9,7}};
        res = o.spiralOrder(matrix);
        printList(res);
        System.out.println();

        matrix = new int[][]{{1,2,3},
        {4,5,6},
        {7,8,9}};
        res = o.spiralOrder(matrix);
        printList(res);
        System.out.println();

        matrix = new int[][]
                {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}};
        res = o.spiralOrder(matrix);
        printList(res);
        System.out.println();
    }

    private static void printList(List<Integer> res) {
        for (int i : res) {
            System.out.println(i);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length == 0) return list;
        int top = 0, right = matrix[0].length-1, bottom = matrix.length-1, left = 0;

        while(left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--;

            for (int i = right; i >= left && top <= bottom; i--) {
                list.add(matrix[bottom][i]);
            }
            bottom--;

            for (int i = bottom; i >= top && left <= right; i--) {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }
}
