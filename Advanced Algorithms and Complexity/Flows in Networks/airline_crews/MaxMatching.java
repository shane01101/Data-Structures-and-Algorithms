import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MaxMatching {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new MaxMatching().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        boolean[][] bipartiteGraph = readData();
        int[] matching = findMatching(bipartiteGraph);
        writeResponse(matching);
        out.close();
    }

    boolean[][] readData() throws IOException {
        int numLeft = in.nextInt();
        int numRight = in.nextInt();
        boolean[][] adjMatrix = new boolean[numLeft][numRight];
        for (int i = 0; i < numLeft; ++i)
            for (int j = 0; j < numRight; ++j)
                adjMatrix[i][j] = (in.nextInt() == 1);
        return adjMatrix;
    }

    private int[] findMatching(boolean[][] bipartiteGraph) 
    {
        int numFlights = bipartiteGraph.length;
        int numCrews = bipartiteGraph[0].length;
        int[] flightResults = new int[numFlights];
        int[] crewResults = new int[numCrews];
        Arrays.fill(flightResults, -1);
        Arrays.fill(crewResults, -1);

        for(int i = 0; i < numFlights; i++)
        {
            boolean[] visited = new boolean[numFlights];
            Arrays.fill(visited, false);
            
            maxBipartiteMatch(bipartiteGraph, i, visited, flightResults, crewResults);
        }

        return flightResults;
    }
    
    private boolean maxBipartiteMatch(boolean[][] bipartiteGraph, int flightIndex, boolean[] visited, int[] flightResults, int[] crewResults)
    {
        if(flightIndex == -1)
            return true;
        
        if(visited[flightIndex])
            return false;         
                    
        visited[flightIndex] = true; //mark crew flag as seen
        
        for(int crewIndex = 0; crewIndex < crewResults.length; ++crewIndex)
        {
            if((bipartiteGraph[flightIndex][crewIndex]) && (maxBipartiteMatch(bipartiteGraph, crewResults[crewIndex], visited, flightResults, crewResults)))
            {
                
                flightResults[flightIndex] = crewIndex;
                crewResults[crewIndex] = flightIndex;
                return true;
            }
        }
        
        return false;
    }

    private void writeResponse(int[] matching) {
        for (int i = 0; i < matching.length; ++i) {
            if (i > 0) {
                out.print(" ");
            }
            if (matching[i] == -1) {
                out.print("-1");
            } else {
                out.print(matching[i] + 1);
            }
        }
        out.println();
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
