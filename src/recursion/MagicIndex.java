package recursion;

/**
 * A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] = i.
 * Given a sorted array of distinct integers, write a method to find a magic index, if one exists,
 * in array A.
 * FOLLOW UP
 * What if the values are not distinct?
 */
public class MagicIndex {
    public static void main(String[] args) {
        int[] nums;

        nums = new int[]{-10, -5, 0, 3, 7};
        System.out.println(findMagicIndex(nums, 0, nums.length - 1));

        nums = new int[]{0, 2, 5, 8, 17};
        System.out.println(findMagicIndex(nums, 0, nums.length - 1));

        nums = new int[]{-10, -5, 3, 4, 7, 9};
        System.out.println(findMagicIndex(nums, 0, nums.length - 1));

        nums = new int[]{-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        System.out.println(findMagicIndexForNonDistinct(nums, 0, nums.length - 1));

        nums = new int[]{3, 3, 3, 3, 3};
        System.out.println(findMagicIndexForNonDistinct(nums, 0, nums.length - 1));

        nums = new int[]{0, 1, 2, 3, 3};
        System.out.println(findMagicIndexForNonDistinct(nums, 0, nums.length - 1));
    }

    private static int findMagicIndex(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == mid) {
            return mid;
        } else if (nums[mid] > mid) {
            return findMagicIndex(nums, start, mid - 1);
        } else {
            return findMagicIndex(nums, mid + 1, end);
        }
    }

    private static int findMagicIndexForNonDistinct(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == mid) {
            return mid;
        }
        int leftVal = findMagicIndexForNonDistinct(nums, start, mid - 1);
        if (leftVal > 0) {
            return leftVal;
        }
        return findMagicIndexForNonDistinct(nums, mid + 1, end);
    }
}
