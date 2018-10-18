import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        int n = text.length();
        StringBuilder result = new StringBuilder();
        String[] matrix = new String[n];
        
        //fill array with all cyclic rotations
        for(int i = 0; i < n; i++) {
            matrix[i] = getRotation(text, i);
        }
        
        //sort lexiographically
        Arrays.sort(matrix);
        
        //append last letter of each string in matrix to result (BWT)
        for(int j = 0;j < n; j++)
            result.append(matrix[j].charAt(n-1));

        return result.toString();
    }
    
    String getRotation(String s, int startPos) {
        int a = startPos % s.length();
        return s.substring(a) + s.substring(0, a);
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
