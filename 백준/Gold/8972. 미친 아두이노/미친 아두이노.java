import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R,C;
	static char[][] map;
	static List<Point> crazy;
	static int str, stc;
	
	static int[] dr = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	public static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		crazy = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			String word = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = word.charAt(j);
				if (map[i][j] == 'R') {
					crazy.add(new Point(i, j));
				} else if (map[i][j] == 'I') {
					str = i;
					stc = j;
				}
			}
		}
		
		String action = br.readLine();
		int ans = -1;
		out : for (int i=1; i<=action.length(); i++) {
			int num = action.charAt(i-1) -'0';
			map[str][stc] = '.';
			str += dr[num-1];
			stc += dc[num-1];
			if (map[str][stc] == 'R') {
				ans = i;
				break;
			}
			
			map[str][stc] = 'I';
			
			int[][] count = new int[R][C];
			
			for (Point p : crazy) {
				map[p.r][p.c] = '.';
				if (p.r < str) {
					if (p.c < stc) {
						p.r += dr[2];
						p.c += dc[2];
					} else if (p.c > stc) {
						p.r += dr[0];
						p.c += dc[0];
					} else {
						p.r += dr[1];
						p.c += dc[1];
					}
				} else if (p.r > str) {
					if (p.c < stc) {
						p.r += dr[8];
						p.c += dc[8];
					} else if (p.c > stc) {
						p.r += dr[6];
						p.c += dc[6];
					} else {
						p.r += dr[7];
						p.c += dc[7];
					}
				} else {
					if (p.c < stc) {
						p.r += dr[5];
						p.c += dc[5];
					} else {
						p.r += dr[3];
						p.c += dc[3];	
					}
				}
				
				count[p.r][p.c] += 1;
				
				if(map[p.r][p.c] == 'I') {
					ans = i;
					break out;
				}
			}
			
			for (int r=0; r<R; r++) {
				for (int c=0; c<C; c++) {
					if (count[r][c] > 1) {
						map[r][c] = '.';
						
						int idx =0;
						while (true) {
							if (idx >= crazy.size()) {
								break;
							}
							Point p = crazy.get(idx);
							if (p.r == r && p.c == c) {
								crazy.remove(p);
							} else {
								idx++;
							}
						}
						
					}
				}
			}
			
			for (Point p : crazy) {
				map[p.r][p.c] = 'R';
			}
		}
		
		if (ans == -1) {
			for (int i=0; i<R; i++) {
				for (int j=0; j<C; j++) {
					bw.write(map[i][j] +"");
				}
				bw.write("\n");
			}
		} else {
			bw.write("kraj " + ans);
		}

		
		br.close();
		bw.close();
	}
}
