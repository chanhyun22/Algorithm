import java.util.*;

class Solution {
    
    public static class Point {
        int r;
        int c;
        int dist;
        
        public Point(int r, int c, int dist){ 
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    
    public int solution(String[] board) {
        int answer = 0;
        
        int r = board.length;
        int c = board[0].length();
        
        char[][] map = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        
        int redr =0;
        int redc =0;
        int greenr =0;
        int greenc =0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    redr = i;
                    redc = j;
                } else if (map[i][j] == 'G') {
                    greenr = i;
                    greenc = j;
                }
            }
        }
        
        Queue<Point> q = new LinkedList<>();
        visited[redr][redc] = true;
        q.add(new Point(redr, redc, 0));
        
        while (!q.isEmpty()){
            Point p = q.poll();
            
            if (p.r == greenr && p.c == greenc) {
                return p.dist;
            }
            
            for (int i=0; i<4; i++) {
                int nr = p.r+dr[i];
                int nc = p.c+dc[i];
                
                while (true) {
                    if (nr >=0 && nr <r && nc >=0 && nc<c) {
                        if(map[nr][nc] != 'D') {
                            nr += dr[i];
                            nc += dc[i];
                        } else {
                            nr -= dr[i];
                            nc -= dc[i];
                            if (!visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.add(new Point(nr, nc, p.dist+1));
                            }
                            break;
                        }
                    } else {
                        nr-= dr[i];
                        nc -= dc[i];
                        if (!visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.add(new Point(nr, nc, p.dist+1));
                        }
                        break;
                    }
                }
                
            } 
            
        }
    
        return -1;
    }
}