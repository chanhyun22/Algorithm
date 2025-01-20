import java.io.*;
import java.util.*;

public class Main {

    public static class Person implements Comparable<Person> {
        int idx;
        int score;

        public Person(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Person o) {
            return o.score - this.score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Person[] sum = new Person[N];
        for (int i = 0; i < N; i++) {
            sum[i] = new Person(i, 0);
        }

        for (int i = 0; i < 3; i++) {
            Person[] p = new Person[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                p[j] = new Person(j, Integer.parseInt(st.nextToken()));
                sum[j].score += p[j].score;
            }
            Arrays.sort(p);
            int[] ans = new int[N];
            ans[p[0].idx] = 1;
            for (int j = 1; j < N; j++) {
                if (p[j].score == p[j - 1].score) {
                    ans[p[j].idx] = ans[p[j - 1].idx];
                } else {
                    ans[p[j].idx] = j + 1;
                }
            }

            for (int j = 0; j < N; j++) {
                bw.write(ans[j] + " ");
            }
            bw.write("\n");
        }

        Arrays.sort(sum);
        int[] last = new int[N];
        last[sum[0].idx] = 1;
        for (int i = 1; i < N; i++) {
            if (sum[i].score == sum[i - 1].score) {
                last[sum[i].idx] = last[sum[i - 1].idx];
            } else {
                last[sum[i].idx] = i + 1;
            }
        }

        for (int i = 0; i < N; i++) {
            bw.write(last[i] + " ");
        }

        br.close();
        bw.close();
    }
}
