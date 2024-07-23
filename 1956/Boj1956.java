import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1956 {
	static int V, E;
	static int INF = 987654321;
	static int min = INF;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        int[][] dist = new int[V+1][V+1];
        
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                dist[i][j] = INF;
            }
        }
        
        for (int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	dist[start][end] = cost;
        }
        
        for (int k=1; k<=V;k++) {
        	for (int i=1; i<=V; i++) {
        		for (int j=1; j<=V; j++) {
        			if (dist[i][j] > dist[i][k] + dist[k][j]) {
        				dist[i][j] = dist[i][k] + dist[k][j];
        			}
        		}
        	}
        }
        
        for (int i=1; i<=V; i++) {
        	if (dist[i][i] < min) {
        		min = dist[i][i];
        	}
        }
        
        if (min == INF) {
        	bw.write("-1");
        } else {
        	bw.write(min + "");
        }
        
        br.close();
        bw.close();
    }
	
	
}
