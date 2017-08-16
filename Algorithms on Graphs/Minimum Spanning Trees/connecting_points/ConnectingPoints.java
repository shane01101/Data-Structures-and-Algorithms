
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ConnectingPoints 
{
    private static int[] rank;
    private static int[] parent;
    
    private static double minimumDistance(int[] x, int[] y) 
    {
        ArrayList<Edge> edgeList = new ArrayList<>();
        double result = 0.;
        initializeDisjointSets(x.length); //crate empty sets
        
        //compute edge weight and add to list
        for (int i = 0; i < x.length; i++) 
        {
            for (int j = i + 1; j < x.length; j++) 
            {
                edgeList.add(new Edge(i, j, computeDistance(x[i],
                                                y[i], x[j], y[j])));
            }
        }
        //Sort by increasing edge weight order (using overridden comparable)
        Collections.sort(edgeList);
        
        //relax edges in increasing order
        for(int i = 0; i < edgeList.size(); i++)
        {
            //get min edge and check if a cycle, if no cycle then add to result
            Edge min = edgeList.get(i);
            if(find(min.u) != find(min.v))
            {
                union(min.u, min.v);
                result += min.w;
            }
        }
        return result;
    }
    
    private static double computeDistance(int x1, int y1, int x2, int y2) 
    {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    public static void makeSet(int n)
    {
        parent[n] = n;
        rank[n] = 0;
    }
    
    public static int find(int n)
    {
        if(parent[n] != n)
        {
            //recursively call find until finding the represenative of the set
            //and move under represenative of the set
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }
    
    public static void union(int i, int j)
    {
        int i_id = find(i);
        int j_id = find(j);
        
        if(i_id == j_id)  //already lie in same set
            return;
        
        if(rank[i_id] > rank[j_id]) //set j_id under i_id set
            parent[j_id] = i_id;
        else
        {
            parent[i_id] = j_id; //set i_id under j_id set
            
            if(rank[i_id] == rank[j_id]) //union increases height of tree
                rank[j_id] = rank[j_id] + 1;
        }
    }
    
    public static void initializeDisjointSets(int n)
    {
        rank = new int[n];
        parent = new int[n];
        
        for(int i = 0; i < n; i++) //create empty sets
            makeSet(i);
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
    
    static class Edge implements Comparable<Edge>
    {
        int u, v;
        double w;
        public Edge(int u, int v, double w)
        {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        
        @Override
        public int compareTo(Edge item) 
        {
            if (this.w < item.w) {
                return -1;
            }
            else if(this.w > item.w){
                return 1;
            }
            return 0;
        }
    }
}