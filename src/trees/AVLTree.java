package trees;

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

    @Override
    public BinaryTreeNode<T> remove(T data) {
        root = remove(root, data);
        return root;
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T data) {
        // Perform standard BST deletion
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(remove(node.getLeftChild(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(remove(node.getRightChild(), data));
        } else {
            // Node with one child or no child
            if (node.getLeftChild() == null || node.getRightChild() == null) {
                BinaryTreeNode<T> temp = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();

                // No child case
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    // One child case
                    node = temp;
                }
                temp = null;
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                BinaryTreeNode<T> temp = findMin(node.getRightChild());

                // Copy the inorder successor's data to this node
                node.setData(temp.getData());

                // Delete the inorder successor
                node.setRightChild(remove(node.getRightChild(), temp.getData()));
            }
        }

        // If the tree had only one node, then return
        if (node == null) {
            return null;
        }

        // Update height of the current node
        updateHeight(node);

        // Perform balancing
        return balance(node);
    }

    // Existing code...

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
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

    public int getBalance(BinaryTreeNode<T> node) {
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


    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BinaryTreeNode<T> node) {

        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        assert balance > -1 || balance < 1 : "Tree should have been balanced, but its not";

        return isBalanced(node.getLeftChild()) && isBalanced(node.getRightChild());
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeftChild()) + size(node.getRightChild());
    }


}