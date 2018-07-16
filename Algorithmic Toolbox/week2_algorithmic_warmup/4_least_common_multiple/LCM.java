import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }
  private static long eucilidGCD(long a, long b){
    if (b == 0)
        return a;
    
    long a_p = a % b;
    return eucilidGCD(b, a_p);
  }
  

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    long a = scanner.nextInt();
    long b = scanner.nextInt();

//    System.out.println(lcm_naive(a, b));
    System.out.println(a*b/eucilidGCD(a, b));
  }
}
