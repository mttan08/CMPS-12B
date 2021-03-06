// --------------------------------------------------------------------------
// QueueTest.java
// Matthew Tan
// mxtan
// pa4
// CMPS 12B/M
// --------------------------------------------------------------------------

public class QueueTest
{
    public static void main(String[] args)
    {
        Queue q = new Queue();

        // tests the enqueue() method
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        System.out.println("Before dequeue(): ");
        System.out.print(q + "\n");

        // tests the peek() method after enqueue()
        System.out.print("Before dequeue(): peek(): " + q.peek() + "\n");
       
        // test the isEmpty() method before emptying the queue
        System.out.print("Is the queue empty? " + q.isEmpty() + "\n");

        // tests the length() method before dequeue()
        System.out.print("What is the length of the queue? " + q.length() + "\n");
        
        System.out.println();

        // tests the dequeue() method
        q.dequeue();
        System.out.println("After dequeue(): ");
        System.out.print(q + "\n");

        // tests the peek() method after dequeue()
        System.out.print("After dequeue(): peek(): " + q.peek() + "\n");
        
        // tests the current length of the queue
        System.out.print("What is the length now? " + q.length() + "\n");        
        
        // tests the isEmpty() method one last timre before dequeueAll()
        System.out.print("Is the queue empty? " + q.isEmpty() + "\n");
        System.out.println();

        // tests the dequeueAll() method
        q.dequeueAll();
        System.out.print("After dequeueAll(): " + "\n");
        // prints out an empty queue
        System.out.print("Empty queue: "+ q + "\n");

        // test the isEmpty() method after dequeueAll()
        System.out.println("Is the queue empty? " + q.isEmpty());

        // tests the length() method after dequeueAll()
        System.out.println("What is the queue length? " + q.length());
        
        // tests dequeue() method
        // since the queue is empty,
        // a QueueEmptyException is returned;
        // q.dequeue();
        
        // tests peek() on an empty queue
        // since the queue is empty,
        // a QueueEmptyException is returned;
        // q.peek();
    }
}
