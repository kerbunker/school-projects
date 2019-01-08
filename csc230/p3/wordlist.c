/**
 *  @file wordlist.c
 *  @author Katelyn Bunker (Kbunker)
 *  This file reads the words from the given word list and enters them into the words global
 *  array while keeping track of the number of words added.
 */
#include "wordlist.h"
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

/** The max umber of words that the words array can store */
#define MAX_WORDS 50
/** The max length the word in the words array can be */
#define MAX_LENGTH 20

/** Global array variable to hold the word list */
char words[ MAX_WORDS ][ MAX_LENGTH + 1 ];

/** Global variable for holding the number of words in the words array. Initialized to 0 */
int wordCount = 0;

/**
 *  This function reads the words from the given file and enters them into the words array
 *  @param filename the pointer to the file to open to read the words
 */
void readWords( char const *filename )
{
    //Opens the given file and gives an error if the file can't be opened
    FILE *input = fopen( filename, "r" );
    if ( input == NULL ) {
        fprintf( stderr, "Can't open word file\n" );
        exit( 1 );
    }
    bool end = false;
    int ch;
    //reads the words in until EOF is reached, or if an invalid word is found
    while ( !end && wordCount < MAX_WORDS ) {
        ch = getc( input );
        if ( ch == EOF ) {
            end = true;
        } else {
            if ( ch >= 'A' && ch <= 'Z' ) {
                fprintf ( stderr, "Invalid word file\n" );
                fclose( input );
                exit( 1 );
            }
            ungetc( ch, input );
            int matches = fscanf( input, "%20[a-z]", words[ wordCount ] );
            ch = getc( input );
            if ( ch != ' ' && ch != '\n' ) {
                fprintf ( stderr, "Invalid word file\n" );
                fclose( input );
                exit( 1 );
            } else if ( matches != 1 ) {
                fprintf ( stderr, "Invalid word file\n" );
                fclose( input );
                exit( 1 );
            }
            ungetc( ch, input );
            fscanf( input, "%*[^a-z]" );
            wordCount++;
        }
    }
    //Checks that the file does not contain too many words
    if ( wordCount == MAX_WORDS && !end ) {
        fprintf ( stderr, "Invalid word file\n" );
        fclose( input );
        exit( 1 );
    }
    fclose( input );
}
