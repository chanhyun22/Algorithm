public class [HSAT 5회 정기 코딩 인증평가 기출] 업무 처리 {
    
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int N = (int) Math.pow(2, H+1) - 1; // 전체 노드의 갯수
        int B = (int) Math.pow(2, H); // 말단 노드의 갯수

        Queue<Integer>[][] worker = new Queue[N][2];
        for (int i=0; i<N; i++) {
            for (int j=0; j<2; j++) {
                worker[i][j] = new LinkedList<>();
            }
        }

        for (int i=N-B; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<K; j++) {
                worker[i][0].add(Integer.parseInt(st.nextToken()));
            }
        }

        int day = 1;
        int ans = 0;
        while (day <= R) {
            if(day % 2 == 0 && !worker[0][1].isEmpty()) {
                ans += worker[0][1].poll();
            } else if (day % 2 == 1 && !worker[0][0].isEmpty()) {
                ans += worker[0][0].poll();
            }

            for (int i=1; i<N-B; i++) {
                int P = (i-1) / 2;
                if (day % 2 == 0 && !worker[i][1].isEmpty()) {
                    if (i%2 == 1) {
                        worker[P][0].add(worker[i][1].poll());
                    } else {
                        worker[P][1].add(worker[i][1].poll());
                    }
                } else if (day % 2 == 1 && !worker[i][0].isEmpty()) {
                    if (i%2 == 1) {
                        worker[P][0].add(worker[i][0].poll());
                    } else {
                        worker[P][1].add(worker[i][0].poll());
                    }
                }
            }

            for (int i=N-B; i<N; i++) {
                int P = (i-1) / 2;
                if (!worker[i][0].isEmpty()) {
                    if (i%2 == 1) {
                        worker[P][0].add(worker[i][0].poll());
                    } else {
                        worker[P][1].add(worker[i][0].poll());
                    }
                }
            }
            day++;
        }
        
        bw.write(ans+"");
        
        br.close();
        bw.close();
    }
}
