import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, Q, size;
	static int[][] map;
	static boolean[][] visited;
	static int sum, max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = (int) Math.pow(2, N);
		map = new int[size][size];
		
		for (int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] tmp = new int[size][size];

		st = new StringTokenizer(br.readLine());
		for (int i=0; i<Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			int length = (int) Math.pow(2, L);
			for (int r = 0; r<size / length; r++) {
				for (int c=0; c< size / length; c++) {		
					for (int r1=0; r1<length; r1++) {
						for (int c1=0; c1<length; c1++) {
							tmp[(r*length) + r1][(c*length) + c1] = map[(r*length) + length -c1 -1][(c*length) + r1];
						}
					}
					
				}
			}
			
			List<Point> list = new ArrayList<>();
			
			for (int r=0; r<size; r++) {
				for (int c=0; c<size; c++) {
					int count =0;
					if (tmp[r][c] != 0) {
						for (int d=0; d<4; d++) {
							if (isIn(r+dr[d], c+dc[d])) {
								if (tmp[r+dr[d]][c+dc[d]] != 0 ) {
									count++;
								}
							}
						}
						if (count <3) {
							list.add(new Point(r,c));
						}
					}
				}
			}
			
			for (Point p : list) {
				tmp[p.r][p.c] -=1;
			}
			
			for (int r=0; r<size; r++) {
				for (int c=0; c<size; c++) {
					map[r][c] = tmp[r][c];
				}
			}
		}
		
		visited = new boolean[size][size];		
		Queue<Point> q = new LinkedList<>();
		
		for (int r=0; r<size; r++) {
			for (int c=0; c<size; c++) {
				if(visited[r][c]) continue;
				visited[r][c] = true;
				int count =0;
				if (map[r][c] != 0) {
					q.add(new Point(r,c));
					while(!q.isEmpty()) {
						Point p = q.poll();
						sum += map[p.r][p.c];
						count += 1;
						
						for (int d=0; d<4; d++) {
							if (isIn(p.r+dr[d], p.c+dc[d])) {
								if (!visited[p.r+dr[d]][p.c+dc[d]] && map[p.r+dr[d]][p.c+dc[d]] != 0) {
									visited[p.r+dr[d]][p.c+dc[d]] = true;
									q.add(new Point(p.r+dr[d], p.c+dc[d]));
								}
							}
						}
					}
					
					if (count > max) { 
						max = count;
					}
 				}
			}
		}
		
		bw.write(sum+"\n"+max);
		
		
		br.close();
		bw.close();
	}

	public static boolean isIn(int r, int c) {
		if(r >=0 && r<size && c>=0 && c<size ) {
			return true;
		}
		
		return false;
	}
}
