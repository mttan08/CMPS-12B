//---------------------------------------------------------------------------
// DictionaryTest.c
// Matthew Tan
// mxtan
// lab5
// CMPS 12B/M
//---------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[])
{
    Dictionary A = newDictionary();
 
    insert(A, "1", "a");
    insert(A, "2", "b");
    insert(A, "3", "c");
    insert(A, "4", "d");
    insert(A, "5", "e");
    insert(A, "6", "f");
    insert(A, "7", "g");

    // insert(A, "2", "c"); // causes a Duplicae Key Error
    printf("Before delete: start printDictionary--------------\n");
    printDictionary(stdout, A);
    printf("Is the list empty? %s\n", (isEmpty(A)?"true":"false"));
    printf("Current size is: %d\n", size(A));
    char* test;
    test = lookup(A, "1");
    printf("value of lookup: %s\n", test);
    printf("Before delete: end printDictionary--------------\n");
    delete(A, "1");
    delete(A, "3");
    delete(A, "5");
    delete(A, "7");
    printDictionary(stdout, A);
    printf("Is the current list empty? %s\n", (isEmpty(A)?"true":"false"));
    printf("Current size is: %d\n", size(A));
    makeEmpty(A);
    printf("After makeEmpty: start printDictionary-------------\n");
    printDictionary(stdout, A);
    // delete(A, "1"); // causes an Empty Dictionary Error
    // test = lookup(A, "1"); // causes a Empty Dictionary Error
    printf("After makeEmpty: end printDictionary-------------\n");
    printf("Is the current list empty? %s\n", (isEmpty(A)?"true":"false"));
    printf("Current size: %d\n", size(A));
}
