package chapter1;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to 0.
 */
public class ZeroMatrix {

  public static void main(String[] args) {

    //Usecase - 1
    int[][] arr =
        {
            {0, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };

    modifyAsZeroMatrix(arr);

    for(int i = 0; i < arr.length; i++) {
      for(int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + ",");
      }
      System.out.println("");
    }
    /**
     * Result:
     * 0,0,0,0,
     * 0,6,7,8,
     * 0,10,11,12,
     */

    //Usecase - 2
    int[][] arr2 =
        {
            {1, 2, 3, 4}, {5, 0, 0, 8}, {9, 10, 11, 12}
        };

    modifyAsZeroMatrix(arr2);

    for(int i = 0; i < arr2.length; i++) {
      for(int j = 0; j < arr2[0].length; j++) {
        System.out.print(arr2[i][j] + ",");
      }
      System.out.println("");
    }
    /**
     * Result:
     * 1,0,0,4,
     * 0,0,0,0,
     * 9,0,0,12,
     */
  }

  private static void modifyAsZeroMatrix(int[][] arr) {
    boolean isFirstRowHasZero = false;
    boolean isFirstColumnHasZero = false;

    int m = arr.length;
    int n = arr[0].length;

    for(int i = 0; i < m; i++) {
      if(arr[i][0] == 0) {
        isFirstColumnHasZero = true;
        break;
      }
    }
    for(int i = 0; i < n; i++) {
      if(arr[0][i] == 0) {
        isFirstRowHasZero = true;
        break;
      }
    }

    for(int i = 1; i < m; i++) {
      for(int j = 1; j < n; j++) {
        if(arr[i][j] == 0) {
          arr[i][0] = 0;
          arr[0][j] = 0;
        }
      }
    }

    for(int i = 1; i < m; i++) {
      if(arr[i][0] == 0) {
        for(int j = 0; j < n; j++) {
          arr[i][j] = 0;
        }
      }
    }
    for(int i = 1; i < n; i++) {
      if(arr[0][i] == 0) {
        for(int j = 0; j < m; j++) {
          arr[j][i] = 0;
        }
      }
    }

    if (isFirstColumnHasZero) {
      for(int i = 0; i < m; i++) {
        arr[i][0] = 0;
      }
    }

    if (isFirstRowHasZero) {
      for(int i = 0; i < n; i++) {
        arr[0][i] = 0;
      }
    }
  }

}
