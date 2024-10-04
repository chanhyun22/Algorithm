import java.util.*;

class Solution {
    
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int r, c;
    public static char[][] map;
    public static boolean[][] visited;
    
    public int[] solution(String[] maps) {
        r = maps.length;
        c = maps[0].length();
        map = new char[r][c];
        visited = new boolean[r][c];
        
        for (int i=0; i<r; i++){
            for (int j=0; j<c; j++){
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        List<Integer> islandList = new ArrayList<>();
        
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if(!visited[i][j]){
                    if (map[i][j] != 'X'){
                        int sum = dfs(i,j);
                        islandList.add(sum);
                    }
                }
            }
        }
        
        if (islandList.isEmpty()){
            return new int[]{-1};
        }
        
        Collections.sort(islandList);
        
        int[] answer = new int[islandList.size()];
        for (int i = 0; i < islandList.size(); i++) {
            answer[i] = islandList.get(i);
        }
        
        return answer;
    }
    
    public static int dfs (int i, int j) {
        visited[i][j] = true;
        int sum = map[i][j] -'0';
        for (int k=0; k<4; k++){
            int ni = i + dr[k];
            int nj = j + dc[k];
            if (ni >=0 && ni < r && nj >=0 && nj<c) {
                if(!visited[ni][nj] && map[ni][nj] != 'X'){
                    sum+= dfs(ni,nj);
                }
            }   
        }
        return sum;
    }
}