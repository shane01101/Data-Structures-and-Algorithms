import java.util.*;

public class Partition3 {
    private static int partition3(int[] A) {
        if(A.length < 3)
            return 0;
        
        int sum = 0;
        
        for (int x : A)
            sum += x;
        
        if(sum %3 != 0)
            return 0;
        
        if(partition3Helper(A, A.length - 1, sum/3, sum/3, sum/3))
            return 1;
        else
            return 0;
    }
    
    private static boolean partition3Helper(int[] arr, int n, int s1, int s2, int s3) {
        if(s1 == 0 && s2 == 0 && s3 == 0)
            return true;
        
        if(n < 0)
            return false;
        
        boolean subset1 = false;
        if(s1 - arr[n] >= 0) 
            subset1 = partition3Helper(arr, n - 1, s1 - arr[n], s2, s3);
        
        boolean subset2 = false;
        if(!subset1 && s2 - arr[n] >= 0) 
            subset2 = partition3Helper(arr, n - 1, s1, s2 - arr[n], s3);
        
        boolean subset3 = false;
        if(!subset1 && !subset2 && s2 - arr[n] >= 0) 
            subset3 = partition3Helper(arr, n - 1, s1, s2, s3 - arr[n]);
        
        return subset1 || subset2 || subset3;
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

