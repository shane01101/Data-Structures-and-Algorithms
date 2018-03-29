import java.util.*;
import java.io.*;

public class tree_orders {
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

    public class TreeOrders {
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

        List<Integer> inOrder() 
        {
            ArrayList<Integer> result = new ArrayList<Integer>();
            Stack<Integer> theStack = new Stack<>();
            int root = 0;
            
            if(n > 0)
                theStack.push(root);
            
            root = left[root];
            
            while(!theStack.isEmpty() || root != -1) 
            {
                if(root != -1) //move left
                {
                    theStack.push(root);
                    root = left[root];
                }
                else //cant move left, pop and move right
                {
                    int top = theStack.pop();
                    result.add(key[top]);
                    root = right[top];
                }
            }
            return result;
        }

        List<Integer> preOrder() 
        {
            ArrayList<Integer> result = new ArrayList<Integer>();
            Stack<Integer> theStack = new Stack<>();
            int root = 0;
            
            if(n > 0)
                theStack.push(root);
            
            while(!theStack.isEmpty()) 
            {
                int top = theStack.pop();
                result.add(key[top]);
                
                if(right[top] != -1)
                    theStack.push(right[top]);
                
                if(left[top] != -1)
                    theStack.push(left[top]);
            }
            return result;
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            Stack<Integer> theStack = new Stack<>();
            int root = 0;
            
            if(n > 0)
                theStack.push(root);
            
            while(!theStack.isEmpty()) 
            {
                int top = theStack.pop();
                result.add(0, key[top]);
                
                if(left[top] != -1)
                    theStack.push(left[top]);
                
                if(right[top] != -1)
                    theStack.push(right[top]);
                
                
            }
            return result;
        }
    }

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                public void run() {
                    try {
                        new tree_orders().run();
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
            TreeOrders tree = new TreeOrders();
            tree.read();
            print(tree.inOrder());
            print(tree.preOrder());
            print(tree.postOrder());
	}
}
