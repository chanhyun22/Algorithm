import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Point> rainbow;
    static PriorityQueue<Group> pq;

    public static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    public static class Group implements Comparable<Group> {
        int count, rcount, r, c;
        public Group(int count, int rcount, int r, int c) {
            this.count = count;
            this.rcount = rcount;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Group o) {
            if (this.count != o.count) return o.count - this.count;
            if (this.rcount != o.rcount) return o.rcount - this.rcount;
            if (this.r != o.r) return o.r - this.r;
            return o.c - this.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        rainbow = new ArrayList<>();
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    rainbow.add(new Point(i, j));
                }
            }
        }

        while (true) {
            visited = new boolean[N][N];
            pq = new PriorityQueue<>();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c] && map[r][c] > 0) {
                        for (Point p : rainbow) visited[p.r][p.c] = false; // 무지개 블록 방문 초기화

                        int color = map[r][c];
                        int count = 0, rcount = 0;
                        Queue<Point> q = new LinkedList<>();
                        q.add(new Point(r, c));
                        visited[r][c] = true;

                        while (!q.isEmpty()) {
                            Point p = q.poll();
                            count++;
                            if (map[p.r][p.c] == 0) rcount++;

                            for (int d = 0; d < 4; d++) {
                                int nr = p.r + dr[d], nc = p.c + dc[d];
                                if (isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == color || map[nr][nc] == 0)) {
                                    visited[nr][nc] = true;
                                    q.add(new Point(nr, nc));
                                }
                            }
                        }

                        if (count >= 2) pq.add(new Group(count, rcount, r, c));
                    }
                }
            }

            if (!pq.isEmpty()) {
                Group g = pq.poll();
                remove(g.r, g.c, map[g.r][g.c]);
            } else {
                break;
            }

            gravity();
            rotate();
            gravity();

            // 무지개 블록 갱신
            rainbow.clear();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 0) rainbow.add(new Point(r, c));
                }
            }
        }

        bw.write(ans + "\n");
        br.close();
        bw.close();
    }

    private static void rotate() {
        int[][] tmp = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                tmp[N - c - 1][r] = map[r][c]; // 90도 반시계 회전
            }
        }
        map = tmp;
    }

    private static void gravity() {
        for (int c = 0; c < N; c++) {
            int bottom = N - 1;
            for (int r = N - 1; r >= 0; r--) {
                if (map[r][c] == -1) {
                    bottom = r - 1;
                } else if (map[r][c] >= 0) {
                    if (bottom != r) {
                        map[bottom][c] = map[r][c];
                        map[r][c] = -2;
                    }
                    bottom--;
                }
            }
        }
    }

    private static void remove(int r, int c, int color) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(r, c));
        visited[r][c] = true;
        map[r][c] = -2;
        int count = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d], nc = p.c + dc[d];
                if (isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == color || map[nr][nc] == 0)) {
                    visited[nr][nc] = true;
                    map[nr][nc] = -2;
                    q.add(new Point(nr, nc));
                    count++;
                }
            }
        }

        ans += count * count;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
