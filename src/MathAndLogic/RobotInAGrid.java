package MathAndLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 */
public class RobotInAGrid {

  public static void main(String[] args) {
    int[][] grid = new int[3][3];
    grid[1][1] = 1;
//    grid[1][0] = 1;

    if (grid == null || grid.length == 0) {
      return;
    }
    int nbrOfRows = grid.length;
    int nbrOfCols = grid[0].length;

    Map<String, List<String>> map = new HashMap<>();
    findPath(grid, 0, 0, nbrOfRows-1, nbrOfCols-1, map);
    for (String s : map.get("0,0")) {
      if (!s.endsWith("-1,-1")) {
        System.out.println(s);
      }
    }
  }

  static List<String> l = new ArrayList<>();

  private static void findPath(int[][] grid, int row, int col, int nbrOfRows,
      int nbrOfCols, Map<String, List<String>> map) {

    String key = row + "," + col;

    if (l.contains(key)) {
      System.out.println("Duplicate");
    } else {
      l.add(key);
    }

    List<String> subList = new ArrayList<>();
    if (grid[row][col] == 1) {
      subList.add("-1,-1");
      map.put(key, subList);
      return;
    }
    if (row < nbrOfRows) {
      if (!map.containsKey((row+1)+","+col)) {
        findPath(grid, row+1, col, nbrOfRows, nbrOfCols, map);
      }
      for (String s : map.get((row+1)+","+col)) {
        subList.add(key + " " + s);
      }
    }
    if (col < nbrOfCols) {
      if (!map.containsKey(row+","+(col+1))) {
        findPath(grid, row, col+1, nbrOfRows, nbrOfCols, map);
      }
      for (String s : map.get(row+","+(col+1))) {
        subList.add(key + " " + s);
      }
    }

    if (row == nbrOfRows && col == nbrOfCols) {
      subList.add(key);
    }
    map.put(key, subList);
  }
}
