class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();
        int half = n/2;
        int diff = 0;
        int questionDiff = 0;
        for(int i = 0; i < half; i++) {
            if(num[i] == '?') 
                questionDiff++;
            else
                diff += num[i] - '0';
        }
        for(int i = half; i < n; i++) {
            if(num[i] == '?') 
                questionDiff--;
            else
                diff -= num[i] - '0';
        }
        if(questionDiff % 2 != 0)
            return true;
        return diff + (questionDiff/2) * 9 != 0;
    }
};