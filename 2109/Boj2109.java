import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2109 {
	
	public static class Lecture implements Comparable<Lecture>{
		int pay;
		int day;
		
		public Lecture(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}
		
		@Override
		public int compareTo(Lecture o) {
			if (this.pay == o.pay) {
				return o.day - this.day;
			}
			return o.pay - this.pay;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int cost = 0;
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        for (int i=0; i<n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int p = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	pq.add(new Lecture(p, d));
        }
        
        boolean[] visited = new boolean[10001];
        
        while(!pq.isEmpty()) {        
        	Lecture l = pq.poll();
        	int curday = l.day;
        	while (visited[curday]) {
        		curday--;
        	}
        	if (curday == 0) {
        		continue;
        	}
        	visited[curday] = true;
        	cost+= l.pay;
        }
        
        bw.write(cost + "");
        
        br.close();
        bw.close();
    }
	
	
}
