class TreeNode {
    int key;
    int height;
    TreeNode left;
    TreeNode right;

    public TreeNode(int key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

public class AVLTree_2 {
    private TreeNode root;

    public AVLTree_2() {
        this.root = null;
    }

    // Helper function to get the height of a node
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Helper function to update the height of a node
    private void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // Helper function for right rotation
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Helper function for left rotation
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Get the balance factor of a node
    private int getBalanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Insert a key into the AVL tree
    public void insert(int key) {
        root = insert(root, key);
    }

    // Recursive insert helper function
    private TreeNode insert(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys are not allowed
            return node;
        }

        updateHeight(node);

        // Perform rotations to balance the tree
        int balance = getBalanceFactor(node);

        // Left Heavy
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Heavy
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left-Right Heavy
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Heavy
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Delete a key from the AVL tree
    public void delete(int key) {
        root = delete(root, key);
    }

    // Recursive delete helper function
    private TreeNode delete(TreeNode node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                TreeNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // Node with two children: Get the inorder successor (smallest
                // in the right subtree)
                TreeNode temp = minValueNode(node.right);

                // Copy the inorder successor's data to this node
                node.key = temp.key;

                // Delete the inorder successor
                node.right = delete(node.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (node == null) {
            return node;
        }

        // Update height of current node
        updateHeight(node);

        // Perform rotations to balance the tree
        int balance = getBalanceFactor(node);

        // Left Heavy
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // Right Heavy
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // Left-Right Heavy
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Heavy
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Get the node with the smallest key in the tree
    private TreeNode minValueNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Inorder traversal of the AVL tree
    public void inOrder() {
        inOrder(root);
    }

    // Recursive inorder traversal helper function
    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree_2 avl = new AVLTree_2();

        avl.insert(10);
        avl.insert(20);
        avl.insert(30);

        System.out.println("Inorder traversal:");
        avl.inOrder();

        avl.delete(20);

        System.out.println("\nInorder traversal after deleting 20:");
        avl.inOrder();
    }
}
