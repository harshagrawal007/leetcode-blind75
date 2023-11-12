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
    private Map<Integer, Node> map;
    public Node cloneGraph(Node node) {
     
        if(node ==null){
            return null;
        }
        map = new HashMap<>();
        return dfs(node, node.val);
    }
    private Node dfs(Node node, int num){
        if(node == null) return null;
        else if(map.containsKey(num)) return map.get(num);
        else{
            Node curNode = new Node(num, new ArrayList<Node>());
            map.put(num, curNode);
            for(Node neighbour : node.neighbors){
                curNode.neighbors.add(dfs(neighbour, neighbour.val));
            }
            return curNode;
        }
    }
}
