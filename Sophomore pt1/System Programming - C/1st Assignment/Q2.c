#include <stdio.h>
#include "Q2.h"
#include "GeneralFunctions.h"

void Q2()// N:M = 2:3 but code should support any dimension.
{ 
    int A[N][M] = {0};
    int B[N][M] = {0};
    int C[N][M] = {0};
    randomizeMat((int*) A, N, M);
    randomizeMat((int*) B, N, M);
    printf("Matrix A:\n");
    printMat((int *) A, N, M);
    printf("\nMatrix B:\n");
    printMat((int *) B, N, M);
    search((int *) A, (int *) B, (int *) C);
    printf("\nMatrix C:\n");
    printMat((int *) C, N, M);
}

void search(int *A, int *B, int *C) 
{
    for (int i = 0; i < N * M; i++, A++, B++, C++) {
        if (*A == *B)
            *C = *A;
        else
            *C = -1;
    }
}
