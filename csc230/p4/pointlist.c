/**
    @file pointlist.c
    @author Katelyn Bunker
    This is the file that manipulates the pointlist for the attractions program
*/
#include "pointlist.h"
#include "point.h"
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

/** The starting cap for the pointlist */
#define START_CAP 5

Pointlist *createPointlist()
{
    //sets the cap and starting count
    int cap = START_CAP;
    int count = 0;
    //mallocs space for the array of pointers
    Point **array = ( Point ** )malloc( cap * sizeof( Point * ) );
    //mallocs space for the pointlist struct
    Pointlist *newList = ( Pointlist * )malloc( sizeof( Pointlist ) );
    newList->cap = cap;
    newList->count = count;
    newList->list = array;
    return newList;

}

void freePointlist( Pointlist *ptlist )
{
    //goes through each point and frees the space held there before freeing the
    //pointer array and then the actual pointlist struct
    int count = ptlist->count;
    while( count > 0 ) {
        if( ptlist->list[ count - 1 ] ) {
            freePoint( ptlist->list[ count - 1 ] );
        }
        count--;
    }
    free( ptlist->list );
    free( ptlist );

}

bool addPoint( Pointlist *ptlist, Point *pt )
{
    //checks that a point does not already have the given name
    int listCount = ptlist->count;
    for( int i = 0; i < listCount; i++ ) {
        if( ptlist->list[ i ] && strcmp( ptlist->list[ i ]->name, pt->name ) == 0 ) {
            //frees the new point if it cannot be added to the list
            freePoint( pt );
            return false;
        }
    }
    //checks whether the pointlist is at capacity and reallocs more space if needed
    if ( ptlist->count >= ptlist->cap ) {
        ptlist->cap *= 2;
        ptlist->list = ( Point ** )realloc( ptlist->list, ptlist->cap * sizeof( Point * ) );
    }
    ptlist->list[ ptlist->count ] = pt;
    (ptlist->count)++;
    return true;

}

bool removePoint( Pointlist *ptlist, char const *name )
{
    int listCount = ptlist->count;
    // checks each point to match the given name to the point name
    for( int i = 0; i < listCount; i++ ) {
        if( ptlist->list[ i ] && strcmp( ptlist->list[ i ]->name, name ) == 0 ) {
            //if the name matches the point is freed and the rest of the points are moved 
            //to fill the space left
            freePoint( ptlist->list[ i ] );
            for( int j = i; j < listCount - 1; j++ ) {
                ptlist->list[ j ] = ptlist->list[ j + 1 ];
            }
            (ptlist->count)--;
            listCount--;
            return true;
        }
    }
    return false;

}

/**
    A helper function for the listPoints function. Sorts the points in the pointlist by the
    distance the points are from the current location of the user.
    @param ptlist the pointlist to sort.
    @param ref the coordinates of the current location
*/
void sortList( Pointlist *ptlist, Coords const *ref )
{
    //Finds the global distance of each point to compare them and sort them
    int count = ptlist->count;
    for( int i = 0; i < count; i++ ) {
        for( int j  = 0; j < count - i - 1; j++ ) {
            double dist1 = globalDistance( &( ptlist->list[ j ]->location ), ref );
            double dist2 = globalDistance( &( ptlist->list[ j + 1 ]->location ), ref );
            if( dist1 > dist2 ) {
                Point *temp = ptlist->list[ j ];
                ptlist->list[ j ] = ptlist->list[ j + 1 ];
                ptlist->list[ j + 1 ] = temp;
            }
        }
    }
}

void listPoints( Pointlist *ptlist, Coords const *ref,
    bool (*test)( Point const *pt, void *data ), void *data )
{
    //sorts the list then uses the test function to test each point before printing the data
    sortList( ptlist, ref );
    int count = ptlist->count;
    for( int i = 0; i < count; i++ ) {
        if( test( ptlist->list[ i ], data ) ) {
            reportPoint( ptlist->list[ i ], ref );
        }
    }
    

}
