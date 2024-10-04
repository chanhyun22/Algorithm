import java.util.*;

class Solution {
    public int[] dr = {-1, 1, 0, 0};
    public int[] dc = {0, 0, -1, 1};
    
    public static class Point {
        int r, c, dist;
        
        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    
    public int solution(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        
        char[][] map = new char[rows][cols];
        
        int startR = 0, startC = 0;
        int leverR = 0, leverC = 0; 
        int endR = 0, endC = 0;    

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'L') {
                    leverR = i;
                    leverC = j;
                } else if (map[i][j] == 'E') {
                    endR = i;
                    endC = j;
                }
            }
        }
        
        int toLever = bfs(map, startR, startC, leverR, leverC);
        if (toLever == -1) return -1;
        
        int toExit = bfs(map, leverR, leverC, endR, endC);
        if (toExit == -1) return -1;
        
        return toLever + toExit;
    }
    
    public int bfs(char[][] map, int startR, int startC, int targetR, int targetC) {
        int rows = map.length;
        int cols = map[0].length;
        
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(startR, startC, 0));
        visited[startR][startC] = true;
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            
            if (p.r == targetR && p.c == targetC) {
                return p.dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (!visited[nr][nc] && map[nr][nc] != 'X') {
                        visited[nr][nc] = true;
                        queue.add(new Point(nr, nc, p.dist + 1));
                    }
                }
            }
        }
        return -1;
    }
}
