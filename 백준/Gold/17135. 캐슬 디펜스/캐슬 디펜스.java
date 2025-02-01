import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, D;
	static int[][] map;
	static int ans;
	static int max;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					max++;
				}
			}
		}

		out: for (int i = 0; i < M - 2; i++) {
			for (int j = i + 1; j < M - 1; j++) {
				for (int k = j + 1; k < M; k++) {
					count = 0;
					attack(i, j, k, map);
					ans = Math.max(count, ans);
					if (ans == max) {
						break out;
					}
				}
			}
		}

		bw.write(ans + "");

		br.close();
		bw.close();
	}

	public static void attack(int i, int j, int k, int[][] curmap) {
		if (ans == max) {
			return;
		}

		int[][] tmp = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[r][c] = curmap[r][c];
			}
		}

		for (int r = 1; r <= N; r++) {
			tmp = shoot(i, j, k, tmp);
			tmp = move(tmp);
		}

	}

	public static int[][] shoot(int i, int j, int k, int[][] curmap) {
		boolean checki = false;
		boolean checkj = false;
		boolean checkk = false;

		int ir = 0;
		int ic = 0;
		int jr = 0;
		int jc = 0;
		int kr = 0;
		int kc = 0;

		for (int distance = 1; distance <= D; distance++) {
			if ((checki && checkj && checkk) || count == max) {
				break;
			}
			for (int r = 1; r <= distance; r++) {
				int c = -distance + r;
				if (!checki) {
					if (isIn(N - r, i + c)) {
						if (curmap[N - r][i + c] == 1) {
							ir = N - r;
							ic = i + c;
							checki = true;
						}
					}
				}
				if (!checkj) {
					if (isIn(N - r, j + c)) {
						if (curmap[N - r][j + c] == 1) {
							jr = N - r;
							jc = j + c;
							checkj = true;
						}
					}
				}

				if (!checkk) {
					if (isIn(N - r, k + c)) {
						if (curmap[N - r][k + c] == 1) {
							kr = N - r;
							kc = k + c;
							checkk = true;
						}
					}
				}

			}
			if (distance >= 2) {
				for (int r = distance - 1; r >= 1; r--) {
					int c = distance - r;
					if (!checki) {
						if (isIn(N - r, i + c)) {
							if (curmap[N - r][i + c] == 1) {
								ir = N - r;
								ic = i + c;
								checki = true;
							}
						}
					}
					if (!checkj) {
						if (isIn(N - r, j + c)) {
							if (curmap[N - r][j + c] == 1) {
								jr = N - r;
								jc = j + c;
								checkj = true;
							}
						}
					}

					if (!checkk) {
						if (isIn(N - r, k + c)) {
							if (curmap[N - r][k + c] == 1) {
								kr = N - r;
								kc = k + c;
								checkk = true;
							}
						}
					}
				}
			}
		}

		if (checki) {
			curmap[ir][ic] = 0;
			count++;
		}

		if (checkj) {
			if (curmap[jr][jc] == 1) {
				curmap[jr][jc] = 0;
				count++;
			}
		}

		if (checkk) {
			if (curmap[kr][kc] == 1) {
				curmap[kr][kc] = 0;
				count++;
			}
		}

		return curmap;

	}

	public static int[][] move(int[][] curmap) {

		for (int r = N - 2; r >= 0; r--) {
			for (int c = 0; c < M; c++) {
				curmap[r + 1][c] = curmap[r][c];
			}
		}

		for (int c = 0; c < M; c++) {
			curmap[0][c] = 0;
		}

		return curmap;
	}

	public static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}
}
