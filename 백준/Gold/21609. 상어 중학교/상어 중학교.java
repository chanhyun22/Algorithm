import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<Point> rainbow;
	static PriorityQueue<Group> pq;

	public static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static class Group implements Comparable<Group> {
		int count;
		int rcount;
		int r;
		int c;

		public Group(int count, int rcount, int r, int c) {
			this.count = count;
			this.rcount = rcount;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Group o) {
			if (this.count != o.count) {
				return o.count - this.count;
			}
			if (this.rcount != o.rcount) {
				return o.rcount - this.rcount;
			}
			if (this.r != o.r) {
				return o.r - this.r;
			}
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
					if (!visited[r][c] && map[r][c] != 0 && map[r][c] != -1 && map[r][c] != -2) {
						for (Point p : rainbow) {
							visited[p.r][p.c] = false;
						}

						int color = map[r][c];
						visited[r][c] = true;
						int count = 0;
						int rcount = 0;
						Queue<Point> q = new LinkedList<>();
						q.add(new Point(r, c));

						while (!q.isEmpty()) {
							Point p = q.poll();
							count++;
							if (map[p.r][p.c] == 0) {
								rcount++;
							}

							for (int d = 0; d < 4; d++) {
								if (isIn(p.r + dr[d], p.c + dc[d]) && !visited[p.r + dr[d]][p.c + dc[d]]) {
									if (map[p.r + dr[d]][p.c + dc[d]] == color || map[p.r + dr[d]][p.c + dc[d]] == 0) {
										visited[p.r + dr[d]][p.c + dc[d]] = true;
										q.add(new Point(p.r + dr[d], p.c + dc[d]));
									}
								}
							}

						}

						if (count >= 2) {
							pq.add(new Group(count, rcount, r, c));
						}
					}
				}
			}

			if (!pq.isEmpty()) {
				Group g = pq.poll();

				int color = map[g.r][g.c];
				remove(g.r, g.c, color);

			} else {
				break;
			}

			gravity();

			rotate();

			gravity();

			rainbow = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 0) {
						rainbow.add(new Point(r, c));
					}
				}
			}

		}

		bw.write(ans + "");

		br.close();
		bw.close();
	}

	private static void rotate() {

		int[][] tmp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = map[c][N - r - 1];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = tmp[r][c];
			}
		}

	}

	private static void gravity() {

		int[][] tmp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = -2;
			}
		}

		for (int c = 0; c < N; c++) {
			int count = 0;
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == -2) {
					count++;
					continue;
				}

				if (map[r][c] == -1) {
					tmp[r][c] = -1;
					count = 0;
					continue;
				}

				tmp[r + count][c] = map[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = tmp[r][c];
			}
		}

	}

	private static void remove(int r, int c, int color) {

		Queue<Point> q = new LinkedList<>();

		map[r][c] = -2;
		int count = 0;
		q.add(new Point(r, c));

		while (!q.isEmpty()) {
			Point p = q.poll();
			count++;

			for (int d = 0; d < 4; d++) {
				if (isIn(p.r + dr[d], p.c + dc[d])) {
					if (map[p.r + dr[d]][p.c + dc[d]] == color || map[p.r + dr[d]][p.c + dc[d]] == 0) {
						map[p.r + dr[d]][p.c + dc[d]] = -2;
						q.add(new Point(p.r + dr[d], p.c + dc[d]));
					}
				}
			}

		}

		ans += (int) Math.pow(count, 2);
	}

	private static boolean isIn(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N) {
			return true;
		}

		return false;
	}

}
