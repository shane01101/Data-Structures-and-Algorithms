import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        while(a.length >= counter) {
            int index = -1;
            String str = "";

            for(int i = 0; i < a.length; i++) {
                String a1 = a[i] + str;
                String a2 = str + a[i];
                
                if (!a[i].equals("") && a1.compareTo(a2) >= 0) {
                    str = a[i];
                    index = i;
                }
            }
            result.append(str);
            a[index] = "";
            counter++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

