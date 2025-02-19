import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int N, M;
	static StringBuilder sb;
	
	static HashMap<Integer, Problem> map;
	static TreeSet<Problem>[] set;
	static TreeSet<Problem> set2;
	static TreeSet<Problem>[] set3;

	public static class Problem implements Comparable<Problem> {
		int P;
		int L;
		int G;

		public Problem(int P, int L, int G) {
			this.P = P;
			this.L = L;
			this.G = G;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.L == o.L) {
				return this.P - o.P;
			}
			return this.L - o.L;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		map = new HashMap<>();
		set = new TreeSet[101];
		
		for (int i=0; i<=100; i++) {
			set[i] = new TreeSet<>();
		}
		
		set2 = new TreeSet<>();
		
		set3 = new TreeSet[101];
		
		for (int i=0; i<=100; i++ ) {
			set3[i] = new TreeSet<>();
		}
		

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			map.put(P, new Problem(P, L, G));
			set[G].add(new Problem(P, L, G));
			set2.add(new Problem(P, L, G));
			set3[L].add(new Problem(P, L, G));
		}

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String commend = st.nextToken();

			if (commend.equals("recommend")) {
				int G = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				recommend(G, x);
			} else if (commend.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				recommend2(x);
			} else if (commend.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				recommend3(x, L);
			} else if (commend.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				add(P, L, G);
			} else {
				int P = Integer.parseInt(st.nextToken());
				solved(P);
			}

		}
		
		bw.write(sb+"");

		br.close();
		bw.close();
	}

	private static void solved(int P) {
		Problem p = map.get(P);
		map.remove(P);
		set[p.G].remove(p);
		set2.remove(p);
		set3[p.L].remove(p);
	}

	private static void add(int P, int L, int G) {
		map.put(P, new Problem(P, L, G));
		set[G].add(new Problem(P, L, G));
		set2.add(new Problem(P, L, G));
		set3[L].add(new Problem(P, L, G));
	}

	private static void recommend3(int x, int L) {
		if (x == 1) {
			while (L <= 100) {
				if (set3[L].isEmpty()) {
					L++;
				} else {
					Problem p = set3[L].first();
					sb.append(p.P+"\n");
					return;
				}
			}
		} else {
			L--;
			while (L > 0) {
				if (set3[L].isEmpty()) {
					L--;
				} else {
					Problem p = set3[L].last();
					sb.append(p.P+"\n");
					return;
				}
			}
		}
		sb.append("-1\n");
	}

	private static void recommend2(int x) {
		if (x == 1) {
			Problem p = set2.last();
			sb.append(p.P+"\n");
		} else {
			Problem p = set2.first();
			sb.append(p.P+"\n");
		}
	}

	private static void recommend(int G, int x) {
		if (x == 1) {
			Problem p = set[G].last();
			sb.append(p.P+"\n");
		} else {
			Problem p = set[G].first();
			sb.append(p.P+"\n");
		}
	}
}
