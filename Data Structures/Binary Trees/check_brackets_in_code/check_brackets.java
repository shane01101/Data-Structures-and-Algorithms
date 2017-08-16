import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        int invalidBracket = -1;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }
            else if (next == ')' || next == ']' || next == '}') {
                if(!opening_brackets_stack.isEmpty())
                {
                    Bracket top = opening_brackets_stack.pop();
                    if(!top.Match(next))
                    {
                        invalidBracket = position + 1;
                        break;
                    }
                }
                else
                {
                    invalidBracket = position + 1;
                    break;
                }
            }
        }
        
        if(!opening_brackets_stack.isEmpty() && invalidBracket == -1) 
        {
            invalidBracket = opening_brackets_stack.get(0).position + 1;
        }

        // Printing answer, write your code here
        if(invalidBracket == -1)
            System.out.println("Success");
        else
           System.out.println(invalidBracket); 
    }
}
