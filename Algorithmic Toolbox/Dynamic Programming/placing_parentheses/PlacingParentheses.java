import java.util.Scanner;

public class PlacingParentheses {
    private static long[][] minMatrix;
    private static long[][] maxMatrix;
            
    private static long getMaximValue(String exp) {
        StringBuilder digit = new StringBuilder();
        StringBuilder operator = new StringBuilder();
        
        for(int a = 0; a< exp.length(); a++)
        {
            if(a % 2 == 0 )
                digit.append(exp.charAt(a));
            else
                operator.append(exp.charAt(a));
        }
        
        minMatrix = new long[digit.length()][digit.length()];
        maxMatrix = new long[digit.length()][digit.length()];
        
        for(int b = 0; b < digit.length(); b++)
        {
            minMatrix[b][b] = Character.getNumericValue(digit.charAt(b));
            maxMatrix[b][b] = Character.getNumericValue(digit.charAt(b));
        }
        
        for(int s = 1; s < digit.length(); s++)
        {
            for(int i = 0; i < digit.length() - s; i++)
            {
                int j = i + s;
                long[] minAndMaxResults = minAndMax(i, j, operator);
                minMatrix[i][j] = minAndMaxResults[0];
                maxMatrix[i][j] = minAndMaxResults[1];
            }
        }
        return maxMatrix[0][digit.length()-1];
    }
    
    private static long[] minAndMax(long x, long y, StringBuilder operator)
    {
        long min_ = Long.MAX_VALUE;
        long max_ = Long.MIN_VALUE;
        int i = (int)x;
        int j = (int)y;
        
        for(int k = i; k < j; k++)
        {
            //System.out.println("Operator: " + operator.charAt(k));
            long a = eval(maxMatrix[i][k], maxMatrix[k+1][j], operator.charAt(k));
            long b = eval(maxMatrix[i][k], minMatrix[k+1][j], operator.charAt(k));
            long c = eval(minMatrix[i][k], maxMatrix[k+1][j], operator.charAt(k));
            long d = eval(minMatrix[i][k], minMatrix[k+1][j], operator.charAt(k));
            min_ = Math.min(Math.min(Math.min(Math.min(min_, a), b), c), d);
            max_ = Math.max(Math.max(Math.max(Math.max(max_, a), b), c), d);
        }
        
        long[] val = new long[2];
        val[0] = min_;
        val[1] = max_;
        return val;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

