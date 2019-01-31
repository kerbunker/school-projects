/**
  @file geometry.c
  @author Katelyn Bunker (kbunker)
  This file is used to determine if a given point is inside the triangle.
*/
#include "geometry.h"
#include <stdio.h>
#include <stdlib.h>

/**
  This function determines if a pixel is inside the triangle using the laft-turn test.
  The first 4 parameters give the location of two consecutive vertices and the tiangle
  and the last 2 parameters give the center of the pixel.
  @param xa the x value of the first vertex of the triangle
  @param ya the y value of the first vertex of the triangle
  @param xb the x value of the second vertex of the triangle
  @param yb the y value of the second vertex of the triangle
  @param x the x value of the center of the pixel
  @param y the y value of the center of the pixel
  @return true if the pixel is inside the triangle
*/
bool leftOf ( double xa, double ya, double xb, double yb, double x, double y )
{
  //calculate the vectors
  double xe = xb - xa;
  double ye = yb - ya;
  double xp = x - xa;
  double yp = y - ya;
  //calculate the cross product
  double result = ( xe * yp ) - ( ye * xp );
  //if it is negative or on the line, the 'left turn test' passes
  if ( result <= 0 ) {
    return true;
  }
  return false;
  

}

/**
  This function uses leftOf() to determine whether a pixle is inside the triangle
  @param x1 the x value of the first vertex of the triangle
  @param y1 the y value of the first vertex of the triangle
  @param x2 the x value of the second vertex of the triangle
  @param y2 the y value of the second vertex of the triangle
  @param x3 the x value of the third vertex of the triangle
  @param y3 the y value of the third vertex of the triangle
  @param x the x value of the pixel of interest
  @param the y value of the pixel of interest
  @return true if the pixel is inside the triangle
*/
bool inside (double x1, double y1, double x2, double y2, double x3, double y3, double x, double y )
{
  //test the 3 angle pairs with the 'left turn test'
  bool result1 = leftOf( x1, y1, x2, y2, x, y );
  bool result2 = leftOf( x2, y2, x3, y3, x, y );
  bool result3 = leftOf( x3, y3, x1, y1, x, y );

  //if all three pass, the pixel is in the triangle
  if ( result1 && result2 && result3 ) {
    return true;
  }
  return false;
  
  
}
