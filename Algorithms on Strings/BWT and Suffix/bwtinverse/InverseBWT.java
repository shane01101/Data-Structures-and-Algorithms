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
    
    int letterToIndex (char letter)
    {
        switch (letter)
        {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            case '$': return 4;
            default: assert (false); return -1;
        }
    }
    
    int getFirstColIndex(final String str, final int[] lc)
    {
        char c = str.charAt(0);
        int count = Integer.parseInt(str.substring(1));
        
        switch (c)
        {
            case '$': return 0;
            case 'A': return count;
            case 'C': return lc[0] + count;
            case 'G': return lc[0] + lc[1] + count;
            case 'T': return lc[0] + lc[1] + lc[2] + count;
            
            default: assert (false); return -1;
        }
    }
    
    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        List<String> bwtList = new ArrayList<>();
        int[] letterCount = new int[5]; //see letterToIndex() for mapping
        
        //create BWT List that use char + char count for easy lookup
        //and update letter char counts to use for first col array
        for(int i = 0; i < bwt.length(); i++)
        {
            letterCount[letterToIndex(bwt.charAt(i))]++;
            bwtList.add(String.valueOf(bwt.charAt(i)) 
                    + letterCount[letterToIndex(bwt.charAt(i))]);
        }
        
        for(int i = 1, index = 0; i < bwtList.size(); i++)
        {
            result.append(bwt.charAt(index));
            index = getFirstColIndex(bwtList.get(index), letterCount);
        }
        
        //reverse string so it is ordered corrctly and append $ to end
        result.reverse();
        result.append("$");
        
        return result.toString();
    }

    String inverseBWTNaive(String bwt) {
        StringBuilder result;
        List<String> matrix = new ArrayList<>();
        String temp = sortStr(bwt); //sorted string
        
        //create a 1 x n matrix
        for(int i = 0; i < temp.length(); i++)
            matrix.add(String.valueOf(temp.charAt(i)));
        
        //fill in matrix n - 1 times
        for(int j = 0; j < bwt.length() - 1; j++)
            addMerComposition(matrix, bwt);
        
        //Remove $ from index 0 and append to end
        result = new StringBuilder(matrix.get(0));
        result.deleteCharAt(0);
        result.append("$");
        
        return result.toString();
    }
    
    String sortStr(String s)
    {
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }
    
    List<String> addMerComposition(List<String> s1, String s2)
    {
        //create new  appended string, remove old string, add back new string
        for(int i = 0; i < s1.size(); i++)
        {
           String temp = String.valueOf(s2.charAt(i)) + s1.get(i);
           s1.remove(i);
           s1.add(i, temp);
        }
        Collections.sort(s1);
        
        return s1;
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