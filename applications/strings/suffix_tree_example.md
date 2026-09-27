
# Suffix-Tree Ex1
String=xabxac
Root---(xa)---Leaf---(bxac)---Leaf(1)
   |           |
   |           |----(c)---Leaf(4)
   |---(a)---Leaf---(bxac)---Leaf(2)
   |           |
   |           |---(c)---Leaf(5)
   |---(c)---Leaf(6)
   |
   |---(bxac)---Leaf(3)

Note:
S[1..6] = xabxac = Label-Path(Leaf(1)) 
S[4..6] = xac = Label-Path(Leaf(4))
S[2..6] = abxac = Label-Path(Leaf(2))
S[5..6] = ac = Label-Path(Leaf(5))
S[6..6] = c = Label-Path(Leaf(6))
S[3..6] = bxac = Label-Path(Leaf(3))

# Implicit Suffix Tree Ex1
Get Implicit suffix tree from Suffix tree

Construct Suffix-Tree
String=xabxa$
Root---(xa)---Leaf---(bxa$)---Leaf(1)
   |           |
   |           |---($)---Leaf(4)
   |---(a)---Leaf---(bxa$)---Leaf(2)
   |           |
   |           |---($)---Leaf(5)
   |---($)---Leaf(6)
   |
   |---(bxa$)---Leaf(3)

Construct Implcit Suffix tree from The suffix Tree
    (step-1) Removing every copy of the terminal symbol $ from the edge labels of the Tree
    (step-2) After step1 finished. Removing any edge that has no label; removing any node that does not have at least two children.

(Step-1) Removing every copy of the terminal symbol $. The new Tree is:
    Root---(xa)---Leaf---(bxa)---Leaf(1)
        |           |
        |           |---()---Leaf(4)
        |---(a)---Leaf---(bxa)---Leaf(2)
        |           |
        |           |---()---Leaf(5)
        |---()---Leaf(6)
        |
        |---(bxa)---Leaf(3)
(Step-2) Removinng edges & nodes. Got Implicit suffix Tree
Root---(xabxa)---Leaf(1)
    |           
    |---(abxa)---Leaf(2)
    |           
    |---(bxa)---Leaf(3)
