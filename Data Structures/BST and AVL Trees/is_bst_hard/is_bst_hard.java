
import java.util.*;
import java.io.*;

public class is_bst_hard {
    
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() 
        {
            //empty tree or 1 node tree is a BST
            if(nodes == 0)
                return true;
            
            return isBinarySearchTreeHelper(0, Long.MIN_VALUE, Long.MAX_VALUE);
            
        }
        
        boolean isBinarySearchTreeHelper(int root, long min, long max)
        {
            if(root == -1) 
                return true;
            
            if((tree[root].key < min) || (tree[root].key >= max))
                return false;
            
            if((!isBinarySearchTreeHelper(tree[root].left, min, tree[root].key)) ||
                (!isBinarySearchTreeHelper(tree[root].right, tree[root].key, max)))
            {
                return false;
            }
            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
