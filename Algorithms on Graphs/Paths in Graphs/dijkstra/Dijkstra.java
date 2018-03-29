import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) 
    {
        boolean[] visited = new boolean[adj.length];
        int[] dist = new int[adj.length];
        int[] path = new int[adj.length];
        
        for(int i = 0; i < adj.length; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        
        dist[s] = 0;
        
        for(int i = 0; i < adj.length; i++)
        {
            int u = findNextSmallest(dist, visited);
            visited[u] = true;
            
            for(int j = 0; j < adj[u].size(); j++)
            {
                int v = adj[u].get(j);
                
                if(dist[v] > dist[u] + cost[u].get(j))
                {
                    dist[v] = dist[u] + cost[u].get(j);
                    path[v] = u;
                }
            }
        }
        
                //print distance
//        System.out.println("Vertex        Distance");
//        for(int i = 0; i < dist.length; i++)
//        {
//            System.out.println(i + "             " + dist[i]);
//        }
        
        if(dist[t] == Integer.MAX_VALUE)
            return -1;
        else 
            return dist[t];
    }
    
    private static int findNextSmallest(int[] dist, boolean[] visited)
    {
        int min = 0;
        int lowVal = Integer.MAX_VALUE;
        
        for(int i = 0; i < dist.length; i++)
            if(dist[i] < lowVal && visited[i] == false)
            {
                min = i;
                lowVal = dist[i];
            }
        return min;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) 
        {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) 
        {
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

