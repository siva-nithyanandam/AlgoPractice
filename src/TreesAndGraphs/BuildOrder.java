package TreesAndGraphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given a list of projects and a list of dependencies(which is a list of pair of projects,
 * where the second project is dependent on first project). All of project's dependencies must be
 * build before the project is. Find a build order that will allow the projects to be built. If
 * there is no valid build order, return an error. Example: Input: projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 *
 * Output: f, e, a, b, d, c
 */

/**
 * Not Depth first search - Use Topological order
 */
public class BuildOrder {

  public static void main(String[] args) {
    char[] projects = {'f', 'b', 'c', 'e', 'a', 'd'};
    Map<Character, LinkedList<Character>> pd = new HashMap<>();
    insertDependencies(pd, 'a', 'd');
    insertDependencies(pd, 'f', 'b');
    insertDependencies(pd, 'b', 'd');
    insertDependencies(pd, 'f', 'a');
    insertDependencies(pd, 'd', 'c');

    List<Character> res = getOrder(projects, pd);
    for(char c : res) {
      System.out.println(c);
    }
  }

  private static List<Character> getOrder(char[] projects, Map<Character, LinkedList<Character>> pd) {
    Map<Character, Boolean> checked = new HashMap<>();
    List<Character> res = new LinkedList<>();
    for (char c : projects) {
      iterateOrder(c, pd, checked, res);
    }
    return res;
  }

  private static void iterateOrder(char src, Map<Character, LinkedList<Character>> pd,
      Map<Character, Boolean> checked, List<Character> res) {
    if (checked.get(src) == null || !checked.get(src)) {
      checked.put(src, true);
      if (pd.get(src) == null) {
        res.add(src);
      } else {
        for(char s : pd.get(src)) {
          iterateOrder(s, pd, checked, res);
        }
        res.add(src);
      }
    }
  }

  private static void insertDependencies(Map<Character, LinkedList<Character>> pd, char dependency,
      char dependent) {
    LinkedList<Character> list = pd.get(dependent);
    if (list == null) {
      list = new LinkedList<>();
    }
    list.add(dependency);
    pd.put(dependent, list);
  }
}
