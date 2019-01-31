#include "point.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

/** The max length for the point description */
#define DESC_LEN 1024

/** Multiplier for converting degrees to radians */
#define DEG_TO_RAD ( M_PI / 180 )

/** Radius of the earth in miles. */
#define EARTH_RADIUS 3959.0

/** The max allowed latitude */
#define MAX_LAT 90

/** The min allowed latitude */
#define MIN_LAT -90

/** The max allowed longititude */
#define MAX_LON 180

/** The min allowed longitude */
#define MIN_LON -180

Point *parsePoint()
{
    Point *newPoint = ( Point * )malloc( sizeof( Point ) );
    char desc[ DESC_LEN + 1 ];
    int matches = scanf( "%20s %lf %lf %1024[^\n\t]", newPoint->name, &( newPoint->location.lat ),
    &( newPoint->location.lon ), desc );
    int ch = getc( stdin );
    if ( matches != 4 || ch != '\n' ) {
        ungetc( ch, stdin );
        return NULL;
    } else if ( ( newPoint->location.lat ) < MIN_LAT || ( newPoint->location.lat ) > MAX_LAT
    || ( newPoint->location.lon ) < MIN_LON || ( newPoint->location.lon ) > MAX_LON ) {
        ungetc( '\n', stdin );
        return NULL;
    }
    int descLen = strlen( desc );
    char *pointDesc = ( char * )malloc( descLen + 1 );
    strcpy( pointDesc, desc );
    newPoint->desc = pointDesc;
    return newPoint;

}

void freePoint( Point *pt )
{
    free( pt->desc );
    free( pt );

}

void reportPoint( Point const *pt, Coords const *ref )
{
    double distance = globalDistance( &( pt->location ), ref );
    printf( "%s (%.1lf miles)\n  %s\n", pt->name, distance, pt->desc );

}

double globalDistance( Coords const *c1, Coords const *c2 )
{
    double v1[] = { cos( ( c1->lon ) * DEG_TO_RAD ) * cos( ( c1->lat ) * DEG_TO_RAD ),
                    sin( ( c1->lon ) * DEG_TO_RAD ) * cos( ( c1->lat ) * DEG_TO_RAD ),
                    sin( ( c1->lat ) * DEG_TO_RAD ) };
                    
    double v2[] = { cos( ( c2->lon ) * DEG_TO_RAD ) * cos( ( c2->lat ) * DEG_TO_RAD ),
                    sin( ( c2->lon ) * DEG_TO_RAD ) * cos( ( c2->lat ) * DEG_TO_RAD ),
                    sin( ( c2->lat ) * DEG_TO_RAD ) };
    
    double dp = 0.0;
    for ( int i = 0; i < sizeof( v1 ) / sizeof( v1[ 0 ] ); i++ ) {
        dp += v1[ i ] * v2[ i ];
    }
    
    double angle = acos( dp );
    
    return EARTH_RADIUS * angle;

}
