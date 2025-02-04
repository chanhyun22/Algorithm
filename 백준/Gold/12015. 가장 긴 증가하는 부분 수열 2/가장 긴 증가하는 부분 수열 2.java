import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        lis[0] = arr[0];
        int idx=0;
        for (int i=1; i<N;i++) {
            if (lis[idx] < arr[i]) {
                lis[++idx] = arr[i];
            } else {
                int num = BinarySearch(0,idx,arr[i]);
                lis[num] = arr[i];
            }
        }
        bw.write(idx+1 + " ");
        
        br.close();
        bw.close();
    }

    private static int BinarySearch(int st, int ed, int value) {
    	int mid = (st+ed) / 2;
        while (st <= ed) {
        	mid = (st+ed)/2;
        	if (lis[mid] == value) {
        		return mid;
        	}
        	if (lis[mid] > value) {
        		ed = mid -1;
        	} else {
        		st = mid +1;
        	}
        }
        return st;
    }
}
