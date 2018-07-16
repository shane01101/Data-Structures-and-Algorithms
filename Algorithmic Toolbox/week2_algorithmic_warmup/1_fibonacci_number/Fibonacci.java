import java.util.Scanner;

public class Fibonacci {
  private static long calc_fibSlow(int n) {
    if (n <= 1)
      return n;

    return calc_fibSlow(n - 1) + calc_fibSlow(n - 2);
  }
  
  private static long calc_fibFast(int n) {
      
    if (n == 0)
        return 0;
    else if (n == 1)
        return 1;
    
    int[] arr = new int[n+1];
    arr[0] = 0;
    arr[1] = 1;
    
    for(int i = 2; i < n+1; i++)
        arr[i] = arr[i-1] + arr[i-2];

    return arr[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

//    System.out.println(calc_fibSlow(n));
    System.out.println(calc_fibFast(n));
  }
}
