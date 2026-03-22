/**
 * LeetCode 261: Graph Valid Tree
 * <p>
 * PROBLEM: Given n nodes (0 to n-1) and edges, determine if the graph is a valid tree.
 * A valid tree has exactly n-1 edges and is connected (no cycles).
 * <p>
 * Example: n=5, edges=[[0,1],[0,2],[0,3],[1,4]] → true (tree)
 * n=5, edges=[[0,1],[1,2],[2,0],[1,3],[1,4]] → false (cycle)
 * <p>
 * APPROACH: DFS Cycle Detection + Connectivity Check
 * PATTERN: Tree validation using graph traversal
 * <p>
 * KEY INSIGHT:
 * - Tree properties: |E| = |V| - 1 AND connected AND acyclic
 * - Use DFS to detect cycles and ensure all nodes visited
 * - Parent tracking prevents false cycle detection in undirected graph
 */

/**
 * SOLUTION: DFS with Cycle Detection
 * Time: O(V + E) | Space: O(V + E)
 *
 * ALGORITHM:
 * 1. Build adjacency list from edges
 * 2. Start DFS from node 0 (assuming connected component)
 * 3. DFS detects cycles using visited set and parent tracking
 * 4. Check if all n nodes were visited (connectivity)
 *
 * WHY THIS WORKS:
 * - DFS with parent prevents revisiting parent (undirected graph issue)
 * - Cycle detected if visiting already visited node (not parent)
 * - Tree requires exactly n-1 edges (checked implicitly by connectivity)
 */
public class Solution {

    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    /**
     * Validates if the given graph is a valid tree.
     * @param n Number of nodes (0 to n-1)
     * @param edges Array of undirected edges [u,v]
     * @return true if graph is a valid tree, false otherwise
     */
    public boolean validTree(int n, int[][] edges) {
        // EDGE CASES: Handle small graphs
        // Empty/single node is valid tree
        if (n == 0 || n == 1)
            return true;
        // Multiple nodes need edges
        if (edges.length == 0)
            return false;
        // BUILD GRAPH: Create adjacency list
        for (var edge : edges) {
            var node1 = edge[0];
            var node2 = edge[1];
            adjacencyList.putIfAbsent(node1, new ArrayList<>());
            adjacencyList.putIfAbsent(node2, new ArrayList<>());
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
        }

        Set<Integer> visited = new HashSet<>();
        // DFS FROM NODE 0: Check for cycles and connectivity
        // If DFS returns true (no cycles) AND all nodes visited → valid tree
        return (depthFirstSearch(edges[0][0], -1, visited) && visited.size() == n);
    }

    /**
     * DFS traversal to detect cycles in undirected graph.
     * @param node Current node being visited
     * @param previous Parent node (to avoid false cycle detection)
     * @param visited Set of visited nodes
     * @return true if no cycle found from this subtree, false otherwise
     */
    private boolean depthFirstSearch(int node, int previous, Set<Integer> visited) {
        if (visited.contains(node))
            return false;

        visited.add(node);
        // EXPLORE NEIGHBORS: Recursively check all adjacent nodes
        for (var neighbor : adjacencyList.get(node)) {
            // SKIP PARENT: Don't go back to parent in undirected graph
            if (neighbor == previous)
                continue;
            // RECURSIVE CHECK: If any subtree has cycle, propagate false
            if (!depthFirstSearch(neighbor, node, visited))
                return false;
        }

        return true;// No cycles in this subtree
    }
}


/* ============================================================================
 * ALGORITHM WALKTHROUGH
 * ============================================================================
 * Input: n=5, edges=[[0,1],[0,2],[0,3],[1,4]]
 *
 * Adjacency List:
 * 0: [1,2,3]
 * 1: [0,4]
 * 2: [0]
 * 3: [0]
 * 4: [1]
 *
 * DFS from node 0 (parent=-1):
 * - Visit 0, add to visited: {0}
 * - Neighbors: 1,2,3
 * - DFS(1, 0):
 *   - Visit 1, add to visited: {0,1}
 *   - Neighbors: 0,4 (skip parent 0)
 *   - DFS(4, 1):
 *     - Visit 4, add to visited: {0,1,4}
 *     - Neighbors: 1 (skip parent 1)
 *     - Return true
 *   - Return true
 * - DFS(2, 0):
 *   - Visit 2, add to visited: {0,1,4,2}
 *   - Neighbors: 0 (skip parent 0)
 *   - Return true
 * - DFS(3, 0):
 *   - Visit 3, add to visited: {0,1,4,2,3}
 *   - Neighbors: 0 (skip parent 0)
 *   - Return true
 *
 * Result: DFS returns true, visited.size()==5 → Valid tree ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * TIME COMPLEXITY: O(V + E)
 * - Building adjacency list: O(E)
 * - DFS traversal: O(V + E) visits each node/edge once
 * - Overall: O(V + E) where V=n nodes, E=edges.length
 *
 * SPACE COMPLEXITY: O(V + E)
 * - Adjacency list: O(V + E) for graph storage
 * - Visited set: O(V) for tracking visited nodes
 * - Recursion stack: O(V) worst case (linear graph)
 * - Overall: O(V + E)
 *
 * WHY EFFICIENT:
 * - Linear in graph size, optimal for graph problems
 * - DFS is simple and effective for cycle detection
 * ============================================================================
 */

/* ============================================================================
 * KEY INSIGHTS & EDGE CASES
 * ============================================================================
 * 1. TREE PROPERTIES:
 *    - Connected: All nodes reachable (visited.size() == n)
 *    - Acyclic: No cycles detected during DFS
 *    - |E| = |V| - 1: Implied by above (not explicitly checked)
 *
 * 2. CYCLE DETECTION IN UNDIRECTED GRAPHS:
 *    - Use visited set to track explored nodes
 *    - Parent parameter prevents false positives
 *    - Early return on cycle detection
 *
 * 3. EDGE CASES:
 *    - n=0: Valid tree (empty graph)
 *    - n=1: Valid tree (single node)
 *    - n>1, edges=0: Invalid (disconnected)
 *    - Self-loops: Would be detected as cycles
 *    - Multiple components: visited.size() < n
 *
 * 4. WHY START FROM edges[0][0]:
 *    - Assumes graph is connected (tree property)
 *    - If disconnected, visited.size() < n will catch it
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Chapter 22: Elementary Graph Algorithms
 *    - DFS for cycle detection in undirected graphs
 *    - Tree validation algorithms
 *
 * 2. Sedgewick & Wayne - "Algorithms (4th Edition)"
 *    - Graph connectivity and cycle detection
 *    - Union-Find vs DFS approaches for trees
 *
 * 3. Skiena - "The Algorithm Design Manual"
 *    - Graph traversal techniques
 *    - Tree recognition problems
 *
 * RELATED PROBLEMS:
 * - LeetCode 323 (Number of Connected Components) - Count components
 * - LeetCode 684 (Redundant Connection) - Find cycle edge
 * - LeetCode 685 (Redundant Connection II) - Directed graph cycles
 * ============================================================================
 */
