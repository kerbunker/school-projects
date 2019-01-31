CC = gcc
CFLAGS = -Wall -std=c99 -g

regular: parse.o pattern.o

regular.o: parse.h pattern.h
parse.o: parse.h
pattern.o: pattern.h

clean:
	rm -f regular.o parse.o pattern.o
	rm -f regular
	rm -f output.txt
