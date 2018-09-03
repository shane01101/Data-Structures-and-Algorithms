import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        //int used[] = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        ArrayList<Integer> order = new ArrayList<>();
        
        for(int i = 0; i < adj.length; i++)
            if(!visited[i])
                dfs(adj, visited, order, i);
        
        Collections.reverse(order);
        
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> order, int u) {
        visited[u] = true;
        
        for(int v : adj[u]) 
            if(!visited[v]) 
                dfs(adj, visited, order, v);

        order.add(u);
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

