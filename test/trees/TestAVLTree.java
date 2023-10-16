package trees;

import trees.AVLTree;
import trees.BinaryTreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAVLTree {

    AVLTree<Integer> treeInt;
    AVLTree<String> treeString;
    @BeforeEach
    public void createAVLTreeWithSomeNumbersAndTreeWithStrings() {
        treeInt = new AVLTree<>();
        treeInt.add(16);
        treeInt.add(13);
        treeInt.add(53);
        treeInt.add(11);
        treeInt.add(14);
        treeInt.add(50);
        treeInt.add(64);
        treeInt.add(41);
        treeInt.add(52);
        treeInt.add(62);
        assertEquals(10, treeInt.size());
        assertTrue(treeInt.isBalanced());

        treeString = new AVLTree<>();
        treeString.add("dog");
        treeString.add("bat");
        treeString.add("horse");
        treeString.add("alligator");
        treeString.add("cobra");
        treeString.add("fox");
        treeString.add("pig");
        treeString.add("leopard");
        treeString.add("shark");
        assertEquals(9, treeString.size());
        assertTrue(treeString.isBalanced());
    }

    @Test
    public void insertSingleRotationLeftToStringTree() {
        treeString.add("tiger");
        assertTrue(treeString.isBalanced());
    }

    @Test
    public void insertSingleRotationRightToTree() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(2);
        assertTrue(tree.isBalanced());
    }

    @Test
    public void insertDoubleRotationLeftToTree() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(5);
        tree.add(4);
        tree.add(10);
        tree.add(11);
        tree.add(15);

        tree.add(7);
        assertTrue(tree.isBalanced());
    }

    @Test
    public void addingNodeThatAlreadyExistsDoesNotAddAnything() {
       treeInt.add(13);
       assertEquals(10, treeInt.size());
    }

    @Test
    public void addingLeftRightCase() {
        treeInt.add(63);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void addingRightLeftCase() {
        treeInt.add(63);

        treeInt.add(30);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void removingLeftRightCase() {
        treeInt.add(63);
        treeInt.add(30);

        treeInt.remove(11);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void removingLeftLeftCase() {
        treeInt.add(63);
        treeInt.add(30);
        treeInt.remove(11);

        treeInt.remove(13);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void removingRightLeftCase() {
        treeInt.add(63);
        treeInt.add(30);
        treeInt.remove(11);
        treeInt.remove(13);

        treeInt.remove(16);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void removingRightRightCase() {
        treeInt.remove(52);
        assertTrue(treeInt.isBalanced());
    }

    @Test
    public void removingNodeFromANullTree() {
        AVLTree<Integer> tree = new AVLTree<>();
        assertNull(tree.remove(55));
    }

    @Test
    public void gettingBalanceOfBalancedTreeReturnsZero() {
        AVLTree<Integer> tree = new AVLTree<>();
        BinaryTreeNode<Integer> node = null;
        assertEquals(0, tree.getBalance(node));
    }
}
