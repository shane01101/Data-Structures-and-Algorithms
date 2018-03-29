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

class check_brackets 
{
    public static void main(String[] args) throws IOException 
    {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        int invalidIndex = -1;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) 
        {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') 
            {
                opening_brackets_stack.push(new Bracket(text.charAt(position), position));
            }
            
            if (next == ')' || next == ']' || next == '}') 
            {
                if(!opening_brackets_stack.isEmpty())
                {
                    Bracket top = opening_brackets_stack.pop();
                    
                    if(!top.Match(next))
                    {
                        invalidIndex = position + 1;
                        break;
                    }
                }
                else //empty
                {
                    invalidIndex = position + 1;
                    break;
                }
            }
        }
        //invalid index must still be in the stack if no break occurred
        if(!opening_brackets_stack.isEmpty() && invalidIndex == -1)
            invalidIndex = opening_brackets_stack.pop().position + 1;

        //Print Answer
        if(invalidIndex != -1)
            System.out.println(invalidIndex);
        else
            System.out.println("Success");
    }
}
