import java.util.*;

public class Scooby {
    public static String reachable(List<String> edges, char start, char end) {
        
        Map<Character, List<Character>> graph = new HashMap<>();
        
        for (String passage : edges) {
            char room1 = passage.charAt(0);
            char room2 = passage.charAt(1);
            
          
            if (!graph.containsKey(room1)) {
                graph.put(room1, new ArrayList<>());
            }
            graph.get(room1).add(room2);
            
        
            if (!graph.containsKey(room2)) {
                graph.put(room2, new ArrayList<>());
            }
            graph.get(room2).add(room1);
        }
        
        Queue<Character> search = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        search.add(start);
        visited.add(start);
        
        while (!search.isEmpty()) {
            char currRoom = search.poll();
            if (currRoom == end) {
                return "yes";
            }
            
            for (char adj : graph.get(currRoom)) {
                if (!visited.contains(adj)) {
                    search.add(adj);
                    visited.add(adj);
                }
            }
        }
        
        return "no";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine(); 
        
        for (int i = 0; i < cases; i++) {
           
            List<String> edges = Arrays.asList(sc.nextLine().split(" "));
            
            String[] connections = sc.nextLine().split(" ");
            char s = connections[0].charAt(0);
            char e = connections[1].charAt(0);
            
            String result = reachable(edges, s, e);
            System.out.println(result);
        }
        
        sc.close();
    }
}
