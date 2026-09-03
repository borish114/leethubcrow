class Solution:
    def uniformArray(self, nums1):
        min_odd = float('inf')
        has_odd = False
        for x in nums1:
            if x % 2 == 1:
                has_odd = True
                min_odd = min(min_odd, x)

        if not has_odd:
            return True

        for x in nums1:
            if x % 2 == 0 and x < min_odd:
                return False
        return True
        