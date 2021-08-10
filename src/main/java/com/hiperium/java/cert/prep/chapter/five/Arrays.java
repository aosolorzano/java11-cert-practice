package com.hiperium.java.cert.prep.chapter.five;

import java.util.ArrayList;
import java.util.List;

public class Arrays {

    public static void main(String[] args) {
        int[] numbers = new int[3];
        System.out.println("Printing default values with Arrays.toString --> " + java.util.Arrays.toString(numbers));
        System.out.println("Printing array length --> " + numbers.length);
        // ARRAYS DOES NOT HAS SIZE() FUNCTION

        // Initialized array
        int[] numbers2 = new int[]{42, 55, 99};

        // Anonymous array
        int[] numbers3 = {42, 55, 99};

        /* ***************** VALID JAVA ARRAYS DECLARATIONS *****************
        int[] numAnimals;
        int [] numAnimals2;
        int []numAnimals3;
        int numAnimals4[];
        int numAnimals5 [];
        int[][] scores = new int[5][];  INITIALIZED AT LEAST THE FIRST ARRAY
        java.util.Date[] dates[] = new java.util.Date[2][];
        ******************************************************************** */

        /* ***************** INVALID JAVA ARRAYS DECLARATIONS *****************
        int[][] java = new int[][]; ARRAY INITIALIZER EXPECTED
        int[][] types = new int[];  ARRAY INITIALIZER EXPECTED
        int[][] types = new int[2]; REQUIRED [][]
        ******************************************************************** */

        String[] bugs = {"cricket", "beetle", "ladybug"};
        String[] alias = {"cricket", "beetle", "ladybug"};
        System.out.println("bugs.equals(alias) = " + bugs.equals(alias)); // FALSE
        System.out.println("bugs == alias) = " + (bugs == alias)); // FALSE
        alias = bugs;
        System.out.println("bugs.equals(alias) = " + bugs.equals(alias)); // TRUE
        System.out.println("bugs == alias) = " + (bugs == alias)); // TRUE

        binarySearch();
        compareArrays();
        fromArrayListToArray();
        arraysAsLists();
        immutableList();
    }

    /**
     * Before sort an array, it must be ordered first.
     */
    private static void binarySearch() {
        System.out.println("**** BINARY SEARCH ****");
        int[] numbers = {8, 6, 2, 1};
        System.out.println(java.util.Arrays.binarySearch(numbers, 2)); // -1 (NOT ORDERED ARRAY)

        java.util.Arrays.sort(numbers);
        // Collections.sort(numbers); USED TO SORT LIST OF WRAPPER CLASSES LIKE INTEGER
        System.out.println(java.util.Arrays.binarySearch(numbers, 2)); // NOW FOUND AT POSITION 1
        System.out.println(java.util.Arrays.binarySearch(numbers, 6)); // FOUND AT POSITION 2
        System.out.println(java.util.Arrays.binarySearch(numbers, 9)); // -5
        // Although 9 isn’t in the list, it would need to be inserted at element 4 to preserve the sorted order.
        // We negate and subtract 1 for consistency, getting –4 –1, also known as –5.

    }

    private static void compareArrays() {
        System.out.println("**** COMPARE ARRAYS ****");
        System.out.println(java.util.Arrays.compare(new int[]{1, 3}, new int[]{1}));                  //  1
        System.out.println(java.util.Arrays.compare(new int[]{1, 3, 9}, new int[]{1}));               //  2
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"A"}));           // 32
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"b"}));           // -1
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"x"}));           //-23
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"1"}));           // 48
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"ab1"}));         // -2
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"ab1c"}));        // -3
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"a", "a"}));      // -1
        System.out.println(java.util.Arrays.compare(new String[]{"a"}, new String[]{"a", "a", "a"})); // -1
        System.out.println(java.util.Arrays.compare(new String[]{null}, new String[]{"ab", "yz"}));   // -1
    }

    private static void fromArrayListToArray() {
        System.out.println("**** FROM ARRAY LIST TO ARRAY ****");
        List<String> list = new ArrayList<>();
        list.add("hawk");
        list.add("robin");
        Object[] objectArray = list.toArray();
        String[] stringArray = list.toArray(new String[0]); // LIKE BEFORE BUT 'new String[0]' PARAMETER IS NEEDED
        list.clear();
        System.out.println("Object Array size = " + objectArray.length);
        System.out.println("String Array size = " + stringArray.length);
    }

    /**
     * After 'Arrays.asList' both variables makes references to the same values on the heap. Thus, the list variable
     * its a backed version of the original array.
     */
    private static void arraysAsLists() {
        System.out.println("**** FROM ARRAY TO ARRAY LIST ****");
        String[] array = {"hawk", "robin"};
        System.out.println("Original String Array values: " + java.util.Arrays.toString(array));

        List<String> list = java.util.Arrays.asList(array); // FIXED-SIZE. BACKED VERSION OF A LIST
        System.out.println("String List values from array: " + list.toString());          // [hawk, robin]

        list.set(1, "test");
        System.out.println("String List values after set new value: " + list.toString()); // [hawk, test]
        System.out.println("String array values: " + java.util.Arrays.toString(array));   // [hawk, test] EQUALS TO LIST

        array[0] = "new";
        System.out.println("String Array after set 'new' value: " + java.util.Arrays.toString(array)); // [new, test]
        System.out.println("String List values after set 'new' value: " + list.toString());            // [new, test] EQUALS TO ARRAY
    }

    private static void immutableList() {
        System.out.println("**** IMMUTABLE LIST ****");
        String[] array = {"hawk", "robin"};
        System.out.println("Original String Array values: " + java.util.Arrays.toString(array));
        List<String> list = List.of(array); // <========= IMMUTABLE LIST
        System.out.println("String List values from array: " + list.toString()); // [hawk, robin]
        array[0] = "new";
        System.out.println("String Array after set 'new' value: " + java.util.Arrays.toString(array));
        System.out.println("String List values after set 'new' value: " + list.toString());
        // list.set(1, "test"); THROWS UnsupportedOperationException
    }
}
