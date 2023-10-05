package lists;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;
    public boolean isEmpty() {
        return root == null;
    }

    //remove

    public BinarySearchTree() {
    }

    public BinarySearchTree(T root) {
        add(root);
    }

    public void add(T data) {
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

    public BinaryTreeNode<T> remove(T data) {
        root = remove(root, data);
        return root;
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {
            node.setLeftChild(remove(node.getLeftChild(), data));
        } else if (compareResult > 0) {
            node.setRightChild(remove(node.getRightChild(), data));
        } else {

            // 1 child
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            //2 children
            BinaryTreeNode<T> smallestRightNode = findMin(node.getRightChild());
            node.setData(smallestRightNode.getData());
            node.setRightChild(remove(node.getRightChild(), smallestRightNode.getData()));
        }

        return node;
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }


    //all of the would be nice
    public void printInOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            printInOrder(node.getLeftChild());
            System.out.printf("%s ", node.getData());
            printInOrder(node.getRightChild());
        }
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