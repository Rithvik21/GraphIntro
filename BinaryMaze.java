import java.util.*;

public class BinaryMaze {
    public int shortestPath(int[][] matrix, int[] start, int[] end) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if (start[0] < 0 || start[0] >= rows || start[1] < 0 || start[1] >= cols ||
            end[0] < 0 || end[0] >= rows || end[1] < 0 || end[1] >= cols) {
            return -1;
        }
        
        if (matrix[start[0]][start[1]] != 1 || matrix[end[0]][end[1]] != 1) {
            return -1;
        }
        
        Queue<int[]> search = new LinkedList<>();
        search.offer(start);
        
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;
        
        int[][] dist = new int[rows][cols];
        dist[start[0]][start[1]] = 0;
        
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        
        while (!search.isEmpty()) {
            int[] curr = search.poll();
            
            if (curr[0] == end[0] && curr[1] == end[1]) {
                return dist[curr[0]][curr[1]];
            }
            
            for (int[] d : dir) {
                int nRow = curr[0] + d[0];
                int nCol = curr[1] + d[1];
                
                if (nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols &&
                    matrix[nRow][nCol] == 1 && !visited[nRow][nCol]) {
                        
                    search.offer(new int[]{nRow, nCol});
                    visited[nRow][nCol] = true;
                    dist[nRow][nCol] = dist[curr[0]][curr[1]] + 1;
                }
            }
        }
        
        return -1;
    }
}
