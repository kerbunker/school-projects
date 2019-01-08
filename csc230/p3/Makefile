CC = gcc
CFLAGS = -Wall -std=c99

hangman: display.o wordlist.o

hangman.o: display.h wordlist.h
display.o: display.h
wordlist.o: wordlist.h

clean:
	rm -f hangman.o display.o wordlist.o
	rm -f hangman