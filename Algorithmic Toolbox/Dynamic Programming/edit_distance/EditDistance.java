package DynamicProgramming2;

import java.util.*;

class EditDistance 
{
    public static int EditDistance(String s, String t) 
    {
        int[][] table = new int[s.length()+1][t.length()+1];

        for(int row = 0; row < s.length() + 1; row++)
        {
            for(int col = 0; col < t.length() + 1; col++)
            {
                if(row == 0)
                    table[row][col] = col;
                else if(col == 0)
                    table[row][col] = row;
                else if(s.charAt(row- 1) == t.charAt(col - 1))
                    table[row][col] = table[row - 1][col - 1];
                else
                    table[row][col] = Math.min(Math.min(table[row - 1][col], 
                            table[row - 1][col - 1]), table[row][col - 1]) + 1;
            }
        }
        return table[s.length()][t.length()];
    }
    public static void main(String args[]) 
    {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }
}
