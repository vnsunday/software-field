#include <cstdlib>
#include <ctime>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>

using namespace std;

/*============================================================
    bEntire = true: accept a suffix equals the Entire string 
 *============================================================*/
std::string a_suffix(std::string str, bool bEntire = false) {
    int nL = str.length();

    if (nL > 0) {
        int nS = std::rand() % (nL);
        return str.substr(nL - nS - 1);
    }
    else {
        return string("");
    }
}

/*============================================================
    Generating a semi-periodic string from a period
 *============================================================*/
std::string generate_semiperiodic(const char* period) {
    std::string ssf = a_suffix(period, true); // Suffix or Entire string
    int ncopy = 1 + std::rand() % 5;

    std::string str = ssf;
    for (int i=0; i<ncopy;i++) {
        str += period;
    }

    return str;
}

/*============================================================
    Generating a periodic string from a period
 *============================================================*/
 std::string generate_periodic(const char* period) {
int ncopy = 2 + std::rand() % 5;

    std::string str = "";
    for (int i=0; i<ncopy;i++) {
        str += period;
    }

    return str;
 }

int main() {
    std::srand(std::time(NULL)); // Random seed

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


    // Periodic testing 
    std::string period = "abc";
    std::string strprd = generate_periodic("abc");
    std::string strsmprd = generate_semiperiodic("abc");

    printf("Semi periodic of %s: %s\r\n", period.c_str(), strsmprd.c_str());
    printf("Periodic of %s: %s\r\n", period.c_str(), strprd.c_str());

    return 0;
}