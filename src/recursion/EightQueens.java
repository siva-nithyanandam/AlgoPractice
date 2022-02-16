package recursion;

import java.util.Stack;

/**
 * Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
 * so that none of them share the same row, column, or diagonal.
 * In this case, "diagonal" means all diagonals, not just the two that bisect the board
 */
public class EightQueens {

    static class QueenPos {
        private int row;
        private int col;

        public QueenPos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int n = 8;
        Stack<QueenPos> queenPosList = new Stack<>();

        for (int i = 0; i < n; i++) {
            System.out.println("When row and col starting at, 0 and " + i);
            if (placeQueens(n, queenPosList, 0, i)) {
                for (QueenPos queenPos : queenPosList) {
                    System.out.println(queenPos.row + "," + queenPos.col);
                }
            } else {
                System.out.println("No such possibilities");
            }
            queenPosList.clear();
        }
    }

    private static boolean placeQueens(int n, Stack<QueenPos> queenPosList, int row, int col) {
        if (row == n) {
            return true;
        }
        while (col < n) {
            if (isSafe(n, queenPosList, row, col)) {
                QueenPos queenPos = new QueenPos(row, col);
                queenPosList.push(queenPos);
                if (placeQueens(n, queenPosList, row+1, 0)) {
                    return true;
                } else {
                    queenPosList.pop();
                }
            }
            col++;
        }
        return false;
    }

    private static boolean isSafe(int n, Stack<QueenPos> queenPosList, int row, int col) {
        for (QueenPos queenPos : queenPosList) {
            if (queenPos.row == row
                    || queenPos.col == col
                    || Math.abs(row - queenPos.row) == Math.abs(col - queenPos.col)) {
                return false;
            }
        }
        return true;
    }
}
