#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <algorithm>
#include <string>
#include <string.h>
#include <vector>

using namespace std;

string nodes[1000]; 
string tree_edge[1000][2];
int nnode;
int nedge;

struct CompareEdge {
    bool operator()(const string val[2], const string val2[2]) const {
        return val[0] < val2[0];
    }
};
// Tree API 
string new_node(string node_code, string parent_node, string nodes[], int& nnode, string edges[][2], int& nedge) {
    // int* ptr = std::upper_bound(edge_l, edge_l + 10, nR);
    // int* ptr1 = NULL;
    // std::upper_bound()

    // Already existed
    if (binary_search( 
                        nodes,
                        nodes + nnode,
                        node_code)) {
        // 
        return "node already existed";                        
    }

    string* pupper;
    if ((pupper = upper_bound(nodes, nodes + nnode, node_code)) != nodes + nnode) {
        // Insert 
        std::move_backward(pupper, nodes + nnode, nodes + nnode + 1);
        (*pupper) = node_code;
        nnode++;
    }

    // Adding edges (parent_node => node_code)
    string (*pupedg)[2]; // 
    // pupedg = upper_bound()
    return ""; // Empty : Success
}

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

void observation_1() {
    // 611
    int jp; // phase index
    int je; // Extension index
    char szBuff[100];
    string path_label_n1("ba");
    string path_label_2("a");

    sprintf(szBuff, "extension %d; phase %d", je, jp);
    //string progress_position(szBuff);
    sprintf(szBuff, "extension %d; phase %d", je + 1, jp + 1);
    //string progress_position(szBuff);
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

    nnode = 0;
    return 0;
}
