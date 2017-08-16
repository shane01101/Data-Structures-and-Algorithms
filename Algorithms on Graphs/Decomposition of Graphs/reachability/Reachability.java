import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean[] visited = new boolean[adj.length];
        
        if(DFS(adj, visited, x, y))
            return 1;
        else
            return 0;
    }
    
    private static boolean DFS(ArrayList<Integer>[] adj, boolean[] visited, int u, int v)
    {
        if(u == v)
            return true;
        
        visited[u] = true;
        //System.out.print(u + " ");
        
        for(int adjacent : adj[u])
        {
            if(!visited[adjacent])
                if(DFS(adj, visited, adjacent, v))
                    return true;
        }
        
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}