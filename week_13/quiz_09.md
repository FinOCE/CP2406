# [Quiz on chapter 9](https://math.hws.edu/javanotes/c9/quiz.html)

## Question 1

*Explain what is meant by a **recursive** subroutine.*

A recursive subroutine is a method that will call itself. They contain a base case to end the loop at a given threshold.

## Question 2

*Consider the following subroutine:*

```java
static void printStuff(int level) {
    if (level == 0) {
        System.out.print("*");
    }
    else {
        System.out.print("[");
        printStuff(level - 1);
        System.out.print(",");
        printStuff(level - 1);
        System.out.print("]");
    }
}
```

*Show the output that would be produced by the subroutine calls `printStuff(0)`, `printStuff(1)`, `printStuff(2)`, and `printStuff(3)`.*

`printStuff(0)`: `*`
`printStuff(1)`: `[*,*]`
`printStuff(2)`: `[[*,*],[*,*]]`
`printStuff(3)`: `[[[*,*],[*,*]],[[*,*],[*,*]]]`

## Question 3

*Suppose that a linked list is formed from objects that belong to the class*

```java
class ListNode {
    int item;       // An item in the list.
    ListNode next;  // Pointer to next item in the list.
}
```

*Write a subroutine that will count the number of zeros that occur in a given linked list of ints. The subroutine should have a parameter of type `ListNode` and should return a value of type `int`.*

```java
public static int countZeros(ListNode list) {
    int count = 0;
    var runner = list;

    while (runner != null) {
        if (runner.item == 0)
            count++;
        
        runner = runner.next;
    }

    return count;
}
```

## Question 4

*Let `ListNode` be defined as in the previous problem. Suppose that head is a variable of type `ListNode` that points to the first node in a linked list. Write a code segment that will add the number 42 in a new node at the end of the list. Assume that the list is not empty. (There is no "tail pointer" for the list.)*

```java
public static void add42(ListNode list) {
    var runner = list;

    while (ruller != null)
        runner = runner.next;
    
    var newNode = new ListNode();
    newNode.item = 42;

    runner.next = newNode;
}
```

## Question 5

*What are the tree operations of a stack?*

Stacks have the operations `push`, `pop`, and `isEmpty`. Push adds a new node to the top of the stack, pop removes the top node, and isEmpty checks if the stack is empty.

## Question 6

*What is the basic difference between a stack and a queue?*

A stack adds nodes to the top, and removes them from the top, whereas the queu adds nodes to the back, and removes them from the front. This means if a set of nodes was added in order then removed from a stack they would be in the reverse order, but if they were put through a queue they would be in the same order as originally.

## Question 7

*What is an **activation record**? What role does a stack of activation records play in a computer?*

An activation record holds the necessary information for executing a subroutine, like the paramaters and variables.

## Question 8

*Suppose that a binary tree of integers is formed from objects belonging to the class*

```java
class TreeNode {
    int item;       // One item in the tree.
    TreeNode left;  // Pointer to the left subtree.
    TreeNode right; // Pointer to the right subtree.
}
```

*Write a recursive subroutine that will find the sum of all the nodes in the tree. Your subroutine should have a parameter of type `TreeNode`, and it should return a value of type `int`.*

```java
public static int sum(TreeNode root) {
    if (root == null)
        return 0;

    int total = root.item;
    total += sum(root.left);
    total += sum(root.right);

    return total;
}
```

## Question 9

*Let `TreeNode` be the same class as in the previous problem. Write a recursive subroutine that makes a copy of a binary tree. The subroutine has a parameter that points to the root of the tree that is to be copied. The return type is `TreeNode`, and the return value should be a pointer to the root of the copy. The copy should consist of newly created nodes, and it should have exactly the same structure as the original tree.*

```java
public static TreeNode copy(TreeNode root) {
    if (root == null)
        return null;

    var copy = new TreeNode();
    copy.item = root.item;
    copy.left = copy(root.left);
    copy.right = copy(root.right);

    return copy;
}
```

## Question 10

*What is a **postorder traversal** of a binary tree?*

A postorder traversal is where the order the nodes are processed goes left, right, root. This means that this traversal will apply to the entire depth of the binary tree, which means for example, the first values from the traversal will be all nodes along the left of the tree. There are also inorder and preorder tarversals, which simply rearrange the order the nodes are processed, having them go left, root, right and root, left, right respectively.

## Question 11

*Suppose that a `<multilist>` is defined by the BNF rule*

```java
<multilist>  ::=  <word>  |  "(" [ <multilist> ]... ")"
```

*where a `<word>` can be any sequence of letters. Give five different `<multilist>`'s that can be generated by this rule. (This rule, by the way, is almost the entire syntax of the programming language LISP! LISP is known for its simple syntax and its elegant and powerful semantics.)*

1. foo
2. ( foo bar )
3. ( ( foo ) ( bar ) )
4. ( foo ( bar) )
5. ( ( ( foo ) bar ) baz )

## Question 12

*Explain what is meant by **parsing** a computer program.*

Parsing a computer program is determining the syntactic structure of the program to determine how it can be constructed using the rules of a grammer (such as a BNF grammar).