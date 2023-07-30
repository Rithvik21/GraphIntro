import java.util.*;

public class Play {
    public int[] count(int scenarios, int[][] cases) {
        int[] results = new int[scenarios]; 
        
        for (int i = 0; i < scenarios; i++) {
            int n = cases[i][0]; 
            int m = cases[i][1]; 
            int l = cases[i][2]; 
            
            List<Integer>[] neighbors = new List[n+1];
            for (int j = 1; j <= n; j++) {
                neighbors[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int x = cases[i][j+3];
                int y = cases[i][j+4];
                neighbors[x].add(y);
            }
            
            Set<Integer> fellDown = new HashSet<>();
            for (int j = 0; j < l; j++) {
                int z = cases[i][j+m+3];
                fellDown.add(z);
            }
            
            boolean[] visited = new boolean[n+1];
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    cnt += search(j, neighbors, visited, fellDown);
                }
            }
            results[i] = cnt;
        }
        
        return results;
    }
    
    public int search(int domino, List<Integer>[] neighbors, boolean[] visited, Set<Integer> knockedOver) {
        visited[domino] = true;
        int count = 1;
        for (int adj : neighbors[domino]) {
            if (!visited[adj] && !knockedOver.contains(adj)) {
                count += search(adj, neighbors, visited, knockedOver);
            }
        }
        return count;
    }
}
