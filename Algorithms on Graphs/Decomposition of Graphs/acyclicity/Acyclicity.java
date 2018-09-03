import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) 
    {
        boolean[] visited = new boolean[adj.length];
        ArrayList<Integer> verticesSeen = new ArrayList<>();
        
        for(int i = 0; i < adj.length; i++) {
            verticesSeen.clear();
            
            if(!visited[i]) {
                verticesSeen.add(i);
                
                if(dfs(adj, visited, verticesSeen, i))
                    return 1;
            }
        }
        return 0;
    }
    
    private static boolean dfs(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> verticesSeen, int u) {
        visited[u] = true;
        
        for(int v: adj[u]) {
            if(verticesSeen.contains(v))
                return true;
            else
                verticesSeen.add(v);
        
            if(!visited[v]) {
                if(dfs(adj, visited, verticesSeen, v))
                    return true;
                
                verticesSeen.clear();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

