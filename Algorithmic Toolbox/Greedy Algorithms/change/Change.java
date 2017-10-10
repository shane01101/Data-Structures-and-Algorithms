import java.util.Scanner;

public class Change 
{
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final int ONE = 1;
    
    private static int getChange(int m) 
    {
        int numCoins = 0;
        
        while (m >= TEN)
        {
            m = m -= TEN;
            numCoins++;
        }
        
        while (m >= FIVE)
        {
            m = m -= FIVE;
            numCoins++;
        }
        
        while (m >= ONE)
        {
            m = m -= ONE;
            numCoins++;
        }
        
        return numCoins;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}