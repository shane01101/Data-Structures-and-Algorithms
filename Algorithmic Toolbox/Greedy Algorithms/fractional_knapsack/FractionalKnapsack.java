import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) 
    {
        double value = 0;
        ArrayList<double[]> theList = new ArrayList<>();
        
        for(int i = 0; i < values.length; i++)
        {
            theList.add(new double[3]);
            theList.get(i)[0] = values[i];
            theList.get(i)[1] = weights[i];
            theList.get(i)[2] = (double)values[i] / (double)weights[i];
        }
        theList.sort(Comparator.comparing(a -> -a[2]));
        
        for(int j = 0; j < theList.size(); j++)
        {
            double a = Math.min(capacity, (int)theList.get(j)[1]);
            value += a*theList.get(j)[2];
            theList.get(j)[1] -= a;
            capacity -= a;
        }

        return value;
    }

    public static void main(String args[]) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) 
        {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 