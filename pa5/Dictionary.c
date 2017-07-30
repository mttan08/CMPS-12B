// -------------------------------------------------------------------------
// Dictionary.c
// Matthew Tan
// mxtan
// pa5
// CMPS 12B/M
// -------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include "Dictionary.h"

const int tableSize = 101;

typedef struct NodeObj
{
    char* key;
    char* value;
    struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node;

// newNode()
// creates a newNode emulating Java's new operator
Node newNode(char* k, char* v)
{
    Node N = malloc(sizeof(NodeObj));
    assert(N!= NULL);
    N->key = k;
    N->value = v;
    N-> next = NULL;
    return N;
}

// freeNode()
// frees the heap memory associated with the Dictionary
void freeNode(Node* pN)
{
    if (pN != NULL && *pN != NULL)
    {
        free(*pN);
        *pN = NULL;
    }
}

typedef struct DictionaryObj
{
    Node* table;
    int numItems;
}DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->table = calloc(tableSize, sizeof(Node));
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for  the Dictionary type
void freeDictionary(Dictionary *pD)
{
    if (pD != NULL && *pD != NULL)
    {
        if (isEmpty(*pD))
        {
            makeEmpty(*pD);
        }
        free((*pD)->table);
        free(*pD);
        *pD = NULL;
    }
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);  // remainder on division by sizeInBits
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
    unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100
    while (*input) {                   // while *input is not '\0' (not end of string)
        result ^= *input++;                // result = result ^ *input (current char alters result) 
        // input++  (go to next char)
        result = rotate_left(result, 5);   // rotate result by fixed amount
    }
    return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

// findKey()
// finds a certain key in the hash table
Node findKey(Dictionary D, char* k)
{
    if (D == NULL)
    {
        fprintf(stderr,
           "Dictionary Error: calling findKey() on NULL Dictionary reference\n");
    }

    Node N;
    N = D->table[hash(k)];
    if (D->table == NULL)
    {
        return NULL;
    }
    while (N != NULL)
    {
        if (strcmp(N->key, k) == 0)
        {
            return N;
        }
        else
        {
            N = N->next;
        }
    }
    return NULL;
}

// isEmpty()
// returns 1(true) if S is empty or 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
    if (D == NULL)
    {
        fprintf(stderr,
           "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
    return (D->numItems);
}

// lookup()
// returns the value v suck that (k, v) is in D, or returns NULL if no
// such value exists
// pre: noe
char* lookup(Dictionary D, char* k)
{
    if (D == NULL)
    {
        fprintf(stderr,
          "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    Node N = findKey(D, k);
    if (N != NULL)
    {
        return N->value;
    }
    return NULL;
}

// insert()
// inserts new (key, value) pair into D
// pre: lookup(D, k) != NULL
void insert(Dictionary D, char* k, char* v)
{
    int i = hash(k);
    if (D == NULL)
    {
        fprintf(stderr,
          "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    
    if (lookup(D, k) != NULL)
    {
        fprintf(stderr,
          "Dictionary Error: cannot insert() duplicate keys\n");
    }
    else
    {
        if (D->table[i] == NULL)
        {
            Node N = newNode(k, v);
            D->table[i] = N;
        }
        else
        {
            Node H = D->table[i];            
            Node N = newNode(k, v);
            N->next = H;
        }
        D->numItems++;
    }
}

// delete()
// deltes a pair with the key k
// pre: lookup(D, k) != NULL
void delete(Dictionary D, char* k)
{
    int i = hash(k);
    if (D == NULL)
    {
        fprintf(stderr,
          "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    } 
    
    if (lookup(D, k) == NULL)
    {
        fprintf(stderr,
          "Dictioinary Error: cannot delete() empty list\n");
    }
    else
    {
        Node N = findKey(D, k);
        if (N == D->table[i])
        {
            Node A = D->table[i];
            D->table[i] = A->next;
            freeNode(&A);
        }
        else
        {
            Node P = D->table[i];
            while (P != NULL)
            {
                Node A = P->next;
                if (strcmp(A->key, k) == 0)
                {
                    break;
                }
                P = P->next;
            }
            
            Node A = P->next;
            Node S = A->next;
            if (S != NULL)
            {
                P->next = S;
                A->next = NULL;
                freeNode(&A);
            }
            else
            {
                P->next = NULL;
                freeNode(&A);
            }
        }
    }
    D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state
// pre: none
void makeEmpty(Dictionary D)
{
    for (int i = 0; i < tableSize; i++)
    {
        while (D->table[i] != NULL)
        {
            Node N;
            N = D->table[i];
            D->table[i] = N->next;
            freeNode(&N);
            N = NULL;
        }
    }
    D->numItems = 0; 
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{
    Node N;
    if (D == NULL)
    {
        fprintf(stderr,
          "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    for(int i = 0; i < tableSize; i++)
    {
        N = D->table[i];
        while (N != NULL)
        {
            fprintf(out, "%s %s\n" , N->key, N->value);
            N = N->next;
        }
    } 
}

