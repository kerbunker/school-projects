/**
  @file triangle.c
  @author Katelyn Bunker (kbunker)
  This is the main component of project 2 and it contains the main function.
  It is responsible for reading input and looking for errors and then it
  generates the output image.
  */
#include "geometry.h"
#include "encoding.h"
#include <stdio.h>
#include <stdlib.h>

/** The number of matches scanf should get for the size of the window */
#define SIZE_MATCHES 2
/** the number of matches scanf should get for the vertices */
#define VERTEX_MATCHES 6
/** The number of matches scanf should get for the RGB color components */
#define COLOR_MATCHES 3
/** The miniumum allowed size */
#define SMIN 1
/** The minimum allowed color component */
#define CMIN 0
/** Used to find the center of the pixel */
#define PIXEL_CENTER 0.5

/**
  The main function for the triangle program. Reads the information in from the user and checks
  for any errors in input. Then prints the header for the output file and checks each pixel
  for whether it is in the triangle and either prints out 0s for the black pixel or the given
  RGB components for if a pixel is inside the triangle.
  @return a successful exit status
  */
int main()
{
  int width;
  int height;
  double x1;
  double y1;
  double x2;
  double y2;
  double x3;
  double y3;
  int red;
  int green;
  int blue;
  //reads the information in from the user
  int matches1 = scanf ( "%d%d", &width, &height );
  int matches2 = scanf ( "%lf%lf%lf%lf%lf%lf", &x1, &y1, &x2, &y2, &x3, &y3 );
  int matches3 = scanf ( "%d%d%d", &red, &green, &blue );

  //checks that the correct amount of information was found
  if ( matches1 != SIZE_MATCHES || matches2 != VERTEX_MATCHES || matches3 != COLOR_MATCHES ) {
    return EXIT_FAILURE;
  }
  
  // checks the size validity
  if ( width < SMIN || height < SMIN ) {
    return EXIT_FAILURE;
  }
  
  //checks the color component validity
  if ( red < CMIN || red > CMAX || green < CMIN || green > CMAX || blue < CMIN || blue > CMAX ) {
    return EXIT_FAILURE;
  }
  
  //prints the output header
  printHeader( width, height );

  // checks each pixel for whether it is inside the tirangle and prints the corresponding colors
  for ( int i = 0; i < height; i++ ) {
    for ( int j = 0; j < width; j++ ) {
      if ( inside ( x1, y1, x2, y2, x3, y3, ( j + PIXEL_CENTER ), ( i + PIXEL_CENTER ) ) ) {
        printValue ( red );
        printValue ( green );
        printValue ( blue );
      } else {
        printValue ( CMIN );
        printValue ( CMIN );
        printValue ( CMIN );
      }
    }
  }
  printf( "\n" );

  return EXIT_SUCCESS;

}
