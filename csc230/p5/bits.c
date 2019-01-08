/**
    @file bits.c
    @author Katelyn Bunker (kbunker)
    
    This file handles the reading and writing of the bitcodes from the input file and to the
    output file while the files are encoded and decoded
*/

#include "bits.h"
#include <stdio.h>
#include <stdlib.h>

/** The max number of bits that can be in the buffer */
#define MAX_BITS 8
/** The mask to find if there is a 1 in the high order bit of the buffer */
#define HIGH_ONE 0x80


void writeBits( const char *code, BitBuffer *buffer, FILE *fp )
{
    int i = 0;
    //while the null character has not been reached in the code string the bits are added
    //to the buffer and moved to the left one for the next bit to be added.
    while ( code[ i ] != '\0' ) {
        if ( buffer->bcount > 0 ) {
            ( buffer->bits ) = ( buffer->bits ) << 1;
        }
        if ( code[ i ] == '0' ) {
            ( buffer->bits ) |= 0x00;
        } else {
            ( buffer->bits ) |= 0x01;
        }
        ( buffer->bcount )++;
        i++;
        
        //when the buffer reaches 8 bits the bitcode is printed to the output file
        //then the buffer is cleared out for the next bits to be added
        if ( buffer->bcount == MAX_BITS ) {
            fprintf( fp, "%c", (buffer->bits) );
            buffer->bcount = 0;
            ( buffer->bits ) = 0x00;
        }
    }
}

void flushBits( BitBuffer *buffer, FILE *fp )
{
    //while the buffer has fewer than 8 bits the bits are moved to the left and 0s are added
    while ( buffer->bcount < MAX_BITS ) {
        ( buffer->bits ) = ( buffer->bits ) << 1;
        ( buffer->bcount )++;
    }
    
    //prints the bitcode given in the buffer to the output file
    fprintf( fp, "%c", buffer->bits );
    buffer->bcount = 0;
    ( buffer->bits ) = 0x00;
}

int readBit( BitBuffer *buffer, FILE *fp )
{
    unsigned char val = HIGH_ONE;
    int ch;
    int ret;
    //if the buffer is empty, the next 8 bits are read from the file to fill the buffer
    //unless the EOF is reached
    if ( buffer->bcount == 0 ) {
        ch = getc( fp );
        if ( ch == EOF ) {
            return -1;
        } else {
            buffer->bits = ch;
            buffer->bcount = MAX_BITS;
        }
    }
    //checks whether the highest order bit is a 1 or 0 then returns the corresponding character
    val = val & ( buffer->bits );
    if ( val ) {
        ret = '1';
    } else {
        ret = '0';
    }
    //moves the buffers to the left so the next bit is the highest order bit to be read next
    ( buffer->bits ) = ( buffer->bits ) << 1;
    ( buffer->bcount )--;
    
    return ret;
}
