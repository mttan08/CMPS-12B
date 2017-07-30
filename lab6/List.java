// -------------------------------------------------------------------------
// List.java
// Matthew Tan
// mxtan
// lab6
// CMPS 12B/M
// -------------------------------------------------------------------------

@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>
{
    // private inner class
    private class Node 
    {
        private T item;
        Node next;

        // private Node constructor
        private Node(T x)
        {
            item = x;
            next = null;
        }
    }
    
    private Node head;
    private int numItems;
    
    // List constructor
    public List()
    {
        head = null;
        numItems = 0;
    }

    // finds an element in the list
    // at a given index
    private Node find(int index)
    {
        Node N = head;
        for (int i = 1; i < index; i++)
        {
            N = N.next;
        }
        return N;
    }

    //  isEmpty
    // pre: none
    // post: returns true of the list is empty, false otherwise
    public boolean isEmpty()
    {
        /*
        if (numItems == 0)
        {
            return true;
        }
        else
        {
            return false'
        }
        */
        return (numItems == 0);
    }

    // size
    // pre: none
    // post: returns the number of elements in this List
    public int size()
    {
        return numItems;
    }

    // get
    // pre: 1 <= index <= size()
    // post: returns item at position index
    public T get(int index) throws ListIndexOutOfBoundsException
    {
        if (index < 1 || index > numItems)
        {
            throw new ListIndexOutOfBoundsException(
                  "get(): invalid index: " + index);
        }
        else
        {
            Node N = find(index);
            return N.item;
        }
    }

    // add
    // inserts newItem in this List at position Index
    // pre: 1 <= index <= size() + 1
    // post: !isEmpty(), items to the right of the newItem are numbered
    public void add(int index, T newItem) throws ListIndexOutOfBoundsException
    {
        if (index < 1 || index > (numItems + 1))
        {
            throw new ListIndexOutOfBoundsException(
                  "add(): invalid index: " + index);
        }

        if (index == 1)
        {
            Node N = new Node(newItem);
            N. next = head;
            head = N;
        }
        else
        {
            Node P = find(index - 1);
            Node S = P.next;
            P.next = new Node(newItem);
            P = P.next;
            P.next = S;
        }
        numItems++;
    }
    
    // remove
    // delets item from position index
    // pre: 1 <= inded <= size()
    // post: items to the right of delete items are renumbered
    public void remove(int index) throws ListIndexOutOfBoundsException
    {
        if (index < 1 || index > numItems)
        {
            throw new ListIndexOutOfBoundsException(
                  "remove(): invalid index: " + index);
        }

        if (index == 1)
        {
            Node N = head;
            head = head.next;
            N.next = null;
        }
        else
        {
            Node P = find(index - 1);
            Node S = P.next;
            P.next = S.next;
            S.next = null;
        }
        numItems--;
    }

    // removeAll
    // pre: none
    // post: isEmpty()
    public void removeAll()
    {
        head = null;
        numItems = 0;
    }

    // toString
    // converts everything in list to a string
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        Node N = head;
        while (N != null)
        {
            sb.append(N.item).append(" ");
            N = N.next;
        }
        return new String(sb);
    }

    // equals
    // compare if the objects are equal
    @SuppressWarnings("unchecked")
    public boolean equals(Object compare) 
    {
        boolean equals = false;
        List<T> list = null;
        Node N = null;
        Node M = null;

        if (this.getClass() == compare.getClass()) 
        {
            list = (List<T>) compare;
            equals = (this.numItems == list.numItems);

            N = this.head;
            M = list.head;
            while (equals && N != null) 
            {
                equals = (N.item == M.item);
                N = N.next;
                M = M.next;
            }
        }
        return equals;
    }
}
