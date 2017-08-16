import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;
    //private int size;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() 
    {
      swaps = new ArrayList<Swap>();
      
      //begin at n/2 and siftDown to root
      for(int i = data.length / 2; i >= 0; i--)
      {
          minHeapify(swaps, i);
      } 
    }
    
    private void minHeapify(List<Swap> swaps, int index)
    {
        int size = data.length;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = -1;
         
        //check for left child and if smaller than parent
        if(left < size && data[left] < data[index]) //there is a right child
        {
            smallest = left;
        }
        else //smallest is parent with no children
            smallest = index;
        
        //check for right child and if smaller than parent
        if(right < size && data[right] < data[smallest])
            smallest = right;

        //swap if parent with child
        if(smallest != index)
        {
            swaps.add(new Swap(index, smallest));
            int tmp = data[index];
            data[index] = data[smallest];
            data[smallest] = tmp;
            minHeapify(swaps, smallest);
        }  
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
