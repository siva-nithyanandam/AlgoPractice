package Design; /**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/18/2021  4:11 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 *
 * Constraints:
 *
 * 0 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * At most 2 * 105 calls will be made to get and put.
 */


public class LFUCache {

  public static void main(String[] args) {
    LFUCache o = new LFUCache(2);
    o.put(1, 100);
    o.put(3, 300);
    o.put(2, 200);
    o.put(3, 350);
    System.out.println(o.get(200));
    o.put(4, 400);
  }

  class LinkedNode{
    int key;
    int val;
    int freq;
    LinkedNode prev;
    LinkedNode next;
    public LinkedNode(int key, int val){
      this.key = key;
      this.val = val;
      freq = 1;
    }
  }

  class DLinkedList{
    LinkedNode head;
    LinkedNode tail;
    int size;
    public DLinkedList(){
      head = new LinkedNode(-1,-1);
      tail = new LinkedNode(-1,-1);

      head.prev = null;
      head.next = tail;

      tail.prev = head;
      tail.next = null;
      size = 0;
    }

    public void remove(LinkedNode node){
      LinkedNode prev = node.prev;
      LinkedNode next = node.next;
      next.prev = prev;
      prev.next = next;
      size --;
    }

    public void addToFirst(LinkedNode node){
      LinkedNode next = head.next;
      node.prev = head;
      node.next = next;
      next.prev = node;
      head.next = node;
      size++;
    }

    public LinkedNode popTail(){
      LinkedNode lastNode = tail.prev;
      remove(lastNode);
      return lastNode;
    }
  }

  private Map<Integer,LinkedNode> cache;
  private Map<Integer,DLinkedList> frequency;
  private int capacity;
  private int minFreq;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    cache = new HashMap<Integer,LinkedNode>();
    frequency = new HashMap<Integer,DLinkedList>();
  }

  public int get(int key) {
    LinkedNode node = cache.get(key);
    if(node == null){
      return -1;
    }
    int value = node.val;
    updateFreq(node);
    return value;
  }

  private void updateFreq(LinkedNode node){
    //1. update frequency
    int oldFreq = node.freq;
    int newFreq = oldFreq+1;
    node.freq = newFreq;
    //remove node from oldFreq List
    DLinkedList oldFreqList = frequency.get(oldFreq);
    oldFreqList.remove(node);
    //add node to newFreq List
    //if newFreqList doesnot exist, create the entry
    if(!frequency.containsKey(newFreq)){
      frequency.put(newFreq, new DLinkedList());
    }
    //add node to newFreqList
    frequency.get(newFreq).addToFirst(node);
    //if oldFreqList doesnot contain any nodes, and minFreq == oldFreq, update minFreq to newFreq
    if(oldFreqList.size == 0 && minFreq == oldFreq)
      minFreq = newFreq;
  }

  public void put(int key, int value) {
    if(capacity < 1) return;
    LinkedNode node = cache.get(key);
    //if the node is already in the cache map
    if(node != null){
      updateFreq(node);
      node.val = value;
      return;
    }
    //if capacity == map size, remove LFU node with minFreq
    if(cache.size() == capacity){
      DLinkedList evictList = frequency.get(minFreq);
      LinkedNode evictNode = evictList.popTail();
      cache.remove(evictNode.key);
    }
    //add node to the map
    node = new LinkedNode(key,value);
    cache.put(key, node);

    //because adding a new node with freq = 1, so minFreq = 1 and add minFreq to the frequency map
    minFreq = 1;
    if(!frequency.containsKey(1)){
      frequency.put(1, new DLinkedList());
    }
    frequency.get(1).addToFirst(node);
  }

  //-------------------------------
  /*
  class Node implements Comparable<Node> {
    int key;
    int val;
    int ts;
    int freq;
    public Node(int key, int val, int ts, int freq) {
      this.key = key;
      this.val = val;
      this.ts = ts;
      this.freq = freq;
    }
    @Override
    public int compareTo(Node node) {
      if (this.freq == node.freq) {
        return this.ts - node.ts;
      }
      return this.freq - node.freq;
    }
  }

  int clock;
  int capacity;
  Map<Integer, Node> map;
  Queue<Node> queue;

  public LFUCache(int capacity) {
    this.clock = 0;
    this.capacity = capacity;
    this.map = new HashMap<>();
    this.queue = new PriorityQueue<>();
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    Node node = map.get(key);
    node.freq++;
    node.ts = clock;
    clock++;
    queue.remove(node);
    queue.offer(node);
    return node.val;
  }

  public void put(int key, int value) {
    if (get(key) == -1) {
      Node node = new Node(key, value, clock, 0);
      if (!map.isEmpty() && map.size() >= this.capacity) {
        Node toBeRemoved = queue.poll();
        map.remove(toBeRemoved.key);
      }
      if (map.size() < this.capacity) {
        map.put(key, node);
        queue.offer(node);
      }
    } else {
      Node node = map.get(key);
      node.val = value;
      queue.remove(node);
      queue.offer(node);
    }
    clock++;
  }*/
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */