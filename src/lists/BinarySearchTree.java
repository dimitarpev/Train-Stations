package lists;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;
    private Comparator<T> comparator = Comparator.naturalOrder();

    //remove
    public BinarySearchTree() {
    }

    public BinarySearchTree(T root) {
        add(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(T data) {
        assert data != null : "Element to add cannot be null";

        root = insert(root, data);
    }

    private BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T data) {
        if (node == null) {
            return new BinaryTreeNode<>(data);
        }

        if (data.compareTo(node.getData()) <= 0) {
            node.setLeftChild(insert(node.getLeftChild(), data));
        } else {
            node.setRightChild(insert(node.getRightChild(), data));
        }

        return node;
    }


    public boolean contains(T data){
        assert data != null : "Element to check cannot be null";

        return contains(root, data);
    }

    private boolean contains(BinaryTreeNode<T> node, T data){
        if (node == null) {
            return false;
        }

        if (node.getData().equals(data)){
            return true;
        } else {
            return contains(node.getLeftChild(), data) || contains(node.getRightChild(), data);
        }
    }

    public BinaryTreeNode<T> get(T data) {
        assert data != null : "Element to get cannot be null";

        return get(root, data);
    }

    private BinaryTreeNode<T> get(BinaryTreeNode<T> node, T data) {
        if (node == null || node.getData().equals(data)) {
            return node;
        }

        if (data.compareTo(node.getData()) < 0) {
            return get(node.getLeftChild(), data);
        } else {
            return get(node.getRightChild(), data);
        }
    }

    public BinaryTreeNode<T> remove(T data) {
        assert data != null : "Element to remove cannot be null";

        root = remove(root, data);
        return root;
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compareResult = comparator.compare(data, node.getData());

        if (compareResult < 0) {
            node.setLeftChild(remove(node.getLeftChild(), data));
        } else if (compareResult > 0) {
            node.setRightChild(remove(node.getRightChild(), data));
        } else {

            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            BinaryTreeNode<T> largestLeftNode = findMax(node.getLeftChild());
            node.setData(largestLeftNode.getData());
            node.setLeftChild(remove(node.getLeftChild(), largestLeftNode.getData()));
        }

        return node;
    }

    private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }

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