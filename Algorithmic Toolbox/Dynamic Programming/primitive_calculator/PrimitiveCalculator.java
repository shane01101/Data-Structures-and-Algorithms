import java.util.*;

public class PrimitiveCalculator {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    
    private static List<Integer> optimal_sequence(int n) {
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
    
    public static List<Integer> efficient_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] arr = new int[n + 1];
        
        //calculate counts for each number
        for(int i = 1;i < n + 1; i++) {
            arr[i] = arr[i - 1] + ONE;
            
            if(i % THREE == 0)
                arr[i] = Math.min(arr[i], arr[i/THREE] + 1);
            if(i % TWO == 0)
                arr[i] = Math.min(arr[i], arr[i/TWO] + 1);
            
        }
        int k = n;
        //backtrack and fill sequcnes of counts backwards
        while (k >= 1) {
            sequence.add(k);
            
        if(arr[k - 1] == arr[k] - 1)
            k--;
        else if(k % TWO == 0 && arr[k / TWO] == arr[k] - 1)
            k /= TWO;
        else if(k % THREE == 0 && arr[k / THREE] == arr[k] - 1)
            k /= THREE;
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = efficient_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}
