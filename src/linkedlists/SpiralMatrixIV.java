package linkedlists;

/**
 * You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1's digit is at the head
 * of the list. Write a function that adds the two numbers and returns the sum as a
 * linked list.
 * Example
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295
 * Output: 2 -> 1 -> 9. That is, 912.
 *
 * Follow Up:
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Example
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295
 * Output: 9 -> 1 -> 2. That is, 912.
 */

/**
 * For #2 problem, its bit tricky. Take only matching lenghts of two lists and add.
 * Once thats complete, take remaining
 * numbers from big list and add it.
 */
public class SpiralMatrixIV {

    static class Node {
        int val;
        Node next;

        Node(int data) {
            this.val = data;
        }
    }
    public static void main(String[] args) {
        Node list1 = new Node(3);
        list1.next = new Node(0);
        list1.next.next = new Node(2);
        list1.next.next.next = new Node(6);
        list1.next.next.next.next = new Node(8);
        list1.next.next.next.next.next = new Node(1);
        list1.next.next.next.next.next.next = new Node(7);
        list1.next.next.next.next.next.next.next = new Node(9);
        list1.next.next.next.next.next.next.next.next= new Node(4);
        list1.next.next.next.next.next.next.next.next.next = new Node(2);
        list1.next.next.next.next.next.next.next.next.next.next = new Node(3);
        list1.next.next.next.next.next.next.next.next.next.next.next = new Node(4);

        SpiralMatrixIV o = new SpiralMatrixIV();
        o.spiralMatrix(3,4, list1);

    }

    Node curr;

    public int[][] spiralMatrix(int m, int n, Node head) {
        this.curr = head;

        int[][] res = new int[m][n];
        int roll = (m+1)/2;


        for (int i = 0; i < roll; i++) {

            for (int c = i; c < n-i; c++) {
                res[i][c] = nextVal();
            }

            for (int r = i+1; r < m-i; r++) {
                res[r][n-i-1] = nextVal();
            }

            for (int c = n-2-i; c >= i && i < m - i - 1; c--) {
                res[m-i-1][c] = nextVal();
            }

            for (int r = m-2-i; r > i; r--) {
                res[r][i] = nextVal();
            }
        }
        return res;
    }

    private int nextVal() {

        if (this.curr != null) {
            int val = this.curr.val;
            this.curr = this.curr.next;
            return val;
        } else {
            return -1;
        }
    }
}
