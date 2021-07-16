package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/11/2021  8:36 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-ii/
 *
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 *
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event ca not be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 */
public class MyCalendarII {

  TreeMap<Integer, Integer> delta = new TreeMap();
  TreeNode root;

  public static void main(String[] args) {
    MyCalendarII o = new MyCalendarII();
    System.out.println(o.book_faster(10, 20));
    System.out.println(o.book_faster(50, 60));
    System.out.println(o.book_faster(10, 40));
    System.out.println(o.book_faster(5, 15));
    System.out.println(o.book_faster(5, 10));
    System.out.println(o.book_faster(25, 55));
  }

  public boolean book_faster(int start, int end) {
    if(isInsertable(root, start, end)){
      root = constructBST(root, start, end);
      return true;
    }
    return false;
  }

  public TreeNode constructBST(TreeNode root, int start, int end){
    if(root==null){
      root = new TreeNode(start, end, false);
    }else if(end<=root.start){
      root.left = constructBST(root.left, start, end);
    }else if(start>=root.end){
      root.right = constructBST(root.right, start, end);
    }else{
      root.isOverlap = true;
      int startMin = Math.min(start, root.start);
      int startMax = Math.max(start, root.start);
      int endMin = Math.min(end, root.end);
      int endMax = Math.max(end, root.end);
      if(startMin<startMax){
        root.left = constructBST(root.left, startMin, startMax);
      }
      if(endMin<endMax){
        root.right = constructBST(root.right, endMin, endMax);
      }
      root.start = startMax;
      root.end = endMin;
    }
    return root;
  }

  public boolean isInsertable(TreeNode root, int start, int end){
    if(end<=start){
      return true;
    }
    if(root==null){
      return true;
    }
    if(end<=root.start){
      return isInsertable(root.left, start, end);
    }
    if(start>=root.end){
      return isInsertable(root.right, start, end);
    }
    if(root.isOverlap){
      return false;
    }
    if(start>=root.start&&end<=root.end){
      return true;
    }
    return isInsertable(root.left, start, root.start) && isInsertable(root.right, root.end, end);
  }

  class TreeNode{
    int start, end;
    boolean isOverlap;
    TreeNode left, right;
    TreeNode(int start, int end, boolean isOverlap){
      this.start = start;
      this.end = end;
      this.isOverlap = isOverlap;
    }
  }

  public boolean book(int start, int end) {
    delta.put(start, delta.getOrDefault(start, 0) + 1);
    delta.put(end, delta.getOrDefault(end, 0) - 1);

    int active = 0;
    for (int d: delta.values()) {
      active += d;
      if (active >= 3) {
        delta.put(start, delta.get(start) - 1);
        delta.put(end, delta.get(end) + 1);
        if (delta.get(start) == 0)
          delta.remove(start);
        return false;
      }
    }
    return true;
  }

}
