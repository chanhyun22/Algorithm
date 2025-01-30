import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int ans;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[][] arr = new int[10][10];
        
        for (int i=0; i<10; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int j=0; j<10; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        ans = 26;
        
        dfs(0, 0, 0, 0, 0, arr);
        
        if (ans == 26) {
        	ans = -1;
        }
        
        bw.write(ans+"");
        
        br.close();
        bw.close();
    }

	public static void dfs(int one, int two, int three, int four, int five, int[][] arr) {
		int sum = one + two + three +four +five;
		if (one > 5 || two > 5 || three > 5 || four > 5 || five >5 || sum > ans) {
			return;
		}
		
		boolean check = true;
		
		out : for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (arr[i][j] == 1) {
					check= false;
					break out;
				}
			}
		}
		
		if (check) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (arr[i][j] == 1) {
					arr[i][j] =0;
					dfs(one+1, two, three, four, five, arr);
					arr[i][j] = 1;
					
					boolean check2 = true;
					if (i < 9 && j < 9) {
						for (int r=0; r<2; r++) {
							for (int c=0; c<2; c++) {
								if (arr[i+r][j+c] == 0) {
									check2 = false;
								}
							}
						}
						if (check2) {
							for (int r=0; r<2; r++) {
								for (int c=0; c<2; c++) {
									arr[i+r][j+c] = 0;
								}
							}
							dfs(one, two+1, three, four, five, arr);
							
							for (int r=0; r<2; r++) {
								for (int c=0; c<2; c++) {
									arr[i+r][j+c] = 1;
								}
							}
						}
					}
					
					boolean check3 = true;
					if (i < 8 && j < 8) {
						for (int r=0; r<3; r++) {
							for (int c=0; c<3; c++) {
								if (arr[i+r][j+c] == 0) {
									check3 = false;
								}
							}
						}
						if (check3) {
							for (int r=0; r<3; r++) {
								for (int c=0; c<3; c++) {
									arr[i+r][j+c] = 0;
								}
							}
							dfs(one, two, three+1, four, five, arr);
							
							for (int r=0; r<3; r++) {
								for (int c=0; c<3; c++) {
									arr[i+r][j+c] = 1;
								}
							}
						}
					}
					
					boolean check4 = true;
					if (i < 7 && j < 7) {
						for (int r=0; r<4; r++) {
							for (int c=0; c<4; c++) {
								if (arr[i+r][j+c] == 0) {
									check4 = false;
								}
							}
						}
						if (check4) {
							for (int r=0; r<4; r++) {
								for (int c=0; c<4; c++) {
									arr[i+r][j+c] = 0;
								}
							}
							dfs(one, two, three, four+1, five, arr);
							
							for (int r=0; r<4; r++) {
								for (int c=0; c<4; c++) {
									arr[i+r][j+c] = 1;
								}
							}
						}
					}	
					
					boolean check5 = true;
					if (i < 6 && j < 6) {
						for (int r=0; r<5; r++) {
							for (int c=0; c<5; c++) {
								if (arr[i+r][j+c] == 0) {
									check5 = false;
								}
							}
						}
						if (check5) {
							for (int r=0; r<5; r++) {
								for (int c=0; c<5; c++) {
									arr[i+r][j+c] = 0;
								}
							}
							dfs(one, two, three, four, five+1, arr);
							
							for (int r=0; r<5; r++) {
								for (int c=0; c<5; c++) {
									arr[i+r][j+c] = 1;
								}
							}
						}
					}
					return;
					
				}
			}
		}
		
		
		
	}
}
