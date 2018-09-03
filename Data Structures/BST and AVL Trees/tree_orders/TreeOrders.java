import java.util.*;
import java.io.*;

public class TreeOrders {
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

    public class TreeOrdersNode {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) { 
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            inOrderHelper(result, 0);

            return result;
        }
        
        void inOrderHelper(ArrayList<Integer> result, int node) {
            if(node == -1)
                return;
            
            inOrderHelper(result, left[node]);
            result.add(key[node]);
            inOrderHelper(result, right[node]);    
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            preOrderHelper(result, 0);
            
            return result;
        }
        
        void preOrderHelper(ArrayList<Integer> result, int node) {
            if(node == -1)
                return;
            
            result.add(key[node]);
            preOrderHelper(result, left[node]);
            preOrderHelper(result, right[node]);    
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            postOrderHelper(result, 0);

            return result;
        }
        
        void postOrderHelper(ArrayList<Integer> result, int node) {
            if(node == -1)
                return;
            
            postOrderHelper(result, left[node]);
            postOrderHelper(result, right[node]);    
            result.add(key[node]);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeOrders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrdersNode tree = new TreeOrdersNode();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
