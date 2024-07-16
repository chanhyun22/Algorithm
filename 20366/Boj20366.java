import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj20366 {
    static int N;
    static int[] arr;
    static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        for (int i=0; i<N-1; i++) {
        	for (int j=1; j<N; j++) {
        		if (i != j) {
        			int sum = arr[i] + arr[j];
        			int start = 0;
        			int end = N-1;
        			
        			while (start < end) {
        				if (start == i || start == j) {
        					start++;
        					continue;
        				}
        				if (end == i || end == j) {
        					end--;
        					continue;
        				}
        				int sum2 = arr[start] + arr[end];
        				if (min > Math.abs(sum- sum2)) {
        					min = Math.abs(sum-sum2);
        				}
        				if (sum < sum2) {
        					end--;
        				} else {
        					start ++;
        				}
        			}
        		}
        	}
        }
        bw.write(min+"");
        
        br.close();
        bw.close();
    }
}
