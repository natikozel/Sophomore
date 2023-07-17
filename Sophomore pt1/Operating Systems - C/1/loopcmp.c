#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#define LINELEN (80)

char* mygets(char* buf, int len);

int main(int argc, char* argv[])
{
	char str1[LINELEN + 1];
	char str2[LINELEN + 1];
	char func[LINELEN + 1] = "./";
	strcat(func, argv[1]);

	if (argc != 2)
		return -1;

	while (1)
	{
		if (mygets(str1, LINELEN) == NULL)
			break;
		if (mygets(str2, LINELEN) == NULL)
			break;
		char* arg1 = (char*)malloc(sizeof(strlen(str1)+1));
		char* arg2 = (char*)malloc(sizeof(strlen(str2)+1));
		strcpy(arg1, str1);
		strcpy(arg2, str2);

		int pid = fork();
		int status = 0;
		if (pid == 0) {
			char* arg[] = { func, arg1, arg2, NULL };
			if (execvp(arg[0], arg))
				exit(1);
			else {
				free(arg1);
				free(arg2);
			}
		}
		else {
			waitpid(pid, &status, 0);
			int exit_status = 0;
			if (WIFEXITED(status)) {
				exit_status = WEXITSTATUS(status);
			}
			else {
				exit_status = -2;
			}
			printf("Exit status of the child was %d\n", exit_status);
		}
			fflush(stdout);
		}
		return 0;
	}

	char* mygets(char* buf, int len)
	{
		char* retval;

		retval = fgets(buf, len, stdin);
		buf[len] = '\0';
		if (buf[strlen(buf) - 1] == 10) /* trim \r */
			buf[strlen(buf) - 1] = '\0';
		else if (retval) while (getchar() != '\n'); /* get to eol */

		return retval;
	}
