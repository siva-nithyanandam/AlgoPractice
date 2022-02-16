package unionfind;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/18/2021  11:20 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/largest-component-size-by-common-factor/
 *
 * Given a non-empty array of unique positive integers nums, consider the following graph:
 *
 * There are nums.length nodes, labelled nums[0] to nums[nums.length - 1];
 * There is an edge between nums[i] and nums[j] if and only if nums[i] and nums[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,6,15,35]
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [20,50,9,63]
 * Output: 2
 *
 * Example 3:
 *
 * Input: nums = [2,3,6,7,4,12,21,39]
 * Output: 8
 *
 * Note:
 *
 * 1 <= nums.length <= 20000
 * 1 <= nums[i] <= 100000
 */
public class LargestComponentSizebyCommonFactor {

  public static void main(String[] args) {
    LargestComponentSizebyCommonFactor o = new LargestComponentSizebyCommonFactor();
    System.out.println(o.largestComponentSize_fastest(new int[]{4,6,15,35}));
    System.out.println(o.largestComponentSize_faster(new int[]{4,6,15,35}));
  }

  public int largestComponentSize_fastest(int[] A) {
    int max = Arrays.stream(A).max().getAsInt();
    boolean[] inA = new boolean[max + 1];
    for (int a: A) {
      inA[a] = true;
    }

    UnionFind uf = new UnionFind(max + 1, inA);

    boolean[] prime = new boolean[max + 1];
    Arrays.fill(prime, true);
    for (int i=2; i <= max; i++) {
      if (!prime[i]) continue;
      for (int j=i; j <= max; j+=i) {
        prime[j] = false;
        if (inA[j]) {
          uf.join(i, j);
        }
      }
    }

    int res = 0;
    for (int a: A) {
      res = Math.max(res, uf.size(a));
    }

    return res;
  }

  class UnionFind {
    int[] parent;
    int[] rank;
    int[] size;

    UnionFind(int n, boolean[] members) {
      parent = new int[n];
      rank = new int[n];
      size = new int[n];
      for (int i=0; i<n; i++) {
        parent[i] = i;
        if (members[i]) {
          size[i] = 1;
        }
      }
    }

    int find(int u) {
      if (parent[u] != u) {
        parent[u] = find(parent[u]);
      }
      return parent[u];
    }

    int size(int u) {
      return size[find(u)];
    }

    void join(int u, int v) {
      int ru = find(u);
      int rv = find(v);
      if (ru == rv) return;
      if (rank[ru] > rank[rv]) {
        parent[rv] = ru;
        size[ru] += size[rv];
      } else if (rank[ru] < rank[rv]) {
        parent[ru] = rv;
        size[rv] += size[ru];
      } else {
        parent[rv] = ru;
        rank[ru]++;
        size[ru] += size[rv];
      }
    }
  }

  public int largestComponentSize_faster(int[] nums) {
    int max = Arrays.stream(nums).reduce(nums[0], (a, b) -> a > b ? a : b);
    Map<Integer, Integer> numFactorMap = new HashMap<>();
    Map<Integer, Integer> groupCount = new HashMap<>();
    UnionFind_I uf = new UnionFind_I(max);

    for (int num : nums) {

      List<Integer> primes = new ArrayList<>(getPrimeFactors(num));
      numFactorMap.put(num, primes.get(0));

      for (int i = 0; i < primes.size() - 1; i++) {
        uf.union(primes.get(i), primes.get(i+1));
      }
    }

    int maxCount = 0;
    for (int num : nums) {
      int groupId = uf.find(numFactorMap.get(num));
      int count = groupCount.getOrDefault(groupId, 0);
      groupCount.put(groupId, count + 1);
      maxCount = Math.max(maxCount, count+1);
    }
    return maxCount;
  }

  class UnionFind_I {
    int[] parent;
    int[] sizes;

    UnionFind_I(int size) {
      this.parent = new int[size+1];
      this.sizes = new int[size+1];

      for (int i = 0; i < size+1; i++) {
        parent[i] = i;
        sizes[i] = 1;
      }
    }

    public int find(int x) {
      if (this.parent[x] != x) {
        this.parent[x] = find(this.parent[x]);
      }
      return this.parent[x];
    }

    public int union(int x, int y) {
      int px = find(x);
      int py = find(y);

      if (px == py) {
        return px;
      }

      if (px > py) {
        int temp = px ^ py;
        px = temp ^ py;
        py = temp ^ px;
      }

      this.parent[py] = px;
      this.sizes[px] += this.sizes[py];
      return px;
    }
  }

  private Set<Integer> getPrimeFactors(int num) {
    Set<Integer> res = new HashSet<>();

    int factor = 2;
    while(num >= factor * factor) {
      if (num % factor == 0) {
        res.add(factor);
        num = num/factor;
      } else {
        factor += 1;
      }
    }
    res.add(num);
    return res;
  }

  public int largestComponentSize(int[] A) {

    int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
    DisjointSetUnion dsu = new DisjointSetUnion(maxValue);
    // attribute each element to all the groups that lead by its factors.
    for (int num : A) {
      for (int factor=2; factor < (int)(Math.sqrt(num))+1; ++ factor)
        if (num % factor == 0) {
          dsu.union(num, factor);
          dsu.union(num, num / factor);
        }
    }

    // count the size of group one by one
    int maxGroupSize = 0;
    HashMap<Integer, Integer> groupCount = new HashMap<>();
    for (int num : A) {
      Integer groupId = dsu.find(num);
      Integer count = groupCount.getOrDefault(groupId, 0);
      groupCount.put(groupId, count+1);
      maxGroupSize = Math.max(maxGroupSize, count+1);
    }

    return maxGroupSize;
  }
}


class DisjointSetUnion {
  private int[] parent;
  private int[] size;

  public DisjointSetUnion(int size) {
    this.parent = new int[size + 1];
    this.size = new int[size + 1];
    for (int i = 0; i < size + 1; ++i) {
      this.parent[i] = i;
      this.size[i] = 1;
    }
  }

  /** return the component id that the element x belongs to. */
  public int find(int x) {
    if (this.parent[x] != x)
      this.parent[x] = this.find(this.parent[x]);
    return this.parent[x];
  }

  /**
   * merge the two components that x, y belongs to respectively,
   * and return the merged component id as the result.
   */
  public int union(int x, int y) {
    int px = this.find(x);
    int py = this.find(y);

    // the two nodes share the same group
    if (px == py)
      return px;

    // otherwise, connect the two sets (components)
    if (this.size[px] > this.size[py]) {
      // add the node to the union with less members.
      // keeping px as the index of the smaller component
      int temp = px;
      px = py;
      py = temp;
    }

    // add the smaller component to the larger one
    this.parent[px] = py;
    this.size[py] += this.size[px];
    return py;
  }
}
