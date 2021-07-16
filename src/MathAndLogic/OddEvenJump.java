package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/29/2021  6:56 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/explore/interview/card/google/67/sql-2/3045/
 * <p>
 * You are given an integer array arr. From some starting index, you can make a series of jumps. The
 * (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th,
 * ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not
 * the indices.
 * <p>
 * You may jump forward from index i to index j (with i < j) in the following way:
 * <p>
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <=
 * arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can
 * only jump to the smallest such index j. During even-numbered jumps (i.e., jumps 2, 4, 6, ...),
 * you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If
 * there are multiple such indices j, you can only jump to the smallest such index j. It may be the
 * case that for some index i, there are no legal jumps. A starting index is good if, starting from
 * that index, you can reach the end of the array (index arr.length - 1) by jumping some number of
 * times (possibly 0 or more than once).
 * <p>
 * Return the number of good starting indices.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [10,13,12,14,15] Output: 2 Explanation: From starting index i = 0, we can make our
 * 1st jump to i = 2 (since arr[2] is the smallest among arr[1], arr[2], arr[3], arr[4] that is
 * greater or equal to arr[0]), then we cannot jump any more. From starting index i = 1 and i = 2,
 * we can make our 1st jump to i = 3, then we cannot jump any more. From starting index i = 3, we
 * can make our 1st jump to i = 4, so we have reached the end. From starting index i = 4, we have
 * reached the end already. In total, there are 2 different starting indices i = 3 and i = 4, where
 * we can reach the end with some number of jumps. Example 2:
 * <p>
 * Input: arr = [2,3,1,1,4] Output: 3 Explanation: From starting index i = 0, we make jumps to i =
 * 1, i = 2, i = 3: During our 1st jump (odd-numbered), we first jump to i = 1 because arr[1] is the
 * smallest value in [arr[1], arr[2], arr[3], arr[4]] that is greater than or equal to arr[0].
 * During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because arr[2] is the largest
 * value in [arr[2], arr[3], arr[4]] that is less than or equal to arr[1]. arr[3] is also the
 * largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3 During our
 * 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because arr[3] is the smallest value in
 * [arr[3], arr[4]] that is greater than or equal to arr[2]. We can't jump from i = 3 to i = 4, so
 * the starting index i = 0 is not good. In a similar manner, we can deduce that: From starting
 * index i = 1, we jump to i = 4, so we reach the end. From starting index i = 2, we jump to i = 3,
 * and then we can't jump anymore. From starting index i = 3, we jump to i = 4, so we reach the end.
 * From starting index i = 4, we are already at the end. In total, there are 3 different starting
 * indices i = 1, i = 3, and i = 4, where we can reach the end with some number of jumps. Example
 * 3:
 * <p>
 * Input: arr = [5,1,3,4,2] Output: 3 Explanation: We can reach the end from starting indices 1, 2,
 * and 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 2 * 104 0 <= arr[i] < 105
 */
public class OddEvenJump {

  public static void main(String[] args) {
    OddEvenJump o = new OddEvenJump();
    System.out.println(o.oddEvenJumps_faster(new int[]{5, 1, 3, 4, 2}));
  }

  public int oddEvenJumps(int[] A) {
    int n = A.length, res = 1;
    boolean[] higher = new boolean[n], lower = new boolean[n];
    higher[n - 1] = lower[n - 1] = true;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    map.put(A[n - 1], n - 1);
    for (int i = n - 2; i >= 0; --i) {
      Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]);
      Map.Entry<Integer, Integer> lo = map.floorEntry(A[i]);
      if (hi != null) {
        higher[i] = lower[(int) hi.getValue()];
      }
      if (lo != null) {
        lower[i] = higher[(int) lo.getValue()];
      }
      if (higher[i]) {
        res++;
      }
      map.put(A[i], i);
    }
    return res;
  }

  class Node {

    int val;
    boolean even, odd;
    Node left, right;

    public Node(int val, boolean even, boolean odd) {
      this.val = val;
      this.even = even;
      this.odd = odd;
    }

    public void insert(int val, boolean even, boolean odd) {
      if (this.val == val) {
        this.even = even;
        this.odd = odd;
        return;
      }
      if (this.val > val) {
        if (left == null) {
          left = new Node(val, even, odd);
          return;
        }
        left.insert(val, even, odd);
      } else {
        if (right == null) {
          right = new Node(val, even, odd);
          return;
        }
        right.insert(val, even, odd);
      }
    }


    Node maxLess(int val) { // 4 < 1
      if (this.val == val) {
        return this;
      }
      if (this.val <= val) {
        if (right == null) {
          return this;
        }
        Node n = right.maxLess(val);
        if (n.val != Integer.MIN_VALUE && val >= n.val) {
          return n;
        } else {
          return this;
        }

      } else {
        if (left == null) {
          return new Node(Integer.MIN_VALUE, false, false);
        }
        return left.maxLess(val);
      }
    }

    //////////      4(t,t)
    //      1(t,f)
    //          3(t, f)

    Node minMore(int val) {
      if (this.val == val) {
        return this;
      }
      if (this.val >= val) {
        if (left == null) {
          return this;
        }
        Node n = left.minMore(val); //max,f,f
        if (n.val != Integer.MAX_VALUE && val <= n.val) { // 12>=14
          return n;
        } else {
          return this;
        }
        // return Math.min(val, left.g(val));
      } else {
        if (right == null) {
          return new Node(Integer.MAX_VALUE, false, false);
        }
        return right.minMore(val);
      }
    }
  }

  public int oddEvenJumps_faster(int[] arr) {
    Node n = new Node(arr[arr.length - 1], true, true);
    int count = 1;
    for (int i = arr.length - 2; i >= 0; i--) {
      Node maxLess = n.maxLess(arr[i]); // max less // 1 false true
      Node minMore = n.minMore(arr[i]); // min more // 4 true true

      boolean even = maxLess.odd; // true
      boolean odd = minMore.even; // true
      //  System.out.println(arr[i] + " " + even + " "  + odd + ", " + g.val + " " + g.even + " " + g.odd + ", " + l.val + " " + l.even + " " + l.odd);
      n.insert(arr[i], even, odd); // 1, false, true
      if (odd) {
        count++;
      }
    }
    return count;
  }
}
