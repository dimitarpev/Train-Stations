package lists;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    // NO DUPLICATE VALUES - if I give example in documentation i can get extra points (2,2,2)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private BinaryTreeNode<T> root;

    @Override
    public void add(T data) {
        root = add(root, data);
    }

    private BinaryTreeNode<T> add(BinaryTreeNode<T> node, T data) {
        // Perform standard BST insertion
        if (node == null) {
            return new BinaryTreeNode<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(add(node.getLeftChild(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(add(node.getRightChild(), data));
        } else {
            // Duplicate values are not allowed in AVL tree
            return node;
        }

        // Update height of the current node
        updateHeight(node);

        // Perform balancing
        return balance(node);
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balance = getBalance(node);

        // Left Heavy
        if (balance > 1) {
            if (getBalance(node.getLeftChild()) < 0) {
                // Left-Right case: Perform left rotation on left child, then right rotation on current node
                node.setLeftChild(leftRotate(node.getLeftChild()));
            }
            // Left-Left case: Perform right rotation on current node
            return rightRotate(node);
        }

        // Right Heavy
        if (balance < -1) {
            if (getBalance(node.getRightChild()) > 0) {
                // Right-Left case: Perform right rotation on right child, then left rotation on current node
                node.setRightChild(rightRotate(node.getRightChild()));
            }
            // Right-Right case: Perform left rotation on current node
            return leftRotate(node);
        }

        // No rotation needed
        return node;
    }

    private int getBalance(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.getLeftHeight() - node.getRightHeight();
    }

    private void updateHeight(BinaryTreeNode<T> node) {
        if (node != null) {
            int leftHeight = node.getLeftHeight();
            int rightHeight = node.getRightHeight();
            node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        }
    }

    private BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        BinaryTreeNode<T> T2 = x.getRightChild();

        // Perform rotation
        x.setRightChild(y);
        y.setLeftChild(T2);

        // Update heights
        updateHeight(y);
        updateHeight(x);

        // Return new root
        return x;
    }

    private BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> x) {
        BinaryTreeNode<T> y = x.getRightChild();
        BinaryTreeNode<T> T2 = y.getLeftChild();

        // Perform rotation
        y.setLeftChild(x);
        x.setRightChild(T2);

        // Update heights
        updateHeight(x);
        updateHeight(y);

        // Return new root
        return y;
    }
}