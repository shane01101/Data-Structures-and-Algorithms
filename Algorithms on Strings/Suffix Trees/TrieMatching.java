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
        List<Node> trie = createTrieFromPatterns(patterns);

        for(int i = 0; i < text.length(); i++) {
            if(prefixTrieMatching(text.substring(i), trie))
                result.add(i);
        }
        return result;
    }

    List<Node> createTrieFromPatterns(List <String> patterns) {
        List<Node> trie = new ArrayList<>();
        trie.add(new Node());

        // create trie from patterns array
        for(int i = 0; i < patterns.size(); i++) {
            Node curNode = trie.get(0);
            for(char symbol : patterns.get(i).toCharArray()) {
                // create a new node in the trie
                if(curNode.next[letterToIndex(symbol)] == Node.NA) {
                    curNode.next[letterToIndex(symbol)] = trie.size();
                    Node newNode = new Node();
                    curNode = newNode;
                    trie.add(newNode);
                } else { // node found, go to next
                    curNode = trie.get(curNode.next[letterToIndex(symbol)]);
                }
            }
        }
        return trie;
    }

    boolean prefixTrieMatching(String text, List<Node> trie) {
        int charIndex = 0; //start at beginning of text
        int curPatternSize = 0; // size of current pattern in trie
        char symbol = text.charAt(charIndex);
        Node curNode = trie.get(0); //root of trie

        while(true) {
            if(isLeaf(curNode))
                return true;
            else if(curNode.next[letterToIndex(symbol)] != -1) {
                curNode = trie.get(curNode.next[letterToIndex(symbol)]); // next node in trie
                curPatternSize++;

                if(text.length() - curPatternSize > 0)
                    symbol = text.charAt(++charIndex); // next char in text
                else if(curPatternSize > text.length()) //text has ended before finding a leaf in pattern
                    return false;
            }
            else
                return false;
        }
    }

    boolean isLeaf(Node n) {
        return n.next[0] == -1 &&
                n.next[1] == -1 &&
                n.next[2] == -1 &&
                n.next[3] == -1;
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
