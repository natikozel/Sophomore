#include <string.h>
#include <stdio.h>

int digcmp(const char *str1, const char *str2) {
    int c1 = 0, c2 = 0;
    for (int i = 0; i < strlen(str1); i++) {
        if (str1[i] >= 48 && str1[i] <= 57)
            c1++;
    }
    for (int i = 0; i < strlen(str2); i++) {
        if (str2[i] >= 48 && str2[i] <= 57)
            c2++;
    }
    if (c1 > c2)
        return 1;
    else if (c2 > c1)
        return 2;
    else
        return 0;
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        return -1;
    }
    return digcmp(argv[1], argv[2]);
}