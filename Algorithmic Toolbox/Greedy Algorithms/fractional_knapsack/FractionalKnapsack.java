import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack 
{
    private static double getOptimalValue(int capacity, int[] values, int[] weights) 
    {
        double value = 0;
        ArrayList<double[]> list = computeList(values, weights);
        
        for(int i = 0; i < list.size(); i++)
        {
            if(capacity == 0)
                break;
            
            if(list.get(i)[1] <= capacity)
            {
                capacity -= list.get(i)[1];
                value += list.get(i)[0];
            }
            else
            {
                value += (double)list.get(i)[0]/(double)(list.get(i)[1]/capacity);
                capacity = 0;
            }
        }
        return value;
    }
    
    private static ArrayList<double[]> computeList(int[] values, int[] weights)
    {
        ArrayList<double[]> theList = new ArrayList<>();
        
        for(int i = 0; i < values.length; i++)
        {
            theList.add(new double[3]);
            theList.get(i)[0] = values[i];
            theList.get(i)[1] = weights[i];
            theList.get(i)[2] = (double)values[i]/(double)weights[i];
        }
        theList.sort(Comparator.comparing(a -> -a[2]));
        
        return theList;
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
        System.out.println(String.format("%.4f", getOptimalValue(capacity, values, weights)));
    }
} 
