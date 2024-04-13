package backtracking;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Nov 02,2023 - 9:08 AM
 */

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency
 *  of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum o = new CombinationSum();
        System.out.println(o.combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(o.combinationSum_another(new int[]{2,3,6,7}, 7));
        System.out.println(o.combinationSum(new int[]{2,3,5}, 8));
        System.out.println(o.combinationSum(new int[]{2}, 1));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subRes = new ArrayList<>();


        for (int i = 0; i < candidates.length; i++) {
            subRes.add(candidates[i]);
            backtrack(i, candidates, candidates[i], target, res, subRes);
            subRes.remove(subRes.size()-1);
        }
        return res;
    }

    private void backtrack(int pos, int[] candidates, int currSum, int target, List<List<Integer>> res, List<Integer>subRes) {

        if (currSum == target) {
            res.add(new ArrayList<>(subRes));
            return;
        } else if (currSum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            subRes.add(candidates[i]);
            backtrack(i, candidates, currSum + candidates[i], target, res, subRes);
            subRes.remove(subRes.size()-1);
        }
    }

    public List<List<Integer>> combinationSum_another(int[] candidates, int target) {
        return new AbstractList<List<Integer>>() {
            private List<List<Integer>> ans;

            @Override
            public List<Integer> get(int index) {
                if (ans == null) {
                    init();
                }
                return ans.get(index);
            }

            private void init() {
                ans = new ArrayList<>();
                Arrays.sort(candidates);
                findCombination(0, target, new ArrayList<>());
            }

            private void findCombination(int index, int target, ArrayList<Integer> elements) {
                if (target == 0) {
                    ans.add(new ArrayList<>(elements));
                    return;
                }
                if (target < 0 || index == candidates.length) {
                    return;
                }
                for (int i = index; i < candidates.length; i++) {

                    if (target >= candidates[i]) {
                        elements.add(candidates[i]);
                        findCombination(i, target - candidates[i], elements);
                        elements.remove(elements.size() - 1);
                    } else {
                        break;
                    }
                }
            }

            @Override
            public int size() {
                if (ans == null) {
                    init();
                }
                return ans.size();
            }
        };
    }

}
