/**
  @file regular.c
  @author Katelyn Bunker (kbunker)
  
  The main file of the regular program which is used to create and match regular expressions.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pattern.h"
#include "parse.h"

/** On the command line, which argument is the pattern. */
#define PAT_ARG 1

/** On the command line, which argument is the input file. */
#define FILE_ARG 2

/** The max lenght for the string */
#define STR_LEN 100

/** The ASCII code for the ESC character */
#define ESC_NUM 27

/** The min arguments allowed */
#define MIN_ARG 2

/** The max arguments allowed */
#define MAX_ARG 3

/**
  Checks the given pattern against the string and prints out the strings that
  contain the pattern, with the pattern highlighted in red.
  
  @param pat the pattern to match
  @param str the string to match the pattern to
*/
void printString( Pattern *pat, char const *str )
{
  int len = strlen( str );
  bool mflag = false;
  char testStr[ len ];
  for ( int i = 0; i < len; i++ ) {
    testStr[ i ] = 'n';
  }
  //checks the string for any matches to the pattern and sets the flag to true
  for ( int begin = 0; begin <=len; begin++ )
    for ( int end = begin; end <= len; end++ )
      if ( matches( pat, begin, end ) ) {
        mflag = true;
        for ( int i = begin; i < end; i++ ) {
          testStr[ i ] = 'y';
        }
      }
  // If a match was found, the string is printed with the matching part in red
  if ( mflag ) {
    int i = 0;
    while ( i < len ) {
      if ( testStr[ i ] == 'n' ) {
        printf( "%c", str[ i ] );
        i++;
      } else {
        printf( "%c%c%c%c%c", ESC_NUM, '[', '3', '1', 'm' );
        while ( i < len && testStr[ i ] == 'y' ) {
          printf( "%c", str[ i ] );
          i++;
        }
        printf( "%c%c%c%c", ESC_NUM, '[', '0', 'm' );
      }
    }
    printf( "\n" );
  }
}

/**
   Entry point for the program, parses command-line arguments, builds
   the pattern and then tests it against lines of input.

   @param argc Number of command-line arguments.
   @param argv List of command-line arguments.
   @return exit status for the program.
*/
int main( int argc, char *argv[] )
{

  if ( argc < MIN_ARG || argc > MAX_ARG ) {
    fprintf( stderr, "usage: regular <pattern> [input-file.txt]\n" );
    exit( EXIT_FAILURE );
  }
  
  FILE *input;
  if ( argc == MAX_ARG ) {
    input = fopen( argv[ FILE_ARG ], "r" );
    if ( !input ) {
      fprintf( stderr, "Can't open input file: %s\n", argv[ FILE_ARG ] );
      exit( EXIT_FAILURE );
    }
  }
  Pattern *pat = parsePattern( argv[ PAT_ARG ] );
  if ( argc == MAX_ARG ) {
      int ch;
      char str[ STR_LEN + 1 ];
      while ( ( ch = getc( input ) ) != EOF ) {
          ungetc( ch, input );
          fscanf( input, "%100[^\n]", str );
          ch = getc( input );
          if ( ch != '\n' ) {
              fprintf( stderr, "Input line too long\n" );
              pat->destroy( pat );
              exit( EXIT_FAILURE );
          }
          pat->locate( pat, str );
          printString( pat, str );
      }
      pat->destroy( pat );
  } else {
    int ch;
    char str[ STR_LEN + 1 ];
    while ( ( ch = getc( stdin ) ) != EOF ) {
      ungetc( ch, stdin );
      fscanf( stdin, "%100[^\n]", str );
      ch = getc( stdin );
      if ( ch != '\n' ) {
        fprintf( stderr, "Input line too long\n" );
        pat->destroy( pat );
        exit( EXIT_FAILURE );
      }
    pat->locate( pat, str );
    printString( pat, str );
    }
    pat->destroy( pat );
  }
  
  if ( input ) {
    fclose( input );
  }

  return EXIT_SUCCESS;
}
