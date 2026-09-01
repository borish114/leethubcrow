class Solution {
    static class State {
        int r, c, energy, mask, moves;
        State(int r, int c, int energy, int mask, int moves) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = 0, sc = 0;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for(int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if(ch == 'S') {
                    sr = i;
                    sc = j;
                }
                if(ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        if(litterCount == 0) {
            return 0;
        }
        int allCollected = (1 << litterCount) - 1;
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        Queue<State> queue = new ArrayDeque<>();
        int startMask = 0;
        if(litterId[sr][sc] != -1) {
            startMask |= (1 << litterId[sr][sc]);
        }
        visited[sr][sc][energy][startMask] = true;
        queue.offer(new State(
            sr,
            sc,
            energy,
            startMask,
            0
        ));
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            State cur = queue.poll();
            if(cur.mask == allCollected) {
                return cur.moves;
            }
            for(int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                if(classroom[nr].charAt(nc) == 'X') {
                    continue;
                }
                if(cur.energy == 0) {
                    continue;
                }
                int newEnergy = cur.energy - 1;
                if(classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }
                int newMask = cur.mask;
                if(litterId[nr][nc] != -1) {
                    newMask |= (1 << litterId[nr][nc]);
                }
                if(visited[nr][nc][newEnergy][newMask]) {
                    continue;
                }
                visited[nr][nc][newEnergy][newMask] = true;
                queue.offer(new State(
                    nr,
                    nc,
                    newEnergy,
                    newMask,
                    cur.moves + 1
                ));
            }
        }
        return -1;
    }
}