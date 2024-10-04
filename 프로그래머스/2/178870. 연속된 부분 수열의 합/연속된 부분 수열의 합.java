class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        
        int sum = sequence[0];
        
        while (end < sequence.length) {
            if (sum == k) {
                if(end - start < minLength){
                    minLength = end - start;
                    answer[0] = start;
                    answer[1] = end;
                }
                sum -= sequence[start];
                start++;
            } else if (sum < k) {
                end++;
                if (end < sequence.length) {
                    sum+= sequence[end];
                }
            } else {
                sum -= sequence[start];
                start++;
            }
        }
        
        return answer;
    }
}