import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InverseBWT {
    private class FastScanner {
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

    private String inverseBWT(String bwt) {
        int n = bwt.length();
        StringBuilder result = new StringBuilder();
        char[] lastCol = bwt.toCharArray();
        int[] charNum = new int[n]; // occurence of each letter
        int[] charCounts = new int[5]; // # of each char

        // counting sort & label char occurrences
        for(int i = 0; i < n; i++) {
            charNum[i] = charCounts[letterToIndex(lastCol[i])]++;
        }

        // reconstruct the string using first last property starting at lastcol[0]
        int indexOfChar = 0;

        while(lastCol[indexOfChar] != '$') {
            result.append(lastCol[indexOfChar]);
            indexOfChar = getFirstColIndex(lastCol[indexOfChar], charNum[indexOfChar] ,charCounts);
        }
        result.append(lastCol[indexOfChar]); // append the last of the first col chars ending with '$'

        // Remove '$' from end, reverse string, and then append '$' to end
        result.deleteCharAt(result.length()-1);
        result.reverse();
        result.append('$');
        return result.toString();
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

    private int getFirstColIndex(char symbol, int usedChars, int[] charCount) {
        switch(symbol) {
            case '$': return 0 + usedChars;
            case 'A': return charCount[0] + usedChars;
            case 'C': return charCount[0] + charCount[1] + usedChars;
            case 'G': return charCount[0] + charCount[1] + charCount[2] + usedChars;
            case 'T': return charCount[0] + charCount[1] + charCount[2] + charCount[3] + usedChars;
            default: return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}