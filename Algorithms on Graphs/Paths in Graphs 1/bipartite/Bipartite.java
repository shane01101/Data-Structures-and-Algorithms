import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static final int BLACK = 0;
    private static final int WHITE = 1;
    private static int bipartite(ArrayList<Integer>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Queue<Integer> theQueue = new LinkedList<>();
        
        for(int i = 0; i < n; i++)
            dist[i] = Integer.MAX_VALUE;
        
        int source = 0;
        dist[source] = BLACK;
        theQueue.add(source);
        
        while(!theQueue.isEmpty()) {
            int top = theQueue.poll();
            
            for(int adjacent: adj[top])
                if(dist[adjacent] == Integer.MAX_VALUE) {
                    theQueue.add(adjacent);
                    //assign neighbor opposite color
                    dist[adjacent] = dist[top] == BLACK ? WHITE : BLACK;
                    
                }
                else //has been explored, check if nodes are same color neighbors
                    if((dist[top] == BLACK && dist[adjacent] == BLACK) 
                            || (dist[top] == WHITE && dist[adjacent] == WHITE))
                        return 0;
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

