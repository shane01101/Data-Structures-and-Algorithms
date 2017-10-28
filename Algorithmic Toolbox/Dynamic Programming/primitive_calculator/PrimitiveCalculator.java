import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> nonoptimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }
    
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] array = new int[n+1];
        
        for(int i = 1; i < n+1; i++){
            array[i] = array[i- 1] + 1;
                       
            if(i%2 == 0)
                array[i] = Math.min(array[i/2] + 1, array[i]);
            if(i%3 == 0)
                array[i] = Math.min(array[i/3] + 1, array[i]);
        }
        
        int j = n;
        
        while(j >= 1) //add into sequence counting down
        {
            sequence.add(j);
            
            if(array[j - 1] == array[j] - 1)
                j = j - 1; 
            else if(j % 2 == 0 && array[j/2] == array[j] - 1)
                j = j / 2;
            else if(j % 3 == 0 && array[j/3] == array[j] - 1)
                j = j / 3;
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

