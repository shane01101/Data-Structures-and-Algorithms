import java.util.*;

public class Inversions {
//
//    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
//        long numberOfInversions = 0;
//        if (right <= left + 1) {
//            return numberOfInversions;
//        }
//        int ave = (left + right) / 2;
//        numberOfInversions += getNumberOfInversions(a, b, left, ave);
//        numberOfInversions += getNumberOfInversions(a, b, ave, right);
//        //write your code here
//        return numberOfInversions;
//    }
    
    private static long mergeSortInv(int[] a, int[] temp, int left, int right) {
        long invCount = 0;
        if(left < right) {
            int mid = (left + right) /2;
            invCount += mergeSortInv(a, temp, left, mid);
            invCount += mergeSortInv(a, temp, mid+1, right);
            invCount += mergeInv(a, temp, left, mid+1, right);
        }
        return invCount;
    }
    
    public static long mergeInv(int[] a, int[] temp, int left, int mid, int right) {
        long invCount = 0;      
        int i = left;
        int j = mid;
        int k = left;
        
        while((i <=  mid - 1) && (j <= right)) {
            if(a[i] <= a[j]) {
                temp[k++] = a[i++];
            }
            else {
                temp[k++] = a[j++];
                invCount += (mid - i) ;  
            }
        }
        
        //copy left to temp
        while(i <= (mid -1)) {
            temp[k++] = a[i++];
        }
        //copy right to temp
        while(j <= right) {
            temp[k++] = a[j++];
        }
        
        //temp to a
        for(i = left; i <= right; i++)
            a[i] = temp[i];
        
        return invCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(mergeSortInv(a, b, 0, a.length-1));
//        for(int x : a)
//            System.out.println(x);
    }
}

