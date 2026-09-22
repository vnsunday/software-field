
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

# Implicit Suffix Tree
S=axabx
Root: 