import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int str;
	static int stc;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		str = (N - 1) / 2;
		stc = (N - 1) / 2;

		for (int i = 1; i <= N-1; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					if (j == 0) {
						if (i % 2 == 0) {
							move(str, stc++, 3);
						} else {
							move(str, stc--, 2);
						}
					} else {
						if (i % 2 == 0) {
							move(str--, stc, 0);
						} else {
							move(str++, stc, 1);
						}
					}
				}
			}
		}
		
		for (int k=0; k<N-1; k++) {
			move(str, stc--, 2);
		}
		
		bw.write(ans+"");

		br.close();
		bw.close();
	}

	private static void move(int r, int c, int d) {
		int sum = map[str][stc];
		int amount = map[str][stc];
		int sand1 = (amount * 1) / 100;
		int sand2 = (amount * 2) / 100;
		int sand5 = (amount * 5) / 100;
		int sand7 = (amount * 7) / 100;
		int sand10 = (amount * 10) / 100;
		sum -= sand1 * 2 + sand2 * 2 + sand5 + sand7 * 2 + sand10 * 2;

		if (d == 0) {
			sand(str - 2, stc, sand5);
			sand(str - 1, stc - 1, sand10);
			sand(str - 1, stc + 1, sand10);
			sand(str, stc - 1, sand7);
			sand(str, stc + 1, sand7);
			sand(str, stc - 2, sand2);
			sand(str, stc + 2, sand2);
			sand(str + 1, stc - 1, sand1);
			sand(str + 1, stc + 1, sand1);
			sand(str - 1, stc, sum);
		} else if (d == 1) {
			sand(str + 2, stc, sand5);
			sand(str + 1, stc - 1, sand10);
			sand(str + 1, stc + 1, sand10);
			sand(str, stc - 1, sand7);
			sand(str, stc + 1, sand7);
			sand(str, stc - 2, sand2);
			sand(str, stc + 2, sand2);
			sand(str - 1, stc - 1, sand1);
			sand(str - 1, stc + 1, sand1);
			sand(str + 1, stc, sum);
		} else if (d == 2) {
			sand(str, stc-2, sand5);
			sand(str-1, stc-1, sand10);
			sand(str+1, stc-1, sand10);
			sand(str-1, stc, sand7);
			sand(str+1, stc, sand7);
			sand(str-2, stc, sand2);
			sand(str+2, stc, sand2);
			sand(str-1, stc+1, sand1);
			sand(str+1, stc+1, sand1);
			sand(str, stc-1, sum);
		} else {
			sand(str, stc+2, sand5);
			sand(str-1, stc+1, sand10);
			sand(str+1, stc+1, sand10);
			sand(str-1, stc, sand7);
			sand(str+1, stc, sand7);
			sand(str-2, stc, sand2);
			sand(str+2, stc, sand2);
			sand(str-1, stc-1, sand1);
			sand(str+1, stc-1, sand1);
			sand(str, stc+1, sum);
		}
		
		map[str][stc] = 0;

	}

	private static void sand(int r, int c, int k) {
		if (isIn(r, c)) {
			map[r][c] += k;
		} else {
			ans += k;
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
