class Solution(object):
    def countCommas(self, n):
        ans = 0
        power = 1000
        while n >= power:
            ans += n - power + 1
            power *= 1000
        return ans
        