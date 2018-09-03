import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    List<String>[] theList;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;
    
    public HashChains() {
        
    }

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }
    
    private int getChainIndex(List<String> theChain, String str) {
        int result = -1;
        
        for(int i = 0; i < theChain.size(); i++)
            if(str.equals(theChain.get(i)))
                result = i;
        
        return result;
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
        //out.flush();
    }
    
    private void processQueryFast(Query query) {
        int hashIndex;
        switch (query.type) {    
            case "add":
                hashIndex = hashFunc(query.s);
                
                if(getChainIndex(theList[hashIndex], query.s) == -1)
                    theList[hashIndex].add(0, query.s);
                break;
            case "del":
                hashIndex = hashFunc(query.s);
                int chainToDelete = getChainIndex(theList[hashIndex], query.s);
                
                if(chainToDelete > -1)
                    theList[hashIndex].remove(chainToDelete);
                
                break;
            case "find":
                hashIndex = hashFunc(query.s);
                
                if(getChainIndex(theList[hashIndex], query.s) > -1)
                    writeSearchResult(true);
                else
                    writeSearchResult(false);
                break;
            case "check":
                hashIndex = query.ind;
                if(theList[hashIndex].size() == 0)
                    out.println();
                else {
                    for(String s : theList[hashIndex])
                        out.print(s + " ");
                    out.println();
                    
                }
                
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        
        theList = new ArrayList[bucketCount];
        
        for(int i = 0; i < theList.length; i++)
            theList[i] = new ArrayList<>();
        
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQueryFast(readQuery());
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
