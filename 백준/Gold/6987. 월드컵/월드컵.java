import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] arr; // 입력받은 경기 결과
    static boolean valid; // 결과가 가능한지 여부
    static int[][] matches = { {0,1}, {0,2}, {0,3}, {0,4}, {0,5},
                               {1,2}, {1,3}, {1,4}, {1,5},
                               {2,3}, {2,4}, {2,5},
                               {3,4}, {3,5},
                               {4,5} }; // 경기 조합 (총 15경기)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 0; t < 4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[6][3]; // 각 팀의 승/무/패 기록

            int totalGames = 0; // 입력된 총 경기 수 체크 (빠른 탈출 조건)
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    totalGames += arr[i][j];
                }
            }

            // 월드컵 조별 예선의 경기 수는 정확히 15경기여야 함
            if (totalGames != 30) {
                bw.write("0 ");
                continue;
            }

            valid = false; // 가능 여부 플래그
            dfs(0); // 15경기 중 첫 번째 경기부터 탐색
            bw.write((valid ? 1 : 0) + " ");
        }

        br.close();
        bw.close();
    }

    // 백트래킹: 15경기를 돌면서 가능한 경우를 탐색
    public static void dfs(int game) {
        if (valid) return; // 이미 가능한 경우가 확인되었으면 더 탐색할 필요 없음

        if (game == 15) { // 15경기를 모두 탐색했을 때
            valid = true;
            return;
        }

        int teamA = matches[game][0];
        int teamB = matches[game][1];

        // 승리: A 승리, B 패배
        if (arr[teamA][0] > 0 && arr[teamB][2] > 0) {
            arr[teamA][0]--; arr[teamB][2]--;
            dfs(game + 1);
            arr[teamA][0]++; arr[teamB][2]++;
        }

        // 무승부: A 무승부, B 무승부
        if (arr[teamA][1] > 0 && arr[teamB][1] > 0) {
            arr[teamA][1]--; arr[teamB][1]--;
            dfs(game + 1);
            arr[teamA][1]++; arr[teamB][1]++;
        }

        // 패배: A 패배, B 승리
        if (arr[teamA][2] > 0 && arr[teamB][0] > 0) {
            arr[teamA][2]--; arr[teamB][0]--;
            dfs(game + 1);
            arr[teamA][2]++; arr[teamB][0]++;
        }
    }
}
