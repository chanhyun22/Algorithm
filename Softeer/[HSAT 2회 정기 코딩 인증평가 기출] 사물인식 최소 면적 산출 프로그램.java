import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Point>[] colors;
    static int N, K, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        colors = new ArrayList[K];

        for (int i = 0; i < K; i++) {
            colors[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Point p = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int color = Integer.parseInt(st.nextToken()) - 1;
            colors[color].add(p);
        }

        for (int i = 0; i < colors[0].size(); i++) {
            Point p = colors[0].get(i);
            makeSquare(1, p.x, p.x, p.y, p.y);
        }

        bw.write(ans + "");

        br.close();
        bw.close();
    }

    public static void makeSquare(int color, int minX, int maxX, int minY, int maxY) {
        int size = (maxX - minX) * (maxY - minY);
        if (size >= ans)
            return;

        if (color == K) {
            ans = size;
            return;
        }

        for (int i = 0; i < colors[color].size(); i++) {
            Point p = colors[color].get(i);
            makeSquare(color + 1, Math.min(minX, p.x), Math.max(maxX, p.x), Math.min(minY, p.y), Math.max(maxY, p.y));
        }

    }
}
