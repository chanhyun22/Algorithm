import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] map;
    static int str, stc;
    static String ans = "";
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        for (int i = 0; i < H; i++) {
            String word = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = word.charAt(j);
            }
        }

        out: for (int i = 0; i < H; i++) { // 시작점 or 끝점 찾기
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '#') {
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (isIn(nr, nc)) {
                            if (map[nr][nc] == '#') {
                                count++;
                            }
                        }
                    }
                    if (count == 1) {
                        str = i;
                        stc = j;
                        break out;
                    }
                }
            }
        }

        bw.write((str + 1) + " " + (stc + 1) + "\n");
        int dir = 0;
        for (int d = 0; d < 4; d++) { // 시작 방향 찾기
            int nr = str + dr[d];
            int nc = stc + dc[d];
            if (isIn(nr, nc)) {
                if (map[nr][nc] == '#') {
                    if (d == 0) {
                        dir = 0;
                        bw.write("^\n");
                    } else if (d == 1) {
                        dir = 1;
                        bw.write(">\n");
                    } else if (d == 2) {
                        dir = 2;
                        bw.write("v\n");
                    } else {
                        dir = 3;
                        bw.write("<\n");
                    }
                }
            }
        }

        map[str][stc] = '.';
        dfs(str, stc, dir);
        bw.write(ans);

        br.close();
        bw.close();
    }

    public static void dfs(int r, int c, int dir) { // 탐색
        int curr = r;
        int curc = c;
        int curdir = dir;
        boolean check = true;
        while (check) {
            check = false;
            for (int d = 0; d < 4; d++) {
                int nr = curr + dr[d];
                int nc = curc + dc[d];
                if (isIn(nr, nc) && map[nr][nc] == '#') {
                    if (curdir == d) {
                        ans += "A";
                        map[nr][nc] = '.';
                        nr += dr[d];
                        nc += dc[d];
                        map[nr][nc] = '.';
                        curr = nr;
                        curc = nc;
                        check = true;
                    } else if ((d - curdir) == 3 || (d - curdir) == -1) {
                        ans += "L";
                        curdir = d;
                        check = true;
                    } else if ((d - curdir) == 1 || (d - curdir) == -3) {
                        ans += "R";
                        curdir = d;
                        check = true;
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        if (r >= 0 && r < H && c >= 0 && c < W) {
            return true;
        }
        return false;
    }
}
