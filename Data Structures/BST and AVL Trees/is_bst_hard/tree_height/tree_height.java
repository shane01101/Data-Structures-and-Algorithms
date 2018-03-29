import java.util.*;
import java.io.*;

public class tree_height 
{
    class FastScanner 
    {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() 
        {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException 
        {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException 
        {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight 
    {
        int n;
        int parent[];

        void read() throws IOException 
        {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) 
            {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() 
        {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) 
            {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i])
                    height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
        
        int computEfficientHeight() 
        {
            int[] nodeDepth = new int[n];
            int height = 0;
            
            if(n==1 && parent[0] == -1)
                return 1;
            
            for(int i = 0; i < nodeDepth.length; i++)
            {
                height = Math.max(height, computEfficientHeightHelper(parent, nodeDepth, i));
            }
                
            return height;
        }
        
        int computEfficientHeightHelper(int[] parent, int[] depths, int index)
        {
            //already computed
            if(depths[index] > 0)
                return depths[index];
            //root
            if(parent[index] == -1)
            {
                depths[index] = 1;
                return 1;
            }
            //compute
            if(depths[parent[index]] == 0)
            {
                computEfficientHeightHelper(parent, depths, parent[index]);
            }
            //depth is parent + 1
            depths[index] = depths[parent[index]] + 1;
            return depths[index];
        }
    }

	static public void main(String[] args) throws IOException 
        {
            new Thread(null, new Runnable() {
                public void run() 
                {
                    try {
                        new tree_height().run();
                    } catch (IOException e) {
                    }
                }
            }, "1", 1 << 26).start();
	}
	public void run() throws IOException 
        {
            TreeHeight tree = new TreeHeight();
            tree.read();
            System.out.println(tree.computEfficientHeight());
	}
}

