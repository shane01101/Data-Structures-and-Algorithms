import java.util.*;

public class CoveringSegments {

    private static List<Integer> optimalPoints(Segment[] segments) {
        List<Integer> results = new ArrayList<>();
        Arrays.sort(segments, (a,b) -> Integer.compare(a.end, b.end));
        
        results.add(segments[0].end);
        Segment cur = segments[0];
        
        for(int i = 1; i < segments.length; i++) {
            if (segments[i].start > cur.end) {
                cur = segments[i];
                results.add(cur.end);
            }  
        }
        return results;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        List<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}