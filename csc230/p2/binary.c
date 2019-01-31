/**
  @file binary.c
  @author Katelyn Bunker (kbunker)
  This program prints out a binary version of the ppm for the triangle.
*/
#include "encoding.h"
#include <stdio.h>
#include <stdlib.h>

/**
  Prints out the header for the ppm file including the height and width of the window
  as well as the max value for the color values (255).
  @param width the width to print for the window
  @param height the height to print for the window
*/
void printHeader( int width, int height ) {
  //Prints out the P6 for the binary file, the width and height, and the max color value
  printf( "P6\n%d %d\n255\n", width, height );
  
}

/**
  Prints the RGB component passed in as the character parameter into binary.
  @param c the unsigned char to print as the RGB component
*/
void printValue( unsigned char c ) {
  //prints the character to the output in binary
  putchar( c );
  
}
