package lists;

import lists.MinHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMinHeap {

    private MinHeap<Integer> heap;
    @BeforeEach
    public void createMinHeapWithIntegers() {
        heap = new MinHeap<>();
    }

    @Test
    public void addFirstElementToMinHeap() {
        heap.add(13);
        assertEquals(13, heap.peek());
    }

    @Test
    public void addingAFewElementsToMinHeapGettingOrderedCorrectly() {
        heap.add(12);
        heap.add(17);
        heap.add(21);
        heap.add(20);
        heap.add(37);
        heap.add(25);
        heap.add(63);
        heap.add(11);

        assertFalse(heap.isEmpty());
        assertEquals(11, heap.peek());
        assertEquals(12, heap.get(1));
        assertEquals(21, heap.get(2));
        assertEquals(17, heap.get(3));
        assertEquals(37, heap.get(4));
        assertEquals(25, heap.get(5));
        assertEquals(63, heap.get(6));
        assertEquals(20, heap.get(7));
    }

    @Test
    public void checkingIfHeapContainsElement() {
        heap.add(1);
        assertTrue(heap.contains(1));
    }

    @Test
    public void checkingIfHeapContainsElementThatIsNotInsideReturnsFalse() {
        heap.add(1);
        assertFalse(heap.contains(2));
    }

    @Test
    public void removingAndPeekingFromHeapThatIsEmptyReturnsNull() {
        assertNull(heap.remove());
        assertNull(heap.peek());
    }
    @Test
    public void removingElementFromHeapWithOnlyOneElement() {
        heap.add(1);
        assertEquals(1, heap.size());
        int element = heap.remove();
        assertTrue(heap.isEmpty());
        assertEquals(1, element);
    }

    @Test
    public void removingElementFromHeapWithOnlyOneLeftChild() {
        heap.add(1);
        heap.add(2);
        assertEquals(2, heap.size());
        int element = heap.remove();
        assertEquals(1, element);
        assertEquals(1, heap.size());
        assertEquals(2, heap.get(0));
    }

    @Test
    public void removingElementFromHeapWithLeftAndRightChildLeftChildBeingLower() {
        heap.add(2);
        heap.add(4);
        heap.add(6);
        assertEquals(3, heap.size());
        int element = heap.remove();
        assertEquals(2, element);
        assertEquals(2, heap.size());
        assertEquals(4, heap.get(0));
        assertEquals(6, heap.get(1));
    }

    @Test
    public void removingElementFromHeapWithLeftAndRightChildRightChildBeingLower() {
        heap.add(2);
        heap.add(6);
        heap.add(4);
        assertEquals(3, heap.size());
        int element = heap.remove();
        assertEquals(2, element);
        assertEquals(2, heap.size());
        assertEquals(4, heap.get(0));
        assertEquals(6, heap.get(1));
    }

    @Test public void removingElementFromHeapWithLeftRightChildsAndLeftSubchildsLeftBeingLower() {
        heap.add(2);
        heap.add(4);
        heap.add(6);
        heap.add(8);
        heap.add(10);
        assertEquals(5, heap.size());
        int element = heap.remove();
        assertEquals(2, element);
        assertEquals(4, heap.size());
        assertEquals(4, heap.get(0));
        assertEquals(8, heap.get(1));
        assertEquals(6, heap.get(2));
        assertEquals(10, heap.get(3));
    }

    @Test
    public void removingTheFirstHeapElementCorrectlyReordersABiggerHeapCorrectly() {
        heap.add(13);
        heap.add(14);
        heap.add(16);
        heap.add(19);
        heap.add(21);
        heap.add(19);
        heap.add(68);
        heap.add(65);
        heap.add(26);
        heap.add(32);
        heap.add(31);
        int removedNum = heap.remove();
        assertEquals(13, removedNum);
        assertEquals(10, heap.size());
        assertEquals(14, heap.peek());
        assertEquals(19, heap.get(1));
        assertEquals(16, heap.get(2));
        assertEquals(26, heap.get(3));
        assertEquals(21, heap.get(4));
        assertEquals(19, heap.get(5));
        assertEquals(68, heap.get(6));
        assertEquals(65, heap.get(7));
        assertEquals(31, heap.get(8));
        assertEquals(32, heap.get(9));
    }

    @Test
    public void heapGeneratesCorrectlyToWebGraphVizFormat() {
        heap.add(13);
        heap.add(14);
        heap.add(16);
        heap.add(19);
        heap.add(21);
        heap.add(19);
        heap.add(68);
        heap.add(65);
        heap.add(26);
        heap.add(32);
        heap.add(31);
        assertEquals("digraph G {\n" +
                "\"13\" -> \"14\"\n" +
                "\"13\" -> \"16\"\n" +
                "\"14\" -> \"19\"\n" +
                "\"14\" -> \"21\"\n" +
                "\"16\" -> \"19\"\n" +
                "\"16\" -> \"68\"\n" +
                "\"19\" -> \"65\"\n" +
                "\"19\" -> \"26\"\n" +
                "\"21\" -> \"32\"\n" +
                "\"21\" -> \"31\"\n" +
                "}", heap.toWebGraphViz());
    }

}
