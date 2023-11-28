import java.util.Arrays;

public class AVLTree extends DataStructure {

    private TreeNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    // Helper function to get the height of a node
    private int height(TreeNode node) {

        if (node == null) return 0;

        return node.height;
    }

    // Helper function to update height of node
    private void updateHeight(TreeNode node) {
        if (node == null) return;

        node.height = 1 + Math.max(height(node.left), height(node.right));
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
    private TreeNode leftRotation(TreeNode x) {

        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Returns the bf of a node
    private int getBalanceFactor(TreeNode node) {

        if (node == null) return 0;

        return height(node.left) - height(node.right);
    }


    // Recursively insert - helper function for add
    private TreeNode insert(TreeNode node, long key, String value) {

        if (node == null) {

            return new TreeNode(key, value);
        }

        // Places the new in its corresponding spot
        if (key < node.key) {

            node.left = insert(node.left, key, value);

        } else if (key > node.key) {

            node.right = insert(node.right, key, value);
        } else {

            // Duplicate keys are not allowed
            return node;
        }

        updateHeight(node);

        node = balancing(node, key); // Balancing if needed

        return node;
    }


    // Recursively remove - helper function for remove
    private TreeNode delete(TreeNode node, long key) {

        if (node == null) return node;

        if (key < node.key) {

            node.left = delete(node.left, key);

        } else if (key > node.key) {

            node.right = delete(node.right, key);

        } else {

            size--; // reducing size

            // Deletion for nodes with only one or no child
            if (node.left == null || node.right == null) {

                TreeNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }

            } else {

                // Node with two children
                // Getting the node with the smallest key in the right subtree
                TreeNode temp = getMinValueNode(node.right);

                // Copying data to the node
                node.key = temp.key;
                node.value = temp.value;

                // Delete the inorder successor
                node.right = delete(node.right, temp.key);
            }


        }

        // If the tree had only one node then return
        if (node == null) {
            return node;
        }

        updateHeight(node); //Updating height of current node

        // Balancing tree if needed
        node = balancing(node, key);

        return node;
    }

    // Balances the tree
    private TreeNode balancing(TreeNode node, long key) {

        // Balancing tree if needed

        int bf = getBalanceFactor(node);

        // Left heavy
        if (bf > 1 && key < node.left.key)
            return rightRotate(node);

        // Right heavy
        if (bf < -1 && key > node.right.key)
            return leftRotation(node);

        // Left-Right heavy
        if (bf > 1 && key > node.left.key) {

            node.left = leftRotation(node.left);
            return rightRotate(node);
        }

        // Right-Left heavy
        if (bf < -1 && key < node.right.key) {

            node.right = rightRotate(node.right);
            return leftRotation(node);
        }

        return node;
    }

    private TreeNode getMinValueNode(TreeNode node) {

        if (node == null) return null;

        while (node.left != null) {

            node = node.left;
        }

        return node;
    }

    private boolean contains(TreeNode node, long key) {

        if (node == null) return false;

        if (key < node.key) {
            return contains(node.left, key); // Search in the left subtree
        } else if (key > node.key) {
            return contains(node.right, key); // Search in the right subtree
        } else {
            return true; // Key is found in the current node
        }
    }

    @Override
    public boolean contains(long key) {

        if (isEmpty()) return false;

        return contains(root, key);
    }

    @Override
    public long[] allKeys() {

        if (isEmpty()) return new long[0];

        long[] keyArray = new long[size];

        inOrder(root, keyArray, new int[1]);

        Arrays.sort(keyArray);
        return keyArray;
    }

    // Helper function for allKeys()
    private void inOrder(TreeNode node, long[] keyArray, int[] index) {

        // Used an int[] index in order to keep track of the index without using global variables
        if (node != null) {
            inOrder(node.left, keyArray, index);
            keyArray[index[0]++] = node.key; // Add the key to the array and increment index
            inOrder(node.right, keyArray, index);
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void add(long key, String value) {

        root = insert(root, key, value);

        size++;
    }

    @Override
    public void remove(long key) {
        if(isEmpty()) return;
        root = delete(root, key);
    }

    @Override
    public String getValue(long key) {

        if(isEmpty()) return null;

        TreeNode node = search(root, key);
        return (node == null) ? null : node.value;
    }

    // Helper for getValue, nextKey, and prevKey
    private TreeNode search(TreeNode node, long key){

        if (node == null || node.key == key) return node;

        if(key < node.key) return search(node.left, key);
        else return search(node.right, key);
    }

    @Override
    public long nextKey(long key) {

        if(isEmpty()) return -1;

        TreeNode node = search(root, key);

        return (node == null || node.right == null) ? -1 : node.right.key;
    }

    @Override
    public long prevKey(long key) {

        if(isEmpty()) return -1;

        TreeNode node = search(root, key);

        return (node == null || node.left == null) ? -1 : node.left.key;
    }

    @Override
    public int rangeKey(long key1, long key2) {

        if(isEmpty()) return -1;

        if(key1 > key2){

            long temp = key1;
            key1 = key2;
            key2 = temp;
        }

        int[] count = new int[1];

        inOrder(root, count, key1, key2);

        return count[0];
    }

    // Helper for rangeKey
    private void inOrder(TreeNode node, int[] count, long key1, long key2){

        // Used an int[] index in order to keep track of the index without using global variables
        if (node != null) {
            inOrder(node.left, count, key1, key2);

            if(key1 <= node.key && node.key <= key2) count[0]++;

            inOrder(node.right, count, key1, key2);
        }
    }

    @Override
    public void printData() {
        System.out.println("{");
        printDataHelper(root);
        System.out.println("}");
    }

    private void printDataHelper(TreeNode node){
        if(node != null){
            printDataHelper(node.left);
            System.out.println("\t" + String.format("%08d", node.key) + " : " + node.value);
            printDataHelper(node.right);
        }
    }

    public int getSize(){

        return size;
    }
    private class TreeNode {

        private long key;
        private String value;

        private int height;
        private TreeNode left, right;

        private TreeNode(long key, String value) {

            this.key = key;
            this.value = value;

            this.height = 1;

            this.left = null;
            this.right = null;
        }

    }
}
