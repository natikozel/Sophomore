CC = gcc
OBJS = main.o Q1.o Q2.o Q3.o GeneralFunctions.o
EXEC = output
DEBUG_FLAG = # assign -g for debug
COMP_FLAG = -std=c99 -Wall

$(EXEC) : $(OBJS)
	$(CC) $(OBJS) -o $(EXEC)
	
main.o : main.c Q1.h Q2.h Q3.h
	$(CC) -c $*.c
	
Q1.o : Q1.c Q1.h GeneralFunctions.h
	$(CC) -c $*.c
	
Q2.o : Q2.c Q2.h GeneralFunctions.h
	$(CC) -c $*.c

Q3.o : Q3.c Q3.h GeneralFunctions.h
	$(CC) -c $*.c

GeneralFunctions.o : GeneralFunctions.c Q1.h Q2.h Q3.h
	$(CC) -c $*.c	
	
clean:
	rm *.o $(EXEC)
