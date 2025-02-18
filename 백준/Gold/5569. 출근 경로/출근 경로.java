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
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        
        long[][][] dp = new long[h][w][4];
        
        for (int i=0; i<h; i++) {
        	dp[i][0][0] = 1;
        }
        
        for (int i=0; i<w; i++) {
        	dp[0][i][1] = 1;
        }
        
        for (int r=1; r<h; r++) {
        	for (int c=1; c<w; c++) {
        		dp[r][c][0] = (dp[r-1][c][0] + dp[r-1][c][3]) % 100000;
        		dp[r][c][1] = (dp[r][c-1][1] + dp[r][c-1][2]) % 100000;
        		dp[r][c][2] = dp[r][c-1][0] % 100000;
        		dp[r][c][3] = dp[r-1][c][1] % 100000;	
        	}
        }
        long ans = (dp[h-1][w-1][0] % 100000) + (dp[h-1][w-1][1] % 100000) + (dp[h-1][w-1][2] % 100000) + (dp[h-1][w-1][3] % 100000);

        ans %= 100000;
        bw.write(ans+"");
        
        
        br.close();
        bw.close();
    }
}
