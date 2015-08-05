/*
    Molly Calhoun
    April 15, 2015
    Caesar cipher decoder
*/

#include <stdio.h>
#include <stdlib.h>

void tallyLetters(FILE *in, int *alphabet)
{
    int offset = 97; // ascii offset
    char c;
    while ((c = getc(in)) != EOF)
    {
        alphabet[c - offset]++;
    }
    rewind(in);
}

char findE(int *alphabet)
{
    int offset = 97;
    int max = alphabet[0];
    char e = 0;
    int i;
    for (i = 0; i < 26; i++)
    {
        if (alphabet[i] > max)
        {
            max = alphabet[i];
            e = i;
        }
    }
    return e + offset;
}

void decrypt(FILE *in, int key)
{
    char c;
    while ((c = getc(in)) != EOF)
    {
        if (c > 'a')
        {
            c = c - key;
        }

        if (c < 'a' && c != '\n')
        {
            c = c + 26;
        }
        else if (c > 'z' && c != '\n')
        {
            c = c - 26;
        }
        
        fputc(c, stdout);
    }
    fputc('\n', stdout);
    rewind(in);
}

int main(int argc, char const *argv[])
{
    if (argc < 2)
    {
        puts("You need an input file.");
        exit(1);
    }
    FILE *in;
    in = fopen(argv[1], "r");

    int alphabet[26] = {0};
    tallyLetters(in, alphabet);
    char e = findE(alphabet);
    int key = e - 'e';

    decrypt(in, key);
    fclose(in);
    exit(0);
}