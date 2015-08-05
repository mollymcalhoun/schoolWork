/*
    Molly Calhoun
    March 31, 2015
    Brainfuck compiler
*/
    
#include <stdio.h>
#include <stdlib.h>

void initialize(FILE *out)
{
    fputs("#include <stdio.h>\n#include<stdlib.h>\n", out);
    fputs("int main(int argc, char const *argv[]) {\n", out);
    fputs("unsigned char buffer[30000];\n", out);
    fputs("unsigned char *p;\n", out);
    fputs("p = buffer;\n", out);
    //fputs("puts(\"hello world\");\n", out);
}

void wrapUp(FILE *out)
{
    fputs("exit(0);\n", out);
    fputs("}\n", out);
}

void parseSource(FILE *out)
{
    char c;
    while ((c = getc(stdin)) != EOF)
    {
        switch(c)
        {
            case '>':
                fputs("++p;\n", out);
                break;
            case '<':
                fputs("--p;\n", out);
                break;
            case '+':
                fputs("++*p;\n", out);
                break;
            case '-':
                fputs("--*p;\n", out);
                break;
            case '.':
                fputs("putchar(*p);\n", out);
                break;
            case ',':
                fputs("*p = getchar();\n", out);
                break;
            case '[':
                fputs("while (*p) {\n", out);
                break;
            case ']':
                fputs("}\n", out);
                break;
        }
    }
}

int main(int argc, char const *argv[])
{
    FILE *out;
    out = fopen("brainfuck.c", "w");
    unsigned char buffer[30000];
    unsigned char *p;
    p = buffer;

    initialize(out);
    parseSource(out);
    wrapUp(out);

    fclose(out);
    system("gcc -Wall brainfuck.c -o brainfuck");
    system("chmod +x brainfuck");
    system("rm brainfuck.c");

    return 0;
}