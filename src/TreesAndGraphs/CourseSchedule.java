package TreesAndGraphs;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

  public static void main(String[] args) {
    CourseSchedule o = new CourseSchedule();
    System.out.println(o.canFinish(2, new int[][]{{0,1}}));
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {

    // course -> list of next courses
    HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

    // build the graph first
    for (int[] relation : prerequisites) {
      // relation[0] depends on relation[1]
      if (courseDict.containsKey(relation[1])) {
        courseDict.get(relation[1]).add(relation[0]);
      } else {
        List<Integer> nextCourses = new LinkedList<>();
        nextCourses.add(relation[0]);
        courseDict.put(relation[1], nextCourses);
      }
    }

    boolean[] path = new boolean[numCourses];

    for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
      if (this.isCyclic(currCourse, courseDict, path)) {
        return false;
      }
    }

    return true;
  }


  /*
   * backtracking method to check that no cycle would be formed starting from currCourse
   */
  protected boolean isCyclic(
      Integer currCourse,
      HashMap<Integer, List<Integer>> courseDict,
      boolean[] path) {

    if (path[currCourse]) {
      // come across a previously visited node, i.e. detect the cycle
      return true;
    }

    // no following courses, no loop.
    if (!courseDict.containsKey(currCourse))
      return false;

    // before backtracking, mark the node in the path
    path[currCourse] = true;

    // backtracking
    boolean ret = false;
    for (Integer nextCourse : courseDict.get(currCourse)) {
      ret = this.isCyclic(nextCourse, courseDict, path);
      if (ret)
        break;
    }
    // after backtracking, remove the node from the path
    path[currCourse] = false;
    return ret;
  }
}
