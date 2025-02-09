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
	
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long[][] dp = new long[N+1][K+1];
		
		for (int i=1; i<=K/arr[1]; i++) {
			dp[1][arr[1] * i] = 1;
		}
		
		for (int r=2; r<=N; r++) {
			for (int c=1; c<=K; c++) {
				if(c==arr[r]) {
					dp[r][c] = dp[r-1][c] + 1; 
				} else if(c> arr[r]) {
					dp[r][c] = dp[r-1][c] + dp[r][c-arr[r]];
				} else {
					dp[r][c] = dp[r-1][c];
				}
			}
		}
		
		bw.write(dp[N][K] +"");
		
		br.close();
		bw.close();
	}

}
