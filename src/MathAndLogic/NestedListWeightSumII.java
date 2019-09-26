package MathAndLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up.
 * i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */

/**
 * Iterative approach.
 * Spread all NestedInteger and add it to Map having level as key. While iterating itself
 * identify max level and finally iterate one more time to calculate the sum.
 * Straight forward solution.
 */
public class NestedListWeightSumII {

  public static class NestedInteger {
    private List<NestedInteger> list;
    private Integer integer;
    public NestedInteger(List<NestedInteger> list){
      this.list = list;
    }
    public void add(NestedInteger nestedInteger) {
      if(this.list != null){
        this.list.add(nestedInteger);
      } else {
        this.list = new ArrayList();
        this.list.add(nestedInteger);
      }
    }
    public void setInteger(int num) {
      this.integer = num;
    }
    public NestedInteger(Integer integer){
      this.integer = integer;
    }
    public NestedInteger() {
      this.list = new ArrayList();
    }
    public boolean isInteger() {
      return integer != null;
    }
    public Integer getInteger() {
      return integer;
    }
    public List<NestedInteger> getList() {
      return list;
    }
  }

  public static void main(String[] args) {
    NestedListWeightSumII o = new NestedListWeightSumII();
    List<NestedInteger> finalNestedList = new ArrayList<>();

    NestedInteger ni;
    NestedInteger ni_list;

    ni = new NestedInteger(1);
    ni_list = new NestedInteger();
    ni_list.add(ni);
    ni_list.add(ni);
    finalNestedList.add(ni_list);

    ni = new NestedInteger(2);
    finalNestedList.add(ni);

    ni = new NestedInteger(1);
    ni_list = new NestedInteger();
    ni_list.add(ni);
    ni_list.add(ni);
    finalNestedList.add(ni_list);

    System.out.println(o.depthSum(finalNestedList));
  }

  public int depthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
  }

  private int helper(List<NestedInteger> nestedList, int level) {
    int sum = 0;
    int maxLayer = 1;
    Map<Integer, List<NestedInteger>> map = new HashMap<>();
    Queue<NestedInteger> queue = new LinkedList<>();
    Queue<Integer> depth = new LinkedList<>();
    for (NestedInteger ni : nestedList) {
      queue.offer(ni);
      depth.offer(1);
    }
    while (!queue.isEmpty()) {
      NestedInteger ni = queue.poll();
      Integer i = depth.poll();
      if (i > maxLayer) {
        maxLayer = i;
      }
      if (ni.isInteger()) {
        List<NestedInteger> list = map.get(i);
        if (list == null) {
          list = new ArrayList<>();
          list.add(ni);
          map.put(i, list);
        } else {
          list.add(ni);
        }
      } else {
        for (NestedInteger ni1 : ni.list) {
          queue.offer(ni1);
          depth.offer(i+1);
        }
      }
    }

    for (Integer i : map.keySet()) {
      List<NestedInteger> list = map.get(i);
      for (NestedInteger ni : list) {
        sum += ni.integer * (maxLayer - i + 1);
      }
    }
    return sum;
  }
}
