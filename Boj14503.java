import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj14503 {
	
	static int N, M;
	static int[][] arr;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 st = new StringTokenizer(br.readLine());
		 int r = Integer.parseInt(st.nextToken());
		 int c = Integer.parseInt(st.nextToken());
		 int d = Integer.parseInt(st.nextToken());
		 arr = new int[N][M];
		 
		 int ans = 0;
		 
		 for (int i=0; i<N;i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j=0;j<M;j++) {
				 arr[i][j] = Integer.parseInt(st.nextToken());
			 }
		 }
		 
		 while (true) {
			 if (arr[r][c] == 0) {
				 arr[r][c] = 2;
				 ans++;
			 }
			 
			 boolean a = clean(r, c);
			 
			 if (!a) { // 빈칸이 없으면
				 if (r-dr[d] >=0 && r-dr[d] < N && c-dc[d] >=0 && c-dc[d] <M) {
					 if (arr[r-dr[d]][c-dc[d]] != 1) {
						 r = r-dr[d];
						 c = c-dc[d];
						 continue;
					 } else {
						 break;
					 }
				 } else {
					 break;
				 }
			 } else { // 빈칸이 있으면
				 d -= 1;
				 if (d<0) {
					 d+=4;
				 }
				 for (int i=0; i<4; i++) {
					 int state = d-i;
					 if (state <0) {
						 state+=4;
					 }
					 if (r+dr[state] >=0 && r+dr[state] <N && c+dc[state] >=0 && c+dc[state]<M ) {
						 if (arr[r+dr[state]][c+dc[state]] ==0) {
							 r = r+dr[state];
							 c = c+dc[state];
							 d= state;
							 break;
						 }
					 }
				 }
			 }
		 }
		 
		 bw.write(ans+"");
		 
		 br.close();
		 bw.close();
	}

	private static boolean clean(int r, int c) {
		for (int i=0; i<4;i++) {
			if (r+dr[i] >=0 && r+dr[i] < N && c+dc[i] >= 0 && c+dc[i] <M) {
				if (arr[r+dr[i]][c+dc[i]] == 0) {
					return true;
				}
			}
		}
		return false;
	}
}
