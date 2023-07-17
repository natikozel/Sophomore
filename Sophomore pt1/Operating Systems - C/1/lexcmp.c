#include <string.h>
#include <stdio.h>

int mystrcmp(const char *str1, const char *str2) {
    int val = strcmp(str1, str2);
    if (val < 0)
        return 1;
    if (val > 0)
        return 2;
    return 0;

}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        return -1;
    }
    return mystrcmp(argv[1], argv[2]);
}