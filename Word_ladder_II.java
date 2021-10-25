class Solution {
    // Final result will be stored
    List<List<String>> ans = new ArrayList<>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Map<String, Set<String>> adj = new HashMap<>();

        //STEP-1: Find min-depth using BFS
        Queue<String> q = new LinkedList<>(); //For BFS traversal
        q.add(beginWord); //Add start node (beginWord)

        Map<String, Integer> visited = new HashMap<>(); // Key->String (Node)...Value->Level (Depth of traversal)
        visited.put(beginWord, 0); // Start node will always be at level 0

        while(!q.isEmpty()) {
            String currStr = q.poll();
            char[] current = currStr.toCharArray();

            // Find all possible adjacent words by changing exactly one character in a word everytime
            for(int i = 0; i < current.length; i++) {
                char temp = current[i];

                //Try all possible 26 letters
                for(char c = 'a'; c <= 'z'; c++) {
                    current[i] = c;
                    String next = new String(current);
                    
                    // Check if new word is present in word set
                    if(words.contains(next)) { 
                        if(!visited.containsKey(next)) { // if the new word was not visited
                            // then store in visisted and increase depth by 1 & add to
                            // add the new word to the queue
                            // add to adjcent map
                            visited.put(next, visited.get(currStr) + 1);
                            q.add(next);
                            adj.computeIfAbsent(currStr, k -> new HashSet<>()).add(next);
                        } else if (visited.get(next) == visited.get(currStr) + 1){ // if the new word present in visisted and is a child to the current string
                            // store into the adjacent map
                            adj.computeIfAbsent(currStr, k -> new HashSet<>()).add(next);
                        }
                    }
                }
                current[i] = temp;
            }
        }
        
        //STEP-2: Find all possible paths at min-depth using DFS 
        DFS(beginWord, endWord, adj, new ArrayList<>());

        return ans;
    }
    
    void DFS(String beginWord, String endWord, Map<String, Set<String>> adj, List<String> path) {
      
        // Add current word into the path
        path.add(beginWord);
        
        // if begin word same as end word then reached end, so add the path into ans
        if(beginWord.equals(endWord)) {
            ans.add(new ArrayList<>(path));
          
            // remove the last inserted word into the path for backtracking
            path.remove(path.size() - 1);
            return;
        }

        if(adj.containsKey(beginWord)) {
            for(String x : adj.get(beginWord))
                DFS(x, endWord, adj, path);
        }
        
        // remove the last inserted word into the path for backtracking
        path.remove(path.size() - 1);
    }
}
