class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] head = new int[n];
        int[] next = new int[n];

        java.util.Arrays.fill(head, -1);

        for(int i = 1; i < n; i++) {
            next[i] = head[parent[i]];
            head[parent[i]] = i;
        }
        int[] newParent = parent.clone();
        int[] last = new int[26];
        int[] previous = new int[n];

        java.util.Arrays.fill(last, -1);

        int[] stackNode = new int[2*n];
        int[] stackState = new int[2*n];
        int top = 0;

        stackNode[top] = 0;
        stackState[top++] = 0;

        while(top > 0) {
            top--;

            int node = stackNode[top];
            int state = stackState[top];

            int c = s.charAt(node) - 'a';

            if(state == 0) {
                previous[node] = last[c];

                if(last[c] != -1) {
                    newParent[node] = last[c];
                }
                last[c] = node;

                stackNode[top] = node;
                stackState[top++] = 1;

                for(int child = head[node]; child != -1; child = next[child]) {
                    stackNode[top] = child;
                    stackState[top++] = 0;
                }
            }
            else {
                last[c] = previous[node];
            }
        }
        java.util.Arrays.fill(head, -1);
        
        for(int i = 1; i < n; i++) {
            int p = newParent[i];
            next[i] = head[p];
            head[p] = i;
        }
        int[] answer = new int[n];
        int[] order = new int[n];
        int[] st = new int[n];

        java.util.Arrays.fill(answer, 1);

        top = 0;
        int count = 0;
        st[top++] = 0;

        while(top > 0) {
            int node = st[--top];
            order[count++] = node;

            for(int child = head[node]; child != -1; child = next[child]) {
                st[top++] = child;
            }
        }
        for(int i = count-1; i > 0; i--) {
            int node = order[i];
            answer[newParent[node]] += answer[node];
        }
        return answer;
    }
}