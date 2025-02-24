import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static public class Point {
		int r;
		int c;
		int l;
		
		Point(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		int idx = 1;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					if (map[i][j] == 1) {
						idx++;
						Queue<Point> q = new LinkedList<>();
						q.add(new Point(i, j, 0));
						
						while (!q.isEmpty()) {
							Point p = q.poll();
							map[p.r][p.c] = idx;
							
							for (int d=0; d<4; d++) {
								if(isIn(p.r + dr[d], p.c + dc[d])) {
									if(!visited[p.r+dr[d]][p.c+dc[d]]) {
										visited[p.r+dr[d]][p.c+dc[d]] = true;
										if (map[p.r+dr[d]][p.c+dc[d]] == 1) {
											q.add(new Point(p.r+dr[d], p.c+dc[d], 0));
										}
									}
								}
							}
							
						}
						
					}
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
 		for (int i=0; i<N; i++) {
 			for (int j=0; j<N; j++) {
 				if (map[i][j] != 0 && map[i][j] != 1) {
 					int count =0;
 					int num = map[i][j];
 					for (int d=0; d<4; d++) {
 						if (isIn (i+dr[d], j+dc[d]) ) {
 							if (map[i+dr[d]][j+dc[d]] == 0) {
 								count ++;
 							}
 						}
 					}
 					
 					if (count != 0) {
 						visited = new boolean[N][N];
 						Queue<Point> q = new LinkedList<>();
 						q.add(new Point(i, j, 0));
 						
 						out : while(!q.isEmpty()) {
 							Point p = q.poll();
 							
 							for (int d=0; d<4; d++) {
 								if(isIn(p.r + dr[d] , p.c + dc[d])) {
 									if (!visited[p.r+dr[d]][p.c+dc[d]]) {
 										visited[p.r+dr[d]][p.c+dc[d]] = true;
 										if (map[p.r+dr[d]][p.c+dc[d]] == 0 ) {
 											q.add(new Point (p.r +dr[d], p.c + dc[d], p.l + 1));
 										} else if (map[p.r +dr[d]][p.c+dc[d]] != num) {
 											ans = Math.min(ans, p.l);
 											break out;
 										}
 									}
 								}
 							}
 						}
 					}
 				}
 			}
 		}
		
		bw.write(ans+"");
		
		br.close();
		bw.close();
	}

	private static boolean isIn(int i, int j) {
		return i>=0 && i<N && j>=0 && j<N;
	}
}
	