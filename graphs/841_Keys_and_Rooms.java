class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.push(0);

        //At the beginning, we have a todo list "stack" of keys to use.
        //'seen' represents at some point we have entered this room.
        while (!stack.isEmpty()) { // While we have keys...
            int node = stack.pop(); // Get the next key 'node'
            for (int nei: rooms.get(node)) // For every key in room # 'node'...
                if (!seen[nei]) { // ...that hasn't been used yet
                    seen[nei] = true; // mark that we've entered the room
                    stack.push(nei); // add the key to the todo list
                }
        }

        for (boolean v: seen)  // if any room hasn't been visited, return false
            if (!v) return false;
        return true;
    }
}

/* 
This is typical DFS problem. Recursively go to each room, 
if it has any keys, use them one by one i.e they are nothing but keys to other rooms,
so get key to next room one after the other, go to that next room,
find keys in the new room and repeat above step. i.e. Initially u r at room 0, 
get all possible keys in that room, for each key, go to that room, 
find keys in that room and go to those next next rooms,  repeat this step. 

Do you know DFS ? I am assuming yes. 
DFS is nothing without his friend visited boolean array.
He is the one using whom DFS will end the recursion,
else we will keep visiting rooms one after the other. 

*/


class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean visited[] = new boolean[rooms.size()];
        dfs(rooms.get(0), 0, rooms, visited);

        for(int i=0;i<visited.length;i++) {
            if(!visited[i]) {
                return false;
            }
        }
        return true; 
    }
    
    private void dfs(List<Integer> keysInRoom, int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true;

        for(Integer i: keysInRoom) {
            if(!visited[i]) {
                dfs(rooms.get(i), i, rooms, visited);
            }
        }
    }
}