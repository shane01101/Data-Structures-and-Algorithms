import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) 
    {
        List<Integer> sequence = new ArrayList<Integer>();
        
        while (n >= 1) 
        {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) 
            {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }
    
    private static List<Integer> efficient_sequence(int n) 
    {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] array = new int[n+1];
        
        for(int i = 1; i < n+1; i++)
        {
            array[i] = array[i-1] + 1;
            
            if(i % 3 == 0)
                array[i] = Math.min(array[i], array[i/3] + 1);
            if(i % 2 == 0)
                array[i] = Math.min(array[i], array[i/2] + 1);
        }
        int k = n;
        
        while(k >= 1)
        {
            sequence.add(k);
            
            if(array[k] - 1 == array[k - 1])
                k = k - 1;
            else if(k % 2 == 0 && array[k] - 1 == array[k/2])
                k = k / 2;
            else if ((k % 3 == 0 && array[k] - 1 == array[k/3]))
                k = k / 3;
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = efficient_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) 
        {
            System.out.print(x + " ");
        }
    }
}