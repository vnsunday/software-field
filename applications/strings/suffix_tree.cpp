#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <string>
#include <string.h>
#include <vector>

using namespace std;

void add_node(int parentID, int newNodeID) {
}

void construct(const char* S) {
}

void suffix_extension_rule1() {
}

vector<string> define_path() {
    vector<string> vr = { "Root", "Node1", "Node2" };
    return vr;
}

vector<string> label() {
    vector<string> vlb = { "Tree", "Edge", "Value"};
    return vlb;
}

void build_suffix_link() {
    string s1 = "a";
    string s2 = "ab";

    int n1 = 0;
    int n2 = 10;

    int nEdgeSuffix = 0;
    int arr[100][2];
    arr[nEdgeSuffix][0] = n2; // Path (n2,n1)
    arr[nEdgeSuffix][1] = n1; 
}

int main()
{
    string str = "abcdeaabd";
    int nN = str.length() + 1; // Root + Leaves

    // Tree structure
    //    Rooted directed 
    int nR; // Root 
    int edge_l[1000];
    int edge_r[1000];
    string edge_lb[1000]; // Edge label
    int ned = 0; // edges count

    int* ptr = std::upper_bound(edge_l, edge_l + 10, nR);
    int* ptr1 = NULL;
    return 0;
}
