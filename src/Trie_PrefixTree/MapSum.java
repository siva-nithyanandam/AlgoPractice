package Trie_PrefixTree;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/31/2022  1:01 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MapSum {

  public static void main(String[] args) {
    MapSum o = new MapSum();
    o.insert("apple",3);
    System.out.println(o.sum("ap"));
    o.insert("app",2);
    o.insert("apple",2);
    System.out.println(o.sum("ap"));
  }

  HashMap<String, Integer> map;
  TrieNode root;

  public MapSum() {
    map = new HashMap();
    root = new TrieNode();
  }
  public void insert(String key, int val) {
    int delta = val - map.getOrDefault(key, 0);
    map.put(key, val);
    TrieNode cur = root;
    cur.score += delta;
    for (char c: key.toCharArray()) {
      cur.children.putIfAbsent(c, new TrieNode());
      cur = cur.children.get(c);
      cur.score += delta;
    }
  }
  public int sum(String prefix) {
    TrieNode cur = root;
    for (char c: prefix.toCharArray()) {
      cur = cur.children.get(c);
      if (cur == null) return 0;
    }
    return cur.score;
  }
}
class TrieNode {
  Map<Character, TrieNode> children = new HashMap();
  int score;
}
