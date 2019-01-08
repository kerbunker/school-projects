CC = gcc
CFLAGS = -Wall -std=c99 -D_GNU_SOURCE
LDLIBS = -lm

attractions: point.o pointlist.o

attractions.o: point.h pointlist.h
point.o: point.h
pointlist.o: pointlist.h point.h
