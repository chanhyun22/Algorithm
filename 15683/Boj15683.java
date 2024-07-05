import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15683 {
	static int N, M;
	static List<CCTV> list;
	static int min = Integer.MAX_VALUE;
	
	static public class CCTV {
		int r;
		int c;
		int num;
		
		public CCTV(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 int[][] map = new int[N][M];
		 list = new ArrayList<>();
		 for (int i=0; i<N;i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j=0; j<M;j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
				 if (map[i][j] != 0 && map[i][j] != 6) {
					 list.add(new CCTV(i, j, map[i][j]));
				 }
			 }
		 }
		 start(0, map);
		 bw.write(min+"");
		 
		 br.close();
		 bw.close();
	}

	private static void start(int idx, int[][] map) {
		if (idx == list.size()) {
			min = Math.min(min, checkMap(map));
			return;
		}
		
		int num = list.get(idx).num;
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		int[][] tmp;
		
		if (num == 1) {
			tmp = copyMap(map);
			shootLeft(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootDown(tmp, r, c);
			start(idx+1, tmp);
		} else if (num == 2) {
			tmp = copyMap(map);
			shootLeft(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootDown(tmp, r, c);
			start(idx+1, tmp);
		} else if (num == 3) {
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootDown(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootLeft(tmp, r, c);
			shootDown(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootLeft(tmp, r, c);
			shootUp(tmp, r, c);
			start(idx+1, tmp);
		} else if (num == 4) {
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootLeft(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootDown(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootDown(tmp, r, c);
			shootLeft(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
			
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootLeft(tmp, r, c);
			shootDown(tmp, r, c);
			start(idx+1, tmp);
		} else {
			tmp = copyMap(map);
			shootUp(tmp, r, c);
			shootDown(tmp, r, c);
			shootLeft(tmp, r, c);
			shootRight(tmp, r, c);
			start(idx+1, tmp);
		}
		
		
		
	}

	private static int checkMap(int[][] map) {
		int count = 0;
		for (int i=0; i<N;i++) {
			for (int j=0; j<M;j++) {
				if (map[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static void shootLeft(int[][] tmp, int r, int c) {
		for (int i=c-1; i>=0; i--) {
			if (tmp[r][i] == 6) break;
			if (tmp[r][i] != 0) continue;
			tmp[r][i] = -1;
		}
	}
	
	private static void shootRight(int[][] tmp, int r, int c) {
		for (int i=c+1; i<M; i++) {
			if (tmp[r][i] == 6) break;
			if (tmp[r][i] != 0) continue;
			tmp[r][i] = -1;
		}
	}

	private static void shootUp(int[][] tmp, int r, int c) {
		for (int i=r-1; i>=0; i--) {
			if (tmp[i][c] == 6) break;
			if (tmp[i][c] != 0) continue;
			tmp[i][c] = -1;
		}
	}
	
	private static void shootDown(int[][] tmp, int r, int c) {
		for (int i=r+1; i<N; i++) {
			if (tmp[i][c] == 6) break;
			if (tmp[i][c] != 0) continue;
			tmp[i][c] = -1;
		}
	}
	
	
	private static int[][] copyMap(int[][] map) {
		int[][] tmp = new int[N][M];
		for(int i=0; i<N;i++) {
			for (int j=0; j<M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}

}
