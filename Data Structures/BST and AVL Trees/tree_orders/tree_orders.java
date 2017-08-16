import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner 
    {
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

	public class TreeOrders 
        {
            int n;
            int[] key, left, right;

            void read() throws IOException 
            {
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
                Stack<Integer> stack = new Stack<>();
                int root = 0;
                stack.push(root); //push root to begin iteration
                root = left[root]; //move left

                while(!stack.isEmpty() || root != -1)
                {
                    if(root != -1) //move all the way left
                    {
                        stack.push(root);
                        root = left[root];
                    }
                    else //cant move left, pop and add to result then move right
                    {
                        int node = stack.pop();
                        result.add(key[node]);
                        root = right[node];
                    }
                }
                return result;
            }

            List<Integer> preOrder() {
                ArrayList<Integer> result = new ArrayList<Integer>();
                Stack<Integer> stack = new Stack<>();
                int root = 0;
                stack.push(root); //push root to begin iteration

                while(!stack.isEmpty())
                {
                    int node = stack.pop();
                    result.add(key[node]);
                    
                    //go right
                    if(right[node] != -1)
                        stack.push(right[node]);
                    
                    //go left
                    if(left[node] != -1)
                        stack.push(left[node]);
                }
                return result;
            }

            List<Integer> postOrder() {
                ArrayList<Integer> result = new ArrayList<Integer>();
                Stack<Integer> stack = new Stack<>();
                int root = 0;
                stack.push(root); //push root to begin iteration

                while(!stack.isEmpty())
                {
                    int node = stack.pop();
                    result.add(0, key[node]); //add to beginning
                    
                    //Add Children
                    if(left[node] != -1)
                        stack.push(left[node]);
                    
                    if(right[node] != -1)
                        stack.push(right[node]); 
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
