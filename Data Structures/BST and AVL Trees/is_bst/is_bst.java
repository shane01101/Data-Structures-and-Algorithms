import java.util.*;
import java.io.*;

public class is_bst {
    public static int lastVal = Integer.MIN_VALUE;
    
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
            Stack<Integer> stack = new Stack<>();
            boolean isBST = true;
            int root = 0;
            
            if(nodes == 0) //empty tree
                return true;
            
            stack.push(root); //push root to begin iteration
            root = tree[root].left; //move left

            while(!stack.isEmpty() || root != -1)
            {
                if(root != -1) //move all the way left
                {
                    stack.push(root);
                    root = tree[root].left;
                }
                else //cant move left, pop and add to result then move right
                {
                    int node = stack.pop();
                    
                    if(tree[node].key < lastVal) //compare to last inorder val
                        return false;
                    lastVal = tree[node].key;
                    //System.out.println(tree[node].key);
                    root = tree[node].right;
                }
            }
            return isBST;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
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
