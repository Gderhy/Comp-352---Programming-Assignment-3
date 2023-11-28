import java.util.Arrays;
import java.util.Random;

public class CleverSIDC {

    public int size;
    private DataStructure dataStructure;
    private String type;

    public CleverSIDC(int size) {

        // Checks if size is valid
        if (size < 0) {

            throw new IllegalArgumentException();
        }

        SetSIDCThreshold(size);
    }


    private void SetSIDCThreshold(int size) {

        if (size < 1000) { // Use DoublyLinkedList

            this.dataStructure = new DoublyLinkedList();
            this.type = "DoublyLinkedList";

        } else if (size < 5000) {

            this.dataStructure = new AVLTree();
            this.type = "AVLTree";

        } else {

            this.dataStructure = new HashTable(size);
            this.type = "HashTable";

        }
    }

    public long generate() {

        long randomKey = generateRandomKey();

        while(contains(randomKey)){

            randomKey = generateRandomKey();
        }

        return randomKey;
    }

    // Generate and return a random 8-digit key
    private static long generateRandomKey() {

        Random random = new Random();
        return Math.abs(random.nextLong() % 100000000L);
    }

    // Checks depending on the dataStructure if the key exists
    private boolean contains(long key){

        return dataStructure.contains(key);
    }

    public long[] allKeys(){

        return dataStructure.allKeys();
    }

    public void add(long key, String value){

        dataStructure.add(key, value);
    }

    public void remove(long key){

        dataStructure.remove(key);
    }

    public String getValue(long key){

        return dataStructure.getValue(key);
    }

    public long nextKey(long key){

        return dataStructure.nextKey(key);
    }

    public long prevKey(long key){

       return dataStructure.prevKey(key);
    }

    public void printData(){

        dataStructure.printData();
    }

    public int rangeKey(long key1, long key2){

        return dataStructure.rangeKey(key1, key2);
    }

    public int getSize(){

        return dataStructure.getSize();
    }




    public static void main(String[] args){


        CleverSIDC test = new CleverSIDC(10001);
        System.out.println("type: " + test.type);



        test.add(1, "4");
        test.add(2, "1");
        test.add(3, "1");
        test.add(test.generate(), "1");
        test.add(test.generate(), "1");
        test.add(test.generate(), "1");

        test.printData();

        System.out.println(Arrays.toString(test.allKeys()));



    }
}