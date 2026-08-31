/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        int index = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        while(curr != null && curr.next != null) {
            boolean isCritical = (curr.val > prev.val && curr.val > curr.next.val) || (curr.val < prev.val && curr.val < curr.next.val);
            if(isCritical) {
                if(first == -1) {
                    first = index;
                }
                if(prevCritical != -1) {
                    minDistance = Math.min(minDistance, index - prevCritical);
                }
                prevCritical = index;
            }
            prev = curr;
            curr = curr.next;
            index++;
        }
        if(first == -1 || first == prevCritical) {
            return new int[] {-1, -1};
        }
        int maxDistance = prevCritical - first;
        return new int[] {minDistance, maxDistance};
    }
}