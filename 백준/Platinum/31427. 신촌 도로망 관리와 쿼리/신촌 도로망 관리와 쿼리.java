import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, Q;
	static boolean[] visted = new boolean[5];
	static int[] count;
	static List<Edge>[] list;
	static long[] weight;
	static int[] p;
	static String s = "ABCDE";
	static Character c;
	static Map<String, String> map = new HashMap<>();
	static PriorityQueue<Num> pq = new PriorityQueue<>();
	
	public static class Edge{
		int st;
		int ed;
		
		public Edge(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
	}
	
	public static class Num implements Comparable<Num>{
		int idx;
		long num;
		
		public Num(int idx, long num) {
			this.idx =idx;
			this.num =num;
		}
		
		@Override
		public int compareTo(Main.Num o) {
			return (int) (this.num - o.num);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		list = new ArrayList[5];
		for (int i=0; i<5;i++) {
			list[i]= new ArrayList<>();
		}
		for (int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0)-'A';
			list[c].add(new Edge(start, end));
		}
		
		permutation("");
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			pq = new PriorityQueue<>();
			weight = new long[5];
			Long ans = 0L;
			for (int j=0; j<5; j++) {
				weight[j] = Long.parseLong(st.nextToken());
				pq.add(new Num(j, weight[j]));
			}
			String key= "";
			
			while(!pq.isEmpty()) {
				Num n = pq.poll();
				switch (n.idx) {
					case 0 :
						c = 'A';
						break;
					case 1 :
						c = 'B';
						break;
					case 2 :
						c = 'C';
						break;
					case 3 :
						c = 'D';
						break;
					case 4 :
						c = 'E';
						break;
				}
				key += c;
			}
			
			StringTokenizer value = new StringTokenizer(map.get(key));
			for (int j=0; j<5; j++) {
				int cnt = Integer.parseInt(value.nextToken());
				ans += cnt * weight[j];
			}
			sb.append(ans).append("\n");
		}

		bw.write(sb+"");
		
		br.close();
		bw.close();
	}

	private static void permutation(String str) {
		if (str.length() == 5) {
			kruscal(str);
			return;
		}
		
		for (int i=0; i<5;i++) {
			if (!visted[i]) {
				visted[i] = true;
				permutation(str+s.charAt(i));
				visted[i] = false;
			}
		}
		
		
	}

	private static void kruscal(String str) {
		
		p = new int[N+1];
		
		for (int i=1; i<=N;i++) {
			p[i] = i;
		}
		
		int cnt =1;
		count = new int[5];
		
		out : for (int i=0; i<5; i++) {
			int state = str.charAt(i)-'A';
			for (Edge e : list[state]) {
				if (findset(e.st) != findset(e.ed)) {
					cnt++;
					count[state]++;
					union(e.st, e.ed);
					if (cnt == N) {
						break out;
					}
				}
			}
		}
		String word ="";
		
		for (int i=0; i<5; i++) {
			word += count[i] + " ";
		}
		map.put(str, word);
	}
	
	public static void union(int a, int b) {
		p[findset(a)] = findset(b);
	}

	private static int findset(int x) {
		if (x!= p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
}
