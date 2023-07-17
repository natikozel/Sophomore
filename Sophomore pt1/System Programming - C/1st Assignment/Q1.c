#include <stdio.h>
#include "Q1.h"
#include "GeneralFunctions.h"

void Q1() 
{
    int rows = 0, cols = 0, mat[P][P], newMat[P][P];
    randomizeMat((int *) mat, P, P);
    while (rows < 1 || rows > P) {
        printf("Please enter the matrix's rows\n");
        scanf("%d", &rows);
    }
    while (cols < 1 || cols > P) {
        printf("Please enter the matrix's columns\n");
        scanf("%d", &cols);
    }
    printf("rows = %d\ncolumns = %d\n\n", rows, cols);
    printf("Matrix A:\n");
    printMat((int *) mat, rows, cols);
    shrinkMat((int *) mat, (int *) newMat, rows, cols);
    transposeMat((int *) mat, (int *) newMat, rows, cols);
    printf("Matrix B (transposed):\n");
    printMat((int *) newMat, cols, rows);

}

void transposeMat(int *mat, int *newMat, int rows, int cols) 
{
    for (int i = 0; i <= P; i++)
        for (int j = 0; j <= P; j++)
            *(newMat + i * cols + j) = *(mat + j * cols + i);

    int *temp = newMat;
    for (int i = 0; i < cols; i++) {
        for (int j = 0; j < rows; j++, newMat++) {
            *newMat = *(temp+ i * cols + j);
        }
    }
}