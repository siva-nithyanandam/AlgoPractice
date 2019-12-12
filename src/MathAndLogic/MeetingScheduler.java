package MathAndLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 *
 *
 * Example 1:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */
public class MeetingScheduler {
    public static void main(String[] args) {
        MeetingScheduler o = new MeetingScheduler();
        List<Integer> res;
        res = o.minAvailableDuration(new int[][]{{10,50},{60,120},{140,210}},
                new int[][]{{0,15},{60,70}}, 8);
        System.out.println(res.size() > 0 ? res.get(0) + "," + res.get(1) : "no value");

        res = o.minAvailableDuration(new int[][]{{10,50},{60,120},{140,210}},
                new int[][]{{0,15},{60,70}}, 12);
        System.out.println(res.size() > 0 ? res.get(0) + "," + res.get(1) : "no value");
    }

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] i : slots1) {
            map.put(i[0], 1);
            map.put(i[1], -1);
        }
        for (int[] i : slots2) {
            if (map.get(i[0]) != null) {
                map.put(i[0], map.get(i[0]) + 1);
            } else {
                map.put(i[0], 1);
            }
            if (map.get(i[1]) != null) {
                map.put(i[1], map.get(i[1]) - 1);
            } else {
                map.put(i[1], -1);
            }
        }
        /*Integer[] arr = map.keySet().toArray(new Integer[map.size()]);
        Arrays.sort(arr);*/
        int cnt = 0, prev = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            if (cnt == 2 && key - prev >= duration) {
                res.add(prev);
                res.add(prev+duration);
                return res;
            }
            cnt += map.get(key);
            prev = key;
        }
        return res;
    }

    public List<Integer> minAvailableDuration_mem_exceeded(int[][] slots1, int[][] slots2, int duration) {
        int minLen = Math.min(slots1.length, slots2.length);
        List<Integer> res = new ArrayList<>();
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min,
                max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max;
        for (int[] i : slots1) {
            if (i[0] < min1) {
                min1 = i[0];
            }
            if (i[1] > max1) {
                max1 = i[1];
            }
        }
        for (int[] i : slots2) {
            if (i[0] < min1) {
                min1 = i[0];
            }
            if (i[1] > max1) {
                max1 = i[1];
            }
        }
        min = Math.min(min1, min2);
        max = Math.max(max1, max2);

        int[] open = new int[max - min + 1];
        int[] close = new int[max - min + 1];
        for (int[] i : slots1) {
            open[i[0]-min] += 1;
            open[i[1]-min] -= 1;
//            close[i[1]-min] += 1;
        }
        for (int[] i : slots2) {
            open[i[0]-min] += 1;
            open[i[1]-min] -= 1;
//            close[i[1]-min] += 1;
        }
        int openCount = 0, closeCount = 0;
        boolean set = false;
        int leftPos = -1;
        for (int i = 0; i < open.length; i++) {
            openCount += open[i];

            if (openCount == 2 && leftPos == -1) {
                leftPos = i;
            } else if (leftPos != -1 && openCount != 2){
                if (i - leftPos >= duration) {
                    res.add(leftPos+min);
                    res.add(leftPos+min+duration);
                    return res;
                }
                leftPos = -1;
            }
        }
        return res;
    }
}
