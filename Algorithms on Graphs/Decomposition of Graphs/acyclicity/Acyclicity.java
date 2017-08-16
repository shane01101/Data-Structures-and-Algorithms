
import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) 
    {
        boolean[] visited = new boolean[adj.length];
        boolean[] ancestor = new boolean[adj.length];
        
        for(int i = 0; i < adj.length; i++)
        {
            if(!visited[i])
                if(DFSCycle(adj, visited, ancestor, i))
                    return 1;
        }     
        return 0;
    }
    
    private static boolean DFSCycle(ArrayList<Integer>[] adj, boolean[] visited, 
            boolean[] ancestor, int v)
    {
        if(!visited[v]) //visit v if not visted
        {
            visited[v] = true;
            ancestor[v] = true;

            for(int adjacent : adj[v]) //iterate through adjacent nodes
            {
                if(!visited[adjacent] && (DFSCycle(adj, visited, ancestor, adjacent)))
                    return true;
                else if(ancestor[adjacent])
                    return true;
            }
        }
        ancestor[v] = false; //set back to false after checking
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

