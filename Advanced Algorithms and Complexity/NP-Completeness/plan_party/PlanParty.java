import java.io.*;
import java.util.*;

class Vertex {
    Vertex() {
        this.weight = 0;
        this.children = new ArrayList<Integer>();
    }

    int weight;
    ArrayList<Integer> children;
}

class PlanParty {
    static Vertex[] ReadTree() throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        tokenizer.nextToken();
        int vertices_count = (int) tokenizer.nval;

        Vertex[] tree = new Vertex[vertices_count];

        for (int i = 0; i < vertices_count; ++i) {
            tree[i] = new Vertex();
            tokenizer.nextToken();
            tree[i].weight = (int) tokenizer.nval;
        }

        for (int i = 1; i < vertices_count; ++i) {
            tokenizer.nextToken();
            int from = (int) tokenizer.nval;
            tokenizer.nextToken();
            int to = (int) tokenizer.nval;
            tree[from - 1].children.add(to - 1);
            tree[to - 1].children.add(from - 1);
        }

        return tree;
    }

    static int[] dfs(Vertex[] tree, int vertex, int parent) 
    {
        int[] fun = new int[2];
        fun[0] = tree[vertex].weight;
        fun[1] = 0;
        
        //must be a leaf, return its weight or cycle (child is parent)
        if((tree[vertex].children.size()) == 0 || (tree[vertex].children.size() == 1 && tree[vertex].children.get(0) == parent))
        {
            return fun;
        }
        else
        {
            for (int child : tree[vertex].children)
                if (child != parent)
                {
                    int[] result = dfs(tree, child, vertex);
                    fun[0] += result[1];
                    fun[1] += Math.max(result[0], result[1]);
                }
        }
        return fun;
    }

    static int MaxWeightIndependentTreeSubset(Vertex[] tree) 
    {
        int size = tree.length;
        if (size == 0)
            return 0;
        int[] result = dfs(tree, 0, -1);

        return Math.max(result[0], result[1]);
    }

    public static void main(String[] args) throws IOException {
      // This is to avoid stack overflow issues
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new PlanParty().run();
                } catch(IOException e) {}
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        Vertex[] tree = ReadTree();
        int weight = MaxWeightIndependentTreeSubset(tree);
        System.out.println(weight);
    }
}
