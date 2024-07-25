import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16202 {
	
	public static class Edge {
		int st;
		int ed;
		int cost;
		
		public Edge(int st, int ed, int cost) {
			this.st = st;
			this.ed = ed;
			this.cost = cost;
		}
	}
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Edge> list = new ArrayList<>();
        p = new int[N+1];
        
        
        
        int[] answer = new int[K+1];
        
        for (int i=1; i<=M;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	list.add(new Edge(a, b, i));
        }
        
        for (int i=1; i<=K;i++) {
        	boolean isMake = false;
        	int cost = 0;
        	int count =0;
        	for (int k=1; k<=N; k++) {
            	p[k] = k;
            }
        	for (int j=i;j<=M;j++) {
        		Edge e = list.get(j-1);
        		if (findset(e.st) != findset(e.ed)) {
        			union(e.st, e.ed);
        			cost+=e.cost;
        			count++;
        		}
        	}
        	if (count == N-1) {
        		isMake = true;
        		answer[i] = cost;
        	}
        	
        	if (!isMake) {
        		break;
        	}
        }
        
        for (int i=1; i<=K;i++) {
        	bw.write(answer[i]+ " ");
        }
        
        br.close();
        bw.close();
    }
	
	private static void union(int a, int b) {
		p[findset(a)] = findset(b);
	}

	private static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
	
	
	
	
}
