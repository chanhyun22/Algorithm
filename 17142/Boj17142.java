import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17142 {
    
    static int N, M;
    static List<Birus> list;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int empty = 0;
    
    static public class Birus {
        int r;
        int c;
        
        public Birus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static public class Point {
        int r;
        int c;
        int time;
        
        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new Birus(i, j));
                }
                if (map[i][j] == 0) {
                    empty++;
                }
            }
        }
        if (empty == 0) {
        	bw.write("0");
        } else {
        	boolean[] visited = new boolean[list.size()];
            dfs(0, 0, visited, map);
            
            if (min == Integer.MAX_VALUE) {
                bw.write("-1");
            } else {
                bw.write(min + "");
            }
        }
        
        
        br.close();
        bw.close();
    }

    private static void dfs(int idx, int start, boolean[] visited, int[][] map) {
        if (idx == M) {
            int maxTime = spreadVirus(visited, map);
            if (maxTime != -1) {
                min = Math.min(maxTime, min);
            }
            return;
        }
        
        for (int i = start; i < list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(idx + 1, i + 1, visited, map);
                visited[i] = false;
            }
        }
    }

    private static int spreadVirus(boolean[] visited, int[][] map) {
        Queue<Point> q = new LinkedList<>();
        int[][] tmpMap = new int[N][N];
        boolean[][] visitedMap = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tmpMap[i], 0, N);
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                int r = list.get(i).r;
                int c = list.get(i).c;
                q.add(new Point(r, c, 0));
                visitedMap[r][c] = true;
            }
        }

        int maxTime = 0;
        int count = 0;
        
        out : while (!q.isEmpty()) {
            Point p = q.poll();
            maxTime = Math.max(maxTime, p.time);
            
            if (count == empty) {
            	while(!q.isEmpty()) {
            		Point p1 = q.poll();
            		maxTime = p1.time;
            	}
            	break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visitedMap[nr][nc] && tmpMap[nr][nc] != 1) {
                    visitedMap[nr][nc] = true;
                    if (tmpMap[nr][nc] == 0) {
                        count++;
                        
                    }
                    q.add(new Point(nr, nc, p.time + 1));
                }
            }
        }
        
        return count == empty ? maxTime : -1;
    }
}
