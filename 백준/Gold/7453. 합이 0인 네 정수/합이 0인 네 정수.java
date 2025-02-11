import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long[][] arr = new long[N][4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        int size = N * N;
        long[] sum1 = new long[size];
        long[] sum2 = new long[size];

        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum1[index] = arr[i][0] + arr[j][1];
                sum2[index] = arr[i][2] + arr[j][3];
                index++;
            }
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);

        int st = 0, ed = size - 1;
        long count = 0;

        while (st < size && ed >= 0) {
            long a = sum1[st];
            long b = sum2[ed];
            long sum = a + b;

            if (sum == 0) {
                long aCount = 1, bCount = 1;
                while (st + 1 < size && sum1[st] == sum1[st + 1]) {
                    st++;
                    aCount++;
                }
                while (ed - 1 >= 0 && sum2[ed] == sum2[ed - 1]) {
                    ed--;
                    bCount++;
                }
                count += aCount * bCount;
                st++;
                ed--;
            } else if (sum < 0) {
                st++;
            } else {
                ed--;
            }
        }

        bw.write(count + "\n");

        br.close();
        bw.close();
    }
}
