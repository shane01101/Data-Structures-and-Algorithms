import java.util.*;

public class FibonacciLastDigit 
{
    private static int getFibonacciLastDigitNaive(int n) 
    {
        if(n==0)
            return 0;
        
        int[] fib = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;
        
        for(int i = 2; i <= n; i++)
            fib[i] = (fib[i - 1] + fib[i - 2]) % 10;
        
        return fib[n];
    }
    
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);
    }
}
