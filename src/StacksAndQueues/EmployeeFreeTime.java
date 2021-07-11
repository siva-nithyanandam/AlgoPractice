package StacksAndQueues;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/20/2021  10:51 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/uber/292/heap-queue-stack/1704/
 * https://leetcode.com/problems/employee-free-time/
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 *
 * Constraints:
 *
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */

class Interval {
  public int start;
  public int end;

  public Interval() {}

  public Interval(int _start, int _end) {
    start = _start;
    end = _end;
  }
};

public class EmployeeFreeTime {

  public static void main(String[] args) {
    EmployeeFreeTime o = new EmployeeFreeTime();
    System.out.println();
  }

  public List<Interval> employeeFreeTime(List<List<Interval>> scheduleList) {
    List<Interval> mergedInterval = mergeKSchedule(scheduleList, 0, scheduleList.size() - 1);
    List<Interval> res = new ArrayList<>();
    int start = mergedInterval.get(0).end;
    int end = 0;
    for(int i = 1; i < mergedInterval.size(); i++) {
      end = mergedInterval.get(i).start;
      if(start != end)
        res.add(new Interval(start, end));
      start = mergedInterval.get(i).end;
    }
    // for(Interval i : mergedInterval) {
    //     System.out.println(i.start + " " + i.end);
    // }
    return res;
  }

  private List<Interval> mergeKSchedule(List<List<Interval>> scheduleList, int start, int end) {
    if(start == end) {
      return scheduleList.get(start);
    }
    int mid = start + (end - start) / 2;
    List<Interval> left = mergeKSchedule(scheduleList, start, mid);
    List<Interval> right = mergeKSchedule(scheduleList, mid + 1, end);
    return merge(left, right);
  }

  private List<Interval> merge(List<Interval> a, List<Interval> b) {
    List<Interval> res = new ArrayList<>();
    int aIndex = 0;
    int bIndex = 0;
    while(aIndex < a.size() && bIndex < b.size()) {
      Interval aInterval = a.get(aIndex);
      Interval bInterval = b.get(bIndex);
      int left = 0;
      int right = 0;
      if(aInterval.end < bInterval.start) {
        left = aInterval.start;
        right = aInterval.end;
        aIndex++;
      }
      else if(bInterval.end < aInterval.start) {
        left = bInterval.start;
        right = bInterval.end;
        bIndex++;
      }
      else {
        left = Math.min(aInterval.start, bInterval.start);
        right = Math.max(aInterval.end, bInterval.end);
        aIndex++;
        bIndex++;
      }
      if(res.size() == 0 || res.get(res.size() - 1).end < left) {
        res.add(new Interval(left, right));
      }
      else {
        res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, right);
      }
    }
    while(aIndex < a.size()) {
      if(res.size() == 0 || res.get(res.size() - 1).end < a.get(aIndex).start) {
        res.add(a.get(aIndex));
      }
      else {
        res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, a.get(aIndex).end);
      }
      aIndex++;
    }
    while(bIndex < b.size()) {
      if(res.size() == 0 || res.get(res.size() - 1).end < b.get(bIndex).start) {
        res.add(b.get(bIndex));
      }
      else {
        res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, b.get(bIndex).end);
      }
      bIndex++;
    }
    return res;
  }
}
