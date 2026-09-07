#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <string> 

using namespace std;

int main()
{
    string str = "abcdeaabd";
    int nN = str.length() + 1; // Root + Leaves

    // Tree structure
    int nR; // Root 
    int edge_l[1000];
    int edge_r[1000];
    string edge_lb[1000]; // Edge label
    int ned = 0; // edges count

    int* ptr = std::upper_bound(edge_l, edge_l + 10, nR);
    int* ptr1 = NULL;
    return 0;
}
