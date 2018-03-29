import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int size) 
    {
        int candidate = findCand(a, size);
        
        return isMajority(a, size, candidate);
    }
    
    private static int findCand(int[] a, int size)
    {
        int maj = 0;
        int count = 1;
        int i = 1;
        
        while (i < size)
        {
            if (a[maj] == a[i])
                count++;
            else
                count--;
            
            if(count == 0)
            {
                maj = i;
                count = 1;
            }
            i++;
        }
        return a[maj];
    }
    
    private static int isMajority(int[] a, int size, int cand)
    {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(a[i] == cand)
                count++;
        }
        if(count > size/2)
            return 1;
        else 
            return -1;
    }
    
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

