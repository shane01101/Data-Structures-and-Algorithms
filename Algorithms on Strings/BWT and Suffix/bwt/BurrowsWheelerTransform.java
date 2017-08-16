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

    String BWT(String text) 
    {
        StringBuilder result = new StringBuilder();
        String[] matrix = new String[text.length()];
        
        //populate matrix[] with all cyclic rotations
        for(int i = 0; i < text.length(); i++)
            matrix[i] = rotateArray(text, i).toString();
        
        Arrays.sort(matrix); //sort lexiographically
        
        //append last char at end of each matrix[] to result
        for(int j = 0; j < matrix.length; j++)
            result.append(matrix[j].charAt(matrix.length -1));

        return result.toString();
    }
    
    StringBuilder rotateArray(String s, int index)
    {
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++, index++)
        {
            result.append(String.valueOf(s.charAt(index % s.length())));
        }
        
        return result;
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
