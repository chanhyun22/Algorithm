import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[3][3];
        int zero = 0;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zero++;
            }
        }

        int result = game(zero % 2 != 0 ? 1 : 2, map);
        char answer = (result == 1) ? 'W' : (result == 0) ? 'D' : 'L';
        System.out.println(answer);
    }

    public static int game(int now, int[][] map) {
        int min = 2; 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = now;
                    if (isWin(now, map)) {
                        min = Math.min(min, -1);
                    } else {
                        min = Math.min(min, game(now == 1 ? 2 : 1, map));
                    }
                    map[i][j] = 0;
                }
            }
        }

        if (min == 1) return -1;
        else if (min == 0 || min == 2) return 0;
        else return 1;
    }

    public static boolean isWin(int now, int[][] map) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == now && map[i][1] == now && map[i][2] == now) return true;
        }

        for (int j = 0; j < 3; j++) {
            if (map[0][j] == now && map[1][j] == now && map[2][j] == now) return true;
        }

        if (map[0][0] == now && map[1][1] == now && map[2][2] == now) return true;
        if (map[0][2] == now && map[1][1] == now && map[2][0] == now) return true;

        return false;
    }
}
