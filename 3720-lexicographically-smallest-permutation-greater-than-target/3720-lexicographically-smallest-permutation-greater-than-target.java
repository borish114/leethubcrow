class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        char[] ans = target.toCharArray();
        for(int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';
            if(count[x] > 0) {
                count[x]--;
                continue;
            }
            for(int c = x+1; c < 26; c++) {
                if(count[c] > 0) {
                    ans[i] = (char) ('a' + c);
                    count[c]--;
                    int pos = i+1;
                    for(int j = 0; j < 26; j++) {
                        while(count[j] > 0) {
                            ans[pos++] = (char) ('a' + j);
                            count[j]--;
                        }
                    }
                    return new String(ans);
                }
            }
            for(int j = i-1; j >= 0; j--) {
                count[target.charAt(j) - 'a']++;
                int prev = target.charAt(j) - 'a';
                for(int c = prev + 1; c < 26; c++) {
                    if(count[c] > 0) {
                        ans[j] = (char) ('a' + c);
                        count[c]--;
                        int pos = j+1;
                        for(int k = 0; k < 26; k++) {
                            while(count[k] > 0) {
                                ans[pos++] = (char) ('a' + k);
                                count[k]--;
                            }
                        }
                        return new String(ans);
                    }
                }
            }
            return "";
        }
        for(int i = n-1; i >= 0; i--) {
            count[target.charAt(i) - 'a']++;
            int x = target.charAt(i) - 'a';
            for(int c = x+1; c < 26; c++) {
                if(count[c] > 0) {
                    ans[i] = (char) ('a' + c);
                    count[c]--;
                    int pos = i + 1;
                    for(int j = 0; j < 26; j++) {
                        while(count[j] > 0) {
                            ans[pos++] = (char) ('a' + j);
                            count[j]--;
                        }
                    }
                    return new String(ans);
                }
            }
        }
        return "";
    }
}