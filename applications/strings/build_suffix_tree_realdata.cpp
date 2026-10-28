#include "tree.hpp"
#include <string>
using namespace std;

void build_a_tree() {
    string nodes[1000];
    string edges[1000][3];
    int nnode;
    int nedge;

    string str1 = "xabxac";

    initialize_tree("root", nodes, nnode, edges, nedge);
    new_node("xa", "root", nodes, nnode, edges, nedge);
    new_node("bxac;1", "xa", nodes, nnode, edges, nedge);
    new_node("a", "root", nodes, nnode, edges, nedge);
    new_node("bxac;2", "a", nodes, nnode, edges, nedge);
    new_node("c;5", "a", nodes, nnode, edges, nedge);
    new_node("c;6", "root", nodes, nnode, edges, nedge);
    new_node("bxac;3", "root", nodes, nnode, edges, nedge);
}

int main() {
    build_a_tree();
    return 0;
}