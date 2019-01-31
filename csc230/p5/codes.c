/**
    @file codes.c
    @author Katelyn Bunker (kbunker)
    
    This file handles the codes portion of encoding and decoding a file. It first creates
    a data structure to hold all the codes from the codes file then finds the correct code
    for a given symbol or finds the correct symbol for a given code.
*/
#include "codes.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

/** Global pointer for the dynamically allocated codes data */
static char **codesStr;

/** Defines the length of the alphabet for holding codes */
#define ALPHA_LEN 26
/** Defines the index for holding the space character */
#define SPACE_INDEX 26
/** Defines the index for holding the newline character */
#define NEWLINE_INDEX 27
/** Defines the index for holding the EOF character */
#define EOF_INDEX 28
/** The max character a word from the codes file would be */
#define WORD_LEN 7
/** The max characters a bitcode from the codes file can be */
#define CODE_LEN 12
/** The return value if no symbol is found corresponding to the bitcode */
#define NO_SYM -2

bool readCodeFile( FILE *codes )
{
    int ch;
    //allocated memory for the array of pointer to the bitcode strings
    codesStr = ( char ** )malloc( ( EOF_INDEX + 1 ) * sizeof( char * ) );
    //initialized all pointer to null
    for ( int i = 0; i < EOF_INDEX + 1; i++ ) {
        codesStr[ i ] = NULL;
    }
    //defines the bitcode array to hold the code while creating the array of code pointers
    char bitcode[ CODE_LEN + 1 ];
    int matches;
    //gets each code until the EOF is hit
    while ( ( ch = getc( codes ) ) != EOF ) {
        int ch2 = getc( codes );
        char word[ WORD_LEN + 1 ];
        //if the code is not designated to a single letter, it finds if it is a '\n', ' ', or EOF
        if ( ch2 != ' ' ) {
            ungetc( ch2, codes );
            ungetc( ch, codes );
            //gets the codeword and checks for space after it before the bitcode
            matches = fscanf( codes, "%7[a-z]", word );
            ch = getc( codes );
            if ( matches != 1 || ch != ' ' ) {
                return false;
            }
            
            //gets the bit code for space, checks that there isn't already a code for it
            //then adds it to the array at the correct index
            if ( strcmp( word, "space" ) == 0 ) {
                matches = fscanf( codes, "%12[01]", bitcode );
                ch = getc( codes );
                if ( matches != 1 && ch != '\n' ) {
                    return false;
                }
                if ( codesStr[ SPACE_INDEX ] ) {
                    return false;
                }
                codesStr[ SPACE_INDEX ] = ( char * )malloc( strlen( bitcode ) + 1 );
                strcpy( codesStr[ SPACE_INDEX ], bitcode );
            //gets the bitcode for a newline, checks that there isn't already a code for it
            //then adds it to the array
            } else if ( strcmp( word, "newline" ) == 0 ) {
                matches = fscanf( codes, "%12[01]", bitcode );
                ch = getc( codes );
                if ( matches != 1 && ch != '\n' ) {
                    return false;
                }
                if ( codesStr[ NEWLINE_INDEX ] ) {
                    return false;
                }
                codesStr[ NEWLINE_INDEX ] = ( char * )malloc( strlen( bitcode ) + 1 );
                strcpy( codesStr[ NEWLINE_INDEX ], bitcode );
            //gets the bitcode for EOF, checks that the index isn't already full and adds it
            } else if ( strcmp( word, "eof" ) == 0 ) {
                matches = fscanf( codes, "%12[01]", bitcode );
                ch = getc( codes );
                if ( matches != 1 && ch != '\n' ) {
                    return false;
                }
                if ( codesStr[ EOF_INDEX ] ) {
                    return false;
                }
                codesStr[ EOF_INDEX ] = ( char * )malloc( strlen( bitcode ) + 1 );
                strcpy( codesStr[ EOF_INDEX ], bitcode );
            }
        //gets the bitcode for each letter of the alphabet then adds it to the array after
        //checking for a duplicate entry
        } else {
            if ( ch < 'a' || ch > 'z' ) {
                return false;
            }
            int index = ch - 'a';
            if ( codesStr[ index ] ) {
                return false;
            }
            matches = fscanf( codes, "%12[01]", bitcode );
            ch = getc( codes );
            if ( matches != 1 && ch != '\n' ) {
                return false;
            }
            codesStr[ index ] = ( char * )malloc( strlen( bitcode ) + 1 );
            strcpy( codesStr[ index ], bitcode );
        }
    }
    //makes sure that all indices have been filled and no codes are empty
    for ( int i = 0; i < ( EOF_INDEX + 1 ); i++ ) {
        if ( !codesStr[ i ] ) {
            return false;
        }
    }
    
    return true;
}

const char *symToCode( int ch )
{
    //checks what character is requested and returns the code at the related indes of the array
    if ( ch == ' ' ) {
        return codesStr[ SPACE_INDEX ];
    } else if ( ch == '\n' ) {
        return codesStr[ NEWLINE_INDEX ];
    } else if ( ch == EOF ) {
        return codesStr[ EOF_INDEX ];
    } else if ( ch < 'a' && ch > 'z' ) {
        return NULL;
    } else {
        return codesStr[ ch - 'a' ];
    }
}

int codeToSym( const char *code )
{
    //compares the code string at each index to the given code string and returns the
    //coresponding symbol if a match is found
    for ( int i = 0; i < EOF_INDEX + 1; i++ ) {
        if ( strcmp( code, codesStr[ i ] ) == 0 ) {
            if ( i < ALPHA_LEN ) {
                return 'a' + i;
            } else if ( i == SPACE_INDEX ) {
                return ' ';
            } else if ( i == NEWLINE_INDEX ) {
                return '\n';
            } else {
                return EOF;
            }
        }
    }
    return NO_SYM;
}

void freeCodeStr()
{
    //frees each index of the codes string then frees the whole array
    for ( int i = 0; i < EOF_INDEX + 1; i++ ) {
        if ( codesStr[ i ] )
            free( codesStr[ i ] );
    }
    free( codesStr );
}
