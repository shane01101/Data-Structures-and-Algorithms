import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    //1. DFS on graph, record postorder
    //2. Compute transpose of graph
    //3. DFS on transpose graph exploring in reverse post order, mark as SCC
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] transpose = computeTranspose(adj); //2.
        Stack<Integer> revPostOrder = new Stack<>();
        
        //1.
        for(int i = 0; i < n; i++)
            if(!visited[i])
                dfs(adj, revPostOrder, visited, i);

        //3. 
        boolean[] visited2 = new boolean[n];
        int sccCounter = 0;

        //pop off stack and explore to find all the SCC
        while(!revPostOrder.isEmpty()) {
            int top = revPostOrder.pop();
            if(!visited2[top]) {
                sccCounter++;
                dfs(transpose, null, visited2, top);
            }
        }
        return sccCounter;
    }
    
    private static void dfs(ArrayList<Integer>[] adj, Stack<Integer> revPostOrder, boolean[] visited, int u) {
        visited[u] = true;
        
        for(int adjacent: adj[u]) {
            if(!visited[adjacent])
                dfs(adj, revPostOrder, visited, adjacent);
        }
        //null for DFS on transpose (2nd DFS) to avoid using another method
        if(revPostOrder != null)
            revPostOrder.push(u);
    }
    
    private static void print(ArrayList<Integer>[] adj) {
        for(int i = 0; i < adj.length; i++) {
            System.out.print(i + ": ");
            
            for(int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].get(j) + ", ");
            }
            System.out.println();
        }
    }
    
    private static ArrayList<Integer>[] computeTranspose(ArrayList<Integer>[] adj) {
        int n = adj.length;
        ArrayList<Integer>[] transpose = (ArrayList<Integer>[])new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            transpose[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < adj[i].size(); j++) {
                int y = adj[i].get(j);
                int x  = i;
                transpose[y].add(x);
            }
        
        return transpose;
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

