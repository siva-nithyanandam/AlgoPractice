package StacksAndQueues;

/**
 * Describe how could you use single array to implement three stacks.
 */

/**
 * Bit complex logic.
 * https://www.geeksforgeeks.org/efficiently-implement-k-stacks-single-array/
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

    public boolean push(int val, int ks) {
      if (free == -1) {
        System.out.println("There is no space left!");
        return false;
      }
      int i = free;
      free = next[i];
      next[i] = top[ks];
      top[ks] = i;
      arr[i] = val;
      return true;
    }

    public int pop(int ks) {
      if(top[ks] == -1) {
        System.out.println("There is no element in this stack!");
      }
      int i = top[ks];
      top[ks] = next[i];
      next[i] = free;
      free = i;
      return arr[i];
    }
  }

  public static void main(String[] args) {
    int k = 3, n = 10;

    KStacks ks = new KStacks(k, n);

    ks.push(15, 2);
    ks.push(45, 2);

    // Let us put some items in stack number 1
    ks.push(17, 1);
    ks.push(49, 1);
    ks.push(39, 1);

    // Let us put some items in stack number 0
    ks.push(11, 0);
    ks.push(9, 0);
    ks.push(7, 0);

    System.out.println("Popped element from stack 2 is " + ks.pop(2));
    System.out.println("Popped element from stack 1 is " + ks.pop(1));
    System.out.println("Popped element from stack 0 is " + ks.pop(0));

    ks.push(7, 0);
    ks.push(7, 0);
    ks.push(7, 0);
    ks.push(7, 0);
    ks.push(7, 0);
    ks.push(7, 0);
  }
}
