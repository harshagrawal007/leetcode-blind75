/**
 * LeetCode Problems: 210 (Course Schedule II) & 269 (Alien Dictionary)
 * 
 * PROBLEM: Find valid ordering of courses based on prerequisites (topological sort)
 *          Also determine character order in alien dictionary from sorted words
 * 
 * APPROACH: Kahn's Algorithm (BFS using Indegree)
 * PATTERN: Topological Sort
 * 
 * KEY CONCEPTS:
 * - Indegree = number of dependencies/prerequisites for a node
 * - Process nodes with 0 indegree first (no dependencies)
 * - Detect cycles: if no node has 0 indegree but graph not empty = cycle exists
 * 
 * ALGORITHM TEMPLATE:
 * 1. Init: Create indegree map & adjacency list
 * 2. Build Graph: For each edge, increment child's indegree & add to parent's list
 * 3. Process: Find nodes with 0 indegree, add to result, decrement children's indegree
 * 4. Validate: If processed count != total count, cycle exists
 */

// TOPOLOGICAL SORT - KAHN'S ALGORITHM (Indegree-based)
// Use when: Finding task order, detecting cycles, ordering with dependencies
// Time: O(V+E) where V=vertices, E=edges | Space: O(V+E) for storing graph
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Topological sort
        // Edge case
        if(numCourses <= 0) return new int[0];
        
        //1. Init Map
        // STEP 1: BUILD - Initialize indegree & adjacency list
        // Indegree = dependency count (how many prerequisites each course has)
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        HashMap<Integer, List<Integer>> topoMap = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
            topoMap.put(i, new ArrayList<Integer>());
        }
        
        //2. Build Map
        // Build the graph: prerequisites[i] = [courseA, courseB] means courseB → courseA
        for(int[] pair : prerequisites) {
            int curCourse = pair[0], preCourse = pair[1];
            topoMap.get(preCourse).add(curCourse);  // put the child into it's parent's list
            inDegree.put(curCourse, inDegree.get(curCourse) + 1); // increase child inDegree by 1
        }
        //3. find course with 0 indegree, minus one to its children's indegree, until all indegree is 0
        // STEP 3: PROCESS - Find & eliminate nodes with 0 dependencies
        // Key insight: A node with indegree 0 can be done immediately
        int[] res = new int[numCourses];
        int base = 0;
        while(!inDegree.isEmpty()) {
            boolean flag = false;   // use to check whether there is cycle
            for(int key : inDegree.keySet()) {  // find nodes with 0 indegree
                if(inDegree.get(key) == 0) {
                    res[base++] = key; // Add to result
                    flag = true;
                    // Decrement all dependent nodes
                    List<Integer> children = topoMap.get(key);  // get the node's children, and minus their inDegree
                    for(int child : children) 
                        inDegree.put(child, inDegree.get(child) - 1);
                     // Mark as processed
                    inDegree.remove(key); // remove the current node with 0 degree and start over
                    break;
                }
            }
             // STEP 4: VALIDATE - Check for cycles
            if(!flag)  // there is a circle --> All Indegree are not 0
                return new int[0];
        }
        return res;
    }
}
// ============================================================================
// SOLUTION 2: ALIEN DICTIONARY (LeetCode 269)
// ============================================================================
// Problem: Given sorted words in alien language, find character ordering
// 
// Insight: Compare consecutive words to deduce character order
// - If word1[i] != word2[i], then word1[i] → word2[i] in the language
// - Check for invalid case: word1 is longer but is prefix of word2 = invalid order
// ============================================================================
public class Solution {
    /**
     * Complexity: Time O(N*L + U + E) | Space O(U + E)
     * where N = number of words, L = max word length, U = unique chars, E = edges
     */
    public String alienOrder(String[] words) {
        // STEP 0: Validate input
        // Edge case
        if(words == null || words.length == 0) return "";

         for(int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // INVALID CASE: word1 is prefix of word2 but longer
            // Example: ["abc", "ab"] is impossible order
            // If word1 should come before word2, but word1 is longer, invalid
            if(word1.startsWith(word2) && word1.length() > word2.length()) {
                return "";
            }
            // Alternative: if word2 is a prefix of word1 and word2 is shorter
            if(word2.length() < word1.length() && word1.startsWith(word2)) {
                return "";
            }
        }
        
        // STEP 1: Init inDegree & topoMap
        // Extract all unique characters from all words
        HashMap<Character, Integer> inDegree = new HashMap<>();
        HashMap<Character, List<Character>> topoMap = new HashMap<>();
        for(String word : words)
            for(char c : word.toCharArray()) {
                inDegree.put(c, 0);
                topoMap.put(c, new ArrayList<Character>());
            }
        
        // STEP 2: Build Map - Find character ordering constraints
        // Only differ at position j? Then parent[j] → child[j]
        for(int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            // Compare character by character
            for(int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char parent = w1.charAt(j), child = w2.charAt(j);
                // First difference determines the order
                if(parent != child) {
                    inDegree.put(child, inDegree.get(child) + 1);
                    topoMap.get(parent).add(child);
                    break;  // Only one constraint per word pair
                }
            }
        }
        
        // STEP 3: Topo sort using Kahn's algorithm
        StringBuilder res = new StringBuilder();
        while(!inDegree.isEmpty()) {
            boolean flag = false;
            // Find character with 0 dependencies
            for(Character c : inDegree.keySet()) {
                if(inDegree.get(c) == 0) {
                    flag = true;
                    res.append(c);  // Add to result (correct order)
                    List<Character> children = topoMap.get(c);
                    // Decrement all dependent characters
                    for(Character ch : children)
                        inDegree.put(ch, inDegree.get(ch) - 1);
                    inDegree.remove(c);  // Process this character
                    break;
                }
            }
            // STEP 4: Detect cycle
            if(flag == false)
                return "";  // No valid ordering exists (cycle detected)
        }
        return res.toString();
    }
 
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            indegree[pre[1]]++;
            adj.get(pre[0]).add(pre[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int finish = 0;
        int[] output = new int[numCourses];
        while (!q.isEmpty()) {
            int node = q.poll();
            output[numCourses - finish - 1] = node;
            finish++;
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        if (finish != numCourses) {
            return new int[0];
        }
        return output;
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS - All Solutions Above
 * ============================================================================
 * Time Complexity: O(V + E)
 *   - V = number of courses
 *   - E = number of prerequisites (edges)
 *   - We visit each course once: O(V)
 *   - We process each prerequisite once: O(E)
 *   - Total: O(V + E)
 * 
 * Space Complexity: O(V + E)
 *   - inDegree/indegree map: O(V)
 *   - adjacency list/topoMap: O(V + E)
 *   - Queue (solution 3): O(V) in worst case
 *   - Total: O(V + E)
 * 
 * WHY THIS IS OPTIMAL:
 * - We must examine every node and edge at least once = lower bound O(V+E)
 * - This solution achieves that bound = optimal
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Donald Knuth - "The Art of Computer Programming, Vol. 1"
 *    - Introduced topological sorting fundamentals
 *    - Foundation for dependency resolution in computing
 * 
 * 2. Alfred Aho et al. - "Algorithms: Design and Analysis" / Cormen et al. "CLRS"
 *    - Detailed analysis of Kahn's Algorithm (BFS approach)
 *    - Proof of correctness for cycle detection
 *    - Comparison with DFS-based topological sort
 * 
 * 3. LeetCode Discussion Forum (Community Solutions)
 *    - Indegree vs DFS approach comparison
 *    - Practical pattern matching for toposort-based problems
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 207 (Course Schedule) - Binary validation version
 * - LeetCode 269 (Alien Dictionary) - Character ordering from word list
 * - LeetCode 2192 (All Ancestors of a Node in a DAG) - Graph traversal
 * - LeetCode 444 (Sequence Reconstruction) - Verify order possibility
 * ============================================================================
 */