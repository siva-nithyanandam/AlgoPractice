package StacksAndQueues;

/**
 * Describe how could you use single array to implement three stacks.
 */
public class ThreeInOneStack {

  static class KStacks {
    int k;
    int n;
    int[] arr;
    int[] top;
    int[] next;
    int free = 0;

    public KStacks(int k, int n) {
      this.k = k;
      this.n = n;
      arr = new int[n];
      top = new int[k];
      next = new int[n];

      //Initialize
      for(int i = 0; i < k; i++) {
        top[i] = -1;
      }

      for(int i = 0; i < n-1; i++) {
        next[i] = i+1;
      }
      next[n-1] = -1;
    }

    public void push(int ks, int val) {

    }
  }

  public static void main(String[] args) {
    KStacks kStacks = new KStacks(3, 10);
    kStacks.push(1, 1);
  }
}
