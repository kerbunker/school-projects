/**
 *  @file display.h
 *  @author Katelyn Bunker (kbunker)
 *  This file is the header file for the display.c file. This helps to print out the
 *  game word for the hangman game and also displays the hangman character when wrong
 *  letters have been quessed.
 */
 
/**
 *  This function prints out the word that is used in the game
 *  @param word[] the character array that holds the game word
 */
void displayWord( char word[] );

/**
 *  Displays the hangman figure during the game when incorrect letters have been quessed
 *  @param int number of wrong guesses
 */
void displayFigure( int );
