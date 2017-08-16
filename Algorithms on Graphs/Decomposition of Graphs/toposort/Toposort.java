
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) 
    {
        //int used[] = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        //Stack<Integer> theStack = new Stack<>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        
        for(int i = 0; i < adj.length; i++)
        {
            if(!visited[i])
                toposort(adj, visited, i, order);
        }
        
        return order;
    }


    private static void toposort(ArrayList<Integer>[] adj, boolean[] visited, int v, ArrayList<Integer> order) 
    {
        visited[v] = true;
        
        for(int adjacent : adj[v])
        {
            if(!visited[adjacent])
                toposort(adj, visited, adjacent, order);
        }
        order.add(0,v);  //add to beginning, same as reverse ordering
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

