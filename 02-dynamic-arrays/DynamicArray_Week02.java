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
public class DynamicArray_Week02 {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray_Week02(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
    } // full constructor

    /** Array-based constructor -- used for testing */
    public DynamicArray_Week02(String[] data) {
        this.foundation = data;
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray_Week02() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * returns 'true' if 'target' is present in the underlying array and 'false' if it isn't.
     * @param target the string to search for in the array
     * @return true if the target is found
     */
    public boolean contains(String target){
        if (foundation == null || target == null) {
            return false;
        }

        int index = 0;
        boolean found = false;
        while (index < foundation.length && !found){
            if (foundation[index].equals(target)){
                found = true;
            }
            index++;
        }
        return found;
    }

    /**
     * returns the string in position `[index]` in the underlying array or null if something wrong.
     * @param index
     * @return 
     */
    public String get(int index){
        String stringAtPos = null;

        //check if index exists
        if (index >= 0 && index < foundation.length){
            stringAtPos = foundation[index];
        }
        return stringAtPos;
    }

    /**
     * returns the String in position `[index]` in the underlying array then removes that value from the array.
     * @param index
     * @return 
     */
    public String remove(int index){
        String value = null;
        if (index >= 0 && index < foundation.length){
            value = foundation[index];
            foundation[index] = null;
        }
        return value;
    }

    /**
     * removes the value from position `[index]` in the underlying array
     * @param index
     */
    public void delete(int index){
        if (index >= 0 && index < foundation.length){
            foundation[index] = null;
        }
    }

    /**
     * adds a string in the `DynamicArray` object, overcoming the fixed size of the `foundation` array.
     * @param string
     */
    public void insert(String string){
        resize();
        foundation[foundation.length-1] = string;
    }

    /**
     * increases the size of the `foundation` array as needed to accomodate additional strings inserted to the object.
     */
    private void resize(){
        String [] newFoundation = new String[foundation.length + 1];
        for(int i = 0; i < foundation.length; i++){
            newFoundation[i] = foundation[i];
        }
        foundation = newFoundation;
    }
    /** Driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = { "Java", "Python", "C", "C++", "Fortran" };
        DynamicArray test = new DynamicArray(testData);
        DynamicArray tset = new DynamicArray(null);
        // Naive testing - I am ashamed to do this but I need
        // to keep things simple for now.
        String testContainsNullTarget = (!test.contains(null)) ? PASS : FAIL;
        String testContainsEmptyData = (!tset.contains("Java")) ? PASS : FAIL;
        String testContainsExisting = (test.contains(testData[1])) ? PASS : FAIL;
        String testContainsNonExisting = (!test.contains(NON_EXISTING)) ? PASS : FAIL;
        String testGetNegative = (test.get(-1) == null) ? PASS : FAIL;
        String testGet = (test.get(0).equals(testData[0])) ? PASS : FAIL;
        String testGetOutOfBounds = (test.get(testData.length + 1) == null) ? PASS : FAIL;
        String testRemove = (testData[1].equals(test.remove(1))) ? PASS : FAIL;
        String testRemoveNull = (test.remove(1) == null) ? PASS : FAIL;
        String testRemoveOutOfBounds = (test.remove(testData.length + 1) == null) ? PASS : FAIL;
        System.out.printf("\nTest for contains(null): ............... %s", testContainsNullTarget);
        System.out.printf("\nTest for contains on null foundation: .. %s", testContainsEmptyData);
        System.out.printf("\nTest for contains (existing): .......... %s", testContainsExisting);
        System.out.printf("\nTest for contains (non existing): ...... %s", testContainsNonExisting);
        System.out.printf("\nTest for get(-1): ...................... %s", testGetNegative);
        System.out.printf("\nTest for get(0): ....................... %s", testGet);
        System.out.printf("\nTest for get(out of bounds): ........... %s\n", testGetOutOfBounds);
        System.out.printf("\nTest for remove(1): .................... %s", testRemove);
        System.out.printf("\nTest for remove(null): ................. %s", testRemoveNull);
        System.out.printf("\nTest for remove(out of bounds): ........ %s\n\n", testRemoveOutOfBounds);
        // If all is good, these two statemets will not crash the program
        test.insert("Pascal");
        test.insert("Basic");
    } // method main


} // class DynamicArray