package trees;

public class BinaryTreeNode<T extends Comparable<T>> {
    private T data;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private int height;

    public BinaryTreeNode(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 1;
    }

    //is leaf?
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public T getData() {
        return data;
    }

    public int getHeight() {
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getLeftHeight() {
        if (leftChild == null) {
            return 0;
        }
        return leftChild.getHeight();
    }

    public int getRightHeight() {
        if (rightChild == null) {
            return 0;
        }
        return rightChild.getHeight();
    }

    public int getDepth(T data) {
        return getDepth(data, 0);
    }

    private int getDepth(T data, int depth) {
        if (data.compareTo(this.data) == 0) {
            return depth;
        } else if (data.compareTo(this.data) < 0 && leftChild != null) {
            return leftChild.getDepth(data, depth + 1);
        } else if (data.compareTo(this.data) > 0 && rightChild != null) {
            return rightChild.getDepth(data, depth + 1);
        } else {
            return -1;
        }
    }


    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setData(T data) {
        this.data = data;
    }
}




