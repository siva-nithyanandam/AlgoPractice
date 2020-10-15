package MathAndLogic;

/**
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 */

//TODO Come back to this
public class SparseMatrixMultiplication {

  public static void main(String[] args) {
    SparseMatrixMultiplication o = new SparseMatrixMultiplication();
    /*int[][] A = new int[][]{{0,0},{2,4},{3,4}};
    int[][] B = new int[][]{{1,2,3,4},{0,0,0,0},{1,2,3,4}};
    int[][] C = o.multiply(A, B);*/

    int[][] A = new int[][]{{1,1,3,4,4},{2,4,3,1,2},{10,12,5,15,12}};
    int[][] B = new int[][]{{1,2,3,4,4},{3,4,3,1,2},{8,23,9,20,25}};
    int[][] C = o.multiply(A, B);
    System.out.print("");
  }

  private int[][] multiply(int[][] A, int[][] B) {
    //TODO Yet to do
  return new int[0][0];
  }
}
