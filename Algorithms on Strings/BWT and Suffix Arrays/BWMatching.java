import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BWMatching {
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

    // Preprocess the Burrows-Wheeler Transform bwt of some text
    // and compute as a result:
    //   * starts - for each character C in bwt, starts[C] is the first position
    //       of this character in the sorted array of
    //       all characters of the text.
    //   * occ_count_before - for each character C in bwt and each position P in bwt,
    //       occ_count_before[C][P] is the number of occurrences of character C in bwt
    //       from position 0 to position P inclusive.
    private void PreprocessBWT(String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
        int n = bwt.length();
        int[] charCounts = new int[5];

        // create n+1 array for each unique char
        for(int a = 0; a < 5; a++)
            occ_counts_before.put(indexToChar(a), new int[n+1]);

        for(int i = 0; i < n; i++) {
            ++charCounts[letterToIndex(bwt.charAt(i))]; // count sort

            // calculate the count array
            for(int j = 0; j < 5; j++) {
                updateCountMapHelper(occ_counts_before, i, bwt.charAt(i), indexToChar(j));
            }
        }

        // use the count sort array to calculate the first occurrence of the first col chars
        starts.put('$', 0);
        if ((charCounts[letterToIndex('A')] != 0)) {
            starts.put('A', charCounts[0]);
        } else {
            starts.put('A', -1);
        }

        if ((charCounts[letterToIndex('C')] != 0)) {
            starts.put('C', charCounts[0] + charCounts[1]);
        } else {
            starts.put('C', -1);
        }

        if ((charCounts[letterToIndex('G')] != 0)) {
            starts.put('G', charCounts[0] + charCounts[1] + charCounts[2]);
        } else {
            starts.put('G', -1);
        }

        if ((charCounts[letterToIndex('T')] != 0)) {
            starts.put('T', charCounts[0] + charCounts[1] + charCounts[2] + charCounts[3]);
        } else {
            starts.put('T', -1);
        }
    }

    // Compute the number of occurrences of string pattern in the text
    // given only Burrows-Wheeler Transform bwt of the text and additional
    // information we get from the preprocessing stage - starts and occ_counts_before.
    int CountOccurrences(String pattern, String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
        int top = 0;
        int bottom = bwt.length() - 1;
        int patternIndex = pattern.length() - 1;

        while(top <= bottom) {
            if(patternIndex >= 0) {
                char symbol = pattern.charAt(patternIndex);

                int topCount = occ_counts_before.get(symbol)[top];
                int bottomCount = occ_counts_before.get(symbol)[bottom];

                if(bottomCount >= topCount) { // if symbol exists in last col
                    top = starts.get(symbol) + occ_counts_before.get(symbol)[top];
                    bottom = starts.get(symbol) + occ_counts_before.get(symbol)[bottom + 1] - 1;
                } else
                    return 0;

            } else
                return bottom - top + 1;

            // move to the i - 1 char
            --patternIndex;
        }
        return 0;
    }

    private void updateCountMapHelper(Map<Character, int[]> occ_counts_before, int index, char bwtSymbol, char countSymbol) {
        int temp[] = occ_counts_before.get(countSymbol);

        if(bwtSymbol == countSymbol)
            temp[index+1] = temp[index] + 1;
        else
            temp[index+1] = temp[index];

        occ_counts_before.put(countSymbol, temp);
    }

    private char indexToChar (int index)
    {
        switch (index) {
            case 0: return '$';
            case 1: return 'A';
            case 2: return 'C';
            case 3: return 'G';
            case 4: return 'T';
            default: return Character.MIN_VALUE;
        }
    }

    private int letterToIndex (char letter)
    {
        switch (letter) {
            case '$': return 0;
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return -1;
        }
    }

    static public void main(String[] args) throws IOException {
        new BWMatching().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        // Start of each character in the sorted list of characters of bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, Integer> starts = new HashMap<Character, Integer>();
        // Occurrence counts for each character and each position in bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, int[]> occ_counts_before = new HashMap<Character, int[]>();
        // Preprocess the BWT once to get starts and occ_count_before.
        // For each pattern, we will then use these precomputed values and
        // spend only O(|pattern|) to find all occurrences of the pattern
        // in the text instead of O(|pattern| + |text|).
        PreprocessBWT(bwt, starts, occ_counts_before);
        int patternCount = scanner.nextInt();
        String[] patterns = new String[patternCount];
        int[] result = new int[patternCount];
        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = CountOccurrences(patterns[i], bwt, starts, occ_counts_before);
        }
        print(result);
    }
}
