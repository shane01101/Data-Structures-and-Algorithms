import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> finished = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            if(!visited[i])
                dfs(adj, visited, finished, i);
        }
        
        for(int j = 0; j < n; j++)
            visited[j] = false;
        
        ArrayList<Integer>[] adjTranspose = getTranspose(adj);
        Stack<Integer> temp = new Stack<>();
        int sccCount = 0;
        
        while(!finished.isEmpty()) {
            int top = finished.pop();
            
            if(!visited[top]) {
                dfs(adjTranspose, visited, temp, top);
                sccCount++;
            }
        }
        return sccCount;
    }
    
    private static void dfs(ArrayList<Integer>[] adj, boolean[] visited, Stack<Integer> finished, int u) {
        visited[u] = true;
        
        for(int adjacent : adj[u]) {
            if(!visited[adjacent])
                dfs(adj, visited, finished, adjacent);
        }
        finished.push(u);
    }
    
    private static ArrayList<Integer>[] getTranspose(ArrayList<Integer>[] adj) {
        int n = adj.length;
        ArrayList<Integer>[] transposeGraph = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            transposeGraph[i] = new ArrayList<>();
        }
        
        for(int x = 0; x < n; x++) 
            for (int y : adj[x]) 
                transposeGraph[y].add(x);
            
        return transposeGraph;
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

