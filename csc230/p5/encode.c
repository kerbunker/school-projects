/**
    @file encode.c
    @author Katelyn Bunker (kbunker)
    
    The main file of the encode program used to read text data from a file
    and encode it into the appropriate symbols and print the symbols to a
    binary file.
*/

#include <stdio.h>
#include <stdlib.h>
#include "codes.h"
#include "bits.h"

/** The number of command line arguments needed for the program to run */
#define COMM_ARGS 4
/** The index for the codes file in the command arguments */
#define CODES_INDEX 1
/** The index for the input file in the command arguments */
#define INPUT_INDEX 2
/** The index for the output file in the command arguments */
#define OUTPUT_INDEX 3

/**
    This function creates the bitcode buffer and goes through the input file encoding each
    symbol given then printing the bicodes to the output file
    @param input the input file to read the characters from
    @param output the output file the write the binary data to
    @return true if the file was printed correctly and false if there were any errors
*/
bool encodeFile( FILE *input, FILE *output )
{
    int ch;
    const char *bitcode;
    //creates the buffer to hold the bits while they are being printed
    BitBuffer *buffer = ( BitBuffer * )malloc( sizeof( BitBuffer ) );
    buffer->bcount = 0;
    buffer->bits = 0x00;
    //gets each character in the file and encodes it to the correct bitcode
    while ( ( ch = getc( input ) ) != EOF ) {
        bitcode = symToCode( ch );
        if ( !bitcode ) {
            fprintf( stderr, "Invalid input file\n" );
            free( buffer );
            return false;
        }
        //writes the bits to the output stream
        writeBits( bitcode, buffer, output );
    }
    bitcode = symToCode( ch );
    writeBits( bitcode, buffer, output );
    //after EOF is written, if the line needs to be filled with 0s, this flushes the buffer
    if ( buffer->bcount > 0 ) {
        flushBits( buffer, output );
    }
    //frees the buffer after it is done being used
    free( buffer );
    return true;
}

/**
    Starts the encode program by opening the file streams given by the arg parameters.
    Checks for the validity of the inputs and then runs the program to encode the
    given file using the codes file
    @param argc the number of command line arguments given
    @param argv the pointer to the array of command line arguments
    @return 0 if the program was successful
*/
int main( int argc, char *argv[] )
{
    //checks for the correct command line arguments
    if ( argc != COMM_ARGS ) {
        fprintf( stderr, "usage: encode <codes-file> <infile> <outfile>\n" );
        exit( 1 );
    }
    
    //opens the file streams needed
    FILE *codes = fopen( argv[ CODES_INDEX ], "r" );
    FILE *input = fopen( argv[ INPUT_INDEX ], "r" );
    FILE *output = fopen( argv[ OUTPUT_INDEX ], "wb" );
    
    //checks that the file streams were opened correctly
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
    //sends the code file to be read and the codes array to be made
    if ( !readCodeFile( codes ) ) {
        fprintf( stderr, "Invalid code file\n" );
        fclose( codes );
        fclose( input );
        fclose( output );
        exit( 1 );
    }
    //sends the input file to the read and encoded and printed to the output file
    if ( !encodeFile( input, output ) ) {
        freeCodeStr();
        fclose( codes );
        fclose( input );
        fclose( output );
        exit( 1 );
    }
    
    //frees the codes array and closes the streams before exiting
    freeCodeStr();
    fclose( codes );
    fclose( input );
    fclose( output );
    return EXIT_SUCCESS;
    

}
