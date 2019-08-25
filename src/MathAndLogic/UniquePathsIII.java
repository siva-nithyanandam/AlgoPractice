package MathAndLogic;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Note:
 * 1 <= grid.length * grid[0].length <= 20
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        int[][] grid = new int[3][4];
        grid[0][0] = 1;
        grid[2][2] = 2;
        grid[2][3] = -1;
        System.out.println(uniquePathsIII.uniquePathsIII(grid));
    }

    public int uniquePathsIII(int[][] grid) {
        int startRow = 0, startCol = 0, nbrOfObstacles = 0;
        int maxRow = grid.length, maxCol = grid[0].length;
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (grid[i][j] == -1) {
                    nbrOfObstacles++;
                } else if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        int elementsToCross = (maxRow * maxCol) - nbrOfObstacles;
        grid[startRow][startCol] = 0;
        return uniquePathsIII(grid, startRow, startCol, maxRow, maxCol, 1, elementsToCross);
    }

    private int uniquePathsIII(int[][] grid, int row, int col, int maxRow, int maxCol,
                               int currCrossed, int elementsToCross) {
        int count = 0;
        if (grid[row][col] == 0) {
            grid[row][col] = -1;
            if (col < maxCol - 1) {
                count += uniquePathsIII(grid, row, col + 1, maxRow, maxCol, currCrossed+1,
                        elementsToCross);
            }
            if (row < maxRow - 1) {
                count += uniquePathsIII(grid, row+1, col, maxRow, maxCol, currCrossed+1,
                        elementsToCross);
            }
            if (col > 0) {
                count += uniquePathsIII(grid, row, col - 1, maxRow, maxCol, currCrossed+1,
                        elementsToCross);
            }
            if (row > 0) {
                count += uniquePathsIII(grid, row-1, col, maxRow, maxCol, currCrossed+1,
                        elementsToCross);
            }
            grid[row][col] = 0;
        } else if (grid[row][col] == 2) {
            if (currCrossed == elementsToCross) {
                count = 1;
            }
        }
        return count;
    }
}
