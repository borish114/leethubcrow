class Solution {
public:
    vector<string> largestString(vector<int>& nums) {
        vector<string> ans;

        for(int x : nums) {
            long long cnt[26] = {};
            cnt[0] = x;
            for(int i = 0; i < 25; i++) {
                cnt[i + 1] += cnt[i] / 2;
                cnt[i] %= 2;
            }
            string res;
            for(int i = 25; i >= 0; i--) {
                res.append(cnt[i], char('a' + i));
            }
            ans.push_back(res);
        }
        return ans;
    }
};