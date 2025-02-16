import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static List<Edge>[] list;
	static int[] p;
	
	public static class Edge implements Comparable<Edge>{
		int st;
		int ed;
		int w;
		
		public Edge(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N];
        for (int i=0; i<N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        p = new int[N];
        for (int i=0; i<N; i++) {
        	p[i] = i;
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for (int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	pq.add(new Edge(a, b, w));
        }
        
        int mincost = 0;
        
        while(!pq.isEmpty()) {
        	Edge e = pq.poll();
        	if (findset(e.st) != findset(e.ed)) {
        		union(e.st, e.ed);
        		mincost += e.w;
        		list[e.st].add(new Edge(e.st, e.ed, e.w));
        		list[e.ed].add(new Edge(e.ed, e.st, e.w));
        	}
        }
        
        PriorityQueue<Edge> pq2 = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq2.add(new Edge(0, 0, 0));
        visited[0] = true;
        int num =0;
        
        while(!pq2.isEmpty()) {
        	Edge e = pq2.poll();
        	num = e.ed;
        	for (Edge e2 : list[e.ed]) {
        		if (!visited[e2.ed]) {
        			visited[e2.ed] = true;
        			pq2.add(new Edge (e2.st, e2.ed, e.w + e2.w));
        		}
        	}        	
        }
        
        visited = new boolean[N];
        pq2.add(new Edge(num, num, 0));
        visited[num] = true;
        
        int maxcost = 0;
        while(!pq2.isEmpty()) {
        	Edge e = pq2.poll();
        	maxcost = e.w;
        	for (Edge e2 : list[e.ed]) {
        		if (!visited[e2.ed]) {
        			visited[e2.ed] = true;
        			pq2.add(new Edge (e2.st, e2.ed, e.w + e2.w));
        		}
        	}
        }
        
        bw.write(mincost+"\n");
        bw.write(maxcost+"");
        
        br.close();
        bw.close();
    }
	
	private static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}

	public static void union(int a, int b) {
		p[findset(a)] = findset(b);
	}
	
}