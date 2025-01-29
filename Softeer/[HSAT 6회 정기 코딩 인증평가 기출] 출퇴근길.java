import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<Integer>[] list;
    static List<Integer>[] listR;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        listR = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            listR[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            listR[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] StoN = new boolean[n + 1];
        boolean[] TtoN = new boolean[n + 1];
        boolean[] NtoS = new boolean[n + 1];
        boolean[] NtoT = new boolean[n + 1];

        StoN[end] = true;
        TtoN[start] = true;

        dfs(start, list, StoN);
        dfs(end, list, TtoN);
        dfs(start, listR, NtoS);
        dfs(end, listR, NtoT);

        int ans = -2;
        for (int i = 1; i <= n; i++) {
            if (StoN[i] && TtoN[i] && NtoS[i] && NtoT[i]) {
                ans++;
            }
        }
        bw.write(ans + "");

        br.close();
        bw.close();
    }

    public static void dfs(int cur, List<Integer>[] curlist, boolean[] visited) {
        if (visited[cur]) {
            return;
        }

        visited[cur] = true;
        for (int next : curlist[cur]) {
            dfs(next, curlist, visited);
        }
    }

}
