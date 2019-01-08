/**
    @file attractions.c
    @author Katelyn Bunker (kbunker)
    This file is the main part of the attractions program. It reads in the command from the 
    console and runs the different commands through making and using a pointlist.
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#include "point.h"
#include "pointlist.h"

/** The max length of the name read in for a point */
#define NAME_LEN 20
/** The max length for a command word */
#define CMD_LEN 6
/** The starting latitute */
#define DEFAULT_LAT 35.772325
/** The starting longitude */
#define DEFAULT_LON -78.673581
/** The max allowed latitude */
#define MAX_LAT 90
/** The min allowed latitude */
#define MIN_LAT -90
/** The max allowed longititude */
#define MAX_LON 180
/** The min allowed longitude */
#define MIN_LON -180

/** The global variable to store the users current location */
Coords currentLoc;

/**
    This function ignores the rest of a line to get rid of data after an incorrect input
*/
void ignoreLine()
{
    //reads in characters until a newline is reached
    int ch = getc( stdin );
    while ( ch != '\n' ) {
        ungetc( ch, stdin );
        scanf( "%*s" );
        ch = getc( stdin );
    }
    
}

/**
    This is the test function for the list command. It returns true for every points so all
    points in a list are printed
    @param pt the pointer to the constant point to test 
    @param data the pointer to the data if it was needed for a test
    @return true to print out the point data
*/
bool listCmd( Point const *pt, void *data )
{
    return true;
}

/**
    This is the test function for the nearby command. It returns true if the point is within
    the given distance from the user using the current location global variable and the 
    distance given in the data pointer
    @param pt the pointer to the point to test if it is within the given distance
    @param data the pointer to the distance that the user wants the points within
    @return true if the point is within the given distance
*/
bool nearCmd( Point const *pt, void *data )
{
    //converts the void pointer to the correct type of pointer
    double *maxDist = ( double *) data;
    //finds the distance the point is from the current location
    double dist = globalDistance( &( pt->location ), &currentLoc );
    //tests if the distance is within the given distance
    if( dist <= *maxDist ) {
        return true;
    }
    return false;
}

/**
    The test function for matching a given word to the data in a points description. This returns
    true if the given word appears by itself in the description regardless of case
    @param pt the pointer to the point to test
    @param data the pointer to the word array to test the description against
    @return true if the given word appears in the description
*/
bool matchCmd( Point const *pt, void *data )
{
    char *matchWord = (char *) data;
    
    int len = strlen( matchWord );
    int descLen = strlen( pt->desc );
    char desc[ descLen + 3 ];
    desc[ 1 ] = '\0';
    strcat( desc, pt->desc );
    desc[ 0 ] = ' ';
    desc[ descLen + 1 ] = ' ';
    desc[ descLen + 2 ] = '\0';
    char *p = strcasestr( desc, matchWord );
    if( p ) {
        if ( ( ( p[ -1 ] >= 'a' ) && ( p[ -1 ] <= 'z' ) )
            || ( ( p[ -1 ] >= 'A' ) && ( p[ -1 ] <= 'Z' ) ) ) {
            return false;
        }
        if ( p[ len ]  == ' ' ) {
            return true;
        }
        if( ( ( p[ len ] >= 'a' ) && ( p[ len ] <= 'z' ) )
            || ( ( p[ len ] >= 'A' ) && ( p[ len ] <= 'Z' ) ) ) {
            return false;
        } else {
            return true;
        }
    }
    return false;
}

/**
    Reads the stdin stream to find the command word given by the user. This then performs the
    necessary funtions to carry out the instructions.
    @param lineCount allows the lineCount to stay current to be increased after each command
    @param list the pointer to the pointlist
    @return true if the user has not quit, of EOF has not been reached.
*/
bool readCmd( int *lineCount, Pointlist *list )
{
    //checks for EOF
    int ch = getc( stdin );
    if ( ch == EOF ) {
        printf( "\n" );
        exit( 0 );
    }
    ungetc( ch, stdin );
    
    //Gets the command word
    char cmdWord [ CMD_LEN + 1 ];
    int matches = scanf( "%6s", cmdWord );
    ch = getc( stdin );
    if ( matches != 1 && ( ch != ' ' || ch != '\n' ) ) {
        ungetc( ch, stdin );
        printf( "Invalid command\n" );
        ignoreLine();
        (*lineCount)++;
        return true;
    }
    if( ch != '\n' ) {
        ungetc( ch, stdin );
    }
    
    //Compares the command word to each possible command and carries out the instructions
    if ( strcmp ( "add", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        Point *p = parsePoint();
        if ( !( p ) ) {
            printf( "Invalid command\n" );
            ignoreLine();
            return true;
        }
        if( !addPoint( list, p ) ) {
            printf( "Invalid command\n" );
            return true;
        }
        return true;
    } else if( strcmp ( "remove", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        char name[ NAME_LEN + 1 ];
        matches = scanf( "%20s", name );
        ch = getc( stdin );
        if( matches != 1 && ch != '\n' ) {
            ungetc( ch, stdin );
            printf( "Invalid command\n" );
            ignoreLine();
            return true;
        }
        if ( !removePoint( list, name ) ) {
            printf( "Invalid command\n" );
            return true;
        }
        return true;
    } else if ( strcmp ( "move", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        double lat;
        double lon;
        matches = scanf( "%lf %lf", &lat, &lon );
        ch = getc( stdin );
        if( matches != 2 || ch != '\n' ) {
            ungetc( ch, stdin );
            printf( "Invalid command\n" );
            ignoreLine();
            return true;
        } else if ( lat < MIN_LAT || lat > MAX_LAT || lon < MIN_LON || lon > MAX_LON ) {
            printf( "Invalid command\n" );
            return true;
        }
        currentLoc.lat = lat;
        currentLoc.lon = lon;
        return true;
    } else if ( strcmp ( "list", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        listPoints( list, &currentLoc, listCmd, NULL );
        return true;
    } else if ( strcmp ( "nearby", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        double dist;
        matches = scanf( "%lf", &dist );
        ch = getc( stdin );
        if( matches != 1 || ch != '\n' ) {
            ungetc( ch, stdin );
            printf( "Invalid command\n" );
            ignoreLine();
            return true;
        }
        listPoints( list, &currentLoc, nearCmd, &dist );
        return true;
    } else if ( strcmp ( "match", cmdWord ) == 0 ) {
        printf( "\n" );
        (*lineCount)++;
        char matchWord[ NAME_LEN + 1 ];
        matches = scanf( " %20[a-z]", matchWord );
        ch = getc( stdin );
        if( matches != 1 || ch != '\n' ) {
            ungetc( ch, stdin );
            printf( "Invalid command\n" );
            ignoreLine();
            return true;
        }
        listPoints( list, &currentLoc, matchCmd, matchWord );
        return true;
    } else if ( strcmp ( "help", cmdWord ) == 0 ) {
        printf( "\n" );
        printf ( "add <name> <latitude> <longitude> <description>\n" );
        printf( "remove <name>\nmove <latitude> <longitude>\nlist\n" );
        printf( "nearby <distance>\nmatch <word>\nhelp\nquit\n" );
        (*lineCount)++;
        return true;
    } else if ( strcmp ( "quit", cmdWord ) == 0 ) {
        printf( "\n" );
        return false;
    } else {
        printf( "\n" );
        printf( "Invalid command\n" );
        ignoreLine();
        (*lineCount)++;
        return true;
    }
}


/**
    The main function of the program. Sets the current location. Prompts the user for the fist
    instruction and creates the point list. Then free the pointlist after the user quits.
    @return the exit status
*/
int main()
{
    int lineCount = 1;
    printf( "%d> ", lineCount );
    double defaultLat = DEFAULT_LAT;
    double defaultLon = DEFAULT_LON;
    
    currentLoc.lat = defaultLat;
    currentLoc.lon = defaultLon;
    Pointlist *list = createPointlist();
    
    while ( readCmd ( &lineCount, list ) ) {
        printf( "%d> ", lineCount );
    }
    freePointlist( list );

    return EXIT_SUCCESS;
}

