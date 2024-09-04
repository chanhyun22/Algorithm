import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp1 = new int[N];
		int[] dp2 = new int[N];
		dp1[0] = 1;
		for (int i=0; i<N;i++) {
			for (int j=0; j<i;j++) {
				if (arr[j] < arr[i]) {
					dp1[i] = Math.max(dp1[i], dp1[j]+1);
				} else {
					dp1[i] = Math.max(dp1[i], 1);
				}
			}
		}
		dp2[N-1] = 1; 
		for (int i=N-1; i>=0; i--) {
			for (int j=i+1; j<N;j++) {
				if (arr[j] < arr[i]) {
					dp2[i] = Math.max(dp2[i], dp2[j]+1);
				} else {
					dp2[i] = Math.max(dp2[i], 1);
				}
				
			}
		}
		int max = 1;
		for (int i=0; i<N;i++) {
			max =Math.max(max, dp1[i] + dp2[i] -1);
		}
		bw.write(max+"");
		
		
		br.close();
		bw.close();
	}
}