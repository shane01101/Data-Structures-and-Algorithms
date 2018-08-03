import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    int[][] arr = new int[s.length() + 1][t.length() + 1]; 
    
    for(int a = 0; a < s.length() + 1; a++)
        arr[a][0] = a;
    
    for(int b = 0; b < t.length() + 1; b++)
        arr[0][b] = b;
    
    for(int i = 1; i < s.length() + 1; i++) {
        for(int j = 1; j < t.length() + 1; j++) {
            if(s.charAt(i - 1) == t.charAt(j - 1))
                arr[i][j] = Math.min(Math.min(arr[i][j - 1] + 1, arr[i - 1][j] + 1), arr[i-1][j-1]);
            else
                arr[i][j] = Math.min(Math.min(arr[i][j - 1] + 1, arr[i - 1][j] + 1), arr[i-1][j-1] + 1);
        }
    }
    return arr[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}

