package Decomposition2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> seen = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            //clear seen so no false positives
            seen.clear();
            
            //add i to seen then explore
            if(!visited[i]) {
                seen.add(i);
                
                if(dfs(adj, seen, visited, i))
                    return 1;
            }
        }
        return 0;
    }
    
    private static boolean dfs(ArrayList<Integer>[] adj, ArrayList<Integer> seen, boolean[] visited, int u) {
        visited[u] = true;
        
        for(int adjacent: adj[u]) {
            //check if vertice already explored, if not add to seen and explore
            if(seen.contains(adjacent))
                return true;
            else
                seen.add(adjacent);
            
            if(!visited[adjacent]) 
                return dfs(adj, seen, visited, adjacent);
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