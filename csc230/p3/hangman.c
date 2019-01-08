/**
 *  @file hangman.c
 *  @author Katelyn Bunker (kbunker)
 *  This file is the main part of the hangman game. It takes in the filename and seed arguments
 *  and sets up the game with them and then cycles through the game until the game has finished
 *  and the user decides not to play again, or the EOF has been reached
 */
#include "wordlist.h"
#include "display.h"
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

/** The number of letters in the alphabet used for the remaining letters array */
#define ALPHA_LEN 26
/** The max number of incorrect guesses allowed before the game is lost */
#define MAX_GUESSES 7
/** The number of command line arguments given if a seed is given */
#define SEED_COMMAND 3
/** The index for the seed used to 'randomize' the game */
#define SEED_INDEX 2

/**
 *  This function runs the main part of the game by printing out the game word and the remaining
 *  letters to guess from and then prompts the user for a letter which it then checks for
 *  validity and whether it is in the game word before displaying the hangman character
 *  if any wrong guesses have been guessed. This function then calls itself if the game
 *  is not finished to repeatedly prompt for a new letter
 *  @param *solvedWord the word the player is trying to guess
 *  @param *gameWord the word in it's current state for the game
 *  @param wordLen the length of the game word
 *  @param *remLetters the array holding the remaining letters to guess from
 */
void playGame( char *solvedWord, char *gameWord, int wordLen, char *remLetters,
    int *wrongGuesses )
{
    //displays the word then the remaining letters
    displayWord( gameWord );
    printf( "\n\n" );
    printf( "Remaining letters:" );
    for ( int i = 0; i < ALPHA_LEN; i++ ){
        if ( remLetters [ i ] != '0' ){
            printf( " %c", remLetters[ i ] );
        }
    }
    //prompts the user for their guess
    printf( "\n\n" );
    printf( "letter> " );
    int input = '*';
    bool valid = false;
    bool posLetter = false;
    //checks that the letter is valid and not already guessed and that only 1 letter was entered
    while ( !valid ) {
        input = getc( stdin );
        char test = getc( stdin );
        if ( input == EOF ) {
            exit( 0 );
        }
        if ( test != '\n' ) {
            ungetc( test, stdin );
            scanf( "%*[^\n]%*c" );
            printf( "\nInvalid letter\n\n" );
            printf( "letter> " );
            continue;
        }
        for ( int i = 0; i < ALPHA_LEN; i++ ) {
            if ( input == remLetters[ i ] ) {
                posLetter = true;
                remLetters[ i ] = '0';
            }
        }
        if ( input >= 'a' && input <= 'z' && !posLetter) {
            printf( "\nInvalid letter\n\n" );
            printf( "letter> " );
        } else if ( input < 'a' || input > 'z' ) {
            printf( "\nInvalid letter\n\n" );
            printf( "letter> " );
        } else {
            valid = true;
        }
    }
    //checks whether the letter is in the gameWord and adds it to the gameWord array in
    //the correct location(s)
    bool inWord = false;
    int j = 0;
    if ( valid ) {
        while ( j < wordLen ) {
            if ( input == solvedWord[ j ] ){
                inWord = true;
                gameWord[ j ] = input;
                j++;
            } else {
                j++;
            }
        }
    }
    //if the guessed letter was not in the game word and wrong guess is added to the count
    if ( !inWord ) {
        (*wrongGuesses)++;
    }
    //checks if the game has been won or lost then recalls playGame if the game continues
    if ( *wrongGuesses >= MAX_GUESSES ) {
        displayFigure( *wrongGuesses );
        printf ( "You lose!\nWord was %s\n\n", solvedWord );
    } else {
        bool solved = true;
        for ( int i = 0; i < wordLen; i++ ) {
            if ( gameWord[ i ] != solvedWord[ i ] ) {
                solved = false;
            }
        }
        if ( solved ) {
            printf( "\n" );
            displayWord( gameWord );
            printf( "\n\nYou win!\n\n" );
        } else {
            displayFigure( *wrongGuesses );
            playGame( solvedWord, gameWord, wordLen, remLetters, wrongGuesses );
        }
    }
    
}

/**
 *  This function sets up the new game with a new word and resets the remaining letters array
 *  as well as all the other variables that are used throughout the game
 */
void newGame ()
{
    //Gets a random word and sets it to the solvedWord pointer then finds its length
    //and creates the gameWord of '_' characters
    int index = rand() % wordCount;
    char *solvedWord = words[ index ];
    int wordLen = strlen( solvedWord );
    char gameWord[ wordLen + 1 ];
    for ( int i = 0; i < wordLen; i++ ) {
        gameWord[ i ] = '_';
    }
    gameWord[ wordLen ] = '\0';
   //makes the array to hold the letters remaining for the user to guess
    char remLetters[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    int wrongGuesses = 0;
    printf( "\n" );
    //starts the game
    playGame( solvedWord, gameWord, wordLen, remLetters, &wrongGuesses );

    //asks the user to play again and calls a newGame if the user response with a yes
    printf( "Play again(y,n)> " );
    char in;
    char test;
    scanf( "%1c", &in );
    test = getc( stdin );
    if ( test != '\n' ) {
        ungetc( test, stdin );
        scanf( "%*[^\n]%*1c" );
    }
    if ( in == 'y' || in == 'Y' ) {
        newGame();
    }
}

/**
 *  The start of the hangman program that gets the arguments from the command line and sets up
 *  the game environment
 *  @param argc the number of command line arguments given by the user
 *  @param *argv the array of the command line arguments given
 *  @return exits with a successful exit status
 */
int main ( int argc, char *argv[] )
{
    if ( argc < 2 || argc > SEED_COMMAND ) {
        fprintf( stderr, "usage: hangman <word-file> [seed]\n" );
        exit( 1 );
    }
    int seed = time( NULL );
    if ( argc == SEED_COMMAND ) {
        seed = atoi( argv[ SEED_INDEX ] );
        if ( seed < 0 ){
            fprintf( stderr, "usage: hangman <word-file> [seed]\n" );
            exit( 1 );
        }
    }
    srand( seed );
    readWords( argv[ 1 ] );

    newGame( );

    return EXIT_SUCCESS;
}
