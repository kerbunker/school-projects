/**
  @file geometry.h
  @author Katelyn Bunker (kbunker)
  This is the header file for the geometry.c file. This declares the functions leftOf and inside
  which are used to determine if a given pixel is inside the triangle.
 */

#include <stdbool.h>

/** For the extra credit, the number of horizontal and vertical super-samples
    per pixel.  This is a preprocessor macro with conditional compilation
    code around it.  That lets us replace this value on the gcc line, if
    we want to.  This constant definition should probably be in triangle.c
    rather than in this header, but putting it here lets me give it to you
    with the starter. */
#ifndef SSAMP
#define SSAMP 1
#endif

/**
  This function used the cross product of 2 vectors to determine if a given pixel (x, y) is
  to the left of the vector formed from 2 angles to determine if the pixel is inside the triangle.
  @param xa the x value of the first vertex
  @param ya the y value of the first vertex
  @param xb the x value of the second vertex
  @param yb the y value of the second vertex
  @param x the x value of the pixel
  @param y the y value of the pixel
  @return true if the "left of" method is true
 */
bool leftOf( double xa, double ya, double xb, double yb,
             double x, double y );

/**
  This function determines if a given pixel (x, y) is inside the triangle
  using the "left of" method.
  @param x1 the x value of the first vertex
  @param y1 the y value of the first vertex
  @param x2 the x value of the second vertex
  @param y2 the y value of the second vertex
  @param x3 the x value of the third vertex
  @param y3 the y value of the third vertex
  @param x the x value of the pixel
  @param y the y value of the pixel
  @return true if the pixel is inside the triangle
 */
bool inside( double x1, double y1, double x2, double y2,
             double x3, double y3, double x, double y );
