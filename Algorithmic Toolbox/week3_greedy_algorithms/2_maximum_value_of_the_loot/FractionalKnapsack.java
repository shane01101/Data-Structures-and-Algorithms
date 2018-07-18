import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        double[] arr = new double[values.length];
        int n = 0;
        
        while (capacity > 0 && n < values.length) {
            if (capacity == 0)
                return value;
            
            int itemIndex = getMaxItemPos(values, weights);
            
            if(itemIndex == -1)
                break;
            
            double a = Math.min(weights[itemIndex], capacity);
            value += a*((double)values[itemIndex]/(double)weights[itemIndex]);
            
            weights[itemIndex] -= a;
            arr[itemIndex] += a;
            capacity-=a;
            n++;
        }
        return value;
    }
    
    private static int getMaxItemPos(int[] values, int[] weights) {
        double max = Double.MIN_VALUE;
        int index = -1;
        
        for(int i = 0; i < values.length; i++) {
            if (weights[i] > 0 && (double)values[i]/(double)weights[i] > max) {
                index = i;
                max = (double)values[i]/(double)weights[i];
            }
        }
        return index;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
