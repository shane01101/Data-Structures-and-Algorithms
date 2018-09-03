import java.util.*;
import java.io.*;

public class is_bst { 
    private static int lastVal = Integer.MIN_VALUE;
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

        boolean isBinarySearchTree() {
            //ArrayList<Integer> result = new ArrayList<>();
            
            if(nodes == 0)
                return true;
         
            //#1 - Store inorder traversal in array then scan again - O(2 * n) = O(n)
//            inOrderArrayHelper(result, 0);
//            
//            for(int i = 0; i < result.size() - 1; i++) {
//                if(result.get(i) > result.get(i + 1))
//                    return false;
//            }
//            return true;

            //#2 Use static var to store last val - O(n)
//            return inOrderStaticHelper(0);

            //#3 Min Max
            return inOrderMinMaxHelper(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
//        
        boolean inOrderMinMaxHelper(int index, int min, int max) {
            if(index == -1)
                return true;
            
            if(min > tree[index].key || max < tree[index].key)
                return false;
           
            if(!inOrderMinMaxHelper(tree[index].left, min, tree[index].key) || 
                !inOrderMinMaxHelper(tree[index].right, tree[index].key, max))
                return false;
            
            return true;
        }
        
//        boolean inOrderStaticHelper(int index) {
//            if(index == -1)
//                return true;
//            
//            if(!inOrderStaticHelper(tree[index].left))
//                return false;
//            
//            if(tree[index].key < lastVal)
//                return false;
//            
//            lastVal = tree[index].key ;
//            
//            if(!inOrderStaticHelper(tree[index].right))
//                return false;
//            
//            return true;
//        }
        
//        void inOrderArrayHelper(ArrayList<Integer> result, int index) {
//            if(index == -1)
//                return;
//            
//            inOrderArrayHelper(result, tree[index].left);
//            result.add(tree[index].key);
//            inOrderArrayHelper(result, tree[index].right);    
//        }
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
