/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        
        int minKey = Integer.MAX_VALUE;
        int maxKey = Integer.MIN_VALUE;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<Integer, TreeNode>> q = new LinkedList<>();
        q.add(new Pair(0, root));
        
        while(!q.isEmpty()) {
            Pair<Integer, TreeNode> p = q.poll();
            int key = p.getKey();
            TreeNode node = p.getValue();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(node.val);

            if(node.left != null) {
                q.add(new Pair<>(key - 1, node.left));
            }
            
            if(node.right != null) {
                q.add(new Pair<>(key + 1, node.right));
            }
            
            minKey = Math.min(minKey, key);
            maxKey = Math.max(maxKey, key);
        }
        
        for(int i = minKey; i <= maxKey; i++) {
            if(map.containsKey(i)) {                
                ans.add(new ArrayList<>(map.get(i)));
            }
        }
        
        return ans;
    }
}
