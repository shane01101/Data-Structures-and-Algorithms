import java.util.Scanner;

public class ChangeDP {
    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    
    private static int getChange(int m) {
        int[] arr = new int[m + 1];
        
        for(int i = 1; i <= m && i < 5; i++) {
            arr[i] = arr[i - 1] + ONE;
            
            if(i == THREE)
                arr[THREE] = 1;
        
            if(i == FOUR)
                arr[FOUR] = 1;
        }
        
        for(int i = 5; i < arr.length; i++) 
            arr[i] = Math.min(Math.min(arr[i - ONE] + ONE, arr[i-THREE] + ONE), arr[i - FOUR] + ONE);
        
        return arr[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}