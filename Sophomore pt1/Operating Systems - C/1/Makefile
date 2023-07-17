CC=gcc
RM=rm
CFLAGS=-c -Wall -O3
LDFLAGS=-lm
SOURCES=$(wildcard *.c)
OBJECTS=$(SOURCES:.c=.o)
EXECS=$(SOURCES:%.c=%)
.PHONY: all
all: $(OBJECTS) $(EXECS)

.c.o:
	$(CC) $(CFLAGS) $< -o $@

.o.: 
	$(CC) $^ $(LDFLAGS) -o $@

.PHONY: clean
clean:
	-@ $(RM) *.o 
	-@ $(RM) $(EXECS)