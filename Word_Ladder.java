class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList); // Insert all words from Dict in myset
      
        // Queue for BFS
        Queue<String> q = new LinkedList<>();
        q.add(beginWord); // start traversing from start node
        
        int depth = 0;
        while(!q.isEmpty()) {
            depth += 1; // depth
            int lsize = q.size(); // No of elements at a level
            
            // Traverse through each level
            while(lsize-- > 0) {
                char[] current = q.poll().toCharArray();
                
                // check for all possible words by changing 1 character at a time
                for(int i = 0; i < current.length; i++) {
                    char temp = current[i];
                    
                    for(char c = 'a'; c <= 'z'; c++) {
                        current[i] = c;
                        
                        String next = new String(current);
                        if(words.contains(next)) {
                            if(next.equals(endWord))
                                return depth + 1;
                            
                            q.add(next);
                            words.remove(next);
                        }
                    }
                  
                    // revert back to original after 
                    current[i] = temp;
                }
            }
        }
        
        return 0;
    }
  
  // Reference : https://www.youtube.com/watch?v=ZVJ3asMoZ18 
}
