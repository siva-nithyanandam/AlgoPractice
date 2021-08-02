package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/2/2021  8:15 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.TreeSet;

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/471/
 *
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: time = "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
 * It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 *
 * Input: time = "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 *
 *
 * Constraints:
 *
 * time.length == 5
 * time is a valid time in the form "HH:MM".
 * 0 <= HH < 24
 * 0 <= MM < 60
 */
public class NextClosestTime {

  public static void main(String[] args) {
    NextClosestTime o = new NextClosestTime();
    System.out.println(o.nextClosestTime_faster("19:34"));
    System.out.println(o.nextClosestTime_faster("20:48"));
    System.out.println(o.nextClosestTime_faster("19:34"));
    System.out.println(o.nextClosestTime_faster("23:59"));
  }

  public String nextClosestTime_faster(String time) {
    boolean[] nos = new boolean[10];
    for(int i = 0 ; i < time.length();i++){
      if(time.charAt(i)  == ':') continue;
      nos[time.charAt(i) - '0'] = true;
    }


    StringBuilder str = new StringBuilder();
    if(minZeroth(nos,time.charAt(4) - '0',str)){
      str.insert(0,time.substring(0,4));
      return str.toString();
    }
    if(minTen(nos,time.charAt(3) - '0',str)){
      str.insert(0,time.substring(0,3));
      return str.toString();
    }

    if(hourZero(nos,time.charAt(1) - '0',str,time.charAt(0) - '0')){
      str.insert(0,time.substring(0,1));
      return str.toString();
    }
    return hourTen(nos,time.charAt(0) - '0',str);
  }

  public String hourTen(boolean[] nos, int val,StringBuilder str){

    for(int i = val+1; i <=2; i++){
      if(nos[i]){
        str.insert(0,i);
        return str.toString();
      }
    }
    for(int i = 0 ; i<= val;i++){
      if(nos[i]){
        str.insert(0,i);
      }
    }
    return str.toString();
  }
  public boolean hourZero(boolean[] nos, int val,StringBuilder str, int prev ){

    int limit = prev == 2? 3 : 9;
    str.insert(0,':');

    for(int i = val+1; i <=limit; i++){
      if(nos[i]){
        str.insert(0,i);
        return true;
      }
    }
    for(int i = 0 ; i<= val;i++){
      if(nos[i]){
        str.insert(0,i);
        return false;
      }
    }
    return false;
  }

  public boolean minTen(boolean[] nos, int val,StringBuilder str ){

    for(int i = val+1; i <=5; i++){
      if(nos[i]){
        str.insert(0,i);
        return true;
      }
    }
    for(int i = 0 ; i<= val;i++){
      if(nos[i]){
        str.insert(0,i);
        return false;
      }
    }
    return false;
  }

  public boolean minZeroth(boolean[] nos, int val,StringBuilder str){

    for(int i = val+1; i <=9; i++){
      if(nos[i]){
        str.append(i);
        return true;
      }
    }
    for(int i = 0 ; i<= val;i++){
      if(nos[i]){
        str.append(i);
        return false;
      }
    }
    return false;
  }

  public String nextClosestTime(String time) {

    int[] nums = new int[4];
    int i = 0, j = 0;
    int givenSum = 0;
    while (i < 5) {
      if (time.charAt(i) == ':') {
        i++;
        continue;
      }
      nums[j] = time.charAt(i) - '0';
      givenSum = (givenSum * 10) + nums[j];
      i++;
      j++;
    }
    TreeSet<Integer> validNums = new TreeSet<>();
    permuteAll(nums, validNums, 0, 0);

    Integer newTime = validNums.ceiling(givenSum+1);
    if (newTime == null) {
      newTime = validNums.iterator().next();
    }

    StringBuilder sb = new StringBuilder();
    i = 0;
    while (i < 5) {
      if (i == 2) {
        sb.append(":");
        i++;
        continue;
      }
      sb.append(newTime % 10);
      newTime /= 10;
      i++;
    }
    sb.reverse();
    return sb.toString();
  }

  private void permuteAll(int[] nums, TreeSet<Integer> validNums, int sum, int cnt) {

    for (int i = 0; i < 4; i++) {
      if (cnt == 0 && nums[i] > 2) {
        continue;
      } else if (cnt == 1 && sum > 1 && nums[i] > 3) {
        continue;
      } else if (cnt == 2 && nums[i] > 5) {
        continue;
      } else if (cnt == 3) {
        validNums.add((sum * 10) + nums[i]);
        continue;
      }
      permuteAll(nums, validNums, (sum * 10) + nums[i], cnt+1);
    }
  }
}
