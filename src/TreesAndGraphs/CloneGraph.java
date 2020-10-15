package TreesAndGraphs;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 *
 *
 * Example:
 *
 *
 *
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 *
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 *
 *
 * Note:
 *
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 */
public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(node2);
        nodeList.add(node4);
        node1.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node3);
        node2.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node2);
        nodeList.add(node4);
        node3.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node3);
        node4.neighbors = nodeList;

        CloneGraph o = new CloneGraph();
        Node res = o.cloneGraph_faster(node1);
        System.out.println("Hi");
    }

    HashSet<Node> set = new HashSet<>();
    HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph_faster(Node node) {
        return helper(node);
    }
    public Node helper(Node node){
        if(node == null) {
            return null;
        }
        if(!map.containsKey(node)){
            map.put(node, new Node(node.val, new ArrayList<>()));
        }else{
            return map.get(node);
        }
        Node cpNode = map.get(node);
        if(cpNode.neighbors == null){
            cpNode.neighbors = new ArrayList<>();
        }
        List<Node> ajs = cpNode.neighbors;
        for(Node n: node.neighbors){
            ajs.add(helper(n));
        }
        return cpNode;
    }

    public Node cloneGraph(Node start) {
        if (start == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        Map<Node, Node> vertexMap = new HashMap<>();
        vertexMap.put(start, new Node(start.val, new ArrayList<>()));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            for (Node neighbor : curr.neighbors) {
                if (!vertexMap.containsKey(neighbor)) {
                    vertexMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }
                vertexMap.get(curr).neighbors.add(vertexMap.get(neighbor));
            }
        }
        return vertexMap.get(start);
    }
}
