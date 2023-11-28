abstract class DataStructure {

    private int size;

    public abstract boolean contains(long key); // Checks if the key exists

    public abstract long[] allKeys(); // Returns a sorted long[] of all the keys

    public abstract boolean isEmpty(); // Returns if DataStructure is empty

    public abstract void add(long key, String value); // Adds to the data structure a new entry

    public abstract void remove(long key); // Removes entry of that key if it exists

    public abstract String getValue(long key); // Returns the value of that entry as a string

    public abstract long nextKey(long key); // Returns the key of the next entry, returns -1 if empty
                                    // , successor is null, or cant find

    public abstract long prevKey(long key); // Returns the key of the prev entry, returns -1 if empty
                                    // , predecessor is null, or cant find

    public abstract int rangeKey(long key1, long key2); // Returns the number of keys between k1 and k2 inclusively

    public abstract void printData(); // Prints key value pairs

    public abstract int getSize(); // Returns the number of elements in the data structure


}
