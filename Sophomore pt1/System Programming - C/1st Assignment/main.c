#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#include "Q1.h"
#include "Q2.h"
#include "Q3.h"


void main() {
    srand(time(NULL));
    char choice;
    do {
        printf("Pick a question (0 to exit).\n\n1/t/T - Transpose Matrix\n2/s/S - Search Matrix\n3/f/F - Festival Tickets\n\n");
        do {
            scanf("%1c", &choice);
        }while (isspace(choice));
        switch (choice) {
            case '1':
            case 't':
            case 'T':
                Q1();
                break;
            case '2':
            case 's':
            case 'S':
                Q2();
                break;
            case '3':
            case 'f':
            case 'F':
                Q3();
                break;
            case '0':
                printf("Goodbye!\n");
                break;
            default:
                printf("Incorrect choice entered, try again.\n");
        }
    } while (choice != 48);
}