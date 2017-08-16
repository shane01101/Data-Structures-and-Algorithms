import java.io.*;
import java.util.*;

class Node
{
    public static final int Letters =  4;
    public static final int NA      = -1;
    public int next [];
    public boolean patternEnd;

    Node ()
    {
        next = new int [Letters];
        Arrays.fill (next, NA);
        patternEnd = false;
    }
}

public class TrieMatchingExtended implements Runnable {
    int letterToIndex (char letter)
    {
        switch (letter)
        {
                case 'A': return 0;
                case 'C': return 1;
                case 'G': return 2;
                case 'T': return 3;
                default: assert (false); return Node.NA;
        }
    }
    
    List<Node> buildTrie(List<String> patterns) {
        List<Node> trie = new ArrayList<>();
        
        trie.add(new Node()); //root node
        
        for(String s : patterns)
        {
            int curNodePtr = 0;
            int endOfPattern = s.length() - 1;
            int charIndex = 0;
            
            for(char c : s.toCharArray())
            {   
                Node currentNode = trie.get(curNodePtr);
                
                if(currentNode.next[letterToIndex(c)] != Node.NA)
                    curNodePtr = currentNode.next[letterToIndex(c)];
                else
                {
                    Node newNode = new Node();
                    trie.add(newNode);
                    currentNode.next[letterToIndex(c)] = trie.size() - 1;
                    curNodePtr = trie.size() - 1;
                }
                
                
                if(charIndex == endOfPattern)
                {
                    Node endOfPatternNode = trie.get(curNodePtr);
                    endOfPatternNode.patternEnd = true;
                }
                charIndex++;
            }
        }
        return trie;
    }

    List <Integer> solve (String text, int n, List <String> patterns) {
        List <Integer> result = new ArrayList <> ();
        List<Node> trie = buildTrie(patterns);       
        
        for(int i = 0; i < text.length(); i++)
        {
            char symbol = text.charAt(i);
            Node curNode = trie.get(0);
            int curNodePtr = 0;
            int textIndex = i;
            int textSize = 0;
            int patternSize = 0;
            
            while(true)
            {
                //if this is a leaf
                if(curNode.next[0] == -1 && curNode.next[1] == -1 
                    && curNode.next[2] == -1 && curNode.next[3] == -1)
                {
                    result.add(i);
                    break;
                }
                //traverse
                else if(curNode.next[letterToIndex(symbol)] != Node.NA)
                {
                    curNodePtr = curNode.next[letterToIndex(symbol)];
                    curNode = trie.get(curNodePtr);
                    patternSize++;
                    
                    if(textIndex < text.length() - 1) //increment text index
                    {
                        symbol = text.charAt(++textIndex);
                        textSize ++;
                        
                        Node temp = trie.get(curNodePtr);
                        if(temp.patternEnd)
                        {
                            result.add(i);
                            break;
                        }
                        
                        
                    }
                    else if(textSize != patternSize - 1) 
                    {
                        //text has ended before finding a leaf in pattern
                        break;
                    }
                    else
                    {
                        Node temp = trie.get(curNodePtr);
                        if(temp.patternEnd)
                        {
                            result.add(i);
                            break;
                        }
                    }
                }
                else //no match
                    break;
            }
        }
        return result;
    }

    public void run () {
            try {
                    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
                    String text = in.readLine ();
                    int n = Integer.parseInt (in.readLine ());
                    List <String> patterns = new ArrayList <String> ();
                    for (int i = 0; i < n; i++) {
                            patterns.add (in.readLine ());
                    }

                    List <Integer> ans = solve (text, n, patterns);

                    for (int j = 0; j < ans.size (); j++) {
                            System.out.print ("" + ans.get (j));
                            System.out.print (j + 1 < ans.size () ? " " : "\n");
                    }
            }
            catch (Throwable e) {
                    e.printStackTrace ();
                    System.exit (1);
            }
    }

    public static void main (String [] args) {
            new Thread (new TrieMatchingExtended ()).start ();
    }
}
