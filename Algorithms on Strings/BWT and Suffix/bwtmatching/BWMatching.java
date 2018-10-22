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
    private void PreprocessBWT(String bwt, int[] starts, int[][] occ_counts_before) {
        calculateStarts(bwt, starts);
        calculate_occ_counts_before(bwt, occ_counts_before);
        //testPrint(occ_counts_before); 
    }
    
    private void testPrint(int[][] occ_counts_before) {
        for(int i = 0; i < occ_counts_before.length; i++) {
            for(int j = 0; j < occ_counts_before[i].length; j++)
                System.out.print(occ_counts_before[i][j] + " ");
            System.out.println();
        }
    }
    
    private void calculate_occ_counts_before(String bwt, int[][] occ_counts_before) {
        for(int row = 0; row < 5; row++) {
            //symbol is one of $ACGT
            char symbol = indextoChar(row);
            
            for(int col = 1; col < bwt.length() + 1; col++) {
                char lastSymbol = bwt.charAt(col - 1);
                if(lastSymbol == symbol)
                    occ_counts_before[row][col] = occ_counts_before[row][col-1] + 1;
                else
                    occ_counts_before[row][col] = occ_counts_before[row][col-1];
            }
        }
    }
    
    private void calculateStarts(String bwt, int[] starts) {
        int[] charCount = new int[5];
        
        for(char c: bwt.toCharArray()) 
            charCount[letterToIndex(c)]++;

        starts[0] = charCount[0] > 0 ? 0 : -1;
        starts[1] = charCount[1] > 0 ? charCount[0] : -1;
        starts[2] = charCount[2] > 0 ? charCount[0] + charCount[1] : -1;
        starts[3] = charCount[3] > 0 ? charCount[0] + charCount[1] + charCount[2] : -1;
        starts[4] = charCount[4] > 0 ? charCount[0] + charCount[1] + charCount[2] + charCount[3] : -1;
    }

    // Compute the number of occurrences of string pattern in the text
    // given only Burrows-Wheeler Transform bwt of the text and additional
    // information we get from the preprocessing stage - starts and occ_counts_before.
    int CountOccurrences(String pattern, String bwt, int[] starts, int[][] occ_counts_before) {
        int top = 0;
        int bottom = bwt.length() - 1;
        int lastCharIndex = pattern.length()-1;

        while(top <= bottom) {
            if(lastCharIndex >= 0) {
                char symbol = pattern.charAt(lastCharIndex);
                lastCharIndex--;
                int symbolHash = letterToIndex(symbol);
                
                if(bottom + 1 >  top) {
                    top = starts[symbolHash] + occ_counts_before[symbolHash][top];
                    bottom = starts[symbolHash] + occ_counts_before[symbolHash][bottom + 1] - 1;
                }
                else
                    return 0;
            }
            else
               return bottom - top + 1;
        }
        return 0;
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
            default: return -1;
        }
    }
    
    char indextoChar (int index)
    {
        switch (index)
        {
            case 0: return '$';
            case 1: return 'A';
            case 2: return 'C';
            case 3: return 'G';
            case 4: return 'T';
            default: return '!'; //should never reach here, error
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
        //Map<Character, Integer> starts = new HashMap<Character, Integer>();
        int[] starts = new int[5];
        // Occurrence counts for each character and each position in bwt,
        // see the description in the comment about function PreprocessBWT
        //Map<Character, int[]> occ_counts_before = new HashMap<Character, int[]>();
        int[][] occ_counts_before = new int[5][bwt.length() + 1];
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
