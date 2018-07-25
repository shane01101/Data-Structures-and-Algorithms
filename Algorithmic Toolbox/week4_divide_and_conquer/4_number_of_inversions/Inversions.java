import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int low, int high) {
        long numberOfInversions = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            numberOfInversions = getNumberOfInversions(a, low, mid);
            numberOfInversions += getNumberOfInversions(a, mid + 1, high);
            numberOfInversions += mergeWithInversions(a, low, mid, high);
        }
        return numberOfInversions;
    }
    
    private static long mergeWithInversions(int[] a, int low, int mid, int high) {
        long inversionCount = 0;
        int arr1size = mid - low + 1;
        int arr2size = high - mid;
        
        int[] leftHelper = new int[arr1size];
        int[] rightHelper = new int[arr2size];
        
        for(int i = 0; i < arr1size; i++)
            leftHelper[i] = a[low + i];
        for(int j = 0; j < arr2size; j++)
            rightHelper[j] = a[mid + 1 + j];
        
        int i = 0, j = 0, k = low;
        
        while(i < arr1size && j < arr2size) {
            if(leftHelper[i] <= rightHelper[j]) {
                a[k] = leftHelper[i];
                i++;
            }
            else {
                a[k] = rightHelper[j];
                j++;
                inversionCount += (arr1size - i);
            }
            k++;
        }
        
        while (i < arr1size) {
            a[k] = leftHelper[i];
            i++;
            k++;
        }
        
        while(j < arr2size) {
            a[k] = rightHelper[j];
            j++;
            k++;
        }
        return inversionCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a, 0, a.length - 1));
        
    }
}