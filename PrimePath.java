import java.util.*;

public class PrimePath {

    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    public boolean oneDigOff(int n1, int n2) {
        int diff = 0;
        for (int i = 0; i < 4; i++) {
            if (n1 % 10 != n2 % 10) {
                diff++;
            }
            n1 /= 10;
            n2 /= 10;
        }
        return diff == 1;
    }

    public int shortestPath(int s, int e) {

        List<Integer> primes = new ArrayList<>();
        for (int i = 1000; i < 10000; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = i + 1; j < primes.size(); j++) {
                if (oneDigOff(primes.get(i), primes.get(j))) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        Queue<Integer> search = new LinkedList<>();
        boolean[] visited = new boolean[primes.size()];
        int[] distance = new int[primes.size()];

        int start = primes.indexOf(s);
        int end = primes.indexOf(e);
        search.add(start);
        visited[start] = true;

        while (!search.isEmpty()) {
            int curr = search.poll();
            if (curr == end) {
                return distance[curr];
            }
            for (int adj : graph.get(curr)) {
                if (!visited[adj]) {
                    distance[adj] = distance[curr] + 1;
                    visited[adj] = true;
                    search.add(adj);
                }
            }
        }
        
        return 0;
    }
    
    
}
