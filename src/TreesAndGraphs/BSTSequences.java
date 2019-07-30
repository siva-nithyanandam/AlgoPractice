package TreesAndGraphs;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A binary search tree was created by traversing through an array from
 * left to right and inserting each element. Given a binary search tree with
 * distinct elements, print all possible arrays that could have led to this tree.
 */

/**
 * Tricky logic - First time takeout left node, second time takeout right node and
 * create all the probabilities.
 */
public class BSTSequences {
    static class Node {
        private int data;
        private Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(4);
        node.left = new Node(2);
        node.left.left = new Node(1);
        node.left.right = new Node(3);
        node.right = new Node(5);
        node.right.right = new Node(6);

        List<Node> nodes = new LinkedList<>();
        nodes.add(node);
        List<List<Integer>> oList = findSequences(nodes);
        for (List<Integer> l : oList) {
            for(Integer value : l) {
                System.out.print(value + ",");
            }
            System.out.print(System.getProperty("line.separator"));
        }
    }

    private static List<List<Integer>> findSequences(List<Node> nodes) {
        if (nodes == null) {
            return null;
        }
        List<List<Integer>> oList = new ArrayList<>();
        List<Node> choices;
        List<Integer> iList;
        for(Node node : nodes) {
            choices = new LinkedList<>();
            choices.addAll(nodes);
            choices.remove(node);
            if (node.left != null) {
                choices.add(node.left);
            }
            if (node.right != null) {
                choices.add(node.right);
            }
            if (choices.size() > 0) {
                List<List<Integer>> prevOList = findSequences(choices);
                for (List<Integer> l : prevOList) {
                    iList = new ArrayList<>();
                    iList.add(node.data);
                    for (int i : l) {
                        iList.add(i);
                    }
                    oList.add(iList);
                }
            } else {
                iList = new ArrayList<>();
                iList.add(node.data);
                oList.add(iList);
            }
        }
        return oList;
    }
}
