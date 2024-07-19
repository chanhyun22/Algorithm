import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj10711 {
    static int H, W;
    static int[][] map;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public static class Point {
    	int r;
    	int c;
    	
    	public Point(int r, int c) {
    		this.r = r;
    		this.c = c;
		}
    }
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Queue<Point> q = new LinkedList<>();
        map = new int[H][W];
        for (int i=0; i<H;i++) {	
        	String word = br.readLine();
        	for (int j=0; j<W;j++) {
        		if (word.charAt(j) == '.') {
        			map[i][j] = 0;
        			q.add(new Point(i, j));
        		} else {
        			map[i][j] = word.charAt(j)-'0';
        		}
        	}
        }
        
        int answer = -1;
        
        while(!q.isEmpty()) {
        	answer++;
        	int size = q.size();
        	for (int i=0; i<size; i++) {
        		Point p = q.poll();
        		for (int k=0;k<8;k++) {
        			if (p.r +dr[k] >=0 && p.r +dr[k] < H && p.c + dc[k] >=0 && p.c+dc[k] < W) {
        				if (1<= map[p.r+dr[k]][p.c+dc[k]] && map[p.r+dr[k]][p.c+dc[k]] <= 8) {
        					map[p.r+dr[k]][p.c+dc[k]]--;
        					if (map[p.r+dr[k]][p.c+dc[k]] == 0) {
        						q.add(new Point(p.r+dr[k], p.c+dc[k]));
        					}
        				}
        				
    				}
        		}
        	}
        }
        
        bw.write(answer+"");
        br.close();
        bw.close();
    }
	
	
}
