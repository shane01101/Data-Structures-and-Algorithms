import java.util.*;
import java.io.*;

public class TreeHeight {
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

    public class Tree {
        int n;
        int root = -1;
        List<Integer>[] children;
        int[] heights;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            children = new ArrayList[n];
            heights = new int[n];

            for(int i = 0; i < n; i++)
                children[i] = new ArrayList<Integer>();

            for(int j = 0; j < n; j++) {
                int parent = in.nextInt();

                if(parent == -1)
                    root = j;
                else
                    children[parent].add(j);
            }
        }

        int computeHeight() {
            int level = 0;
            Queue<Integer> theQueue = new LinkedList<Integer>();
            theQueue.add(root);

            // Use BFS to compute height for each level traversed
            while(!theQueue.isEmpty()) {
                int top = theQueue.remove();

                for(int child : children[top]) {
                    theQueue.add(child);
                    heights[child] = heights[top] + 1;
                }
                level = heights[top] + 1;
            }
            return level;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeHeight().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        Tree tree = new Tree();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
