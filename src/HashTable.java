public class HashTable extends DataStructure {

    private final int capacity;
    private Entry[] table;
    private int size;
    public HashTable(int capacity) {

        this.capacity = capacity;
        this.table = new Entry[this.capacity];
    }

    // Hash function which incorporates both bitwise ops and multiplications
    private int hash(long key) {
        key ^= (key >>> 33);
        key *= 0xff51afd7ed558ccdL;
        key ^= (key >>> 33);
        key *= 0xc4ceb9fe1a85ec53L;
        key ^= (key >>> 33);
        return (int) (key % capacity);
    }


    @Override
    public boolean contains(long key) {
        return false;
    }

    @Override
    public long[] allKeys() {

        if(isEmpty()) return new long[0];

        long[] keyArray = new long[size];

        int count = 0;
        for(Entry current : table){

            while(current != null){

                keyArray[count++] = current.key;
                current = current.next;
            }
        }

        return keyArray;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(long key, String value) {

        int index = hash(key);
        Entry newEntry = new Entry(key, value);

        if (table[index] == null) { // Table has room for this element

            table[index] = newEntry;

        } else { // If hash has collided

            Entry current = table[index];

            // Will loop until the last element of the list in order to have the .next assigned to newEntry
            while (current.next != null) {

                // If the key already exists, the value will be replaced

                if (current.key == key) {
                    current.value = value;
                    return; // Handles not incrementing size
                }

                current = current.next;
            }

            current.next = newEntry; // Sets the last node of the linkedlist to the newEntry
        }

        size++;
    }

    @Override
    public void remove(long key) {

        if (isEmpty()) return;

        int index = hash(key);
        Entry current = table[index];
        Entry previous = null;

        while (current != null) {

            if (current.key == key) {
                if (previous == null){ // if the key found was the first element in the collision list

                    table[index] = current.next;

                } else { // if the key found was not the first element in the collision list

                    previous.next = current.next;
                }

                size--; // Decrement if found
            }

            previous = current;
            current = current.next;
        }

        // Nothing happens if key was not found

    }

    @Override
    public String getValue(long key) {

        if (isEmpty()) return null;

        int index = hash(key);
        Entry current = table[index];

        while (current != null) {

            if (current.key == key) {

                return current.value;
            }

            current = current.next;
        }

        return null; // if not found
    }

    @Override
    public long nextKey(long key) {
        return 0;
    }

    @Override
    public long prevKey(long key) {
        return 0;
    }

    @Override
    public int rangeKey(long key1, long key2) {
        return 0;
    }

    @Override
    public void printData() {

    }

    @Override
    public int getSize() {
        return this.size;
    }

    private class Entry {

        private long key;
        private String value;
        private Entry next;

        public Entry(long key, String value) {

            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
