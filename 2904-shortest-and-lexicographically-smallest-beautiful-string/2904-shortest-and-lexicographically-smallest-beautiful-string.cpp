class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        int left = 0, ones = 0, minLen = INT_MAX;
        string ans = "";
        for(int right = 0; right < s.size(); right++) {
            if(s[right] == '1') ones++;
            while(ones > k) {
                if(s[left] == '1') ones--;
                left++;
            }
            if(ones == k) {
                while(s[left] == '0') left++;
                int len = right - left + 1;
                string cur = s.substr(left, len);
                if(len < minLen || (len == minLen && cur < ans)) {
                    minLen = len;
                    ans = cur;
                }
            }
        }
        return ans;
    }
};