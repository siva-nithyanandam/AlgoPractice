package arrays;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique
 * permutations.
 * <p>
 * Example:
 * <p>
 * Input: [3,1,2] Output: [ [1,1,2], [1,2,1], [2,1,1] ]
 */

/**
 * Very tricky logic. Got to go through.
 * In a high level, Its pointing indexes in different levels
 */
public class PermutationsII {

    public static void main(String[] args) {
        List<List<Integer>> res = permuteUnique2(new int[]{1, 1, 2});
        for (List<Integer> i : res) {
            for (Integer j : i) {
                System.out.print(j + ",");
            }
            System.out.println("");
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private static void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);

            swap(nums, i, start);
            helper(start + 1, nums, result);
            swap(nums, i, start);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private static List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> subRes = new LinkedList<>();
        permuteUnique(nums, subRes, res);
        return res;
    }

    private static void permuteUnique(int[] nums, List<Integer> subRes, List<List<Integer>> res) {
        if (nums == null || nums.length == 0) {
            res.add(new LinkedList<>(subRes));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            subRes.add(nums[i]);
            int[] a = new int[nums.length - 1];
            System.arraycopy(nums, 0, a, 0, i);
            System.arraycopy(nums, i + 1, a, i, nums.length - i - 1);
            permuteUnique(a, subRes, res);
            subRes.remove(subRes.size() - 1);
        }
    }

    public static List<List<Integer>> permuteUnique2(int[] nums) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        boolean[] readState = new boolean[nums.length];
        Arrays.fill(readState, false);
        int[] set = new int[nums.length];
        dfs(nums, readState, set, 0, result);
        return result;
    }

    private static void dfs(int[] nums, boolean[] readState, int[] set, int count,
                            List<List<Integer>> result) {
        if (count == nums.length) {
            ArrayList<Integer> r = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                r.add(nums[set[i]]);
            }
            result.add(r);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !readState[i - 1]) {
                continue;
            }
            if (readState[i]) {
                continue;
            }
            readState[i] = true;
            set[count] = i;
            dfs(nums, readState, set, count + 1, result);
            set[count] = -1;
            readState[i] = false;
        }
    }
}
