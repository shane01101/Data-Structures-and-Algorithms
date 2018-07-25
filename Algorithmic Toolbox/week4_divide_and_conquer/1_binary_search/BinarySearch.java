import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearchRecursive(int[] a, int low, int high, int key) {
        if (high < low)
            return -1;
        
        int mid = low + ((high - low) /2);
        
        if(key == a[mid])
            return mid;
        else if (key < a[mid])
            return binarySearchRecursive(a, low, mid - 1, key);
        else
            return binarySearchRecursive(a, mid + 1, high, key);
    }
    
    static int binarySearchIterative(int[] a, int key) {
        int low = 0, high = a.length - 1;
        
        while(low <= high) {
            int mid = low + (high - low) /2;
            
            if(a[mid] == key)
                return mid;
            else if(a[mid] < key)
                low = mid + 1;
            else if(a[mid] > key)
                high = mid - 1;
        }
        return -1;
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearchIterative(a, b[i]) + " ");
//            System.out.print(linearSearch(a, b[i]) + " ");
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
