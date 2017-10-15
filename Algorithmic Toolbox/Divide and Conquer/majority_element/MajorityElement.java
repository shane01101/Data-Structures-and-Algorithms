import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        
        mergeSort(a, 0, a.length - 1);
        
        int majorityElement = a[a.length/2];
        int leftCount = 0;
        int rightCount = 0;
        
        for(int i = a.length/2; i >= 0 ; i--) 
            if(majorityElement == a[i])
                leftCount++;

        
        for(int i = a.length/2 + 1; i < a.length; i++)
            if(majorityElement == a[i])
                rightCount++;
        
        if((leftCount + rightCount) > a.length / 2)
            return 0;
        
        return -1;
    }
    
    private static void mergeSort(int[] a, int left, int right) {
        if(left < right) {
            int mid = (left + right) /2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }
    
    public static void merge(int[] a, int left, int mid, int right) {
        //size of 2 sub arrays
        int sz1 = mid - left + 1;
        int sz2 = right - mid;
        
        //Temp sub arrays
        int[] arr1 = new int[sz1];
        int[] arr2 = new int[sz2];
        
        for(int i = 0; i < sz1; ++i)
            arr1[i] = a[left + i];
        for(int j = 0; j < sz2; ++j)
            arr2[j] = a[mid + 1 + j];
        
        int i = 0;
        int j = 0;
        int k = left;
        
        while(i < sz1 && j < sz2) {
            if(arr1[i] <= arr2[j]) {
                a[k] = arr1[i];
                i++;
            }
            else {
                a[k] = arr2[j];
                j++;
            }
            k++;
        }
        
        while(i < sz1) {
            a[k] = arr1[i];
            i++;
            k++;
        }
        
        while(j < sz2) {
            a[k] = arr2[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
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
