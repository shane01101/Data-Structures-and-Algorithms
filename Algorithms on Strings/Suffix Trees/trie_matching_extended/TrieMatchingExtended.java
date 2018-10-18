import java.io.*;
import java.util.*;

class Node2
{
    public static final int Letters =  4;
    public static final int NA      = -1;
    public int next [];
    public boolean patternEnd;

    Node2 ()
    {
        next = new int [Letters];
        Arrays.fill (next, NA);
        patternEnd = false;
    }
    
    boolean isLeaf() {
        for(int i = 0; i < Letters; i++)
            if(next[i] != NA)
                return false;
        
        return true;
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
            default: assert (false); return Node2.NA;
        }
    }

    List <Integer> solve (String text, int n, List <String> patterns) {
        List <Integer> result = new ArrayList <Integer> ();
        List<Node2> trie = buildTrie(patterns);
        
        for(int i = 0; i < text.length(); i++) {
            if(prefixTrieMatching(text.substring(i), trie))
                result.add(i);
        }

        return result;
    }
    
    boolean prefixTrieMatching(String text, List<Node2> trie) {
        
        int curNodePtr = 0;
        Node2 curNode = trie.get(curNodePtr);
        int patternIndex = 0;
        char c = text.charAt(patternIndex);
        
        while(true) {
            
            if(curNode.isLeaf() || curNode.patternEnd) {
                return true;
            }
            else if (curNode.next[letterToIndex(c)] != Node2.NA){
                 curNodePtr = curNode.next[letterToIndex(c)];
                 curNode = trie.get(curNodePtr);
                 
                 if(curNode.isLeaf() || (curNode.patternEnd && text.length() >= patternIndex)) //reached leaf, pattern found
                     return true;
                 
                 //text length out of bounds
                 if(patternIndex == text.length() -1)
                     return false;
                 
                 c = text.charAt(++patternIndex);
            }
            else
                return false;
        }
    }
    
    List<Node2> buildTrie(List<String> patterns) {
        List<Node2> trie = new ArrayList<>();
        trie.add(new Node2()); //root
        
        for(String s : patterns) {
            int curNodePtr = 0;
            int endOfPattern = s.length() - 1;
            int curCharIndex = 0; 
            
            for(char c : s.toCharArray()) {
                Node2 currentNode = trie.get(curNodePtr);
                
                if(currentNode.next[letterToIndex(c)] != Node.NA)
                    curNodePtr = currentNode.next[letterToIndex(c)];
                else { //add the letter the the map
                    Node2 newNode = new Node2();
                    trie.add(newNode);
                    currentNode.next[letterToIndex(c)] = trie.size() - 1;
                    curNodePtr = trie.size() - 1;
                }  
                
                //mark end of pattern
                if(endOfPattern == curCharIndex) {
                    Node2 endNode = trie.get(curNodePtr);
                    endNode.patternEnd = true;
                }
                curCharIndex++;
            }
        }
        return trie;
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
