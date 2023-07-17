#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include <errno.h>
#include <pthread.h>

typedef struct {
    int start;
    int end;
    pthread_mutex_t* mutex;
} ThreadArgs;

void parseargs(char* argv[], int argc, int* lval, int* uval, int* nval, int* tval);
int isprime(int n);
void* checkPrimes(void* arg);

int count = 0;
char* flagarr= NULL;
int lval = 1;
pthread_mutex_t floatLock;


int main(int argc, char** argv)
{
    int uval = 100;
    int nval = 10;
    int tval = 4;
    int num;
    // Parse arguments
    parseargs(argv, argc, &lval, &uval, &nval, &tval);
    if (uval < lval)
    {
        fprintf(stderr, "Upper bound should not be smaller then lower bound\n");
        exit(1);
    }
    if (lval < 2)
    {
        lval = 2;
        uval = (uval > 1) ? uval : 1;
    }

    // Allocate flags
    flagarr = (char*)malloc(sizeof(char) * (uval - lval + 1));
    if (flagarr == NULL)
        exit(1);

    pthread_t threads[tval];
    pthread_mutex_t mutex;
    pthread_mutex_init(&floatLock, NULL);
    pthread_mutex_init(&mutex, NULL);
    ThreadArgs* threadArgs = (ThreadArgs*)malloc(sizeof(ThreadArgs));
        threadArgs->start = lval;
        threadArgs->end = uval;
        threadArgs->mutex = &mutex;

    // Create threads
    for (int i = 0; i < tval; i++) {
        pthread_create(&threads[i], NULL, checkPrimes, (void*)threadArgs);
    }

    // Wait for threads to finish
    for (int i = 0; i < tval; i++) {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&mutex);
    free(threadArgs);
    // Set flagarr
//    for (num = lval; num <= uval; num++)
//    {
//        if (isprime(num))
//        {
//            flagarr[num - lval] = 1;
//            count++;
//        }
//        else {
//            flagarr[num - lval] = 0;
//       }
//    }

    // Print results
    printf("Found %d primes%c\n", count, count ? ':' : '.');
    for (num = lval; num <= uval; num++)
        if (flagarr[num - lval])
        {
            if (nval > 0) {
                printf("%d%c", num, nval > 1 ? ',' : '\n');
                nval--;
            }
        }
    free (flagarr);
    return 0;
}

// NOTE : use 'man 3 getopt' to learn about getopt(), opterr, optarg and optopt 
void parseargs(char* argv[], int argc, int* lval, int* uval, int* nval, int* tval)
{
    int ch;

    opterr = 0;
    while ((ch = getopt(argc, argv, "l:u:n:t:")) != -1)
        switch (ch)
        {
        case 'l':  // Lower bound flag
            *lval = atoi(optarg);
            break;
        case 'u':  // Upper bound flag 
            *uval = atoi(optarg);
            break;
        case 'n':
            *nval = atoi(optarg);
            break;
        case 't':
            *tval = atoi(optarg);
            break;
        case '?':
            if ((optopt == 'l') || (optopt == 'u'))
                fprintf(stderr, "Option -%c requires an argument.\n", optopt);
            else if (isprint(optopt))
                fprintf(stderr, "Unknown option `-%c'.\n", optopt);
            else
                fprintf(stderr, "Unknown option character `\\x%x'.\n", optopt);
            exit(1);
        default:
            exit(1);
        }
}

int isprime(int n)
{
    
    static int* primes = NULL; 	// NOTE: static !
    static int size = 0;		// NOTE: static !
    static int maxprime;		// NOTE: static !
    int root;
    int i;

    // Init primes array (executed on first call)
    pthread_mutex_lock(&floatLock);
    if (primes == NULL)
    {
        primes = (int*)malloc(2 * sizeof(int));
        if (primes == NULL)
            exit(1);
        size = 2;
        primes[0] = 2;
        primes[1] = 3;
        maxprime = 3;
    }
    pthread_mutex_unlock(&floatLock);
    root = (int)(sqrt(n));

    // Update primes array, if needed
    while (root > maxprime)
        for (i = maxprime + 2; ; i += 2) {
            if (isprime(i))
            {
                pthread_mutex_lock(&floatLock);
                size++;
                primes = (int*)realloc(primes, size * sizeof(int));
                if (primes == NULL)
                    exit(1);
                primes[size - 1] = i;
                maxprime = i;
                pthread_mutex_unlock(&floatLock);
                break;
            }
        }

    // Check 'special' cases
    
    if (n <= 0)
        return -1;
    if (n == 1)
        return 0;

    // Check prime
    pthread_mutex_lock(&floatLock);
    for (i = 0; ((i < size) && (root >= primes[i])); i++) {
        if ((n % primes[i]) == 0) {
            pthread_mutex_unlock(&floatLock);
            return 0;
        }
    }
    pthread_mutex_unlock(&floatLock);
    return 1;

}

// Thread function to check prime numbers
void* checkPrimes(void* arg) {

    ThreadArgs* threadArgs = (ThreadArgs*)arg;
    int begin = lval;
    int myNum = 0;

    while (!(threadArgs->start > threadArgs->end)) {
        pthread_mutex_lock(threadArgs->mutex);
        if (threadArgs->start <= threadArgs->end) 
            myNum = threadArgs->start++;
        
        pthread_mutex_unlock(threadArgs->mutex);
            if (isprime(myNum)) {
                flagarr[myNum - begin] = 1;
                count++;
            } else
                flagarr[myNum - begin] = 0;
    }
    pthread_exit(NULL);
}