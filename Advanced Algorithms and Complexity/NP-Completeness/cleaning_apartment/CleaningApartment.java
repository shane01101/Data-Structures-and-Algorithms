import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class CleaningApartment {
    private final InputReader reader;
    private final OutputWriter writer;

    public CleaningApartment(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new CleaningApartment(reader, writer).run();
        writer.writer.flush();
    }

    class Edge {
        int from;
        int to;
    }

    class ConvertHampathToSat {
        int numVertices;
        Edge[] edges;

        ConvertHampathToSat(int n, int m) {
            numVertices = n;
            edges = new Edge[m];
            for (int i = 0; i < m; ++i) {
                edges[i] = new Edge();
            }
        }

        void printEquisatisfiableSatFormula() {
            int numVariables = numVertices * numVertices;
            int numClauses = 0;
            boolean[][] adjList = new boolean[numVertices][numVertices];
            List<Integer> theClauses = new ArrayList<>();
            
            for(Edge edge : edges)
            {
                adjList[edge.from - 1][edge.to - 1] = true;
                adjList[edge.to - 1][edge.from - 1] = true;
            }
            
            numClauses += calcVertexClause(theClauses);
            numClauses += calcPositionClause(theClauses);
            numClauses += calcNonAdjClause(theClauses, adjList);
            
            writer.printf("%d %d\n", numClauses, numVariables);
            
            for(int i = 0; i < theClauses.size(); i++)
            {
                writer.printf(theClauses.get(i) + " ");
                
                if(theClauses.get(i) == 0)
                    writer.printf("\n");
            }
        }
        
        int calcVertexClause(List<Integer> theClauses)
        {
            int cl = 0; 
            for(int i = 1; i <= numVertices; i++)
            {
                for(int j = 0; j < numVertices; j++)
                {
                    theClauses.add(j * numVertices + i);
                }
                theClauses.add(0);
                cl++;
                
                for(int j = 0; j < numVertices - 1; j++)
                {
                    for(int k = j + 1; k < numVertices; k++)
                    {
                        theClauses.add(0 - (j * numVertices + i));
                        theClauses.add(0 - (k * numVertices + i));
                        theClauses.add(0);
                        cl++;
                    }
                }
            }
            return cl;
        }
        
        int calcNonAdjClause(List<Integer> theClauses, boolean[][] adjList)
        {
            int cl = 0;
            for(int i = 0; i < numVertices - 1; i++)
            {
                for(int j = i + 1; j < numVertices; j++)
                {
                    if(!adjList[i][j])
                    {
                        for(int k = 0; k < numVertices - 1; k++)
                        {
                            theClauses.add(0 - (k * numVertices + (i + 1)));
                            theClauses.add(0 - ((k + 1) * numVertices + (j + 1)));
                            theClauses.add(0);
                            
                            theClauses.add(0 - (k * numVertices + (j + 1)));
                            theClauses.add(0 - ((k + 1) * numVertices + (i + 1)));
                            theClauses.add(0);
                            cl+=2;
                        }
                    }
                }
            }
            return cl;
        }
        
        int calcPositionClause(List<Integer> theClauses)
        {
            int cl = 0;
            for(int i = 0; i < numVertices; i++)
            {
                for(int j = 1; j <= numVertices; j++)
                {
                    theClauses.add(i * numVertices + j);
                }
                theClauses.add(0);
                cl++;
                
                for(int j = 1; j < numVertices; j++)
                {
                    for(int k = j + 1; k <= numVertices; k++)
                    {
                        theClauses.add(0 - (i * numVertices + j));
                        theClauses.add(0 - (i * numVertices + k));
                        theClauses.add(0);
                       cl++;
                    }
                }
            }
            return cl;
        }
    }
    
    

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();

        ConvertHampathToSat converter = new ConvertHampathToSat(n, m);
        for (int i = 0; i < m; ++i) {
            converter.edges[i].from = reader.nextInt();
            converter.edges[i].to = reader.nextInt();
        }
        
        //long startTime = System.currentTimeMillis();
        converter.printEquisatisfiableSatFormula();
        //long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;
        //System.out.println("Running Time: " + totalTime);
        
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
