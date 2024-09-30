import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] dr1 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc1 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int[] dr2 = { -1, 1, 0, 0 };
	static int[] dc2 = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static boolean isMounted;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					isMounted = true;
					if (dfs(i, j)) {
						ans++;
					}
					
				}

			}
		}

		bw.write(ans + "");

		br.close();
		bw.close();
	}

	private static boolean dfs(int i, int j) {
		visited[i][j] = true;

		for (int h = 0; h < 8; h++) {
			int ni = i + dr1[h];
			int nj = j + dc1[h];
			if ( ni >= 0 && ni < N && nj >= 0 && nj < M) {
				if (map[i][j] < map[i + dr1[h]][j + dc1[h]]) {
					isMounted = false;
				} else if (map[i][j] == map[ni][nj] && !visited[ni][nj]) {
					dfs(ni, nj);
				}
			}
		}
		
		return isMounted;

	}
}