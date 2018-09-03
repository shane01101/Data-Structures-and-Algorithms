import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        int n = adj.length;
        int[] bipartite = new int[n];
        int result = 1;
        Queue<Integer> theQueue = new LinkedList<>();
        
        for(int i = 0; i < n; i++)
            bipartite[i] = -1;
        bipartite[0] = 0;
        theQueue.add(0);
        
        while(!theQueue.isEmpty()) {
            int u = theQueue.poll();
            
            for(int v : adj[u]) {
                if(bipartite[v] == -1) {
                    theQueue.add(v);
                    if(bipartite[u] == 0)
                        bipartite[v] = 1;
                    else
                        bipartite[v] = 0;
                }
                else {
                    if(bipartite[u] == bipartite[v]) {
                        result = 0;
                        break;
                    }
                }
            }
        }
        return result;
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

