package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/13/2021  6:45 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 *
 *
 * Example 1:
 *
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * Example 2:
 *
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 *
 * Constraints:
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 */
public class TheSkylineProblem {

  public static void main(String[] args) {
    TheSkylineProblem o = new TheSkylineProblem();
    System.out.println(o.getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}}));
  }

  private class Wall {
    public int x;
    public boolean isLeft;
    public int height;
    public Wall(int x, int height, boolean isLeft){
      this.x = x;
      this.height = height;
      this.isLeft = isLeft;
    }
  }

  public List<List<Integer>> getSkyline(int[][] buildings) {
    ArrayList<Wall> ws = new ArrayList<>();
    for(int[] building: buildings){
      Wall left = new Wall(building[0], building[2], true);
      Wall right = new Wall(building[1], building[2], false);
      ws.add(left);
      ws.add(right);
    }

    Collections.sort(ws, (a, b) -> {
      if (a.x != b.x) { // if wall not on same x, left most first
        return a.x - b.x;
      } else if (a.isLeft && b.isLeft) { // if they are both left wall and on the same x, higher wall first
        return b.height - a.height;
      } else if (!a.isLeft && !b.isLeft){ // if they are both right wall and on the same x, lower wall first
        return a.height - b.height;
      }
      return a.isLeft && !b.isLeft? -1 : 1; // if they are on the same x, left wall first then right wall
    });

    List<List<Integer>> rst = new LinkedList<>();
    int preHeight = 0;
    PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
    // we need to ignore the building that we already passed
    HashMap<Integer, Integer> ignore = new HashMap<>();
    max.add(0);
    for(Wall cur: ws){
      if(cur.isLeft){
        max.add(cur.height);
      } else {
        // put the left wall into ignore list so next loop will remove them
        ignore.put(cur.height, ignore.getOrDefault(cur.height, 0) + 1);
      }

      while(ignore.get(max.peek()) != null && ignore.get(max.peek()) > 0){
        int h = max.poll();
        ignore.put(h, ignore.get(h) - 1);
      }

      int curHeight = max.peek();
      if(curHeight != preHeight){
        List<Integer> temp = new LinkedList<>();
        temp.add(cur.x);
        temp.add(curHeight);
        rst.add(temp);
        preHeight = curHeight;
      }
    }

    return rst;
  }
}
