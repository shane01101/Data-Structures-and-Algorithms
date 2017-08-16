import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
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


    private void assignJobs() {
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
    
    private void assignJobsFast()
    {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<WorkerThread>  workerQueue = 
                new PriorityQueue<>(numWorkers, new Comparator<WorkerThread>()
        {
            @Override
            public int compare (WorkerThread a, WorkerThread b) 
            {
               return a.timeAvailable == b.timeAvailable ? a.index - b.index :
                    (int) (a.timeAvailable - b.timeAvailable);
            }
        });
        
        //add workers to the queue
        for(int i = 0; i < numWorkers; i++)
            workerQueue.add(new WorkerThread(i));
        
        for(int i = 0; i < jobs.length; i++)
        {
            //Get thread to assign to job
            WorkerThread worker = workerQueue.poll();
            
            //Save timecard data
            assignedWorker[i] = worker.index;
            startTime[i] = worker.timeAvailable;
            
            //reset timeAvailable to be used again and add to queue
            worker.timeAvailable += jobs[i];
            workerQueue.add(worker);
        }
    }
    
    private static class WorkerThread
    {
        int index;
        long timeAvailable;
        public WorkerThread (int index) 
        {
            this.index = index;
            timeAvailable = 0;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsFast();
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
}

