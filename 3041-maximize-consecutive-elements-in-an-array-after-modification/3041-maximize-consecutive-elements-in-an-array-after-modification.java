class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer,Integer> dp = new HashMap<>();
        int ans = 0;
        for(int x : nums) {
            int oldX = dp.getOrDefault(x,0);
            int oldPrev = dp.getOrDefault(x-1, 0);
            dp.put(x, Math.max(dp.getOrDefault(x,0), oldPrev+1));
            dp.put(x+1, Math.max(dp.getOrDefault(x+1, 0), oldX+1));
            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x+1)));
        }
        return ans;
    }
}