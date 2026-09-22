
# BASIC DEFINITION
SuffixTree(S[1..m]): Rooted Directed Tree
Exactly m-leaves: numbered 1 to m
Each Internal node (not root):  At least two children
Each edge: labeled with nonempty substring of S.
No two edges of a node can have edge-labels beginning with the same character.

Define. Path-Label
Define. String-depth

Key-features: Any leaf i; the concatenation of the edge-labels on the path from the root to the leaf exactly spells out the suffix of S starting at position i. That is it spells out S[i..m]


# CONSTRUCT SUFFIX TREE

## Suffix Extension Rule

S[j..i]=β:a suffix of S[1..i]

Rule 1. In current tree, path β ends at a leaf. Path β meaning: the path from the root labeled β.
Rule 2. No path from the end of string β starts with character S(i+1), but at least one labeled path continues from the end of β.
Rule 3. Some path from the end of string β starts with character S(i+1).