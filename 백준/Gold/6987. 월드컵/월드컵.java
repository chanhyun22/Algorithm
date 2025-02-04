import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			ans = 0;
			arr = new int[6][3];
			int[][] score = new int[6][3];

			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 1, score);
			
			bw.write(ans+" ");
		}

		br.close();
		bw.close();
	}

	public static void dfs(int a, int b, int[][] score) {
		if (b == 6) {
			a += 1;
			b = a + 1;
		}

		if (a == 5) {
			boolean check = true;
			for (int i=0; i<6; i++) {
				for(int j=0; j<3; j++) {
					if (score[i][j] != arr[i][j]) { 
						check = false;
					}
				}
			}
			
			if (check) {
				ans = 1;
			}
			return;
		}
		
		score[a][0] +=1;
		score[b][2] +=1;
		dfs(a, b+1, score);
		score[a][0] -=1;
		score[b][2] -=1;
		
		score[a][1] +=1;
		score[b][1] +=1;
		dfs(a, b+1, score);
		score[a][1] -=1;
		score[b][1] -=1;
		
		score[a][2] +=1;
		score[b][0] +=1;
		dfs(a, b+1, score);
		score[a][2] -=1;
		score[b][0] -=1;

	}

}
