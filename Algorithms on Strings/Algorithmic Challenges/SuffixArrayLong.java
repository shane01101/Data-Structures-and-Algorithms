import java.util.*;
import java.io.*;

public class SuffixArrayLong {
    public static final int ALPHABET_SIZE = 5;

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

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        int[] order = sortCharacters(text);
        int[] charClasses = computeCharClasses(text, order);
        int length = 1;

        while (length < text.length()) {
            order = sortDoubled(text, length, order, charClasses);
            charClasses = updateClasses(order, charClasses, length);
            length *= 2;
        }
        return order;
    }

    private int[] sortCharacters(String text) {
        int n = text.length();
        int[] order = new int[n];
        int[] count = new int[ALPHABET_SIZE];

        for(int i = 0; i < n; i++)
            count[letterToIndex(text.charAt(i))]++;

        for(int j = 1; j < 5; j++)
            count[j] = count[j] + count[j-1];

        for(int k = n - 1; k >= 0; k--) {
            int c = letterToIndex(text.charAt(k));
            count[c]--;
            order[count[c]] = k;
        }
        return order;
    }

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

    private int[] sortDoubled(String text, int length, int[] order, int[] charClasses) {
        int n = text.length();
        int[] count = new int[n];
        int[] newOrder = new int[n];

        for(int i = 0; i < n; i++)
            count[charClasses[i]] = count[charClasses[i]] + 1;

        for(int j = 1; j < n; j++)
            count[j] = count[j] + count[j - 1];

        for(int k = n - 1; k >= 0; k--) {
            int start = (order[k] - length + n) % n;
            int clazz = charClasses[start];
            count[clazz] = count[clazz] - 1;
            newOrder[count[clazz]] = start;
        }
        return newOrder;
    }

    private int[] updateClasses(int[] newOrder, int[] charClasses, int length) {
        int n = newOrder.length;
        int[] newClass = new int[n];
        newClass[newOrder[0]] = 0;

        for(int i = 1; i < n; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + length) % n;
            int midPrev = (prev + length) % n;

            if(charClasses[cur] != charClasses[prev] || charClasses[mid] != charClasses[midPrev])
                newClass[cur] = newClass[prev] + 1;
            else
                newClass[cur] = newClass[prev];
        }
        return newClass;
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
            default: return -1; //should never get here
        }
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
