import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static List<Integer> listb;
	static List<Integer> listw;
	static int[] dr = {-1, -1};
	static int[] dc = {-1, 1};
	static int maxb = 0;
	static int maxw = 0;
	static boolean[] vistedb;
	static boolean[] vistedw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		listb = new ArrayList<>();
		listw = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					if ((i+j) % 2 ==0) {
						listb.add(i * 10 + j);
					} else {
						listw.add(i*10 +j);
					}
				}
			}
		}
		vistedb = new boolean[listb.size()];
		vistedw = new boolean[listw.size()];
		bishopb(0, 0, 0, 0);
		bishopw(0, 0, 0, 0);
		bw.write(maxb+maxw + "");

		br.close();
		bw.close();
	}

	private static void bishopw(int idx, int n, int row, int col) {
		if (check(row, col)) {
			if (maxw < idx) {
				maxw = idx;
			}
		} else {
			return;
		}
		if (idx == listw.size()) {
			return;
		}

		for (int i = n; i < listw.size(); i++) {
			if (!vistedw[i]) {
				int num = listw.get(i);
				int r = num / 10;
				int c = num % 10;
				arr[r][c] = 2;
				vistedw[i] = true;
				bishopw(idx + 1, i, r, c);
				arr[r][c] = 1; 
				vistedw[i] = false;
			}
		}
	}
	
	private static void bishopb(int idx, int n, int row, int col) {
		if (check(row, col)) {
			if (maxb < idx) {
				maxb = idx;
			}
		} else {
			return;
		}
		if (idx == listb.size()) {
			return;
		}

		for (int i = n; i < listb.size(); i++) {
			if (!vistedb[i]) {
				int num = listb.get(i);
				int r = num / 10;
				int c = num % 10;
				arr[r][c] = 2;
				vistedb[i] = true;
				bishopb(idx + 1, i, r, c);
				arr[r][c] = 1; 
				vistedb[i] = false;
			}
		}
	}

	private static boolean check(int row, int col) {
		for (int k = 0; k < 2; k++) {
			int curi = row;
			int curj = col;
			while (0 <= curi && curi < N && 0 <= curj && curj < N) {
				curi = curi + dr[k];
				curj = curj + dc[k];
				if (0 <= curi && curi < N && 0 <= curj && curj < N) {
					if (arr[curi][curj] == 2) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
