/**
 *  @file display.c
 *  @author Katelyn Bunker (Kbunker)
 *  This file displays the word to be solved and the part of the hangman to be displayed
 */
#include "display.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/** The number of wrong guesses to print the head and both torso pieces */
#define HEAD_TORSO 3
/** The number of wrong guesses to print the head, torso, and 1 arm */
#define ONE_ARM 4
/** The number of wrong guesses to print out the head, torso, and both arms */
#define TWO_ARMS 5
/** The number of wrong guesses to print the head. torso, both arms, and 1 leg */
#define ONE_LEG 6

/**
 *  This function displays the word to be solved for the puzzle with spaces between
 *  each character
 *  @param word[] the array storing the word to be displayed
 */
void displayWord( char word[] )
{
    int length = strlen( word );
    printf( "%c", word[ 0 ] );
    for ( int i = 1; i < length; i++ ) {
        printf ( " %c", word[ i ] );
    }
}

/**
 *  This function displays number of parts of the hangman corresponding to the
 *  number of errors the user has made guessing the word
 *  @param numberOfParts the number of hangman pieces to be displayed
 */
void displayFigure( int numberOfParts )
{
    if ( numberOfParts == 0 ) {
        printf( "\n" );
    }else if ( numberOfParts == 1 ) {
        printf( "\n O \n\n" );
    }else if ( numberOfParts == 2 ) {
        printf( "\n O \n | \n\n" );
    }else if ( numberOfParts == HEAD_TORSO ) {
        printf( "\n O \n | \n | \n\n" );
    }else if ( numberOfParts == ONE_ARM ) {
        printf( "\n O \n/| \n | \n\n" );
    }else if ( numberOfParts == TWO_ARMS ) {
        printf( "\n O \n/|\\\n | \n\n" );
    }else if ( numberOfParts == ONE_LEG ) {
        printf( "\n O \n/|\\\n | \n/\n\n" );
    } else {
        printf( "\n O \n/|\\\n | \n/ \\\n\n" );
    }

}
