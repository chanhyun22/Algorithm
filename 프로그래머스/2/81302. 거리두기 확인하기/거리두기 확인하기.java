class Solution {
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i=0; i<5; i++){
            char[][] map = new char[5][5];
            for (int j=0; j<5;j++){
                String word = places[i][j];
                for (int k=0; k<5;k++){
                    map[j][k] = word.charAt(k);
                }
            }
            boolean suc = true;
            out : for (int j=0; j<5; j++) {
                for (int k=0; k<5; k++){
                    if (map[j][k] =='P') {
                        for (int l=0; l<4;l++) {
                            if (j+dx[l] >=0 && j+dx[l] <5 && k+dy[l] >=0 && k+dy[l] <5) {
                                if (map[j+dx[l]][k+dy[l]] =='P') {
                                    suc = false;
                                    break out;
                                } else if (map[j+dx[l]][k+dy[l]] == 'O') {
                                    int nx = j+dx[l];
                                    int ny = k+dy[l];
                                    for (int m=0; m<4;m++){
                                        if (nx+dx[m] >=0 && nx+dx[m] <5 && ny+dy[m] >=0 && ny+dy[m] <5) {
                                            if (nx+dx[m] != j || ny+dy[m] != k) {
                                                if (map[nx+dx[m]][ny+dy[m]]=='P'){
                                                    suc=false;
                                                    break out;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            
            if (suc) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }
}