import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;
    
    private List<Worker> workerPool;
    int size;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = size = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobsNaive() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }
    
    private void assignJobsOptimal() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        workerPool = new ArrayList<>();
        
        buildHeap();
        for (int i = 0; i < jobs.length; i++) 
        {
            assignedWorker[i] = (int)workerPool.get(0).index;
            startTime[i] = workerPool.get(0).priority;
            increasePriority(0, jobs[i]);
        }
    }
    
    private void buildHeap() {
      for(int i = 0; i < size ; i++)
          workerPool.add(new Worker(i, 0));
      
      for(int i = size/2; i>=0; i--)
          siftDown(i);
    }
    
    private void siftDown(final int index)
    {
        int minIndex = index;
        int leftChild = 2*index+1;
        
        if(leftChild < size)
            if(workerPool.get(leftChild).priority < workerPool.get(minIndex).priority)
                minIndex = leftChild;
            else if(workerPool.get(leftChild).priority == workerPool.get(minIndex).priority)
                if(workerPool.get(leftChild).index < workerPool.get(minIndex).index)
                    minIndex = leftChild;
        
        int rightChild = 2*index+2;
        
        if(rightChild < size)
            if(workerPool.get(rightChild).priority < workerPool.get(minIndex).priority)
                minIndex = rightChild;
            else if(workerPool.get(rightChild).priority == workerPool.get(minIndex).priority)
                if(workerPool.get(rightChild).index < workerPool.get(minIndex).index)
                    minIndex = rightChild;
        
        if(index != minIndex)
        {
            long temp = workerPool.get(index).priority;
            workerPool.get(index).priority = workerPool.get(minIndex).priority;
            workerPool.get(minIndex).priority = temp;
            
            long temp2 = workerPool.get(index).index;
            workerPool.get(index).index = workerPool.get(minIndex).index;
            workerPool.get(minIndex).index = temp2;
            
            siftDown(minIndex);
        }
    }
    
    private void increasePriority(final int index, final long newPriority)
    {
        long oldPriority = workerPool.get(index).priority;
        workerPool.get(index).priority = newPriority + oldPriority;
        siftDown(0);
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsOptimal();
        writeResponse();
        out.close();
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
    
    static class Worker {
        long index;
        long priority;

        public Worker(long index, long priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
