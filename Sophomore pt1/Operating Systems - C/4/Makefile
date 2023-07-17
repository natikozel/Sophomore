CC=gcc
RM=rm
CFLAGS=-c -Wall -O3
LDFLAGS=-lm
SOURCES=$(wildcard *.c)
OBJECTS=$(SOURCES:.c=.o)
EXECS=$(SOURCES:%.c=%)
.PHONY: all
all: $(OBJECTS) $(EXECS)

primecount:
	gcc -g -pthread primecount.c -o primecount -lm

clean:
	-@ $(RM) *.o 
	-@ $(RM) $(EXECS)