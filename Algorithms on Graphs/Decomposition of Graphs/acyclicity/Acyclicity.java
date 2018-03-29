import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) 
    {
        boolean[] visited = new boolean[adj.length];
        boolean[] curCycle = new boolean[adj.length];
        
        for(int i = 0; i < adj.length; i++)
        {
            visited[i] = false;
            curCycle[i] = false;
        }
        
        for(int i = 0; i < adj.length; i++)
        {
            if(visited[i] == false)
            {   
                if(DFSCycle(adj, visited, curCycle, i))
                    return 1;
            }
        }
        return 0;
    }
    private static boolean DFSCycle(ArrayList<Integer>[] adj, boolean[] visited, 
            boolean[] curCycle, int index)
    {
        visited[index] = true;
        curCycle[index] = true;

        for(int adjacent : adj[index])
        {
            if(visited[adjacent] == false && DFSCycle(adj, visited, curCycle, adjacent))
                return true;
            else if(curCycle[adjacent])
                return true;
        }
        curCycle[index] = false;
        
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

