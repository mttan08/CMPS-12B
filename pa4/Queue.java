// ---------------------------------------------------------------------------
// Queue.java
// Matthew Tan
// mxtan
// CMPS 12B/M
// pa4
// ---------------------------------------------------------------------------

public class Queue implements QueueInterface
{
    private class Node
    {
        Object item;
        private Node next;

        private Node(Object x)
        {
            item = x;
            next = null;
        }
    }

    private int numItems;
    private Node front;
    private Node back;

    public Queue()
    {
        front = null;
        back = null;
        numItems = 0;
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

    public int length()
    {
        return numItems;
    }

    public void enqueue(Object newItem)
    {
        Node N = new Node(newItem);
        if (front == null)
        {
            front = N;
        }
        else
        {
            back.next = N;
        }
        back = N;
        numItems++;
    }

    public Object dequeue() throws QueueEmptyException
    {
        if (isEmpty())
        {
            throw new QueueEmptyException("cannot dequeue() a empty queue");
        }
        else
        {
            Object temp = front.item;
            // System.out.println("temp is: " + temp);
            front = front.next;
            numItems--;
            return temp;
        }
    }

    public Object peek() throws QueueEmptyException
    {
        if (isEmpty())
        {
            throw new QueueEmptyException("cannot peek() an empty queue");
        }
        else
        {
            Object temp = front.item;
            return temp;
        }
    }

    public void dequeueAll() throws QueueEmptyException
    {
        front = null;
        back = null;
        numItems = 0;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        for (Node N = front; N != null; N = N.next)
        {
            sb.append(N.item).append(" ");
        }
        return new String(sb);
    }
}
