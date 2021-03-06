// -------------------------------------------------------------------------
// Dictionary.java
// Matthew Tan
// mxtan
// pa3
// CMPS 12B/M
// -------------------------------------------------------------------------


public class Dictionary implements DictionaryInterface
{
    private class Node 
    {
        String key;
        String value;
        Node next;

        private Node(String k, String v)
        {
            key = k;
            value = v;
            next = null;
        }
    }

    private Node head;
    private int numItems;

    public Dictionary()
    {
        head = null;
        numItems = 0;
    }

    private Node findKey(String key)
    {
        Node N = head;
        while (N != null)
        {
            String getKey = N.key;
            if (!getKey.equals(key))
            {
                N = N.next;
            }
            else
            {
                return N;
            }
        }
        return null;
    }

    private Node findPreviousNode(String key)
    {
        for (Node P = head; P != null; P = P.next)
        {
            Node N = P.next;
            //System.out.println("findPreviousNode: " + P.key + " ");

            String getKey = N.key;
            if (getKey.equals(key))
            {
                return P;
            }
        }
        return null;

    }
    
    public boolean isEmpty()
    {
        /*
        if (numItems == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        */
        return (numItems == 0);
    }
    
    public int size()
    {
        return numItems;
    }

    public String lookup(String key)
    {
        Node N = findKey(key);
        if (N != null)
        {
            return N.value;
        }
        return null;
    }

    public void insert(String key, String value) throws DuplicateKeyException
    {
        if (lookup(key) != null)
        {
            throw new DuplicateKeyException("cannot insert duplicate keys");
        }
        else
        {
            if (head == null)
            {
                Node N = new Node(key, value);
                head = N;
            }
            else
            {
                Node N = head;
                while (N != null)
                {
                    if (N.next == null) 
                    {
                        break;
                    }
                    N = N.next;
                }
                N.next = new Node(key, value);
            }
        }
        numItems++;
    }

    public void delete(String key) throws KeyNotFoundException
    {
        Node N = findKey(key);
        if (lookup(key) == null)
        {
            throw new KeyNotFoundException("cannot delete non-existent key");
        }
        else
        {
            if (N == head)
            {
                Node A = head;
                head = head.next;
                N.next = null;
            }
            else
            {
                // Node P = findPreviousNode(key);
                Node P = head;
                while (P != null)
                {
                    Node A = P.next;
                    String getKey = A.key;
                    if (getKey.equals(key))
                    {
                        break;
                    }
                    P = P.next;
                }
                
                Node A = P.next;
                Node S = A.next;
                if (S != null)
                {
                    P.next = S;
                    A.next = null;
                }
                else
                {
                    A.next = null;
                    P.next = null;
                }
            }
        }
        numItems--;
    }

    public void makeEmpty()
    {
        head = null;
        numItems = 0;
    }

    public String toString()
    {
        String s = "";
        StringBuffer sb = new StringBuffer();
        Node N = head;
        while (N != null) 
        {
            sb.append(N.key).append(" ").append(N.value).append("\n");
            N = N.next;
        }
        return new String(sb);
    }
}
