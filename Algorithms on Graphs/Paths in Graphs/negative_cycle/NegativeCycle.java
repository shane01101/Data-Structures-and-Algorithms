import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        long prev[] = new long[adj.length];
        long dist[] = new long[adj.length];
        
        //Assign all dist to infinity/Max Integer and source node to 0 cost
        for(int i = 0; i < adj.length; i++)
        {
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;
        
        //Run the algorithm |V| - 1 times
        for(int i = 0; i < adj.length - 1; i++)
        {   
            for(int u = 0; u < adj.length; u++) 
            {
                for(int v : adj[u])
                {
                    if(dist[v] > dist[u] + cost[u].indexOf(v))
                    {
                        dist[v] = dist[u] + cost[u].indexOf(v);
                        prev[v] = u;
                    }
                }
            }
        }
        
        //Run the algorithm one last time to see if any distances change
        //No changes should occur if no negative cycles
        for(int u = 0; u < adj.length; u++) 
        {
            for(int v : adj[u])
            {
                if(dist[v] > dist[u] + cost[u].indexOf(v))
                {
                    return 1; //Negative cycle since dist changed
                }
            }
        }
        return 0;
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
        System.out.println(negativeCycle(adj, cost));
    }
}