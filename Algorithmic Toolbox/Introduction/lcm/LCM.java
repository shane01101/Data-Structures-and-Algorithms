import java.util.Scanner;

public class LCM 
{
    private static long lcm_naive(int a, int b) 
    {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }
    
    private static long gcd_efficient(long a, long b) 
    {
        if (b == 0)
            return a;
        
        long aPrime = a % b;
        
        return gcd_efficient(b, aPrime);
    }

    public static void main(String args[]) 
    {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextInt();
        long b = scanner.nextInt();

        System.out.println((a * b) / gcd_efficient(a, b));
    }
}
