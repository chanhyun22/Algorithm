import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			y = y-x+1;
			int count1 =0;
			int count2 =1;
			long sum=1;
			
			while (sum < y) {
				count1++;
				sum+= count2 * 1;
				if (count1 % 2 == 0 ) {
					count2++;
				}
			}
			bw.write(count1+"\n");
		}
		
		br.close();
		bw.close();
	}
}
