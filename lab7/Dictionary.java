// -------------------------------------------------------------------------
// Dictionary.java
// Matthew Tan
// mxtan
// lab7
// CMPS 12B/M
// -------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface
{
    private class Node
    {
        String key;
        String value;
        Node left;
        Node right;

        private Node(String k, String v)
        {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    Node root;
    int numPairs;    

    public Dictionary()
    {
        root = null;
        numPairs = 0;
    }
    
    public Node findKey(Node N, String key)
    {
        if (N == null || key.compareTo(N.key) == 0)
        {
            return N;
        }

        if (key.compareTo(N.key) < 0)
        {
            return findKey(N.left, key);
        }
        else
        {
            return findKey(N.right, key);
        }
    }
    
    public Node findParent(Node N, Node R)
    {
        Node P = null;
        if (N != R)
        {
            P = R;
            while (P.left != N && P.right != N)
            {
                String getNKey = N.key;
                String getRKey = R.key;
                if (getNKey.compareTo(getRKey) < 0)
                {
                    P = P.left;
                }
                else
                {
                    P = P.right;
                }
            }
        }
        return P;
    }

    public Node findLeftmost(Node N)
    {
        Node L = N;
        if (L != null)
        {
            while (L.left != null)
            {
                L = L.left;
            }
        }
        return L;
    }
    
    private String printInOrder(Node N)
    {
        String s ="";
        if (N != null)
        { 
            s = printInOrder(N.left) 
                + N.key + " " + N.value + "\n"
                + printInOrder(N.right);
        }
        return s;
    }

    public void deleteAll(Node N)
    {
        if (N != null)
        {
            deleteAll(N.left);
            deleteAll(N.right);
        }
    }

    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty()
    {
        /*
        if (numPairs == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        */

        return (numPairs == 0);
    }

    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size()
    {
        return numPairs;
    }


    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key)
    {
        Node N;
        N = findKey(root, key);
        return (N == null ? null: N.value);
    }

    // insert()
    // inserts new (key, value) pair into this Dictionary
    // pre: lookup(key) == null
    public void insert(String key, String value) throws DuplicateKeyException
    {
        Node N, A, B;

        if (lookup(key) != null)
        {
            throw new DuplicateKeyException(
                   "Dictionary: cannot insert() duplicate keys.");
        }
        else
        {
            N = new Node(key, value);
            A = root;
            B = null;
            while (A != null)
            {
                B = A;
                if (key.compareTo(A.key) < 0)
                {
                    A = A.left;
                }
                else
                {
                    A = A.right;
                }
            }
            if (B == null)
            {
                root = N;
            }
            else if (key.compareTo(B.key) < 0)
            {
                B.left = N;
            }
            else
            {
                B.right = N;
            }
            numPairs++;
        }
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key) != null
    public void delete(String key) throws KeyNotFoundException
    {
        Node N, P, S;
        N = findKey(root, key);

        if (lookup(key) == null)
        {
            throw new KeyNotFoundException(
                "Dictionary: cannot delete() non-existent key.");
        }
        if (N.left == null && N.right == null)
        {
            if (N == root)
            {
                root = null;
            }
            else
            {
                P = findParent(N, root);
                if (P.right == N)
                {
                    P.right = null;
                }
                else
                {
                    P.left = null;
                }
            }
        }
        else if (N.right == null)
        {
            if (N == root)
            {
                root = N.left;
            }
            else
            {
                P = findParent(N, root);
                if (P.right == N)
                {
                    P.right = N.left;                
                }
                else
                {
                    P.left = N.left;
                }
            }
        }
        else if (N.left == null)
        {
            if (N == root)
            {
                root = N.right;
            }
            else
            {
                P = findParent(N, root);
                if (P.right == N)
                {
                    P.right = N.right;
                }
                else
                {
                    P.left = N.right;
                }
            }
        }
        else
        {
            S = findLeftmost(N.right);
            N.key = S.key;
            N.value = S.value;
            P = findParent(N, S);
            if (P.right == S)
            {
                P.right = S.right;               
            }
            else
            {
                P.left = S.left;
            }
        }
        numPairs--;
    }

    // makeEmpty()
    // pre: none
    public void makeEmpty()
    {
        deleteAll(root);
        root = null;
        numPairs = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString()
    {
        String s = printInOrder(root);
        return s;
    }
}
