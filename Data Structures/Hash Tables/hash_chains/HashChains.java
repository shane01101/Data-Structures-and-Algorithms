import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> [] elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public HashChains()
    {
        
    }
    
    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) 
    {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
         out.flush();
    }

    private void processQuery(Query query) 
    {   
        if (query.type.equals("add")) 
        {
            if(!elems[hashFunc(query.s)].contains(query.s))
                    elems[hashFunc(query.s)].add(0, query.s);
        } 
        else if (query.type.equals("del")) 
        {
           if(!elems[hashFunc(query.s)].isEmpty() ||
                    elems[hashFunc(query.s)].contains(query.s))
                    elems[hashFunc(query.s)].remove(query.s);
        }
        else if (query.type.equals("find")) 
        {
            writeSearchResult(elems[hashFunc(query.s)]
                    .contains(query.s));
        }
        else if (query.type.equals("check")) 
        {
            for(String s : elems[query.ind])
                    out.print(s + " ");
                
            out.println();
            out.flush();
        }
    }

    public void processQueries() throws IOException {
        //elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        
        elems = new ArrayList[bucketCount];
        for(int i = 0; i <  bucketCount; i++)
            elems[i] = new ArrayList<String>();
        
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
