import java.util.*;
import java.io.*;

public class is_bst {
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
        
        public int last_val = Integer.MIN_VALUE;
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
        boolean solve() 
        {
            if(nodes == 0)
                return true;
            
            return isBST_Recursive(0);
            //return isBinarySearchTree(0);
        }
        

        boolean isBinarySearchTree(int n1) 
        {
            boolean isBST = true;
            int lastVal = Integer.MIN_VALUE;
            Stack<Integer> theStack = new Stack<>();
            int root = 0;
            
            if(nodes == 0)
                return true;
                
            theStack.push(root);
            root = tree[root].left;
            
            while(!theStack.isEmpty() || root != -1) 
            {
                if(root != -1) //move left
                {
                    theStack.push(root);
                    root = tree[root].left;
                }
                else //cant move left, pop and move right
                {
                    int top = theStack.pop();

                    if(lastVal > tree[top].key)
                        return false;
                    lastVal = tree[top].key;

                    root = tree[top].right;
                }
            }
            return isBST;
        }
        
        boolean isBST_Recursive(int n) 
        {
            if(n == -1)
                return true;
            
            if(!(isBST_Recursive(tree[n].left)))
                return false;
            
            if(tree[n].key < last_val)
                return false;
            last_val = tree[n].key;
            
            if(!(isBST_Recursive(tree[n].right)))
                return false;
            
            return true;
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
        if (tree.solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}