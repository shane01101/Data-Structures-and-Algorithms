import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Evacuation {
    private static FastScanner in;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(maxFlow(graph, 0, graph.size() - 1));
    }

    private static int maxFlow(FlowGraph graph, int from, int to) {
        int maxFlow = 0;
        int prev = -1;
        
        int[] parent = new int[graph.size()];
        
        for(int i = 0; i < parent.length; i++)
            parent[i] = -1;
        
        while(bfs(graph, from, to, parent)) //there is a path source to dest
        {
            int curPathFlow = Integer.MAX_VALUE; //min flow of current path
            
            //get and assign the minimum flow in the souce to dest path
            for(int i = to; i != from; i=graph.getEdge(parent[i]).from)
            {
                Edge curEdge = graph.getEdge(parent[i]);
                curPathFlow = Math.min(curPathFlow, curEdge.capacity - curEdge.flow);
            }
            
            //Add flow to the forward and backward edges in the path
            for(int i = to; i != from; i=graph.getEdge(parent[i]).from)
            {
                prev = parent[i];
                graph.addFlow(prev, curPathFlow);
            }
            maxFlow += curPathFlow; //update the maxFlow
        }
        return maxFlow;
    }
    
    private static boolean bfs(FlowGraph graph, int from, int to, int[] parent)
    {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;
        
        queue.add(from);
        visited[from] = true;
        
        while(!queue.isEmpty())
        {
            Integer u = queue.remove();
            
            for(Integer v : graph.getIds(u))
            {
                Edge curEdge = graph.getEdge(v);
                if(visited[curEdge.to] == false && curEdge.capacity - curEdge.flow > 0)
                {
                    queue.add(curEdge.to);
                    parent[curEdge.to] = v;
                    visited[curEdge.to] = true;
                }
            }
        }
        return visited[to];
    }

    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
        }
        return graph;
    }

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    /* This class implements a bit unusual scheme to store the graph edges, in order
     * to retrieve the backward edge for a given edge quickly. */
    static class FlowGraph {
        /* List of all - forward and backward - edges */
        private List<Edge> edges;

        /* These adjacency lists store only indices of edges from the edges list */
        private List<Integer>[] graph;

        public FlowGraph(int n) {
            this.graph = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; ++i)
                this.graph[i] = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity) {
            /* Note that we first append a forward edge and then a backward edge,
             * so all forward edges are stored at even indices (starting from 0),
             * whereas backward edges are stored at odd indices. */
            Edge forwardEdge = new Edge(from, to, capacity);
            Edge backwardEdge = new Edge(to, from, 0);
            graph[from].add(edges.size());
            edges.add(forwardEdge);
            graph[to].add(edges.size());
            edges.add(backwardEdge);
        }

        public int size() {
            return graph.length;
        }

        public List<Integer> getIds(int from) {
            return graph[from];
        }

        public Edge getEdge(int id) {
            return edges.get(id);
        }

        public void addFlow(int id, int flow) {
            /* To get a backward edge for a true forward edge (i.e id is even), we should get id + 1
             * due to the described above scheme. On the other hand, when we have to get a "backward"
             * edge for a backward edge (i.e. get a forward edge for backward - id is odd), id - 1
             * should be taken.
             *
             * It turns out that id ^ 1 works for both cases. Think this through! */
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}