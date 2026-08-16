#include <stdio.h>
#include <stdlib.h>

int main() {
    const char* szAuthor[] = {
        "A", 
        "M"
    };

    // 
    const char* azBioMR[][2] = {
        { "Time", "1941-" },
        { "Location", "Pittsburgh Pennsylvania-US" },
        { "", "" },
        { "", "" },
        { "", "" },
        { "", "" },
        { "", "" }
    };

    const char* azBioPR[][2] = {
        { "Time", "1944-" },
        { "Location", "Melbourne Australlia" },
        { "", "" },
        { "", "" },
        { "", "" },
        { "", "" },
        { "", "" }

    }; // 

    int n = sizeof(szAuthor) / sizeof(szAuthor[0]);
    int n1 = sizeof(azBioMR) / sizeof(azBioMR[0]);
    int n2 = sizeof(azBioPR) / sizeof(azBioPR[0]);
    printf("Array Length=%d; %d; %d\r\n", n, n1, n2);

    return 0;
}