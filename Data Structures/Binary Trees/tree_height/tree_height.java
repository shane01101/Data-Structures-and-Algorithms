import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                    parent[i] = in.nextInt();
            }
        }

        int computeHeightSlow() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i])
                    height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
        
        int buildTreeAndGetHeight() {
            Node[] tree = new Node[n];
            int root = -1;
            
            for(int i = 0; i < n; i++)
                tree[i] = new Node();
            
            for(int i = 0; i < n; i++) {
               if(parent[i] == -1)
                   root = i;
               else {
                   tree[parent[i]].addChild(i);
               }
            }
            return computeHeightFast(tree, root);
        }
        
        int computeHeightFast(Node[] tree, int cur) {
            if(tree[cur].isEmptyChildren())
                return 1;
            
            List<Integer> heights = new ArrayList<>();
            
            for(int x: tree[cur].children) {
                heights.add(computeHeightFast(tree, x));
            }
            Collections.sort(heights);
            
            return 1 + heights.get(heights.size() - 1);
        }
    }
    
    public class Node {
        List<Integer> children;
        public Node() {
            children = new ArrayList<>();
        }
        
        public void addChild(int x) {
            children.add(x);
        }
        
        public boolean isEmptyChildren() {
            return children.isEmpty();
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {}
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        ///tree.computeHeightFast();
        System.out.println(tree.buildTreeAndGetHeight());
        //System.out.println(tree.computeHeightSlow());
    }
}
