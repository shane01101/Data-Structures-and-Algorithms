import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        //int[] result = new int[text.length()];
        int[] order = sortCharacters(text);
        int[] charClass = computeCharClasses(text, order);
        int L = 1;
        
        while(L < text.length())
        {
            order = sortDoubled(text, L, order, charClass);
            charClass = updateClasses(order, charClass, L);
            L = 2*L;
        }

        return order;
    }
    
    private int letterToIndex (char letter)
    {
        switch (letter)
        {
            case '$': return 0;
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: assert (false); return -1;
        }
    }
    
    private int[] sortCharacters(String s)
    {
        int[] order = new int[s.length()];
        int[] count = new int[5];
        
        for(int i = 0; i < s.length(); i++)
            count[letterToIndex(s.charAt(i))] 
                    = count[letterToIndex(s.charAt(i))] + 1;
        
        for(int j = 1; j < 5; j++)
            count[j] = count[j] + count[j-1];
        
        for(int k = s.length() - 1; k >= 0; k--)
        {
            char c = s.charAt(k);
            count[letterToIndex(c)] = count[letterToIndex(c)] - 1;
            order[count[letterToIndex(c)]] = k;
        }
        return order;
    }
    
    private int[] computeCharClasses(String s, int[] order)
    {
        int[] charClass = new int[s.length()];
        //charClass[order[0]] = 0;
        
        for(int i = 1; i < s.length(); i++)
        {
            if(s.charAt(order[i]) != s.charAt(order[i - 1]))
                charClass[order[i]] = charClass[order[i - 1]] + 1;
            else
                charClass[order[i]] = charClass[order[i - 1]];
        }
        return charClass;
    }
    
    private int[] sortDoubled(String S, int L, int[] order, int[] charClass)
    {
        int[] count = new int[S.length()];
        int[] newOrder = new int[S.length()];
        
        for(int i = 0; i < S.length(); i++)
            count[charClass[i]] = count[charClass[i]] + 1;
        
        for(int j = 1; j < S.length(); j++)
            count[j] = count[j] + count[j - 1];
        
        for(int k = S.length() - 1; k >=0; k--)
        {
            int start = (order[k] - L + S.length()) % S.length();
            int cl = charClass[start];
            count[cl] = count[cl] - 1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }
    
    private int[] updateClasses(int[] newOrder, int[] charClass, int L)
    {
        int n = newOrder.length;
        int[] newClass = new int[n];
        newClass[newOrder[0]] = 0;
        
        for(int i = 1; i < n; i++)
        {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + L) % n;
            int midPrev = (prev + L) % n;
            
            if(charClass[cur] != charClass[prev] 
                || charClass[mid] != charClass[midPrev])
                newClass[cur] = newClass[prev] + 1;
            else
                newClass[cur] = newClass[prev];
        }
        
        return newClass;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
