import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearchRecursive(int[] arr, int key) {
        return binarySearchHelperRecursive(arr, 0, arr.length - 1, key);
    }

    static int binarySearchIterative(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if(key == arr[mid])
                return mid;
            else if (key < arr[mid]) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static int binarySearchHelperRecursive(int[] arr, int low, int high, int key) {
        if (high < low)
            return -1;

        int mid = low + (high - low) / 2;

        if(arr[mid] == key)
            return mid;
        else if (key < arr[mid])
            return binarySearchHelperRecursive(arr, low, mid - 1, key);
        else
            return binarySearchHelperRecursive(arr, mid+1, high, key);
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
