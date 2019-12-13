import java.util.*;

public class Bipartite {
    private static final int WHITE = 0;
    private static final int BLACK = 1;

    private static int bipartite(ArrayList<Integer>[] adj) {
        Queue<Integer> theQueue = new LinkedList<>();
        int[] dist = new int[adj.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = WHITE;
        theQueue.add(0);

        while(!theQueue.isEmpty()) {
            int top = theQueue.poll();

            for(int v: adj[top]) {
                if(dist[v] == Integer.MAX_VALUE) {
                    theQueue.add(v);
                    dist[v] = dist[top] == WHITE ? BLACK : WHITE;
                } else if(dist[top] == dist[v])
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