import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[] p;
	static int N, M, K;
	static List<Integer>[] list;
	static List<Candy> newlist;
	
	public static class Candy{
		int candy;
		int p;
		public Candy(int candy, int p) {
			this.candy = candy;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		p = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			p[i] = i;
		}
		for (int i=1; i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a<b) {
				Union(a,b);
			}
			else {
				Union(b,a);
			}
		}
		
		list = new ArrayList[N+1];
		for (int i=1; i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for (int i=1; i<=N;i++) {
			list[findset(p[i])].add(i);
		}
		newlist = new ArrayList<>();
		for (int i=1; i<=N;i++) {
			if (!list[i].isEmpty()) {
				int num = 0;
				int count =0;
				for (int j : list[i]) {
					num++;
					count += arr[j];
				}
				newlist.add(new Candy(count, num));
			}
		}
		int[][] dp = new int[newlist.size()+1][K];
		for (int i=0; i<newlist.size();i++) {
			int candy = newlist.get(i).candy;
			int p = newlist.get(i).p;
			for (int j=0; j<K;j++) {
				if (j >= p) { 
					dp[i+1][j] = Math.max(dp[i][j-p] + candy, dp[i][j]);
				} else {
					dp[i+1][j] = dp[i][j];
				}
			}
		}
		bw.write(dp[newlist.size()][K-1] + "");
		
		br.close();
		bw.close();
	}

	private static void Union(int a, int b) {
		p[findset(a)] = p[findset(b)];
	}

	private static int findset(int x) {
		if (x!= p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
}
