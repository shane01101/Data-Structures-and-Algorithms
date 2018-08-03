import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
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
    private void assignJobsFast() {
        Worker[] theHeap = buildHeap();
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        
        for(int i = 0; i < jobs.length; i++) {
            assignedWorker[i] = getMin(theHeap);
            increasePriority(theHeap, jobs[i]);
            startTime[i] = i;
        }
    }
    
    public Worker[] buildHeap() {
        Worker[] theHeap = new Worker[numWorkers];
        
        for(int i = theHeap.length/2; i >= 0; i--)
            siftDown(theHeap, i);
        
        return theHeap;
    }
    
    public void siftDown(Worker[] theHeap, int i) {
        int size = numWorkers;
        int minIndex = i;
        int lChild = 2 * i + 1;
        int rChild = 2 * i + 2;
        
        if(lChild < size && theHeap[minIndex].priority > theHeap[lChild].priority)
            minIndex = lChild;
        
        if(rChild < size && theHeap[minIndex].priority > theHeap[rChild].priority)
            minIndex = rChild;
        
        if(minIndex != i) {
            Worker t = theHeap[minIndex];
            theHeap[minIndex] = theHeap[i];
            theHeap[i] = t;
            siftDown(theHeap, minIndex);
        }
    }
    
//    public void siftUp(Worker[] theHeap, int i) {
//        int parent = (i - 1) / 2;
//        
//        while(i > 0 && theHeap[i].priority < theHeap[parent].priority) {
//            Worker t = theHeap[i];
//            theHeap[i] = theHeap[parent];
//            theHeap[parent] = t;
//            
//            i = parent;
//        }
//    }
    
    public int getMin(Worker[] theHeap) {
        int result = theHeap[0].index;
        return result;
    }
    
    public void increasePriority(Worker[] theHeap, int time) {
        theHeap[0].priority += time;
        siftDown(theHeap, 0);
    }
    
    private void assignJobsSlow() {
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

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsSlow();
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
    
    public class Worker {
        private int index;
        private int priority;
        
        public Worker(int index) {
            this.index = index;
            this.priority = index;
        }
    }
}
