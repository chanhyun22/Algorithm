import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;

        for (int i = 0; i < N - 2; i++) {
            long count = 0;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] < arr[j]) {
                    count++;
                }

                if (arr[i] > arr[j]) {
                    ans += count;
                }
            }
        }

        bw.write(ans + "");

        br.close();
        bw.close();
    }
}
