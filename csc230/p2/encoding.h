/**
  @file encoding.h
  @author Katelyn Bunker (kbunker)
  This file is the header file used to declare the max color component and the
  printHeader and printValue functions for the text.c and binary.c files
 */

/** Maximum color component value. */
#define CMAX 255

/**
  Prints the header for the text and binary outputs for the triangle program
  @param width the width of the window
  @param height the height of the window
 */
void printHeader( int width, int height );

/**
  Prints the character for the color value for a given pixel
  @param c the character for the RGB color value
 */
void printValue( unsigned char c );
