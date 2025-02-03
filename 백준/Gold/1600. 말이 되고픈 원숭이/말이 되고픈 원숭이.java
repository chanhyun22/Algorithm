import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1 };
	static boolean[][][] visited;

	public static class Point {
		int r;
		int c;
		int count;
		int ans;

		public Point(int r, int c, int count, int ans) {
			this.r = r;
			this.c = c;
			this.count = count;
			this.ans = ans;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = Integer.MAX_VALUE;
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
						
			if (p.r == H-1 && p.c == W-1) {
				if (ans > p.ans) {
					ans = p.ans;
				}
				continue;
			}
			
			for (int i=0; i<4; i++) {
				if (isIn(p.r + dr[i], p.c + dc[i])) {
					if (!visited[p.r+dr[i]][p.c+dc[i]][p.count] && map[p.r +dr[i]][p.c+dc[i]] == 0) {
						visited[p.r+dr[i]][p.c+dc[i]][p.count] = true;
						q.add(new Point(p.r+dr[i], p.c+dc[i], p.count, p.ans+1));
					}
				}
			}
			if (p.count < K) {
				for (int i=4; i<12; i++) {
					if (isIn(p.r+dr[i], p.c + dc[i])) {
						if (!visited[p.r+dr[i]][p.c+dc[i]][p.count+1] && map[p.r+dr[i]][p.c+dc[i]] == 0) {
							visited[p.r+dr[i]][p.c+dc[i]][p.count+1] = true;
							q.add(new Point(p.r+dr[i], p.c+dc[i], p.count+1, p.ans+1));
						}
					}
				}
			}
		}
		if (ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		
		if (map[0][0] == 1) {
			ans = -1;
		}
		bw.write(ans+"");
		
		br.close();
		bw.close();
	}

	private static boolean isIn(int r, int c) {
		if (r >=0 && r<H && c>=0 && c<W) {
			return true;
		}
		
		return false;
	}

}
