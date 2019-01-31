CC = gcc
CFLAGS = -Wall -std=c99 -g

all: encode decode

encode: codes.o bits.o
decode: codes.o bits.o

encode.o: codes.h bits.h
decode.0: codes.h bits.h
codes.o: codes.h
bits.o: bits.h

clean:
	rm -f encode.o decode.o codes.o bits.o
	rm -f encode decode
	rm -f output.txt output.bin