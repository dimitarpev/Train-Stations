package maps;

import maps.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestHashMap {

    private HashMap<String, String> map;
    @BeforeEach
    public void creatingHashMapOfStringsAndLength11BeforeEachTest() {
        map = new HashMap<>(11);
    }
    @Test
    public void checkingIfEmptyHashMapIsEmptyReturnsTrue() {
        assertTrue(map.isEmpty());
    }

    @Test
    public void putANewKeyValuePairInTheHashMapReturnsTheValueThatHasBeenPut(){
        String key = "k1";
        String value = "v1";
        assertEquals(value, map.put(key, value));
        assertFalse(map.isEmpty());
    }

    @Test
    public void putAKeyValuePairWithAlreadyExistingPairWithThatKeyUpdatesTheValueInThatExistingPair() {
        String key = "k1";
        String valueOld = "v1";
        String valueNew = "v1.1";
        assertEquals(valueOld, map.put(key, valueOld));
        assertEquals(valueNew, map.put(key, valueNew));
        assertEquals(valueNew, map.get(key));
    }

    @Test
    public void tryingToPutAKeyWhichIsNullGivesError() {
        String key = null;
        String value = "v1";
        AssertionError error = assertThrows(AssertionError.class, () -> map.put(key, value));
        assertEquals("The value of key cannot be null", error.getMessage());
    }

    @Test
    public void tryingToPutAValueWhichIsNullGivesError() {
        String key = "k1";
        String value = null;
        AssertionError error = assertThrows(AssertionError.class, () -> map.put(key, value));
        assertEquals("The value of value cannot be null", error.getMessage());
    }

    @Test
    public void callingGetAndPassingAnExistentKeyReturnsThePairValue() {
        String key = "k1";
        String value = "v1";
        map.put(key, value);
        assertEquals(value, map.get(key));
    }

    @Test
    public void tryingToGetAKeyThatDoesNotExistReturnsNull() {
        String key = "k1";
        String value = "v1";
        String inexistantKey = "k2";
        map.put(key, value);
        assertNull(map.get(inexistantKey));
    }

    @Test
    public void tryingToGetANullKeyGivesError() {
        String key = null;
        AssertionError error = assertThrows(AssertionError.class, () -> map.get(key));
        assertEquals("The value of key cannot be null", error.getMessage());
    }

    @Test
    public void gettingTheSizeOfHashMapWithOnePairReturns1() {
        String key = "k1";
        String value = "v1";
        map.put(key, value);
        assertEquals(1, map.size());
    }

    @Test
    public void removingAPairFromHashMapReturnsPairsValue() {
        String key = "k1";
        String value = "v1";
        map.put(key, value);
        assertEquals(value, map.remove(key));
    }

    @Test
    public void removingAPairFromHashMapThatDoesNotExistReturnsNull() {
        String key = "k1";
        String value = "v1";
        String noSuchKey = "k2";
        map.put(key, value);
        assertNull(map.remove(noSuchKey));
    }

    @Test
    public void tryingToRemoveNullKeyFromHashMapGivesError() {
        String key = null;
        AssertionError error = assertThrows(AssertionError.class, () -> map.remove(key));
        assertEquals("The value of key cannot be null", error.getMessage());
    }

    @Test
    public void callingKeySetMethodOfHashMapWithTwoPairsReturnsThosePairsKeys() {
        map.put("k1", "v1");
        map.put("k2", "v2");
        Set<String> result = map.keySet();
        assertEquals(2, result.size());
        String[] array = result.toArray(new String[2]);
        assertEquals("k1", array[0]);
        assertEquals("k2", array[1]);
    }

    @Test
    public void callingValuesMethodOfHashMapWithTwoPairsReturnsThosePairsValues() {
        map.put("k1", "v1");
        map.put("k2", "v2");
        Collection<String> result = map.values();
        assertEquals(2, result.size());
        String[] array = result.toArray(new String[2]);
        assertEquals("v1", array[0]);
        assertEquals("v2", array[1]);
    }

    @Test
    public void addingElementsToHashMapOfInitialSize3UntilRemapLimitIsReached() {
        HashMap<Integer, String> littleMap = new HashMap<>(3);
        for (int i = 0; i < 11; i++) {
            littleMap.put(i, "v"+i);
        }
        assertEquals(11, littleMap.size());
    }

}
