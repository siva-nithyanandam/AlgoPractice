package Sliding;
/**
 * @time 5/5/24-11:39 AM
 * @author sivaprakashnithyanandam
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/description/?envType=daily-question&envId=2024-05-05
 * <p>
 * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 * Example 2:
 * <p>
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * Example 3:
 * <p>
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= people.length <= 5 * 104
 * 1 <= people[i] <= limit <= 3 * 104
 */
public class BoatstoSavePeople {

    public static void main(String[] args) {
        BoatstoSavePeople o = new BoatstoSavePeople();
        System.out.println(o.numRescueBoats_n(new int[]{3, 2, 2, 1}, 3));
        System.out.println(o.numRescueBoats(new int[]{1, 2}, 3));
    }

    /**
     * O(N logN) implementation:
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boats = 0;

        while (i <= j) {

            boats++;

            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
        }
        return boats;
    }

    /**
     * So, where's the bottleneck? the more complex part is the sorting part. The way to reduce that is if we can sort it any other way.
     * In this case, we can do it the Count sort way (if you know about LSB Radix sort, you got it)
     * Once the sorting comes down to O(N), the complexity comes down to O(N)
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats_n(int[] people, int limit) {
        int[] count = new int[limit + 1];// to keep the count
        for (int p : people) {
            count[p] += 1;// populate counts [1,1,2,2,2,3,3,4,5,5,3,3] becomes [0, 2, 3, 4, 1, 2]
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];// cumulative counts [0, 2, 5, 9, 10, 12]
        }
        // means 1 should be at position 0 and 1; 2 should be at 2, 3, 4;
//       for(int c: count) System.out.println(c);
        int[] sorted = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            sorted[count[people[i] - 1]++] = people[i];// now it's sorted. notice people[i]-1
        }
//       for(int s: sorted) System.out.println(s);
        int left = 0, right = people.length - 1, boats = 0;// same as Approach 01 from here on.
        while (left <= right) {
            int delta = limit - sorted[right];
            if (sorted[left] <= delta) left += 1;
            boats += 1;
            right -= 1;
        }
        return boats;
    }

    /**
     * https://leetcode.com/problems/boats-to-save-people/solutions/5112765/bucket-sort-o-n/?envType=daily-question&envId=2024-05-05
     *
     * Bucket Sorting
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats_n_v2(int[] people, int limit) {
        int count = 0;
        int[] buckets = new int[limit + 1];
        for (int weight : people) {
            buckets[weight]++;
        }
        int left = 0, right = limit;
        while (left <= right) {
            while (left <= right && buckets[left] <= 0) {
                left++;
            }
            while (left <= right && buckets[right] <= 0) {
                right--;
            }
            if (buckets[left] <= 0 && buckets[right] <= 0) {
                break;
            }
            buckets[left]-= (left + right <= limit) ? 1 : 0;
            count++;
            buckets[right]--;
        }
        return count;
    }
}
