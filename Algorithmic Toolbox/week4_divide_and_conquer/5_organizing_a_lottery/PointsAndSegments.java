import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        Point[] allPoints = new Point[2 * starts.length + points.length];
        HashMap<Integer, Integer> countsMap = new HashMap<>();
        int[] counts = new int[points.length];
        
        for(int i = 0; i < starts.length; i++) {
            allPoints[i * 2] = new Point(starts[i], 'l');
            allPoints[i * 2 + 1] = new Point(ends[i], 'r');
        }
        
        for(int j = starts.length * 2, k = 0; j < allPoints.length; j++, k++)
            allPoints[j] = new Point(points[k], 'p');
        
        Comparator<Point> comparatorX = Comparator
        .comparingInt(Point::getDigit)
        .thenComparing(p -> p.letter);
//        Arrays.sort(allPoints, (a,b) -> Integer.compare(a.digit, b.digit));
        Arrays.sort(allPoints, comparatorX);
        
        int segmentCount = 0;
        for(int i = 0; i < allPoints.length; i++) {
            if(allPoints[i].letter == 'l')
                segmentCount++;
            else if(allPoints[i].letter == 'r')
                segmentCount--;
            else if(allPoints[i].letter == 'p')
                countsMap.put(allPoints[i].digit, segmentCount);
        }
        
        //Get counts for each point from CountsMap using points index as key
        for(int j = 0; j < points.length; j++) {
            counts[j] = countsMap.get(points[j]);
        }
        
        return counts;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        //int[] cnt = naiveCountSegments(starts, ends, points);
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
    private static class Point {
        int digit;
        char letter;

        Point(int digit, char letter) {
            this.digit = digit;
            this.letter = letter;
        }
        
        int getDigit() {
            return digit;
        }
    }
}

