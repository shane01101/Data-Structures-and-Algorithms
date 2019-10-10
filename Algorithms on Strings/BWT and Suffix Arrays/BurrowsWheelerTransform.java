import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    private String BWT(String text) {
        int n = text.length();
        StringBuilder result = new StringBuilder();
        String[] matrix = new String[n];

        // populate matrix with all cyclic rotations;
        for(int i = 0; i < n; i++) {
            matrix[i] = text.substring(i) + text.substring(0,i);
        }

        // sort cyclic rotations
        Arrays.sort(matrix);

        // assign bwt to result
        for(int i = 0; i < n; i++)
            result.append(matrix[i].charAt(n - 1));

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
