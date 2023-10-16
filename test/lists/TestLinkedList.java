package lists;

import lists.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLinkedList {

    @Test
    public void addFirstElementInLinkedList(){
        LinkedList<String> list = new LinkedList<>();
        list.add("element1");
        assertEquals("element1", list.get(0));
    }

    @Test
    public void addTwoElementsInLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("element1");
        list.add("element2");
        assertEquals("element1", list.get(0));
        assertEquals("element2", list.get(1));
    }

    @Test
    public void addThreeElementsInLinkedListToTestLogic() {
        LinkedList<String> list = new LinkedList<>();
        list.add("element1");
        list.add("element2");
        list.add("element3");
        assertEquals("element1", list.get(0));
        assertEquals("element2", list.get(1));
        assertEquals("element3", list.get(2));
    }

    @Test
    public void addingNullElementToListGivesAnError() {
        LinkedList<String> list = new LinkedList<>();
        AssertionError error = assertThrows(AssertionError.class, () ->  list.add(null));
        assertEquals("Element cannot be null", error.getMessage());
    }

    @Test
    public void addingElementToIndex0() {
        LinkedList<String> list = new LinkedList<>();
        list.add(0, "element1");
        assertEquals("element1", list.get(0));
    }

    @Test
    public void addingElementToIndex2InListOf4Items() {
        LinkedList<String> list = new LinkedList<>();
        list.add("element1");
        list.add("element2");
        list.add("element3");
        list.add("element4");
        list.add(2, "addedElement");
        assertEquals("addedElement", list.get(2));
    }

    @Test
    public void addingElementToNegativeIndexGivesError() {
        LinkedList<String> list = new LinkedList<>();
        AssertionError error = assertThrows(AssertionError.class, () ->  list.add(-1, "not going to be added"));
        assertEquals("Index cannot be a negative number", error.getMessage());
    }

    @Test
    public void addingElementToIndexHigherThanTheListLengthGivesError() {
        LinkedList<String> list = new LinkedList<>();
        AssertionError error = assertThrows(AssertionError.class, () ->  list.add(1, "not going to be added"));
        assertEquals("Index must be smaller than the list size", error.getMessage());
    }

    @Test
    public void afterCallingListClearTheListMustBeEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.add("element1");
        list.add("element2");
        list.add("element3");

        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        AssertionError error = assertThrows(AssertionError.class, () ->  list.get(0));
        assertEquals("Index must be in range of the list's length", error.getMessage());
    }

    @Test
    public void checkingIfListContainsElementThatIsTheFirstOneReturnsTrue() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e1";
        list.add(element);

        assertTrue(list.contains(element));
    }

    @Test
    public void checkingIfListContainsElementThatIsTheSecondOneReturnsTrue() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e2";

        list.add("e1");
        list.add(element);

        assertTrue(list.contains(element));
    }

    @Test
    public void checkingIfListContainsElementThatIsTheThirdOneReturnsTrue() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e3";

        list.add("e1");
        list.add("e2");
        list.add(element);

        assertTrue(list.contains(element));
    }

    @Test
    public void checkingIfListContainsElementThatDoesNotExistReturnsFalse() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e1";

        assertFalse(list.contains(element));
    }

    @Test
    public void gettingTheIndexOfTheFirstElementReturns0() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e1";
        list.add(element);

        assertEquals(0, list.indexOf(element));
    }

    @Test
    public void tryingtoGetIndexOfElementThatDoesNotExistInAListOf1() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e2";
        list.add("e1");

        assertEquals(-1, list.indexOf(element));
    }

    @Test
    public void gettingIndexOfSecondElementReturns1() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e2";
        list.add("e1");
        list.add(element);

        assertEquals(1, list.indexOf(element));
    }

    @Test
    public void gettingIndexOfThridElementReturns2() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e3";
        list.add("e1");
        list.add("e2");
        list.add(element);

        assertEquals(2, list.indexOf(element));
    }

    @Test
    public void gettingIndexOfElementThatDoesNotExistInListOf3ElementsReturnsNegative1() {
        LinkedList<String> list = new LinkedList<>();
        String element = "un-existing element";
        list.add("e1");
        list.add("e2");
        list.add("e3");

        assertEquals(-1, list.indexOf(element));
    }

    @Test
    public void removeElementAtIndex0FromListOf1Elements() {
        LinkedList<String> list = new LinkedList<>();
        list.add("e1");
        assertTrue(list.remove(0));
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeElementAtIndex1FromListOf2Elements() {
        LinkedList<String> list = new LinkedList<>();
        list.add("e1");
        list.add("e2");
        assertTrue(list.remove(1));

    }

    @Test
    public void removeElementFromListOf1Element() {
        LinkedList<String> list = new LinkedList<>();
        String element = "e1";
        list.add(element);
        assertTrue(list.remove(element));
    }

    @Test
    public void settingElementOfIndex0ToDifferentElementReturnsValueOfPreviousElement() {
        LinkedList<String> list = new LinkedList<>();
        String elementOld = "eOld";
        String elementNew = "eNew";
        list.add(elementOld);
        assertEquals(elementOld, list.set(0, elementNew));
        assertEquals(elementNew, list.get(0));
    }

    @Test
    public void creatingASubListOfTheLinkedList() {
        LinkedList<Character> list = new LinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        list.add('e');
        list.add('f');
        LinkedList<Character> sublist = list.subList(0, 3);
        assertEquals(3, sublist.size());
        assertEquals('a', sublist.get(0));
        assertEquals('b', sublist.get(1));
        assertEquals('c', sublist.get(2));
    }

    @Test
    public void creatingASubListWithIndexesNotInRangeGivesError() {
        LinkedList<Character> list = new LinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');

        AssertionError error = assertThrows(AssertionError.class, () ->  list.subList(2, 6));
        assertEquals("Indexes must be in range of the list's length", error.getMessage());
    }

}
