import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        //sorted array easy
//       Arrays.sort(a);
//       int mid = left + ((right - left) /2);
//       return getMajorityElementHelper(a,a[mid]);

        //O(n) using two passes
        int candidate = findCand(a, a.length);
        return getMajorityElementHelper(a, candidate);

        //O nlog(n) divide and conquer
//        if(left == right)
//            return -1;
//        
//        if (left + 1 == right) 
//            return a[left];
//        return getMajorityElementRecursive(a, 0, a.length - 1);
        
    }
    private static int getMajorityElementRecursive(int[] a, int left, int right) {
        //base case
        if (left == right) 
           return a[left];
        
        if (left + 1 == right) 
            return a[left];
        
        int mid = left + ((right - left) / 2);
        
        int leftMajority = getMajorityElementRecursive(a, left, mid);
        int rightMajority = getMajorityElementRecursive(a, mid + 1, right);
        
        if(leftMajority == rightMajority)
            return leftMajority;
        
        int leftCount = 0;
        int rightCount = 0;
        
        for(int i = 0; i < a.length; i++) {
            if(a[i] == leftMajority)
                leftCount++;
            else if(a[i] == rightMajority)
                rightCount++;
            
            if(leftCount > a.length/2 || rightCount > a.length/2)
                break;
        }
        
        if(leftCount > a.length /2)
            return leftMajority;
        else if(rightCount > a.length/2)
            return rightMajority;
        else
            return -1;
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
    
    private static int getMajorityElementHelper(int[] a, int majority) {
        int count = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == majority)
                count++;
        }
        
        if(count > a.length/2)
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

