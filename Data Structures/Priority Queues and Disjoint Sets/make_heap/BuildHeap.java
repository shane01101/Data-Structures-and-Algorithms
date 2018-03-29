import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;
    private int size;

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

    private void buildHeap() {
      size = data.length;
      swaps = new ArrayList<>();
      
      for(int i = size/2; i>=0; i--)
          siftDownCountSwaps(swaps, i);
    }
    
    private void siftDownCountSwaps(List<Swap> swaps, int index)
    {
        int minIndex = index;
        int leftChild = 2*index+1;
        
        if(leftChild < size && data[leftChild] < data[minIndex])
            minIndex = leftChild;
        
        int rightChild = 2*index+2;
        
        if(rightChild < size && data[rightChild] < data[minIndex])
            minIndex = rightChild;
        
        if(index != minIndex)
        {
            swaps.add(new Swap(index, minIndex));
            int temp = data[index];
            data[index] = data[minIndex];
            data[minIndex] = temp;
            siftDownCountSwaps(swaps, minIndex);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        buildHeap();
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
