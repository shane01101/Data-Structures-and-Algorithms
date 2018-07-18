import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int counter = 0;
        
        while (m >= 10) {
            m -= 10;
            counter++;
        }
        while (m >= 5) {
            m -= 5;
            counter++;
        }
        while (m >= 1) {
            m -= 1;
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

