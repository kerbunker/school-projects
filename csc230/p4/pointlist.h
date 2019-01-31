/**
    @file pointlist.h
    @author Katelyn Bunker (kbunker)
    This file is the header file for the pointlist. This contains the struct for the pointlist
    as well as the functions dealing with the pointlist
*/
#include "point.h"
#include <stdbool.h>

/**
    The struct for the pointlist structure. This contains the count for the number of points
    current in the list as well as the max number of points currently allowed in the list.
    This struct also includes the pointer to the array of pointers to the points that make up
    the list.
*/
typedef struct {
    int count;
    int cap;
    Point **list;
} Pointlist;

/**
    This creates a new pointlist of a default initial capacity and dynamically
    allocates the array of the pointers to the Points that will make up the list.
    @return the pointer to the created pointlist
*/
Pointlist *createPointlist();

/**
    Frees the points as well as the pointlist array.
    @param ptlist the pointer to the pointlist to free
*/
void freePointlist( Pointlist *ptlist );

/**
    Adds a given point to the pointlist. Checks that there is not already a point with the
    same name as the given point and also reallocates space if the list is already at capacity
    @param ptlist the pointer to the pointlist to add the point to
    @param pt the pointer to the point to add to the list
    @return true if the point is successfully added
*/
bool addPoint( Pointlist *ptlist, Point *pt );

/**
    Removes the point from the list and frees the space it had used.
    @param ptlist the pointer to the pointlist to remove the point from
    @param name the name of the point to remove
    @return true if the point is successfully removed. returns false if a point with
    the given name could not be found or if the pointlist is empty
*/
bool removePoint( Pointlist *ptlist, char const *name );

/**
    Lists the points from the pointlist that fit the given criteria using the given test function
    to test if the point should be printed.
    @param ptlist the pointer to the pointlist to print to data from
    @param ref the current location of the user
    @param test the pointer to the test function to test if a point should be printed
    @param pt the pointer to the point to test in the test function. (test function parameter)
    @param data the pointer to the data used by the test function (test function parameter)
    @param data the pointer to the data to be passed to the test function
*/
void listPoints( Pointlist *ptlist, Coords const *ref,
    bool (*test)( Point const *pt, void *data ), void *data );