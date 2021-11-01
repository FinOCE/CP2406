import textio.TextIO;

public class Exercise9_2 {
    private static TreeNode root;

    public static void main(String[] args) {
        try {
            if (TextIO.readUserSelectedFile() == false) {
                System.out.println("No file was selected...");
                System.exit(1);
            }

            while (true) {
                String word = readNextWord();

                if (word == null)
                    break;

                word = word.toLowerCase();

                if (treeContains(root, word) == false)
                    treeInsert(word);
            }

            int wordCount = countNodes(root);
            System.out.printf("There were %d unique words in the file\n", wordCount);

            if (wordCount == 0) {
                System.out.println("Because there were no words, no need to save data");
                System.exit(0);
            }

            TextIO.writeUserSelectedFile();
            System.out.printf("%d words found in file:\n", wordCount);
            treeList(root);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.exit(0);
    }

    private static String readNextWord() {
        char ch = TextIO.peek();

        while (ch != TextIO.EOF && !Character.isLetter(ch)) {
            TextIO.getAnyChar();
            ch = TextIO.peek();
        }

        if (ch == TextIO.EOF)
            return null;

        String word = "";
        while (true) {
            word += TextIO.getAnyChar();

            ch = TextIO.peek();
            if (ch == '\'') {
                TextIO.getAnyChar();
                ch = TextIO.peek();

                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();
                } else
                    break;
            }

            if (!Character.isLetter(ch))
                break;
        }

        return word;
    }

    // ================================================================
    // THE CODE BELOW IS COPIED FROM THE SORT TREE DEMO
    // https://math.hws.edu/javanotes/source/chapter9/SortTreeDemo.java
    // ================================================================

    private static class TreeNode {
        String item; // The data in this node.
        TreeNode left; // Pointer to left subtree.
        TreeNode right; // Pointer to right subtree.

        TreeNode(String str) {
            // Constructor. Make a node containing the specified string.
            // Note that left and right pointers are initially null.
            item = str;
        }
    } // end nested class TreeNode

    /**
     * Add the item to the binary sort tree to which the global variable "root"
     * refers. (Note that root can't be passed as a parameter to this routine
     * because the value of root might change, and a change in the value of a formal
     * parameter does not change the actual parameter.)
     */
    private static void treeInsert(String newItem) {
        if (root == null) {
            // The tree is empty. Set root to point to a new node containing
            // the new item. This becomes the only node in the tree.
            root = new TreeNode(newItem);
            return;
        }
        TreeNode runner; // Runs down the tree to find a place for newItem.
        runner = root; // Start at the root.
        while (true) {
            if (newItem.compareTo(runner.item) < 0) {
                // Since the new item is less than the item in runner,
                // it belongs in the left subtree of runner. If there
                // is an open space at runner.left, add a new node there.
                // Otherwise, advance runner down one level to the left.
                if (runner.left == null) {
                    runner.left = new TreeNode(newItem);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.left;
            } else {
                // Since the new item is greater than or equal to the item in
                // runner it belongs in the right subtree of runner. If there
                // is an open space at runner.right, add a new node there.
                // Otherwise, advance runner down one level to the right.
                if (runner.right == null) {
                    runner.right = new TreeNode(newItem);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.right;
            }
        } // end while
    } // end treeInsert()

    /**
     * Return true if item is one of the items in the binary sort tree to which root
     * points. Return false if not.
     */
    static boolean treeContains(TreeNode root, String item) {
        if (root == null) {
            // Tree is empty, so it certainly doesn't contain item.
            return false;
        } else if (item.equals(root.item)) {
            // Yes, the item has been found in the root node.
            return true;
        } else if (item.compareTo(root.item) < 0) {
            // If the item occurs, it must be in the left subtree.
            return treeContains(root.left, item);
        } else {
            // If the item occurs, it must be in the right subtree.
            return treeContains(root.right, item);
        }
    } // end treeContains()

    /**
     * Print the items in the tree in inorder, one item to a line. Since the tree is
     * a sort tree, the output will be in increasing order.
     */
    private static void treeList(TreeNode node) {
        if (node != null) {
            treeList(node.left); // Print items in left subtree.
            System.out.println("  " + node.item); // Print item in the node.
            treeList(node.right); // Print items in the right subtree.
        }
    } // end treeList()

    /**
     * Count the nodes in the binary tree.
     * 
     * @param node A pointer to the root of the tree. A null value indicates an
     *             empty tree.
     * @return the number of nodes in the tree to which node points. For an empty
     *         tree, the value is zero.
     */
    private static int countNodes(TreeNode node) {
        if (node == null) {
            // Tree is empty, so it contains no nodes.
            return 0;
        } else {
            // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes(node.left);
            int rightCount = countNodes(node.right);
            return 1 + leftCount + rightCount;
        }
    } // end countNodes()
}