public class [HSAT 4회 정기 코딩 인증평가 기출] 슈퍼컴퓨터 클러스터 {
    
}
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new long[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long start = 1;
        long end = 2000000000;
        long ans = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (Check(mid)) {
                start = mid+1;
                ans = mid;
            } else {
                end = mid-1;
            }
        }

        bw.write(ans+"");
        
        br.close();
        bw.close();
    }

    public static boolean Check(long mid) {
        long sum = 0;
        for (int i=0; i<N; i++) {
            if (arr[i] < mid) {
                sum += (mid - arr[i]) * (mid - arr[i]);
                if (sum > B) {
                    break;
                }
            }
        }

        if (sum > B) {
            return false;
        } else {
            return true;
        }
    }
}
