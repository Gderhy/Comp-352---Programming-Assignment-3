public class CleverSIDC {

    public int size;
    private Object dataStructure;

    public CleverSIDC(int size) {

        this.size = size;

        if (size < 1000) { // Use DoublyLinkedList

            this.dataStructure = new DoublyLinkedList();

        } else if (size < 10000) {


        }

    }





}