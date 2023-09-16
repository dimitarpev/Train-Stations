package lists;

public class HashMap<K,V> implements Map<K,V> {
    private final int tableSize;
    private LinkedList<Pair<K,V>>[] mapArray;
    private int currentSize = 0;

    public HashMap(int tableSize) {
        this.tableSize = tableSize;
        mapArray = new LinkedList[tableSize];
    }


    private int getElementMod(int hashValue){
        return hashValue % tableSize;
    }

    @Override
    public V put(K key, V value) {
        assert key != null : "The value of key cannot be null";
        assert value != null : "The value of value cannot be null";

        int elementPositionInArray = getElementMod(key.hashCode());
        assert elementPositionInArray < tableSize : "Element position cannot be higher than the array's length";

        //If no key/value pairs have been added to that position, create a new linked list to be able to add one
        if (mapArray[elementPositionInArray] == null){
            mapArray[elementPositionInArray] = new LinkedList<>();
        }

        if (keyIsUnique(key, elementPositionInArray)){
            mapArray[elementPositionInArray].add(new Pair<>(key, value));
            currentSize++;
        } else {
            // update value of existing key
            updatePairValue(key, value, elementPositionInArray);
        }


        return value;
    }

    private void updatePairValue(K key, V value, int elementPosition) {
        LinkedList<Pair<K,V>> currentList = mapArray[elementPosition];

        if (currentList != null){
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getKey().equals(key)){
                   currentList.get(i).setValue(value);
                }
            }
        }

    }

    private boolean keyIsUnique(K key, int elementPosition) {
        //Irritates through all elements of matching list to see if key value is unique

        LinkedList<Pair<K,V>> currentList = mapArray[elementPosition];
        if (currentList != null){
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getKey().equals(key)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public V get(K key) {
        assert key != null : "The value of key cannot be null";

        int elementPositionInArray = getElementMod(key.hashCode());
        LinkedList<Pair<K,V>> currentList = mapArray[elementPositionInArray];
        if (currentList != null){
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getKey().equals(key)){
                    return currentList.get(i).getValue();
                }
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        assert key != null : "The value of key cannot be null";
        int elementPositionInArray = getElementMod(key.hashCode());
        LinkedList<Pair<K,V>> currentList = mapArray[elementPositionInArray];
        if (currentList != null){
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getKey().equals(key)){
                    V value = currentList.get(i).getValue();
                    currentList.remove(i);
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < mapArray.length; i++) {
            if (mapArray[i] != null){
                return false;
            }
        }
        return true;
    }
}
