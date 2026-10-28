#ifndef _SOFTWAREFIELD_APPLICATIONS_TREE_HPP_
#define _SOFTWAREFIELD_APPLICATIONS_TREE_HPP_

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <algorithm>
#include <string>
#include <string.h>
#include <vector>

struct CompareEdge {
    bool operator()(const std::string val[3], const std::string val2[3]) const {
        return val[0] < val2[0];
    }
};

inline void initialize_tree(std::string root_node, std::string nodes[], int& nnode, std::string edges[][3], int& nedge) {
    nodes[0] = root_node;
    nnode = 1;
    nedge = 0;
}

inline std::string new_node(std::string node_code, std::string parent_node, std::string nodes[], int& nnode, std::string edges[][3], int& nedge) {

    // Already existed
    if (std::binary_search(nodes, nodes + nnode, node_code) || 
        !std::binary_search(nodes, nodes + nnode, parent_node)) {
        // 
        return "Invalid node code";                        
    }

    std::string* pupper;
    // Upperbound:
    // Return value:
    //    The position 
    // Insert 
    pupper = std::upper_bound(nodes, nodes + nnode, node_code);
    std::move_backward(pupper, nodes + nnode, nodes + nnode + 1);
    (*pupper) = node_code;
    nnode++;

    // Adding edges (parent_node => node_code)
    std::string (*pupedg)[3]; // 
    std::string newedge[3] { parent_node, node_code, "" };
    
    // pupedg = upper_bound()
    // Move back 
    pupedg = upper_bound(
        edges,
        edges + nedge,
        newedge,
        CompareEdge()
    );
    // Moving backward
    std::string (*it)[3]; // 
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

inline std::string parent_node_of(std::string node, std::string nodes[], int& nnode, std::string edges[][3], int& nedge, std::string& parent_node, std::string edge_detail[]) {
    // Find all parent nodes of a specified nodes
    for (int i=0; i<nedge;i++) {
        if (edges[i][2] == node) {
            parent_node = edges[i][1];

            edge_detail[0] = edges[i][0];
            edge_detail[1] = edges[i][1];
            edge_detail[2] = edges[i][2];
            return ""; // Success
        }
    }
    return "Not Found";
}

inline std::string children_nodes_of(std::string node, std::string nodes[], int& nnode, std::string edges[][3], int& nedge, std::vector<std::string>& children) {
    children.clear();
    for (int i=0; i<nedge; i++) {
        if (edges[i][1] == node) {
            children.push_back(edges[i][2]);
        }
    }
    return "";
}

#endif