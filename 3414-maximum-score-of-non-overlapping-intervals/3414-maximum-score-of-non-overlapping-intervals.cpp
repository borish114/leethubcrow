class Solution {
public:
    struct State {
        long long score;
        vector<int> indices;
        State() : score(LLONG_MIN) {}
        State(long long s, vector<int> v) : score(s), indices(v) {}
    };
    bool better(const State& a, const State& b) {
        if(a.score != b.score)
            return a.score > b.score;
        return lexicographical_compare(
            a.indices.begin(), a.indices.end(), b.indices.begin(), b.indices.end()
        );
    }

    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<array<long long, 4>> a;
        for(int i = 0; i < n; i++) {
            a.push_back({
                intervals[i][0],
                intervals[i][1],
                intervals[i][2],
                i
            });
        }
        sort(a.begin(), a.end(), [](const auto& x, const auto& y) {
            if(x[1] != y[1])
                return x[1] < y[1];
            return x[0] < y[0];
        });
        vector<long long> ends(n);
        for(int i = 0; i < n; i++)
            ends[i] = a[i][1];
        vector<int> prev(n, -1);
        for(int i = 0; i < n; i++) {
            int pos = lower_bound(
                ends.begin(),
                ends.begin() + i,
                a[i][0]
            ) - ends.begin();
            prev[i] = pos - 1;
        }
        vector<vector<State>> dp(
            n+1,
            vector<State>(5)
        );
        for(int i = 0; i <= n; i++) {
            dp[i][0] = State(0, {});
        }
        for(int i = 1; i <= n; i++) {
            int idx = i-1;
            for(int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i-1][k];
                int p = prev[idx] + 1;
                if(dp[p][k - 1].score != LLONG_MIN) {
                    State take = dp[p][k-1];
                    take.score += a[idx][2];
                    take.indices.push_back((int)a[idx][3]);
                    sort(take.indices.begin(), take.indices.end());
                    if(better(take, dp[i][k])) {
                        dp[i][k] = take;
                    }
                }
            }
        }
        State answer = dp[n][0];
        for(int k = 1; k <= 4; k++) {
            if(better(dp[n][k], answer))
                answer = dp[n][k];
        } 
        return answer.indices;
    }
};