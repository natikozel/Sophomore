#include <stdio.h>
#include "GeneralFunctions.h"
#include <ctype.h>
#include "Q3.h"

void Q3() // Main function - doTickets
{
    int festivalMat[DAYS] = {0}, min, minDay, max, maxDay, days, arrivalDay, amountOfTickets;
    char answer = 0;
    printf("How many days does the Festival last? (10 - 18)\n");
    do {
        scanf("%d", &days);
        if (days > 18 || days < 10)
            printf("Stick to the requested day range!\nTry again.\n");
    } while (days > 18 || days < 10);

    do {
        if (getDayAndTickets(festivalMat, &arrivalDay, &amountOfTickets) == 1)
            festivalMat[arrivalDay] = amountOfTickets;
        printf("Do you want to buy more tickets? Y/N\n");
        do {
            scanf("%c", &answer);
        } while (isspace(answer));
    } while (answer == 'Y' || answer == 'y');
    findMaxMinArr((int *) festivalMat, &min, &max, &minDay, &maxDay, sizeof(festivalMat) / sizeof(festivalMat[0]));
    printf("Highest profitable day: Day %d with %d tickets sold\nLowest profitable day: Day %d with %d tickets sold\n\n ", maxDay, max, minDay, min);
}


void findMaxMinArr(int *arr, int *min, int *max, int *minDay, int *maxDay, int size) 
{
    *max = *arr;
    *min = TICKETS;
    for (int i = 0; i < size; i++, arr++) {
        if (*arr > *max) {
            *max = *arr;
            *maxDay = i;
        } if (*arr < *min && *arr != 0) {
            *min = *arr;
            *minDay = i;
        }
    }
}

int getDayAndTickets(int *mat, int *arrivalDay, int *amountOfTickets) 
{
    printf("Enter the day you want to buy tickets for or enter 0 if you don't.\n");
    do {
        scanf("%d", arrivalDay);
        if (*arrivalDay < 0 || *arrivalDay > 18)
            printf("Day chosen is past the day range, try again.\n");
        else if (*arrivalDay == 0)
            return 0;
    } while (*arrivalDay < 0 || *arrivalDay > 18);
    printf("Enter the amount of tickets you want to buy for that day\n");
    do {
        scanf("%d", amountOfTickets);
        if (TICKETS - *(mat + *arrivalDay) < *amountOfTickets)
            printf("There are only %d tickets left for the day you chose, adjust your decisions.\n",
                   TICKETS - *(mat + *arrivalDay));
        else if (*amountOfTickets > TICKETS)
            printf("There are only %d tickets, choose equal or lower to that.\n", TICKETS);
        else if (*amountOfTickets < 0)
            printf("You can't buy a negative amount, Try again!\n");
    } while (*amountOfTickets > TICKETS || *amountOfTickets < 0);
    return 1;
}