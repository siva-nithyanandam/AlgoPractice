package HashMapSet;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 17,2023 - 10:17 PM
 */

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-difference-of-two-arrays/?envType=study-plan-v2&envId=leetcode-75
 *
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 * Example 2:
 *
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * -1000 <= nums1[i], nums2[i] <= 1000
 */

public class FindtheDifferenceofTwoArrays {

    public static void main(String[] args) {
        FindtheDifferenceofTwoArrays o = new FindtheDifferenceofTwoArrays();
        System.out.println(o.findDifference(new int[]{1,2,3}, new int[]{2,4,6}));
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return new AbstractList<List<Integer>>(){
            private List<List<Integer>> list;

            public List<Integer> get(int index){
                if(list == null){
                    helper();
                }
                return list.get(index);
            }
            public int size(){
                if(list == null){
                    helper();
                }
                return list.size();
            }

            private void helper(){
                list = new ArrayList<>();
                boolean[] uniqueNumbers1 = new boolean[2001];
                for(int i = 0 ; i < nums1.length ; i++){
                    uniqueNumbers1[nums1[i] + 1000] = true;
                }
                boolean[] uniqueNumbers2 = new boolean[2001];
                List<Integer> answer1 = new ArrayList<>();
                for(int i = 0 ; i < nums2.length ; i++){
                    uniqueNumbers2[nums2[i] + 1000] = true;
                    if(!uniqueNumbers1[nums2[i] + 1000]){
                        answer1.add(nums2[i]);
                        uniqueNumbers1[nums2[i] + 1000] = true;
                    }
                }
                List<Integer> answer0 = new ArrayList<>();
                for(int i = 0 ; i < nums1.length ; i++){
                    if(!uniqueNumbers2[nums1[i] + 1000]){
                        answer0.add(nums1[i]);
                        uniqueNumbers2[nums1[i] + 1000] = true;
                    }
                }
                list.add(answer0);
                list.add(answer1);
            }
        };
    }

    public List<List<Integer>> findDifference_own(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {
            map.put(num, 1);
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subRes = new ArrayList<>();

        for (int num1 : nums1) {
            if (map.containsKey(num1)) {
                if (map.get(num1) >= 0) {
                    map.put(num1, 0);
                }
            } else {
                map.put(num1, -1);
                subRes.add(num1);
            }
        }
        res.add(subRes);

        subRes = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                subRes.add(entry.getKey());
            }
        }
        res.add(subRes);
        return res;
    }

}
