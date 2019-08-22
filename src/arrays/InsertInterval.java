package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals;
        int[] newInterval;
        int[][] res;

        intervals = new int[][]{{1,3},{6,9}};
        newInterval = new int[]{2,5};
        res = insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i][0] + ", " + res[i][1]);
            System.out.println("");
        }

        System.out.println("");

        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{4,8};
        res = insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i][0] + ", " + res[i][1]);
            System.out.println("");
        }

        System.out.println("");

        intervals = new int[][]{{1,5}};
        newInterval = new int[]{2,7};
        res = insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i][0] + ", " + res[i][1]);
            System.out.println("");
        }

        System.out.println("");

        intervals = new int[][]{{1,5},{8,9}};
        newInterval = new int[]{2,10};
        res = insertFromInternet(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i][0] + ", " + res[i][1]);
            System.out.println("");
        }
    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {

        int i = 0;
        List<int[]> res = new ArrayList<>();
        while(i < intervals.length) {
            if (newInterval[0] <= intervals[i][1]) {
                if (newInterval[0] >= intervals[i][0]) {
                    res.add(new int[]{Math.min(newInterval[0], intervals[i][0]), newInterval[1]});
                } else {
                    res.add(new int[]{newInterval[0], newInterval[1]});
                }
                break;
            } else {
                res.add(new int[]{intervals[i][0], intervals[i][1]});
            }
            i++;
        }

        if (i == intervals.length) {
            res.add(new int[]{newInterval[0], newInterval[1]});
        }

        while(i < intervals.length) {
            if (newInterval[1] <= intervals[i][1]) {
                int[] s = res.get(res.size() - 1);
                if (newInterval[1] >= intervals[i][0]) {
                    s[1] = Math.max(newInterval[1], intervals[i][1]);
                    i++;
                } else {
                    s[1] = newInterval[1];
                }
                break;
            }
            i++;
        }

        while(i < intervals.length) {
            res.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        int[][] resArr = new int[res.size()][2];
        for(int j = 0; j < res.size(); j++) {
            resArr[j][0] = res.get(j)[0];
            resArr[j][1] = res.get(j)[1];
        }
        return resArr;
    }

    public static int[][] insertFromInternet(int[][] intervals, int[] newInterval) {
        //int[][] res = new int[][]{};
        int len = intervals.length;
        if(intervals==null || intervals.length==0){
            return new int[][]{{newInterval[0],newInterval[1]}};
        }
        int start = newInterval[0];
        int end = newInterval[1];
        if(start>intervals[len-1][1]){
            int[][] res= new int[len+1][2];
            for(int i=0; i<len; ++i){
                res[i][0] = intervals[i][0];
                res[i][1] = intervals[i][1];
            }
            res[len][0] = newInterval[0];
            res[len][1] = newInterval[1];
            return res;
        }
        else if(end<intervals[0][0]){
            int[][] res= new int[len+1][2];
            res[0][0] = newInterval[0];
            res[0][1] = newInterval[1];
            for(int i=1; i<len+1; ++i){
                res[i][0] = intervals[i-1][0];
                res[i][1] = intervals[i-1][1];
            }
            return res;
        }

        int startposition = findstart(intervals,start);
        int endposition = findend(intervals,end);
        if(startposition==endposition){
            intervals[startposition][0] = Math.min(intervals[startposition][0],newInterval[0]);
            intervals[startposition][1] = Math.max(intervals[startposition][1],newInterval[1]);
            return intervals;
        }

        int[][] res = new int[intervals.length-(endposition-startposition)][2];
        for(int i=0; i<startposition; ++i){
            res[i][0] = intervals[i][0];
            res[i][1] = intervals[i][1];
        }
        res[startposition][0] = Math.min(intervals[startposition][0],newInterval[0]);
        res[startposition][1] = Math.max(intervals[endposition][1],newInterval[1]);

        for(int i=startposition+1; i<res.length; ++i){
            res[i][0] = intervals[i-startposition+endposition][0];
            res[i][1] = intervals[i-startposition+endposition][1];
        }
        return res;
    }

    private static int findstart(int[][] intervals, int start){
        int left = 0;
        int right = intervals.length-1;
        while(left<right-1){
            int mid = left + (right-left)/2;
            if(intervals[mid][1]==start){
                return mid;
            }
            else if(intervals[mid][1]>start){
                right = mid;
            }
            else{
                left = mid;
            }
        }
        if(intervals[left][1]>=start)
            return left;
        else
            return right;
    }

    private static int findend(int[][] intervals, int end){
        int left = 0;
        int right = intervals.length-1;
        while(left<right-1){
            int mid = left + (right-left)/2;
            if(intervals[mid][0]==end){
                return mid;
            }
            else if(intervals[mid][0]>end){
                right = mid;
            }
            else{
                left = mid;
            }
        }
        if(intervals[right][0]<=end)
            return right;
        else
            return left;
    }
}
