// -------------------------------------------------------------------------
// Dictionary.c
// Matthew Tan
// mxtan
// lab5
// CMPS 12B/M
// -------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

typedef struct NodeObj
{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* k, char* v)
{
    Node N = malloc(sizeof(NodeObj));
    assert(N != NULL);
    N -> key = k;
    N -> value = v;
    N -> next = NULL;
    return (N);
}


void freeNode(Node *pN)
{
    if (pN != NULL && *pN != NULL)
    {
        free(*pN);
        *pN = NULL;
    }
}

typedef struct DictionaryObj
{
    Node head;
    int numItems;
} DictionaryObj;

Dictionary newDictionary(void)
{
    Dictionary d = malloc(sizeof(DictionaryObj));
    assert(d != NULL);
    d->head = NULL;
    d->numItems = 0;
    return d;
}


void freeDictionary(Dictionary* pD)
{
    if (pD != NULL && *pD != NULL)
    {
        if (!isEmpty(*pD))
        {
            makeEmpty(*pD);
        }
        free(*pD);
        *pD = NULL;
    }
}

Node findKey(Dictionary D, char* key)
{
    //printf("findKey: got here1!!!!\n");
    //printf("findKey: key is: %s\n", key);
    //printf("findKey: D is: %d\n", D);
    // printf("findKey: numItems: %d\n", D->numItems);
    if (D == NULL)
    {
        fprintf(stderr,
               "Dictionary Error: calling findKey() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
         
    }

    //printf("findKey: before D -> head: %p\n", D -> head);
    if (D->head == NULL)
    {
        return NULL;
    }
    Node N = D->head;
    //printf("findKey: after D -> head: \n");
    //printf("findKey: N is: %d\n", N);
    // int count = 0;
    while (N != NULL)
    {
        // count++;
        // printf("findKey: count is: %d\n", count);
        //printf("findKey: before N -> key: \n");
        char* getKey = N->key;
        // printf("findKey: getKey is: %s, key is: %s\n", getKey, key);
        if (strcmp(getKey, key) == 0)
        {
            // printf("findKey: found it!\n");
            return N;
        }
        else
        {
            N = N->next;
            // printf("findKey: did not find!\n");
        }
    } // end of while
    return NULL;
}

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

int size(Dictionary D)
{
    return (D->numItems);
}

char* lookup(Dictionary D, char* k)
{
    //printf("lookup:Got here 1!!!\n");
    //printf("lookup: k is: %s\n", k);
    //printf("lookup: D is: %d\n", D);

    if (D == NULL)
    {
        fprintf(stderr,
               "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    Node N = findKey(D, k);
    //printf("lookup: N is: %d\n", N);
    if (N != NULL)
    {
        return N->value;
    }
    return NULL;
}

void insert(Dictionary D, char* k, char* v)
{
    /*
    printf("insert: got here 1!!!\n");
    printf("insert: k is: %s\n", k);
    printf("insert: D is %d\n", D);
    */
    if (D == NULL)
    {
        fprintf(stderr,
               "Dictionary Error: calling insert() on NULL LinkedList reference");
        exit(EXIT_FAILURE);
    }

    if (lookup(D, k) != NULL)
    {
        printf("Dictionary Error: cannot insert() duplicate keys\n");
    }
    else
    {
        if (D->head == NULL)
        {
            Node N = newNode(k, v);
            //printf("insert: N is: \n", N);
            D->head = N;
            //printf("insert: D is: \n", D);
        }
        else
        {
            Node N = D->head;
            // printf("insert: N is: %d\n", N);
            while (N != NULL)
            {
                if (N->next == NULL)
                {
                    break;
                }
                N = N->next;
            }
            N->next = newNode(k, v);
        } // end of else
        D->numItems++;
    }
}

void delete(Dictionary D, char* k)
{

    // printf("delete: delete start---------------\n");

    if (D == NULL)
    {
        fprintf(stderr,
              "Dictionary Error: calling printDictionary() on NULL stack reference\n");
        exit(EXIT_FAILURE);
    }

    if (lookup(D, k) == NULL)
    {
        printf("DictionaryError: cannot delete() empty list\n"); 
    }
    else
    {
        Node N = findKey(D, k);
        // printf("delete: N is: %d\n", N);
        // printf("delete: N == D -> head %d\n", N == D -> head);
        if (N == D->head)
        {
            // printf("delete: deleting head\n");
            Node A = D->head;
            // printf("delete: A is: %d\n", A); 
            D->head = A->next;
            freeNode(&A);
        }
        else
        {
            Node P = D->head;
            while (P != NULL)
            {
                Node A = P->next;
                char* getKey = A->key;
                if (strcmp(getKey, k) == 0)
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
    // printf("delete: delete end---------------\n");
}

void makeEmpty(Dictionary D)
{
    D->head = NULL;
    D->numItems = 0;
}

void printDictionary(FILE* out, Dictionary D)
{
    Node N;

    if (D == NULL)
    {
        fprintf(stderr,
              "Dictionary Error: calling printDictionary() on NULL stack reference\n");
        exit(EXIT_FAILURE);
    }

    for (N = D->head; N != NULL; N = N->next)
    {
        fprintf(out, "%s %s", N -> key, N->value);
        fprintf(out, "\n");
    }
}
