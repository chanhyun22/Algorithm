import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Circle[] arr;

	static class Circle implements Comparable<Circle> {
		int k;
		int x;
		int r;
		int p;

		public Circle(int k, int x, int r, int p) {
			this.k = k;
			this.x = x;
			this.r = r;
			this.p = p;
		}

		@Override
		public int compareTo(Circle o) {
			return this.p - o.p;
		}

	}
	
	static class Circle2 implements Comparable<Circle2> {
		int k;
		int x;
		int r;
		int p;

		public Circle2(int k, int x, int r, int p) {
			this.k = k;
			this.x = x;
			this.r = r;
			this.p = p;
		}

		@Override
		public int compareTo(Circle2 o) {
			return o.p - this.p;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new Circle[N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			arr[k] = new Circle(k, x, r, x + r);
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		if (external(A, B)) { 
			PriorityQueue<Circle> listA = new PriorityQueue<>();
			PriorityQueue<Circle2> listB = new PriorityQueue<>();
			PriorityQueue<Circle> Common = new PriorityQueue<>();
			
			for (int i=1; i<=N; i++) {
				if (i != A && i !=B) {
					if (internal(A, i)) {
						if (external(i, B)) {
							listA.add(new Circle(arr[i].k, arr[i].x, arr[i].r, arr[i].p));
						}
						
						if (internal(B, i)) {
							Common.add(new Circle(arr[i].k, arr[i].x, arr[i].r, arr[i].p));
						}
					}
					
					if (internal(B, i)) {
						if (external(i, A)) {
							listB.add(new Circle2(arr[i].k, arr[i].x, arr[i].r, arr[i].p));
						}
					}
				}
			}
			
			if (Common.isEmpty()) {
				int ans = 3 + listA.size() + listB.size();
				bw.write(ans + "\n");
				bw.write(A + " ");
				while(!listA.isEmpty()) {
					bw.write(listA.poll().k + " ");
				}
				bw.write("0 ");
				while(!listB.isEmpty()) {
					bw.write(listB.poll().k + " ");
				}
				bw.write("" + B);
			} else {
				int ans = 2 + listA.size() + 1 + listB.size();
				bw.write(ans+ "\n");
				bw.write(A + " ");
				while(!listA.isEmpty()) {
					bw.write(listA.poll().k + " ");
				}
				
				for (int i = 0; i < 1; i++) {
					Circle c = Common.poll();
					bw.write(c.k + " ");
				}
				
				while(!listB.isEmpty()) {
					bw.write(listB.poll().k + " ");
				}
				
				bw.write("" + B);
			}
			
			
		}  else {
			if (internal(A, B)) {
				PriorityQueue<Circle> list = new PriorityQueue<>();
				
				for (int i=1 ; i<=N; i++) {
					if (i!= A && i!= B) {
						if (internal(A, i) && internal(i, B)) {
							list.add(new Circle(arr[i].k, arr[i].x, arr[i].r, arr[i].p));
						}
					}
				}
				int ans = 2 + list.size();
				bw.write(ans + "\n");
				bw.write(A + " ");
				while(!list.isEmpty()) {
					bw.write(list.poll().k + " ");
				}
				bw.write("" + B);
			} else {
				PriorityQueue<Circle2> list = new PriorityQueue<>();
				for (int i=1; i<=N; i++) {
					if (i!=A && i!=B) {
						if (internal(B, i) && internal(i, A)) {
							list.add(new Circle2(arr[i].k , arr[i].x, arr[i].r, arr[i].p));
						}
					}
				}
				int ans = 2 + list.size();
				bw.write(ans + "\n");
				bw.write(A + " ");
				while(!list.isEmpty()) {
					bw.write(list.poll().k + " ");
				}
				bw.write("" + B);
			}
		} 
		

		br.close();
		bw.close();
	}

	private static boolean internal(int a, int b) {
		int d = Math.abs(arr[a].x - arr[b].x);
		if (arr[a].r < arr[b].r) {
			if (d < arr[b].r - arr[a].r) {
				return true;
			}
		}
		return false;
	}

	private static boolean external(int a, int b) {
		int d = Math.abs(arr[a].x - arr[b].x);
		if (arr[a].r + arr[b].r < d) {
			return true;
		}
		return false;
	}
}
