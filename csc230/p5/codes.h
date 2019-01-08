/**
    @file codes.h
    @author Katelyn Bunker (kbunker)
    This is the header file for the codes file. This includes the declarations of the functions that
    are used by the encode or decode programs
*/
#include <stdbool.h>
#include <stdio.h>

/**
    Reads the code file and creates the data structure to store the codes data for the program
    to use to convert back and forth between text and bitcode.
    @param codes the pointer to the codes file to read the encoding data
    @return true if the file only contained the a-z characters and space, newline and EOF
    and returns false if any character is duplicated or not valid.
*/
bool readCodeFile( FILE *codes );

/**
    Finds the bitcode string that corresponds to the given character
    @param ch the character to find the bicode string corresponding to
    @return the pointer to the bitcode string
*/
const char *symToCode( int ch );

/**
    Finds the character corresponding to the bitcode string given.
    @param code the pointer to the string of the bitcode
    @return the int value of the character
*/
int codeToSym( const char *code );

/**
    Frees the dynamically allocated code string as well as all the dynamically allocated
    strings holding the codes
*/
void freeCodeStr();
