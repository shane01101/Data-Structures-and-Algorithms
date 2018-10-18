import java.io.*;
import java.util.*;

class Node
{
    public static final int Letters =  4;
    public static final int NA      = -1;
    public int next [];

    Node ()
    {
        next = new int [Letters];
        Arrays.fill (next, NA);
    }
    
    boolean isLeaf() {
        for(int i = 0; i < Letters; i++)
            if(next[i] != NA)
                return false;
        
        return true;
    }
}

public class TrieMatching implements Runnable {
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

    List <Integer> solve (String text, int n, List <String> patterns) {
        List <Integer> result = new ArrayList <Integer> ();
        List<Node> trie = buildTrie(patterns);
        
        for(int i = 0; i < text.length(); i++) {
            String s = text.substring(i);
            if(prefixTrieMatching(s, trie))
                result.add(i);
        }

        return result;
    }
    
    boolean prefixTrieMatching(String text, List<Node> trie) {
        
        int curNodePtr = 0;
        Node curNode = trie.get(curNodePtr);
        int patternIndex = 0;
        char c = text.charAt(patternIndex);
        
        while(true) {
            
            if(curNode.isLeaf()) {
                return true;
            }
            else if (curNode.next[letterToIndex(c)] != Node.NA){
                 curNodePtr = curNode.next[letterToIndex(c)];
                 curNode = trie.get(curNodePtr);
                 
                 if(curNode.isLeaf()) //reached leaf, pattern found
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
    
    List<Node> buildTrie(List<String> patterns) {
        List<Node> trie = new ArrayList<>();
        trie.add(new Node()); //root
        
        for(String s : patterns) {
            int curNodePtr = 0;
            
            for(char c : s.toCharArray()) {
                Node currentNode = trie.get(curNodePtr);
                
                if(currentNode.next[letterToIndex(c)] != Node.NA)
                    curNodePtr = currentNode.next[letterToIndex(c)];
                else { //add the letter the the map
                    Node newNode = new Node();
                    trie.add(newNode);
                    currentNode.next[letterToIndex(c)] = trie.size() - 1;
                    curNodePtr = trie.size() - 1;
                }  
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
        new Thread (new TrieMatching ()).start ();
    }
}
