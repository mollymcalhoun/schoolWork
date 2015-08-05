Midterm assignment to write a C transpiler for the esoteric programming language 'Brainfuck.'

The Brainfuck compiler (bfc.c) reads a brainfuck source file from standard input character-by-character, and writes C code for each character to a file until it reaches the EOF in the input.

Then, the gcc is called via the system() command to compile the newly created transpiled C code into a file called 'brainfuck.' The system() command is then used to remove the intermediate 'brainfuck.c' file. To see the intermediate file, the line:

    system("rm brainfuck.c");

  can be commented out.