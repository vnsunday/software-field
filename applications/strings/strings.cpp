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
        { "Organizations", "Future Concept Division" },
        { "", "" }
    }; // 

    const char* azBioPR[][2] = {
        { "Time", "1944-" },
        { "Location", "Melbourne Australlia" }

    }; // 

    const char* azAA[][2] = {
        { "Time", "1948" },
        { "Native", "Italy" },
        { "Location", "Naples" },
        { "Location", "Salerno" },
        { "Location", "Atlanta, US" }
    };

    const char* azRG[][2] = {
        { "Location", "Palermo, Italy" }
    };

    //  Seminumerical 
    const char* azRBY[][2] = {
        { "Time", "1948"},
        { "Location", "Santiago, Chile" }, 
        { "Organization", "ACM Fellow" },
        { "Organization", "IEEE" },
    };
    const char* azGG[][2] = {
        { "", "" }
    };

    int n = sizeof(szAuthor) / sizeof(szAuthor[0]);
    int n1 = sizeof(azBioMR) / sizeof(azBioMR[0]);
    int n2 = sizeof(azBioPR) / sizeof(azBioPR[0]);
    printf("Array Length=%d; %d; %d\r\n", n, n1, n2);
    return 0;
}