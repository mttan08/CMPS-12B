/*
 * charType.c
 * Matthew Tan
 * mxtan
 * CMPS 12B/M
 * lab4
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s , char* a, char* d, char* p, char* w);

int main(int argc, char* argv[])
{
    FILE* in;
    FILE* out;
    char* line;
    char* alpha;
    char* digit;
    char* punct;
    char* whitespace;
    int lineNumbers = 1;

    if (argc != 3)
    {
        printf("Usage: %s input-file output-file \n", argv[0]);
        exit(EXIT_FAILURE);
    }
   
    in = fopen(argv[1], "r");
    if (in == NULL)
    {
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    out = fopen(argv[2],"w");
    if (out == NULL)
    {
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    line = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
    alpha = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
    digit = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
    punct = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
    whitespace = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
    assert(line != NULL && alpha != NULL && digit != NULL 
           && punct != NULL && whitespace != NULL);
    
    while (fgets(line, MAX_STRING_LENGTH, in) != NULL)
    {
        //  printf("Line is: %s\n", line);        
        extract_chars(line, alpha, digit, punct, whitespace);
        // printf("Calling extract: alpha is: %s\n", alpha);
        fprintf(out, "line %d contains: \n", lineNumbers);
        // printf("alpha string length is: %d\n", strlen(alpha));
        // causes core dump
        // printf("%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);

        // alphabetic characters
        if (strlen(alpha) > 1) 
        {
            fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
        } 
        else 
        {
            fprintf(out, "%d alphabetic character: %s\n", (int)strlen(alpha), alpha);
        }

        // digits
        if (strlen(digit) > 1) 
        {
            // printf("Got here!! \n");
            fprintf(out, "%d numeric characters: %s\n", (int)strlen(digit), digit);
        } 
        else 
        {
            // printf("Got here!! \n");
            fprintf(out, "%d numeric character: %s\n", (int)strlen(digit), digit);
        }

        // punctuation
        if (strlen(punct) > 1) 
        {
            // printf("Got here!! \n");
            fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punct), 
                    punct);
        } 
        else 
        {
            fprintf(out, "%d punctuation character: %s\n", (int)strlen(punct), 
                    punct);
        }

        // white_space
        if (strlen(whitespace) > 1) 
        {
            // printf("Got here!! \n");
            fprintf(out, "%d whitespace characters: %s\n", (int)strlen(whitespace), 
                    whitespace);
        } 
        else 
        {
            fprintf(out, "%d whitespace character: %s\n", (int)strlen(whitespace), 
                    whitespace);
        }

        lineNumbers++;
    }

    free(line);
    free(alpha);
    free(digit);
    free(punct);
    free(whitespace);
    
    // close files
    fclose(in);
    fclose(out);

    return (EXIT_SUCCESS);
}

void extract_chars(char* s, char* a, char* d, char* p, char* w)
{
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int m = 0;

    // printf("extract_chars: Before loop: s is: %s\n", s);

    while (s[i] != '\0' && i < MAX_STRING_LENGTH)
    {
        // printf("extract_chars: During loop: s is: %s\n", s);
        if (isalpha((int)s[i]))
        {
            a[j] = s[i];
            // printf("a[%d] is: %c, s[%d] is: %c\n", j, a[j], i, s[i]);
            j++;
        }
        else if (isdigit((int)s[i]))
        {
            d[k] = s[i];
            k++;
        }
        else if (ispunct((int)s[i]))
        {
            p[l] = s[i];
            l++;
        }
        else
        {
            w[m] = s[i];
            m++;
        }

        i++;
    }
  
    a[j] = '\0';
    // printf("Before passing back: a is:  %s\n", a);
    d[k] = '\0';
    p[l] = '\0';
    w[m] = '\0'; 
}

