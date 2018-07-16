import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class CircuitDesign {
    private final InputReader reader;
    private final OutputWriter writer;

    public CircuitDesign(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                InputReader reader = new InputReader(System.in);
                OutputWriter writer = new OutputWriter(System.out);
                new CircuitDesign(reader, writer).run();
                writer.writer.flush();
            }
        }, "1", 1 << 26).start();
    }

    class Clause {
        int firstVar;
        int secondVar;
    }

    class TwoSatisfiability {
        int numVars;
        Clause[] clauses;

        TwoSatisfiability(int n, int m) {
            numVars = n;
            clauses = new Clause[m];
            for (int i = 0; i < m; ++i) {
                clauses[i] = new Clause();
            }
        }

        boolean isSatisfiable(int[] result) {
            ArrayList<ArrayList<Integer>> implGraph = new ArrayList<>();
            ArrayList<ArrayList<Integer>> implGraphRev = new ArrayList<>();
            ArrayList<ArrayList<Integer>> sccList = new ArrayList<>();
            int[] sccArray = new int[numVars*2];
            ArrayList<Integer> postOrder = new ArrayList<>();
            boolean[] visited = new boolean[numVars*2];
            
            Arrays.fill(visited, false);
            createImplicationGraph(implGraph, implGraphRev);
           
            //find post order on reverse graph using DFS
            for(int i = 0; i < implGraphRev.size(); i++)
            {
                if(visited[i] == false)
                    dfsPostOrder(implGraphRev, postOrder, visited, i);
            }

            Collections.reverse(postOrder);
            Arrays.fill(visited, false);
            int countSCC = 0;
            
            //find SCC of graph
            for(int i = 0; i < postOrder.size(); i++)
            {
                if(visited[postOrder.get(i)] == false)
                {
                    dfsSCC(implGraph, sccArray, visited, postOrder.get(i), ++countSCC);
                }
            }
            
            //if x and xNot lie in the same SCC, it is unsatisfiable
            for(int i = 0; i < numVars; i++)
            {
                if(sccArray[i] == sccArray[i + numVars])
                    return false;
            }
            
            //Group SCC Array into SCC adjacency list
            for(int i = 0; i < sccArray.length; i++)
            {
                sccList.add(new ArrayList<Integer>());
            }
            
            for(int i = 0; i < sccArray.length; i++)
            {
                sccList.get(sccArray[i] - 1).add(i);
            }
            
            //Traverse SCC in reverse order and assign true (1) if not seen, or
            //false (0) if assigned already
            int[] seen = new int[numVars];
            Arrays.fill(seen, -1); //-1 for not seen
            
            for(int i = 0; i < sccList.size(); i++)
            {
                for(int literal : sccList.get(i))
                {
                    if(literal < numVars)
                    {
                        if(seen[literal] == -1)
                        {
                            result[literal] = 0;
                            seen[literal] = 0;
                        }
                    }
                    else
                    {
                        if(seen[literal - numVars] == -1)
                        {
                            result[literal - numVars] = 1;
                            seen[literal - numVars] = 1;
                        }
                    }
                }
            }
            return true;
        }
        
        void createImplicationGraph(ArrayList<ArrayList<Integer>> gr, ArrayList<ArrayList<Integer>> grRev)
        {
            int x, xNot, y, yNot;
            
            for(int i = 0; i < numVars*2; i++)
            {
                gr.add(new ArrayList<Integer>());
                grRev.add(new ArrayList<Integer>());
            }
            
            for(int i = 0 ; i < clauses.length; i++)
            {
                if(clauses[i].firstVar > 0)
                {
                    x = clauses[i].firstVar - 1;
                    xNot = x + numVars;
                }
                else
                {
                    x = 0 - clauses[i].firstVar - 1 + numVars;
                    xNot = x - numVars;
                }
                
                if(clauses[i].secondVar > 0)
                {
                    y = clauses[i].secondVar - 1;
                    yNot = y + numVars;
                }
                else
                {
                    y = 0 - clauses[i].secondVar - 1 + numVars;
                    yNot = y - numVars;
                }
                
                gr.get(xNot).add(y);
                gr.get(yNot).add(x);
                
                grRev.get(x).add(yNot);
                grRev.get(y).add(xNot);
            }  
        }
    }
    
    public static void dfsPostOrder(ArrayList<ArrayList<Integer>> grRev, ArrayList<Integer> order,  boolean[] visited, int v)
    {
        visited[v] = true;
        
        for(int adjacent : grRev.get(v))
        {
            if(visited[adjacent] == false)
                dfsPostOrder(grRev, order, visited, adjacent);
        }
        order.add(v); 
    }
    
    public static void dfsSCC(ArrayList<ArrayList<Integer>> gr, int[] scc,  boolean[] visited, int v, int numSCC)
    {
        visited[v] = true;
        scc[v] = numSCC;
        
        for(int adjacent : gr.get(v))
        {
            if(visited[adjacent] == false)
                dfsSCC(gr, scc, visited, adjacent, numSCC);
        }
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();

        TwoSatisfiability twoSat = new TwoSatisfiability(n, m);
        for (int i = 0; i < m; ++i) {
            twoSat.clauses[i].firstVar = reader.nextInt();
            twoSat.clauses[i].secondVar = reader.nextInt();
        }

        int result[] = new int[n];
        if (twoSat.isSatisfiable(result)) {
            writer.printf("SATISFIABLE\n");
            for (int i = 1; i <= n; ++i) {
                if (result[i-1] == 1) {
                    writer.printf("%d", -i);
                } else {
                    writer.printf("%d", i);
                }
                if (i < n) {
                    writer.printf(" ");
                } else {
                    writer.printf("\n");
                }
            }
        } else {
            writer.printf("UNSATISFIABLE\n");
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
