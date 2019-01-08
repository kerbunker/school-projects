/**
    @file decode.c
    @author Katelyn Bunker (kbunker)
    
    The main file of the decode program used to read coded binary data from a file
    and decode it into text and print it to a text file.
*/

#include <stdio.h>
#include <stdlib.h>
#include "codes.h"
#include "bits.h"

/** The max number of bits in a bitcode */
#define CODE_LEN 12
/** The number of command line arguments needed for the program to run */
#define COMM_ARGS 4
/** The index for the codes file in the command arguments */
#define CODES_INDEX 1
/** The index for the input file in the command arguments */
#define INPUT_INDEX 2
/** The index for the output file in the command arguments */
#define OUTPUT_INDEX 3

/**
    Decodes the input file by reading each bit and comparing the collected bitcode to the codes
    array until it matches a character, then prints the character to the output file.
    @param input the input stream to read the binary data from
    @param output the output stream to write the decoded text to
    @return true if the file is decoded and false if any errors occur
*/
bool decodeFile( FILE *input, FILE *output )
{
    char code[ CODE_LEN + 1 ];
    int i = 0;
    char sym;
    //creates the bitcode buffer to use while decoding the program
    BitBuffer *buffer = ( BitBuffer * )malloc( sizeof( BitBuffer ) );
    buffer->bcount = 0;
    buffer->bits = 0x00;
    //reads in the bits and compares them to the codes array until the EOF symbol is found
    while ( sym != EOF && i < CODE_LEN + 1 ) {
        //reads the character for the bits
        code[ i ] = readBit( buffer, input );
        code[ i + 1 ] = '\0';
        i++;
        //compares the collected bitcode to the codes array
        if ( ( sym = codeToSym( code ) ) > -1 ) {
            //if the bitcode matches a symbol, it prints the symbol
            fprintf( output, "%c", sym );
            i = 0;
        }
    }
    //if a code longer than 12 is read in and EOF was not found, returns false to show that an
    //error was found
    if ( i == CODE_LEN + 1 && sym != EOF ) {
        free( buffer );
        fprintf( stderr, "Invalid input file\n" );
        return false;
    }
    //frees the buffer
    free( buffer );
    return true;
    
}

/**
    Starts the program to decode a binary file. Reads in the given codes, input, and output
    streams and checks their validity then reads the codes file and decodes the input file
    before closing all the streams
    @param argc the number of command line arguments read in
    @param argv the array of command line arguments
    @return 0 if the program was successful
*/
int main( int argc, char *argv[] )
{
    //checks that the correct number of command line arguments were read in
    if ( argc != COMM_ARGS ) {
        fprintf( stderr, "usage: encode <codes-file> <infile> <outfile>\n" );
        exit( 1 );
    }
    
    // opens up each file stream
    FILE *codes = fopen( argv[ CODES_INDEX ], "r" );
    FILE *input = fopen( argv[ INPUT_INDEX ], "rb" );
    FILE *output = fopen( argv[ OUTPUT_INDEX ], "w" );
    
    //checks that each file stream was opened successfully
    if ( !codes ) {
        fprintf( stderr, "%s: No such file or directory\n", argv[ CODES_INDEX ] );
        if ( input )
            fclose( input );
        if ( output )
            fclose( output );
        exit( 1 );
    }
    if ( !input ) {
        fprintf( stderr, "%s: No such file or directory\n", argv[ INPUT_INDEX ] );
        if ( codes )
            fclose( codes );
        if ( output )
            fclose( output );
        exit( 1 );
    }
    if ( !output ) {
        fprintf( stderr, "%s: No such file or directory\n", argv[ OUTPUT_INDEX ] );
        if ( codes )
            fclose( codes );
        if ( input )
            fclose( input );
        exit( 1 );
    }
    //reads the codes file to create the codes array
    if ( !readCodeFile( codes ) ) {
        fprintf( stderr, "Invalid code file\n" );
        fclose( codes );
        fclose( input );
        fclose( output );
        exit( 1 );
    }
    //reads in the input file and decodes it
    if ( !decodeFile( input, output ) ) {
        freeCodeStr();
        fclose( codes );
        fclose( input );
        fclose( output );
        exit( 1 );
    }
    
    //frees and closes all open memory and ends the program
    freeCodeStr();
    fclose( codes );
    fclose( input );
    fclose( output );
    return EXIT_SUCCESS;
    

}
