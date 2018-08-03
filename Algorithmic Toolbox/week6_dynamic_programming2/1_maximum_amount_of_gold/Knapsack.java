import java.util.*;

public class Knapsack {
    static int optimalWeight(int capacity, int[] w) {
        int[][] arr = new int[w.length + 1][capacity + 1];
        
        for(int i = 1; i < w.length + 1; i++) {
            for(int j = 1; j < capacity + 1; j++) {
                arr[i][j] = arr[i - 1][j];
                
                if(w[i-1] <= j) {
                    int val = arr[i - 1][j - w[i-1]] + w[i-1];
                    if(arr[i][j] < val)
                        arr[i][j] = val;
                }
            }
        }     
        return arr[w.length][capacity];
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