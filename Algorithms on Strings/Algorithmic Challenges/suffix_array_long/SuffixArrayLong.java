import java.util.*;
import java.io.*;

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
        int[] charClasses = computeCharClasses(text, order);
        
        int m = 1;
        
        while (m < text.length()) {
            order = sortDoubled(text, m, order, charClasses);
            charClasses = updateClasses(order, charClasses, m);
            m = 2 * m;
        }
       
        return order;
        //return result;
    }
    
    //O(text.length() + alphabetSize)
    private int[] sortCharacters(String text) {
        int n = text.length();
        int alphabetSize = 5;
        int[] order = new int[n];
        int[] count = new int[alphabetSize];
        
        for(int i = 0; i < n; i++) //incr char count
            count[letterToIndex(text.charAt(i))]++;
        
        for(int j = 1; j < alphabetSize; j++) //change count sort to index of sorted chars
            count[j] = count[j] + count[j-1];
        
        for(int k = n - 1; k >=0; k--) {
            int c = letterToIndex(text.charAt(k));
            count[c] = count[c] - 1;
            order[count[c]] = k;
        }
        return order;
    }
    
    //compute equivalence classes on single char
    private int[] computeCharClasses(String text, int[] order) {
        int n = text.length();
        int[] charClasses = new int[n];
        charClasses[order[0]] = 0;
        
        for(int i = 1; i < n; i++) {
            if(text.charAt(order[i]) != text.charAt(order[i - 1]))
                charClasses[order[i]] = charClasses[order[i - 1]] + 1;
            else
                charClasses[order[i]] = charClasses[order[i - 1]];
        }
        return charClasses;
    }
    
    //sort double cyclic shifts
    private int[] sortDoubled(String text, int m, int[] order, int[] charClasses) {
        int n = text.length();
        int[] count = new int[n];
        int[] newOrder = new int[n];
        
        for(int i = 0; i < n; i++)
            count[charClasses[i]] = count[charClasses[i]] + 1;
        
        for(int j = 1; j < n; j++)
            count[j] = count[j] + count[j-1];
        
        for(int k = n - 1; k >= 0; k--) {
            int start = (order[k] - m + n) % n;
            int cl = charClasses[start];
            count[cl] = count[cl] - 1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }
    
    //update the equivalence classes of 2*m
    private int[] updateClasses(int[] newOrder, int[] charClasses, int m) {
        int n = newOrder.length;
        int[] newCharClasses = new int[n];
        newCharClasses[newOrder[0]] = 0;
        
        for(int i = 1; i < n; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + m) % n;
            int midPrev = (prev + m) % n;
            
            //if the equivalence classes arent the same increment the next
            //sorted by 1 else assign the same value as prev
            if(charClasses[cur] != charClasses[prev] || charClasses[mid] != charClasses[midPrev])
                newCharClasses[cur] = newCharClasses[prev] + 1;
            else
                newCharClasses[cur] = newCharClasses[prev];
        }
        return newCharClasses;
    }
    
    int letterToIndex (char letter)
    {
        switch (letter)
        {
            case '$': return 0;
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return -1; //should never get here
        }
    }
    
    char indexToLetter (char letter)
    {
        switch (letter)
        {
            case 0: return '$';
            case 1: return 'A';
            case 2: return 'C';
            case 3: return 'G';
            case 4: return 'T';
            //default: return '!'; //should never get here
        }
        return (char) -1;
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
