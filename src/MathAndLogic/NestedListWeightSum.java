package MathAndLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 */
public class NestedListWeightSum {

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
    NestedListWeightSum o = new NestedListWeightSum();
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
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        sum += ni.integer * level;
      } else {
        sum += helper(ni.list, level + 1);
      }
    }
    return sum;
  }
}
