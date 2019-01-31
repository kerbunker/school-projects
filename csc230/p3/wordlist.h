/**
 *  @file wordlist.h
 *  @author Katelyn Bunker (Kbunker)
 *  This file is the header file for the wordlist.c which reads the words from the given filename
 *  to use for the hangman game.
 */

/** The max umber of words that the words array can store */
#define MAX_WORDS 50
/** The max length the word in the words array can be */
#define MAX_LENGTH 20

/** Global variable array to hold the words read from the given file */
extern char words[ MAX_WORDS ][ MAX_LENGTH + 1 ];
//** Global variable to hold the number of words in the words array */
extern int wordCount;

/**
 *  This function takes in the filename paramter and reads the words in from the file
 *  and adds them to the words global variable array while checking the validity.
 *  @param *filenam a pointer to the filename to open to read the game words
 */
void readWords( char const *filename );
