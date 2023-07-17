CC = gcc
EXEC = nicecmp loopcmp
DEBUG = -g
CFLAGS = -std=c99 -Wall -Werror $(DEBUG)

all: $(EXEC)

nicecmp: nicecmp.c
	$(CC) nicecmp.c -o nicecmp
	
loopcmp: loopcmp.c
	$(CC) loopcmp.c -o loopcmp
	
clean:
	rm -rf $(EXEC)		
	
