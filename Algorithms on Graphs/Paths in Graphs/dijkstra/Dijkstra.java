
import java.util.*;

public class Dijkstra 
{
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int source, int dest) {
        boolean visited[] = new boolean[adj.length];
        int path[] = new int[adj.length];
        int dist[] = new int[adj.length];
        
        //Assign all dist to infinity/Max Integer and source node to 0 cost
        for(int i = 0; i < adj.length; i++)
        {
            path[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;
        
        for(int i = 0; i < adj.length; i++)
        {
            int u = findNext(dist, visited); //find smallest unmarked v
            visited[u] = true;
            
            for(int j = 0; j < adj[u].size(); j++) //for every (u,v) explorable in  E
            {
                int v = adj[u].get(j);
                int w = cost[u].get(j);
                
                if(dist[v] > dist[u] + w)
                {
                    dist[v] = dist[u] + w;
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
        
        if(dist[dest] == Integer.MAX_VALUE)
            return -1;
        else 
            return dist[dest];
    }
    
    public static int findNext(int dist[], boolean visited[])
    {
        int min = 0;
        int lowVal = Integer.MAX_VALUE;
        
        for(int i = 0; i < dist.length; i++)
        {
            if(dist[i] < lowVal && visited[i] == false)
            {
                min = i;
                lowVal = dist[i];
            }
        }
        return min;
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
