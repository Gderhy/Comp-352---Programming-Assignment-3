import java.util.Arrays;

public class DoublyLinkedList extends DataStructure {

    private int size;
    private Node head, tail;

    public DoublyLinkedList() {

        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Puts newNode at the back
    public void append(long key, String value) {
        Node newNode = new Node(key, value);

        if (size == 0) { // Checks if empty
            this.head = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }

        this.tail.next = newNode;
        newNode.prev = this.tail;
        tail = newNode;
        this.size++;
    }

    // Puts newNode at the front
    public void prepend(long key, String value) {
        Node newNode = new Node(key, value);

        if (size == 0) { // Checks if empty
            this.head = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }

        this.head.prev = newNode;
        newNode.next = this.head;
        this.head = newNode;
    }

    // Removes first occurrence of node with key
    public void remove(long key) {

        if (head == null) return;

        Node index = this.head;

        while (index != null) {

            if (index.key == key) { // removes this node

                if (index == head) { // If index is head

                    this.head = head.next;

                    if (head.prev != null) { // Ensures that head has no prev
                        head.prev = null;
                    }

                } else if (index == tail) { // if index is tail

                    this.tail = index.prev;
                    if (this.tail != null) {

                        this.tail.next = null; // Ensures tail is last link
                    }

                } else { // if index is not head

                    index.prev.next = index.next;
                    index.next.prev = index.prev;
                }

                size--;
                return;
            }

            index = index.next;
        }
    }

    @Override
    public String getValue(long key) {

        if (isEmpty()) return null;

        Node index = head;

        while (index != null) {

            if (index.key == key) {

                return index.value;
            }

            index = index.next;
        }

        return null;
    }

    @Override
    public long nextKey(long key) {

        if (isEmpty()) return -1;

        Node index = head;

        while (index != null) {

            if (index.key == key) { // if the key is found
                if (index.next == null) { // if the successor is null

                    return -1;
                } else {

                    return index.next.key;
                }
            }
            index = index.next;
        }

        return -1;
    }

    @Override
    public long prevKey(long key) {

        if (isEmpty()) return -1;

        Node index = head;

        while (index != null) {

            if (index.key == key) { // if the key is found
                if (index.prev == null) { // if the predecessor is null

                    return -1;
                } else {

                    return index.prev.key;
                }
            }
            index = index.next;
        }

        return -1;
    }

    @Override
    public int rangeKey(long key1, long key2) {

        // Returns -1 if empty
        if (isEmpty()) return -1;

        // Ensure key1 is smaller than or equal to key2
        if (key1 > key2) {
            long temp = key1;
            key1 = key2;
            key2 = temp;
        }


        Node index = head;
        int count = 0;

        while (index != null) {

            if (key1 <= index.key && index.key <= key2)
                count++; // Increments num if matches conditions

            if (index.key > key2) break; // Breaks when the key of the index node is bigger
            // then k2 since no conditions will ever be met

            index = index.next;
        }

        return count;
    }

    @Override
    public void printData() {

        if (isEmpty()) {

            System.out.println("Empty");
            return;
        }

        Node index = head;

        System.out.println("{");
        while (index != null) {

            System.out.println("\t" + String.format("%08d", index.key) + " : " + index.value);

            index = index.next;
        }

        System.out.println("}");
    }


    @Override
    public boolean contains(long key) {

        if (head == null) return false;

        Node index = head;

        while (index != null) {

            if (index.key == key) {

                return true;
            }
            index = index.next;
        }

        return false;
    }

    @Override
    public long[] allKeys() {

        if (isEmpty()) return new long[0];

        long[] keyArray = new long[size];

        Node currentNode = head;
        int index = 0;

        while (currentNode != null) {

            keyArray[index] = currentNode.key;
            currentNode = currentNode.next;
            index++;
        }

        Arrays.sort(keyArray);

        return keyArray;
    }

    // Checks if list is empty
    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public void add(long key, String value) {

        append(key, value);
    }

    // returns size of list
    public int getSize() {

        return this.size;
    }


    // Inner class containing key and value
    private class Node {

        private String value;
        private long key;

        private Node prev, next;

        private Node(long key, String value) {

            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

}
