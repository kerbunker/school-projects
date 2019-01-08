/**
  @file text.c
  @author Katelyn Bunker (kbunker)
  Prints out the output file for the text ppm files. Prints the header with the size and
  color information and then the data for each pixel.
  */
#include "encoding.h"
#include <stdio.h>
#include <stdlib.h>

#define ONE_DIGIT_LIMIT 10
#define TWO_DIGIT_LIMIT 100
#define MAX_LENGTH 70
#define ONE_DIGIT 1
#define TWO_DIGIT 2
#define THREE_DIGIT 3
#define ONE_DIGIT_SPACE 2
#define TWO_DIGIT_SPACE 3
#define THREE_DIGIT_SPACE 4

/**
  Prints out the header for the text ppm format with P3, the width and height and the highest value
  of the pixel.
  @param width gives the width of the window
  @param height gives the height of the window
  */
void printHeader( int width, int height )
{
    printf ( "P3\n%d %d\n255\n", width, height );
}

/**
  Prints out each RGB value for the pixel
  @param c the character value of the pixel to print
  */
void printValue( unsigned char c )
{
  static int lineCount = 0;
  //Adds the number and adds to the count when a space is not needed
  if ( lineCount == 0 ) {
    printf( "%d", c );
    if ( c < ONE_DIGIT_LIMIT ) {
      lineCount += ONE_DIGIT;
    } else if ( c < TWO_DIGIT_LIMIT ) {
      lineCount += TWO_DIGIT;
    } else {
      lineCount += THREE_DIGIT;
    }
    //Adds the number and adds to the count when a space is needed and checks for the line length
  } else {
    if ( c < ONE_DIGIT_LIMIT && lineCount < ( MAX_LENGTH - ONE_DIGIT ) ) {
      printf( " %d", c );
      lineCount += ONE_DIGIT_SPACE;
    } else if ( c < TWO_DIGIT_LIMIT && lineCount < ( MAX_LENGTH - TWO_DIGIT ) ) {
      printf( " %d", c );
      lineCount += TWO_DIGIT_SPACE;
    } else if ( lineCount < ( MAX_LENGTH - THREE_DIGIT ) ) {
      printf( " %d", c );
      lineCount += THREE_DIGIT_SPACE;
    } else {
      //if the line limit has been reached a new line is printed and the number is printed
      printf( "\n" );
      lineCount = 0;
      printf( "%d", c );
      if ( c < ONE_DIGIT_LIMIT ) {
        lineCount += ONE_DIGIT;
      } else if ( c < TWO_DIGIT_LIMIT ) {
        lineCount += TWO_DIGIT;
      } else {
        lineCount += THREE_DIGIT;
      }
    }
  }
}
