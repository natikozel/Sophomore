#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

#define LINELEN (80)

char *mygets(char *buf, int len);
int mygeti();

int main(int argc, char *argv[])
{
	char *cmpstr[] = {"lexcmp", "lencmp"};
	int veclen = sizeof(cmpstr)/sizeof(char *);
	char firstString[LINELEN + 1];
	char secondString[LINELEN + 1];
	int index;
	int fd[2];
	int lenfd[2];   
	int lexfd[2];

    
    if (pipe(fd) == -1)
    	perror("pipe");
    	
    if (pipe(lenfd) == -1)
    	perror("lenpipe");

	if (pipe(lexfd) == -1)
    	perror("lexpipe");
    	
    switch(fork())
	{
		case -1:  
			perror("fork");
	
		case 0:		
			if (close(fd[0]) == -1)
                perror("pipe read close");

            if (close(lenfd[0]) == -1)
                perror("childpipe read close");

			if (close(lenfd[1]) == -1)
                perror("childpipe write close");

			if (close(lexfd[1]) == -1)
                perror("childpipe write close");
		
			if (lexfd[0] != 0)  
			{ 			
				if (dup2(lexfd[0], 0) == -1)
                    perror("dup2");
				if (close(lexfd[0]) == -1)
                    perror("pipe read close");				
			}
		
			if (fd[1] != 1) 
			{	
				if (dup2(fd[1], 1) == -1)      
                    perror("dup2");
				if (close(fd[1]) == -1)       
                    perror("pipe write close");
			}
		
			if (execlp("./loopcmp", "./loopcmp", "lexcmp", NULL))
				perror("execvp");
				exit(EXIT_FAILURE);				
			
		default:
			break;
	}

	switch(fork())
	{
		case -1:  
			perror("fork");
	
		case 0:		
			if (close(fd[0]) == -1)
                perror("pipe read close");

            if (close(lexfd[0]) == -1)
                perror("childpipe read close");

			if (close(lexfd[1]) == -1)
                perror("childpipe write close");

			if (close(lenfd[1]) == -1)
                perror("childpipe write close");
		
			if (lenfd[0] != 0)  
			{ 			
				if (dup2(lenfd[0], 0) == -1)
                    perror("dup2");
				if (close(lenfd[0]) == -1)
                    perror("childpipe read close");				
			}
		
			if (fd[1] != 1) 
			{	
				if (dup2(fd[1], 1) == -1)      
                    perror("dup2");
				if (close(fd[1]) == -1)       
                    perror("pipe write close");
			}
		
			if (execlp("./loopcmp", "./loopcmp", "lencmp", NULL))
				perror("execvp");
				exit(EXIT_FAILURE);				
			
		default:
			break;
	}
	
	if (close(fd[1]) == -1)      
        perror("pipe write close");
        
	if (close(lexfd[0]) == -1)      
        perror("childpipe read close");

	if (close(lenfd[0]) == -1)      
        perror("childpipe read close");
		
        
	while (1) {
        printf("Please enter first string:\n");
		if (mygets(firstString, LINELEN) == NULL)
            break;
        printf("Please enter second string:\n");
        if (mygets(secondString, LINELEN) == NULL)
            break;
		do {
			printf("Please choose:\n");
			for (int i=0 ; i < veclen ; i++)
				printf("%d - %s\n", i, cmpstr[i]);
			index = mygeti();
		} while ((index < 0) || (index >= veclen));
		
		int lenOfFirstString = strlen(firstString) + 1;
        int lenOfSecondString = strlen(secondString) + 1;
		
		char* newString1 = (char*)malloc(strlen((firstString) + 1) * sizeof(char));
        if (!newString1)
            perror("malloc");
            
		char* newString2 = (char*)malloc(strlen((secondString) + 1) * sizeof(char));
        if (!newString2)
            perror("malloc");

		strcpy(newString1, firstString);
        strcpy(newString2, secondString);   
        strcat(newString1, "\n");
        strcat(newString2, "\n");

		if (index == 1) {

			if (write(lenfd[1], newString1, lenOfFirstString) != lenOfFirstString)
				perror("pipe write fail");
                    
			if (write(lenfd[1], newString2, lenOfSecondString) != lenOfSecondString)
        		perror("pipe write fail");
		} else if (index == 0) {

			if (write(lexfd[1], newString1, lenOfFirstString) != lenOfFirstString)
				perror("pipe write fail");
                    
			if (write(lexfd[1], newString2, lenOfSecondString) != lenOfSecondString)
        		perror("pipe write fail");
		}
		else {
			printf("Wrong input");
		}


		free(newString1);
        free(newString2);
		
		char resultFromLoopCmp;
		if (read(fd[0], &resultFromLoopCmp, sizeof(char)) != sizeof(char))
            perror("childpipe read failed");
		
		char emptyBuf;
		if (read(fd[0], &emptyBuf, sizeof(char)) != sizeof(char))
            perror("childpipe read failed");

		int finalResult = (int)(resultFromLoopCmp - '0');

        printf("\n%s(%s, %s) == %d\n\n", cmpstr[index], firstString, secondString, finalResult);
        fflush(stdout);		
	}
	
	if (close(fd[0]) == -1)      
        perror("pipe read close");
        
	if (close(lenfd[1]) == -1)    
        perror("childpipe write close");
        
	if (close(lenfd[1]) == -1)    
        perror("childpipe write close");
        
	exit(EXIT_SUCCESS);
		
}

char *mygets(char *buf, int len)
{
	char *retval;

	retval = fgets(buf, len, stdin);
	buf[len] = '\0';
	if (buf[strlen(buf) - 1] == 10)
		buf[strlen(buf) - 1] = '\0';
	else if (retval) 
		while (getchar() != '\n');

	return retval;
}

int mygeti()
{
	int ch;
	int retval=0;

	while(isspace(ch=getchar()));
	while(isdigit(ch))
	{
		retval = retval * 10 + ch - '0';
		ch = getchar();
	}
	while (ch != '\n')
		ch = getchar();
	return retval;
}
