import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    String inverseBWT(String bwt) {
        int n = bwt.length();
        StringBuilder result = new StringBuilder();
        char[] lastCol = bwt.toCharArray();
        int[] charNum = new int[n];
        int[] charCount = new int[5];
        int[] firstColIndex = new int[5];
        
        //Count Sort and label index of occurences of each letter
        for(int i = 0; i < n; i++) {
            charNum[i] = charCount[letterToIndex(lastCol[i])]++;
        }
        
        //begin with lastcol[0] and reconstruct using first/last property
        int curStringIndex = 0;
        result.append(lastCol[curStringIndex]);
        
        for(int i = 0; i < n - 1; i++) {
            char lastChar = result.charAt(result.length() - 1);
            curStringIndex = getFirstColIndex(lastChar, charNum[curStringIndex], charCount);
            result.append(lastCol[curStringIndex]);
        }
        
        //reverse string and move '$' to the end
        result.reverse();
        result.deleteCharAt(0);
        result.append('$');
        return result.toString();
    }
    
    int getFirstColIndex(char charIdentifier, int usedChars, int[] charCount) {
        switch (charIdentifier)
        {
            case '$': return 0 + usedChars;
            case 'A': return charCount[0] + usedChars;
            case 'C': return charCount[0] + charCount[1] + usedChars;
            case 'G': return charCount[0] + charCount[1] + charCount[2] + usedChars;
            case 'T': return charCount[0] + charCount[1] + charCount[2] + charCount[3] + usedChars;
            default: return -1;
        }
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
    
    char IndextoChar (int index)
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
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}