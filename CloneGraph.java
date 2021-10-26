/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    
    public Node cloneGraph(Node node) {
        return DFS(node);
    }
    
    // Using DFS
    private Node DFS(Node node) {
        if(node == null) return null;
        
        Stack<Node> stk = new Stack<>();
        stk.push(node);
        
        HashMap<Node, Node> visitedNode = new HashMap<>();
        visitedNode.put(node, new Node(node.val));
        
        while(!stk.isEmpty()) {
            Node curr = stk.pop();
            
            for(Node neighbour : curr.neighbors) {
                if(!visitedNode.containsKey(neighbour)) {
                    visitedNode.put(neighbour, new Node(neighbour.val));
                    stk.push(neighbour);
                }
                visitedNode.get(curr).neighbors.add(visitedNode.get(neighbour));
            }
        }
        return visitedNode.get(node);
    }
    
    // Using BFS
    private Node BFS(Node node) {
        if(node == null) return null;
        
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        
        HashMap<Node, Node> visitedNode = new HashMap<>();
        visitedNode.put(node, new Node(node.val));
        
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            for(Node neighbour : curr.neighbors) {
                if(!visitedNode.containsKey(neighbour)) {
                    Node clonedNode = new Node(neighbour.val);
                    visitedNode.put(neighbour, clonedNode);
                    q.add(neighbour);
                }
                
                // adding elements into cloned node adjancency list
                visitedNode.get(curr).neighbors.add(visitedNode.get(neighbour));
            }            
        }
        
        return visitedNode.get(node);
    }
}
