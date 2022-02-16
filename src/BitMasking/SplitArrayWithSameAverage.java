package BitMasking;

import java.util.*;

/**
 * https://leetcode.com/problems/split-array-with-same-average/
 *
 * You are given an integer array nums.
 *
 * You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).
 *
 * Return true if it is possible to achieve that and false otherwise.
 *
 * Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
 * Example 2:
 *
 * Input: nums = [3,1]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 104
 */
public class SplitArrayWithSameAverage {

    public static void main(String[] args) {
        SplitArrayWithSameAverage o = new SplitArrayWithSameAverage();
        System.out.println(o.splitArraySameAverage_self(new int[]{17,3,7,12,1}));
        System.out.println(o.splitArraySameAverage_self(new int[]{1,2,3,4,5,6,7,8}));
    }

    public boolean splitArraySameAverage_self(int[] nums) {

        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int total = Arrays.stream(nums).sum();
        Boolean[][][] visited = new Boolean[n][n][total];

        return dp(nums, visited, 0, 0, 0, total, n);
    }

    private boolean dp(int[] nums, Boolean[][][] visited, int idx, int len, int sum, int total, int n) {

        if (len >= n/2 || idx >= n) {
            return (total - sum) * len == (sum * (n-len)) && len > 0 && (n-len > 0);
        }

        if (visited[idx][len][sum] != null) {
            return visited[idx][len][sum];
        }

        boolean incl = dp(nums, visited, idx+1, len+1, sum+nums[idx], total, n);
        boolean excl = dp(nums, visited, idx+1, len, sum, total, n);

        boolean ans = incl || excl;
        visited[idx][len][sum] = ans;
        return ans;
    }

    public boolean splitArraySameAverage_16ms(int[] A) {
        int aLength = A.length;
        if (aLength < 2) {
            return false;
        }
        if (aLength == 2) {
            return A[0] == A[1];
        }
        int sum = 0;
        for (int i = 0; i < aLength; i++) {
            A[i] = A[i] * aLength;
            sum += A[i];
        }
        //Average of arrays
        int average = sum / aLength;
        //Define a negative List (all elements less than average)
        List<Integer> listM = new ArrayList<>();
        //Define a positive List (all elements greater than average)
        List<Integer> listP = new ArrayList<>();
        //Sum of all elements in the Negative List. (To be used later)
        int mSum = 0;
        //Cyclic array, de-averaging each element and saving elements in negative list and positive list respectively
        for (int i = 0; i < aLength; i++) {
            int a = A[i];
            int newA = a - average;
            //Return True directly if the//element is 0 off average.
            if (newA == 0) {
                return true;
            }
            //element greater than 0, put in positive list
            if (newA > 0) {
                //If the negative List has the same element, return True
                if (listM.contains(newA)) {
                    return true;
                }
                listP.add(newA);
            }
            //element less than 0, put negative List after absolute value
            else {
                // Masanon Joka List of homologous elements, return true
                if (listP.contains(Math.abs(newA))) {
                    return true;
                }
                //Sum of all elements in the Negative List. (To be used later)
                mSum += Math.abs(newA);
                listM.add(Math.abs(newA));
            }
        }
        //Traverses the sum of all possible combinations in a negative array
        Set<Integer> mSumSet = new HashSet<>();
        for (int m : listM) {
            Set<Integer> mSumSetTemp = new HashSet<>();
            for (int mm : mSumSet) {
                mSumSetTemp.add(m + mm);
            }
            mSumSet.addAll(mSumSetTemp);
            mSumSet.add(m);
        }
        //Remove the sum of all elements of the negative array because the subset is to be true
        mSumSet.remove(Integer.valueOf(mSum));
        //Traverses the sum of all possible combinations in a positive array
        Set<Integer> pSumSet = new HashSet<>();
        for (int p : listP) {
            Set<Integer> pSumSetTemp = new HashSet<>();
            for (int pp : pSumSet) {
                //Returns True if the value exists in a negative array
                if (mSumSet.contains(p + pp)) {
                    return true;
                }
                pSumSetTemp.add(p + pp);
            }
            pSumSet.addAll(pSumSetTemp);
            //Returns True if the value exists in a negative array
            if (mSumSet.contains(p)) {
                return true;
            }
            pSumSet.add(p);
        }
        return false;
    }

    public boolean splitArraySameAverage_dup(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int i : A) {
            sum = sum + i;
        }

        boolean[][][] memo = new boolean[A.length][sum + 1][A.length];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                for (int k = 0; k < memo[i][j].length; k++) {
                    memo[i][j][k] = true;

                }
            }
        }

        float s1 = 0;
        for (int i = 1; i < A.length; i++) {
            s1 = (float) (sum * i) / n;
            if (s1 % 1 == 0) {
                if (helper(A, (int) s1, i, 0, memo))
                    return true;
            }
        }
        return false;
    }

    boolean helper(int[] A, int sum, int cnt, int index, boolean[][][] memo) {

        if (cnt == 0)
            return sum == 0;
        if (index > A.length - 1)
            return false;
        if (!memo[index][sum][cnt])
            return false;
        if (A[index] <= sum) {
            if (helper(A, sum - A[index], cnt - 1, index + 1, memo)) {
                memo[index][sum][cnt] = true;
                return true;
            }
        }
        if (helper(A, sum, cnt, index + 1, memo)) {
            memo[index][sum][cnt] = true;
            return true;
        }
        memo[index][sum][cnt] = false;
        return false;
    }


    public boolean splitArraySameAverage(int[] nums) {
        Boolean[][][] dp = new Boolean[nums.length][nums.length / 2][100000];
        return go(nums, 0, 0, 0, Arrays.stream(nums).sum(), dp);
    }

    public boolean go(int[] arr, int idx, int sum, int len, int total, Boolean[][][] dp) {
        if (idx >= arr.length || len >= arr.length / 2) {
            return (sum * (arr.length - len) == (total - sum) * len) && len > 0 && (arr.length - len > 0);
        }
        if (dp[idx][len][sum] != null) {
            return dp[idx][len][sum];
        }
        boolean incl = go(arr, idx + 1, sum + arr[idx], len + 1, total, dp);
        boolean excl = go(arr, idx + 1, sum, len, total, dp);

        dp[idx][len][sum] = incl || excl;
        return incl || excl;
    }


}
