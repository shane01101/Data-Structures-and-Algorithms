import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private static final int cardinality = 1000;
    private List<Contact>[] contacts;
    
    public PhoneBook()
    {
        contacts = new ArrayList[cardinality];
        
        for(int i = 0; i < cardinality; i++)
            contacts[i] = new ArrayList<Contact>();
    }

    public static void main(String[] args) {
        
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }

    private void processQuery(Query query) 
    {
        int hashIndex = getHashIndex(query.number);
        int chainIndex = getChainIndex(contacts[hashIndex], query.number);
        
        if (query.type.equals("add")) 
        {
            if(chainIndex == -1)
                contacts[hashIndex].add(new Contact(query.name, query.number));
            else
                contacts[hashIndex].get(chainIndex).name = query.name;
        } 
        else if (query.type.equals("del")) 
        {
            if(chainIndex > -1) //exists
            {
                contacts[hashIndex].remove(chainIndex);
            }
        }
        else { //find
            String response = "not found";
            
             if(chainIndex > -1)
                 response = contacts[hashIndex].get(chainIndex).name;
            
            writeResponse(response);
        }
    }
    
    private int getHashIndex(int num)
    {
        return num % cardinality;
    }
    
    private int getChainIndex(List<Contact> contactHash, int num)
    {
        int result = -1;
        
        for(int i = 0; i < contactHash.size(); i++)
            if(contactHash.get(i).number == num)
                result = i;
            
        return result;
    }
    

    public void processQueries() {
        
        
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
