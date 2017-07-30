// --------------------------------------------------------------------------
// DictionaryTest.java
// Matthew Tan
// mxtan
// pa3
// CMPS 12B/M
// --------------------------------------------------------------------------

public class DictionaryTest
{
    public static void main(String[] args)
    {
        String test;
        Dictionary A = new Dictionary();

        // tests inserting items into Dictionary ADT
        A.insert("1", "a");
        A.insert("2", "b");
        A.insert("3", "c");
        A.insert("4", "d");
        A.insert("5", "e");
        A.insert("6", "f");
        A.insert("7", "g");
        System.out.println(A);
        
        // tests looking up items in Dictionary ADT
        test = A.lookup("1");
        System.out.println("key = 1 " + (test == null ? "not found" 
             : ("value = " + test)));

        test = A.lookup("3");
        System.out.println("key = 3 " + (test == null ? "not found" 
             : ("value = " + test)));

        test = A.lookup("5");
        System.out.println("key = 5 " + (test == null ? "not found" 
             : ("value = " + test)));

        test = A.lookup("7");
        System.out.println("key = 7 " + (test == null ? "not found" 
             : ("value = " + test)));

        test = A.lookup("8");
        System.out.println("key = 8 " + (test == null ? "not found" 
             : ("value = " + test)));
        System.out.println();

        // Following causes DuplicateKeyException
        // A.insert("2", "f");

        // Following causes KeyNotFoundException
        // A.delete("8");

        // You can do the following:
        // A.insert("9", "i");
        // insert works because you
        // are inserting a new key that is not duplicate
        // System.out.println(A);
       
        // tests deleting items in DictionaryADT
        A.delete("1");
        A.delete("3");
        A.delete("7");
        System.out.println(A);
                
        System.out.println(A.isEmpty());
        System.out.println(A.size());
        A.makeEmpty();

        // prints out an empty list:
        // System.out.println(A);
        System.out.println(A.isEmpty());

        // Causes KeyNotFoundException since 
        // there are no keys in the list
        // A.delete("4");

        System.out.println(A);
    }
}
