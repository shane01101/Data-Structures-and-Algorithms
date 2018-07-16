import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProductSlow(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = Math.max(max_product,
                    (long)numbers[first] * (long)numbers[second]);
            }
        }

        return max_product;
    }
    
    static long getMaxPairwiseProductFast(int[] numbers) {
        Arrays.sort(numbers);
        int n = numbers.length;
        return (long)numbers[n-1] * (long)numbers[n-2];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
        
//        Random rand = new Random();
//        while(true) {
//            int max_n = rand.nextInt(199000) + 2;
//            int[] rand_numbers = new int[max_n];
//            
//            for(int i= 0; i < rand_numbers.length; i++) {
//                rand_numbers[i] = rand.nextInt(199000) + 2;
//                //System.out.println(rand_numbers[i]);
//            }
//            long result1 = getMaxPairwiseProductSlow(rand_numbers);
//            long result2 = getMaxPairwiseProductFast(rand_numbers);
//            
//            if(result1 == result2)
//                System.out.println("OK");
//            else {
//                System.out.printf("Wrong answer result1: %d, result2: %d", result1, result2);
//                break;
//            }
//        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}