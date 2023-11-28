public class DoublyLinkedList {

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

    // Removes first occurence of node with key
    public void delete(long key) {

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
                    if(this.tail != null){

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


    // Checks if list is empty
    public boolean isEmpty() {

        return head == null;
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
