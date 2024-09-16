// Name:
// Student number:
import java.util.ArrayList;

public class DynamicHashMap {

    /**
     * This interface is partly based on Java's HashMap:
     * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
     */
    private int capacity;
    private double loadFactor;
    private ArrayList<ArrayList<Integer>> table;
    private ArrayList<ArrayList<String>> keys;


    /**
     * Create a new empty hash map
     * @param tSize - the number of cells in the table
     *      or the maximum number of chain that can be in the table
     * @param loadFactor - The loadFactor is defined as the average chain length.
     *      If the average chain length exceeds the loadFactor
     *      the table size should be doubled, and rehashing done.
     *      The loadFactor given here stays constant.
     */
    public DynamicHashMap(int tSize, double loadFactor) {
        this.capacity = tSize;
        this.loadFactor = loadFactor;

        keys = new ArrayList<ArrayList<String>>();
        table = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < tSize; i++) {
            keys.add(new ArrayList<String>());
            table.add(new ArrayList<Integer>());
        }
    }


    /**
     * Returns the current highest number of cells in the table.
     * This is also equal to the maximum amount of chains that can be in the table.
     */
    public int getTableSize() {
        return capacity;
    }


    /**
     * Returns the set load factor of the table.
     * This value determines when to double the table size and rehash all entries.
     */
    public double getLoadFactor() {
        return loadFactor;
    }


    private Integer[] chain(int index) {
        return table.get(index).toArray(new Integer[table.get(index).size()]);
    }


    private String chainToString(Integer[] chain) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < chain.length; i++) {
            sb.append(chain[i]);
            if (i + 1 != chain.length) {
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * String representation of all table chains
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getTableSize(); i++) {
            sb.append(chainToString(chain(i)));  
        }

        return sb.toString();
    }


    ////// You may not change any code above this line //////

    ////// Implement the methods below this line //////

    /**
     * Calculate and return the hash of the given key.
     * The input key should be interpreted as an ASCII string.
     * 
     * The hash should be calculated by XORing all the characters of the string
     * Example: h(abcd) = (a XOR b XOR c XOR d) mod TSize
     *          h(a)    = (a) mod TSize
     * 
     * NOTE: This hash function limits the maximum size of the table to 128.
     * See section 10.1.2 in the textbook.
     * 
     * For information on the XOR operator:
     * https://en.wikipedia.org/wiki/Exclusive_or
     * 
     * For information on ASCII:
     * https://en.wikipedia.org/wiki/ASCII
     */
    public int hash(String key) {

        // Your code here
        int h =0;
        for(int i=0;i<key.length();i++){
            if(i==0){
                h = Integer.parseInt(key.substring(0, 1));
            }
            else{
                h ^= Integer.parseInt(key.substring(i, i+1));
            }
        }
        return h%capacity;
    }

    
    /**
     * Return the value associated with the key. If no value is associated, return null.
     */
    public Integer get(String key) {

        // Your code here
        return null;
    }


    /**
     * Set the value asociated with the key.
     * If after the update, the internal loadFactor is greater than the set loadFactor,
     * the table size should be doubled and all key-values pairs should be re-inserted.
     * 
     * Return the previous value or null if no previous value was associated with the key.
     */
    public Integer put(String key, Integer value) {

        // Your code here
        return null;
    }


    /**
     * Remove the value associated with the given key.
     * 
     * The table size should never decrease.
     * Return the associated value or null if no value was associated
     */
    public Integer remove(String key) {

        // Your code here
        return null;
    }


    //Helper methods


}