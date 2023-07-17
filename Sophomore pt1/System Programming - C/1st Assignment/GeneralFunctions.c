#include <stdio.h>
#include <stdlib.h>
#include "Q1.h"
#include "Q2.h"
#include "Q3.h"


void randomizeMat(int *mat, int rows, int cols) 
{
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            *(mat + i * cols + j) = rand() % 10;
        }
    }
}

void printMat(int *mat, int rows, int cols) 
{
    for (int i = 1; i <= rows * cols; i++, mat++) {
        printf("%-4d", *mat);
        if (i % cols == 0)
            printf("\n");
    }
}


void shrinkMat(int *mat, int *newMat, int rows, int cols) 
{
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++)
            *(newMat + i * cols + j) = *(mat + N * i + j);
    }
}

