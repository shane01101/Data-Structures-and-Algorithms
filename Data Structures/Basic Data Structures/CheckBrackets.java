import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    char type;
    int position;

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
}

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> theStack = new Stack<Bracket>();
        int unMatchedIndex = -1;

        for (int i = 0; i < text.length(); i++) {
            char next = text.charAt(i);

            if (next == '(' || next == '[' || next == '{') {
               theStack.push(new Bracket(next, i));
            }
            else if (next == ')' || next == ']' || next == '}') {
                if(!theStack.isEmpty()) {
                    Bracket top = theStack.pop();

                    if(!top.Match(next)) {
                        unMatchedIndex = i;
                        break;
                    }
                } else {
                    unMatchedIndex = i;
                    break;
                }
            }
        }

        // if a no match hasn't been found and stack has brackets, take the top index as error
        if(!theStack.isEmpty() && unMatchedIndex == -1)
            unMatchedIndex = theStack.pop().position;

        if(unMatchedIndex == -1)
            System.out.println("Success");
        else
            System.out.println(unMatchedIndex + 1);
    }
}
