import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	static int ans;
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        
        arr = new int[N];
        
        stk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(stk.nextToken());
        }
        
        int st = 1;
        int ed = N;
        ans = 1;
        
        while (st <= ed) {
        	int mid = (st + ed) / 2;
        	
        	if(check(mid)) {
        		ans = mid;
        		st = mid +1;
        	} else {
        		ed = mid - 1;
        	}
        }
        
        bw.write(ans+"");
        
        br.close();
        bw.close();
    }

	private static boolean check(int mid) {
		int count = 0; 
		Set<Integer> set = new HashSet<>();
		int left = 0;
		
		for (int right = 0; right < N ; right++) {
			while (set.contains(arr[right])) {
				set.remove(arr[left]);
				left++;
			}
			
			
			set.add(arr[right]);
			
			if (set.size() == mid) {
				count++;
				set.clear();
				if (count >= M) {
					return true;
				}
			}

		}
		
		return false;

	}
}