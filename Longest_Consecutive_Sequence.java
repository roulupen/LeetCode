class Solution {
    
    /*
      Algorithm:
      1) Store all the elements into a Set, initialize longest sequence  = 0
      2) Loop over all eleemnts
        2.a) If Set does not contains num - 1, means this is starting of a new sequence, set current sequence as 1
        2.b) Increase the current number by one by one until the elements are present in the set
          - increase the sequence by one in every iteration
        2.c) Check if current sequence is greater than longest sequence then assign longest sequence as current sequence.
      3) return longest sequence
    */
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        
        for(int num : nums)
            num_set.add(num);
        
        int longestSeq = 0;
        for(int num : nums) {
            
            if(!num_set.contains(num - 1)) {
                int currentSeq = 1;
                int currentNum = num;
                
                while(num_set.contains(currentNum + 1)) {
                    currentNum++;
                    currentSeq++;
                }
                
                longestSeq = Math.max(longestSeq, currentSeq);
            }
            
        }
        
        
        return longestSeq;
    }
}
