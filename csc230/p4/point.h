/**
    @file point.h
    @author Katelyn Bunker (kbunker)
    This is the header file for the point file. This contains the structs for the 
    location as well as the point struct and the functions that deal with the points
*/
#ifndef POINT_H
#define POINT_H

/** Representation for a location, in latitude and longitude. */
typedef struct {
    /** Latitude value for a global location. */
    double lat;
  
    /** Longitude value for a global location. */
    double lon;
} Coords;

/** The max length that a name can be in the struct */
#define NAME_LEN 20

/** The struct for the Point containing the name array, the location
    as well as the pointer to the dynamically allocated description array */
typedef struct {
    /** array for the point name */
    char name[ NAME_LEN + 1 ];
    /** The global location of the point */
    Coords location;
    /** The pointer to the dynamically allocated description array */
    char *desc;
} Point;

/**
    Reads the data from stdin to create a new point while testing the data validity
    @return the pointer to the new point
*/
Point *parsePoint();

/**
    Frees the point and the description when it is no longer needed
    @param pt the pointer to the point to free the data
*/
void freePoint( Point *pt );

/**
    Prints the data for the given point using the current location to print the correct distance
    @param pt the pointer to the point to print the pata
    @param ref the pointer to the current location to print the distance
*/
void reportPoint( Point const *pt, Coords const *ref );

/**
    Finds the distance between two locations given as coords structs
    @param c1 the pointer to the first location
    @param c2 the pointer to the second location
    @return the distance between the points
*/
double globalDistance( Coords const *c1, Coords const *c2 );

#endif
