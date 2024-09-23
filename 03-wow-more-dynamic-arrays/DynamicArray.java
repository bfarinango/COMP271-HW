/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
import java.util.Scanner;
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    /** Measures how many places in the array are in use */
    private int occupancy;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.occupancy = 0;
    } // full constructor

    /**
     * Array-based constructor -- used for testing.
     * 
     * WARNING: SHALLOW ARRAY COPY
     * 
     * @param data
     */
    public DynamicArray(String[] data) {
        this(DEFAULT_SIZE);
        if (data != null) {
            this.foundation = data;
            this.occupancy = data.length;
        }
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Checks if the specified string is present in the dynamic array.
     * 
     * @param target The string to search for in the array
     * @return true if the string is found, false otherwise
     */
    public boolean contains(String target) {
        boolean found = false;
        /*
         * Before introducing this.occupancy in the object, the method traversed
         * this.foundation through its entire length, i.e., the while loop allowed its
         * index i to reach this.foundation.length. As we saw, however, not every
         * element in this.foundation may be used. this.occupancy tells us what is the
         * last used element in this.foundation. There is no point searching after that
         * element, as all values are going to be null. So, for the while loop here we
         * change the condition from while(i<this.foundation length &...) to
         * while(i<this.occupancy &&...)
         */
        if (target != null && this.foundation != null) {
            int i = 0;
            // No need to guard against occupancy==0, because if array is empty, loop will
            // not even run.
            while (i < this.occupancy && !found) {
                found = this.foundation[i] != null && this.foundation[i].equals(target);
                i++;
            }
        }
        return found;
    } // method contains

    /**
     * Retrieves the string at the specified index in the array.
     * 
     * @param index The index of the string to retrieve
     * @return The string at the specified index, or null if the index is invalid
     */
    public String get(int index) {
        String string = null;
        // No need to guard against occupancy==0, because if array is empty, the method
        // will return null anyway
        if (index >= 0 && this.foundation != null && index < this.foundation.length) {
            string = this.foundation[index];
        }
        return string;
    } // method get

    /**
     * Removes the string at the specified index in the array and sets its position
     * to null. Then it moves every element to the right of the removed element, one
     * position to the left. The position of the last element to be copied to the
     * left is then emptied out (null).
     * 
     * @param index The index of the string to remove
     * @return The string that was removed, or null if the index is invalid
     */
    public String remove(int index) {
        String removed = null;
        // We check occupancy, because there is no reason to perform this in an empty
        // array
        if (this.occupancy > 0 && index >= 0 && index < this.foundation.length) {
            removed = this.foundation[index];
            this.foundation[index] = null;
            // Shift things after the removed string, one position to the left
            for (int i = index; i < occupancy - 1; i++) {
                this.foundation[i] = this.foundation[i + 1];
            }
            // Previously last occupied cell, now empty
            this.foundation[occupancy - 1] = null;
            // update occupancy
            this.occupancy--;
        }
        return removed;
    } // method remove

    /**
     * Deletes the string at the specified index in the array.
     * 
     * This method uses this.remove and simply ignores the returned string.
     * 
     * @param index The index of the string to delete
     */
    public void delete(int index) {
        String whatEver = remove(index);
    } // method delete

    /**
     * Resizes the underlying array by increasing its capacity by 1.
     * 
     * This method is called internally when the current array reaches its capacity
     * and a new element needs to be inserted.
     */
    private void resize() {
        String[] temp = new String[this.foundation.length + 1];
        /*
         * Instead of:
         * for (int i = 0; i < this.foundation.length; i++) {
         * we can write
         * for (int i = 0; i < this.occupancy; i++) {
         * since there is no reason to copy null values from one array to another.
         */
        for (int i = 0; i < this.occupancy; i++) {
            temp[i] = this.foundation[i];
        }
        this.foundation = temp;
    } // method resize

    /**
     * Inserts a new string into the dynamic array.
     * 
     * If the string is not null and the array is full, it will be resized to
     * accommodate the new element.
     * 
     * @param string The string to insert into the array
     */
    public void insert(String string) {
        // Guard against null argument
        if (string != null) {
            // If there is no room left in underlying array, resize it first
            if (this.occupancy == this.foundation.length) {
                this.resize();
            }
            // Room in underlying array assured
            this.foundation[this.occupancy] = string;
            this.occupancy++;
        }
    } // method insert

    /**
     * creates a textual representation of the object, where each element is 
     * separated by a ", ". The resulting string is enclosed in "[]" brackets.
     * @return A string representation of the object
     * returns "[]" if array is null or empty
     */
    public String toString() {
        String fullString = "[";
        //check if array exists and is not empty
        if (this.foundation != null && this.foundation.length > 0) {
            //adds the first element of array
            if (this.foundation[0] == null) {
                fullString += "null";
            } else {
                fullString += this.foundation[0];
            }
            //iterates through the rest of the array and updates fullString
            for (int i = 1; i < this.foundation.length; i++) {
                fullString += ", ";
                //add "null" if element is null
                if (this.foundation[i] == null) {
                    fullString += "null";
                } else {
                    fullString += this.foundation[i];
                }
            }
        }
        fullString += "]";
        return fullString;
    }

    /**
     * returns the array position of `string` in the underlying array. 
     * If there are multiple occurences of `string` in `this.foundation`, the method returns the position of the first occurrence. 
     * If there are none, the method shall return -1.
     * @param string The string used to search for in the array
     * @return The index of the first occurence of the string, or -1 if not found.
     */
    public int index(String string){
        //start at index -1 if string is not found
        int index = -1;
        //checks if string has been found
        boolean found = false;
        for (int i = 0; i < this.foundation.length; i++){
            //checks if element equals string and it's the first occurence
            if(this.foundation[i].equals(string) && !found){
                index = i;
                found = true;
            }
        }
        return index; 
    }

    /**
     * returns the percentage of the underlying array elements that are current not null.
     * @return The percentage of non-null elements in the array, as a double value between 0 and 100.
     */
    public double usage(){
        double count = 0;
        //iterates through foundation array
        for(int i = 0; i < this.foundation.length; i++){
            //increments counter if element is not null
            if(this.foundation[i] != null){
                count++;
            }
        }
        //calculates the percentage of non-null elements
        double percentage = (count/this.foundation.length) * 100;
        //returns rounded percentage
        return Math.round(percentage * 100.0)/100.0;
    }

    /** Driver/test code */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = { "Java", "Python", "C", "C++", "Fortran" };
        DynamicArray test = new DynamicArray(testData);
        System.out.println(test.toString());
        System.out.println(test.index("C++"));
        System.out.println(test.usage());
        
    } // method main

} // class DynamicArray
