import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        long[] dist = new long[n];
        boolean[] visited = new boolean[n];
        int size = n - 1;
        
        for(int i = 0; i < n; i++) 
            dist[i] = Integer.MAX_VALUE;
        
        dist[s] = 0;
        
        while(size > 0) {
            int u = extractMin(dist, visited);
            size--;
            
            if(u == -1) //no outgoing edges, next iteration
                continue;
            
            for(int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                if(dist[v] > dist[u] + cost[u].get(i)) 
                    dist[v] = dist[u] + cost[u].get(i);
            }
        }

        if((int)dist[t] == Integer.MAX_VALUE)
            return -1;
        else
            return (int)dist[t];
    }
    
    private static int extractMin(long[] dist, boolean[] visited) {
        long min = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0; i < dist.length; i++)
            if(min > dist[i] && !visited[i]) {
                min = dist[i];
                index = i;
            }
        
        if(index == -1) 
            return -1;
        
        visited[index] = true;
        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}