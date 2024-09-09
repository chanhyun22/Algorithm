import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int V,E;
	static int[] P;
	
	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int c;
		public Edge(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = new int[V+1];
		Edge[] edges = new Edge[E];
		for (int i =1; i<=V;i++) {
			P[i] = i;
		}
		for (int i =0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a,b,c);
		}
		Arrays.sort(edges);
		int sum = 0;
		for (Edge e : edges) {
			if (findset(e.a) != findset(e.b)) {
				union(e.a,e.b);
				sum += e.c;
			}
		}
		bw.write(sum+"");
	
		br.close();
		bw.close();
	}
	public static void union(int a, int b) {
		P[findset(a)] = findset(b);
	}
	
	private static int findset(int a) {
		if (a != P[a]) {
			P[a] = findset(P[a]);
		}
		return P[a];
	}
	
	
	
}