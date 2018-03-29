import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static final int UNASSIGNED = -1;
    private static final int WHITE = 0;
    private static final int BLACK = 1;
    private static int bipartite(ArrayList<Integer>[] adj) 
    {
        Queue<Integer> theQueue = new LinkedList<Integer>();
        int[] colorSet = new int[adj.length];
        
        for(int i = 0; i < adj.length; i++)
            colorSet[i] = UNASSIGNED;
        
        colorSet[0] = WHITE;
        theQueue.add(0);
        
        while(!theQueue.isEmpty())
        {
            int u = theQueue.remove();
            
            for(int v : adj[u])
            {
                if(colorSet[v] == UNASSIGNED)
                {
                    theQueue.add(v);
                    colorSet[v] = ((colorSet[u] + 1) % 2 == 0) ? WHITE : BLACK;
                }
                else if(colorSet[u] == colorSet[v])
                        return 0;
            }
        }
        return 1;
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

