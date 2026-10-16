#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <algorithm>
#include <string>
#include <string.h>
#include <vector>

using namespace std;

string nodes[1000]; 
string edges[1000][3]; // Node1,Node2,Label
int nnode;
int nedge;

struct CompareEdge {
    bool operator()(const string val[3], const string val2[3]) const {
        return val[0] < val2[0];
    }
};
// Tree API 
void initialize_tree(string root_node, string nodes[], int& nnode, string edges[][3], int& nedge) {
    nodes[0] = root_node;
    nnode = 1;
    nedge = 0;
}

string new_node(string node_code, string parent_node, string nodes[], int& nnode, string edges[][3], int& nedge) {
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
    string (*pupedg)[3]; // 
    string newedge[3] { parent_node, node_code, "" };
    // pupedg = upper_bound()
    // Move back 
    pupedg = upper_bound(
        edges,
        edges + nedge,
        newedge,
        CompareEdge()
    );
    // Moving backward
    string (*it)[3]; // 
    for (it = edges + nedge; it > pupedg; it--) {
        (*it)[0] = (*(it-1))[0];
        (*it)[1] = (*(it-1))[1];
        (*it)[2] = (*(it-1))[2];
    }

    // Assign
    (*pupedg)[0] = newedge[0];
    (*pupedg)[1] = newedge[1];
    (*pupedg)[2] = newedge[2]; 

    return ""; // Empty : Success
}

string parent_node_of(string node, string nodes[], int& nnode, string edges[][3], int& nedge, string& parent_node) {
    // Find all parent nodes of a specified nodes
    
    for (int i=0; i<nedge;i++) {
        if (edges[i][2] == node) {
            parent_node = edges[i][1];
            return ""; // Success
        }
    }
    return "Not Found";
}

string children_nodes_of(string node, string nodes[], int& nnode, string edges[][3], int& nedge, vector<string>& children) {
    children.clear();
    for (int i=0; i<nedge; i++) {
        if (edges[i][1] == node) {
            children.push_back(edges[i][2]);
        }
    }

    return "";
}

string path_label(string leaf_node, string nodes[], int nnode, string edges[][3], int nedge) {
    // Find path label 
    // Spread wider
    int index = atoi(leaf_node.c_str());
    bool bRootReached = false;

    string parentnode;
    string endnode = leaf_node;
    string parent_node;
    vector<string> path;


    while (parent_node_of(endnode, 
                        nodes, nnode,
                        edges, nedge, 
                        parent_node).empty()) {
        // path.push_back();
    }

    return "";
}

string ukkonen(string str1) {
    int m = str1.length();

    for (int i=0; i<m-1; i++) {
        // Phase (i+1)
        for (int j = 0; j < i+1; j++) {
            // 
            string label_find = str1.substr(j, (i-j+1));

            // TODO: find the label here
        }
    }
}

string find_label() {
    // Every
    return "";
}

string suffix_extension(string str1, int j, int i) {
    // [j..i] a suffix of [1..i]
    string beta = str1.substr(j, i - j  + 1);

    // Case 1.
    /*==================================================
        beta ends at a leaf.
            [j..i]
     *==================================================*/


    return "";
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
    string str1("xabxa");
    initialize_tree("<root>", nodes, nnode, edges, nedge);


    nnode = 0;
    return 0;
}
