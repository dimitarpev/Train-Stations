package trees;

import trees.BinarySearchTree;
import trees.BinaryTreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBinarySearchTree {

    private BinarySearchTree<String> tree;
    @BeforeEach
    public void creatingTreeAndAddingSomeLetterElementsInBalancedOrder() {
        tree = new BinarySearchTree<>();
        tree.add("D");
        tree.add("B");
        tree.add("F");
        tree.add("A");
        tree.add("C");
        tree.add("E");
        tree.add("G");
        assertEquals(7, tree.size());
    }

    @Test
    public void addingANodeToATree() {
        tree.add("X");
        assertEquals(8, tree.size());
        assertEquals("X", tree.get("X").getData());
    }

    @Test
    public void addingANullElementToATreeGivesError() {
        AssertionError error = assertThrows(AssertionError.class, () -> tree.add(null));
        assertEquals("Element to add cannot be null", error.getMessage());
    }

    @Test
    public void checkingIfNonEmptyTreeIsEmptyReturnsFalse() {
        assertFalse(tree.isEmpty());
    }

    @Test
    public void checkingIfTreeContainsExistingElementReturnsTrue() {
        assertTrue(tree.contains("A"));
    }

    @Test
    public void checkingIfTreeContainsNonExistingElementReturnsFalse() {
        assertFalse(tree.contains("I dont exist"));
    }

    @Test
    public void tryingToCheckIfANullElementIsContainedInTreeReturnsNull() {
        AssertionError error = assertThrows(AssertionError.class, () -> tree.contains(null));
        assertEquals("Element to check cannot be null", error.getMessage());
    }

    @Test
    public void gettingAnElementFromTheLeftSubTree() {
        assertEquals("A", tree.get("A").getData());
    }

    @Test
    public void gettingAnElementFromTheRightSubTree() {
        assertEquals("G", tree.get("G").getData());
    }

    @Test
    public void tryingToGetAnElementWhichIsNullReturnError() {
        AssertionError error = assertThrows(AssertionError.class, () -> tree.get(null));
        assertEquals("Element to get cannot be null", error.getMessage());
    }

    @Test
    public void gettingTheSizeOfTreeWith7ElementsReturns7() {
        assertEquals(7, tree.size());
    }

    @Test
    public void removingANodeWith1RightChildFromRightSubTree() {
        tree.add("X");
        BinaryTreeNode<String> root = tree.remove("G");
        assertEquals("D", root.getData());
        assertNull(tree.get("G"));
        assertEquals(7, tree.size());

    }

    @Test
    public void removingANodeWith1LeftChildFromRightSubTree() {
        tree.remove("G");
        BinaryTreeNode<String> root = tree.remove("F");
        assertEquals("D", root.getData());
        assertNull(tree.get("F"));
        assertEquals(5, tree.size());

    }

    @Test
    public void removingANodeWith0ChildrenFromLeftSubTree() {
        BinaryTreeNode<String> root = tree.remove("A");
        assertEquals("D", root.getData());
        assertNull(tree.get("A"));
        assertEquals(6, tree.size());
    }

    @Test
    public void removingTheRootNodeWhichHasTwoChildrenResultsInRootBeingTheLeftPredecessor() {
        BinaryTreeNode<String> root = tree.remove("D");
        assertEquals("C", root.getData());
        assertNull(tree.get("D"));
        assertEquals(6, tree.size());
    }

    @Test
    public void removingAnElementThatDoesNotExistReturnsTheRootAndNothingGetsRemoved() {
        BinaryTreeNode<String> root = tree.remove("I do not exist");
        assertEquals("D",root.getData());
        assertEquals(7, tree.size());
    }

    @Test
    public void tryingToRemoveElementWhichIsNullGivesAnError() {
        AssertionError error = assertThrows(AssertionError.class, () -> tree.remove(null));
        assertEquals("Element to remove cannot be null", error.getMessage());
    }

    @Test
    public void creatingATreeWithDirectlySpecifyingTheRootElementCreatesATreeWithThatRoot() {
        String root = "Im root";
        BinarySearchTree<String> specTree = new BinarySearchTree<>(root);
        assertEquals(1, specTree.size());
        assertTrue(specTree.contains(root));
    }

    @Test
    public void isLeafOnANodeThatIsLeafReturnsTrue() {
        BinaryTreeNode<String> node = tree.get("A");
        assertTrue(node.isLeaf());
    }

    @Test
    public void gettingTheHeightOfANode() {
        BinaryTreeNode<String> node = tree.get("F");
        assertEquals(2, node.getHeight());
    }

    @Test
    public void gettingTheDepthOfTheFarthestLeftNode() {
        BinaryTreeNode<String> node = tree.get("D");
        assertEquals(2, node.getDepth("A"));
    }

    @Test
    public void gettingTheDepthOfTheFarthestRightNode() {
        BinaryTreeNode<String> node = tree.get("D");
        assertEquals(2, node.getDepth("G"));
    }

    @Test
    public void gettingDepthOfNodeThatIsNotBelowThatNodeReturnNegative1() {
        BinaryTreeNode<String> node = tree.get("A");
        assertEquals(-1, node.getDepth("F"));
    }

    @Test
    public void generatingWebGraphVizOutputOfATree() {
        String webGraphViz = tree.toWebGraphViz();
        assertEquals("digraph G {\n" +
                "\"D\" -> \"B\"\n" +
                "\"B\" -> \"A\"\n" +
                "\"B\" -> \"C\"\n" +
                "\"D\" -> \"F\"\n" +
                "\"F\" -> \"E\"\n" +
                "\"F\" -> \"G\"\n" +
                "}", webGraphViz);
        String toString = tree.toString();
        assertTrue(toString.contains("7"));
    }

}
