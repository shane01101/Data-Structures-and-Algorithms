import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) 
    {
        int[][] table = new int[w.length + 1][W + 1];
        
        for(int i = 0; i < w.length + 1; i++)
            table[i][0] = 0;
        
        for(int j = 0; j < W + 1; j++)
            table[0][j] = 0;
        
        for(int m = 1; m < w.length + 1; m++)
        {
            for(int n = 1; n < W + 1; n++)
            {
                table[m][n] = table[m - 1][n];
                
                if(n >= w[m - 1])
                {
                    table[m][n] = Math.max(table[m][n], 
                        table[m - 1][n - w[m - 1]] + w[m - 1]);
                }
            }
        }
        return table[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}