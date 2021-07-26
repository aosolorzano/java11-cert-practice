package com.hiperium.tests.java.unit.four;

public class FlowControl {

    public static void main(String[] args) {
        var dayOfTheWeek = 5;
        // var dayOfTheWeek = 6;
        // var dayOfTheWeek = 0;
        switch (dayOfTheWeek) {
            case 0:
                System.out.println("Sunday");
            default:
                System.out.println("Weekday");
            case 6:
                System.out.println("Saturday");
                break;
        }
    }

    final int getCookies() {
        return 4;
    }

    void feetAnimals() {
        final int bananas = 1;
        int apples = 2;
        final int applesFinal = 2;
        int numberOfAnimals = 3;
        final int cookies = getCookies();
        final int cookiesTwo = 2;
        final int cookiesFinal = 4;

        switch (numberOfAnimals) { // MUST BE CONSTANTS AT COMPILE-TIME
            case bananas:
            // case apples: DOES NOT COMPILE -> Constant expression required
            case applesFinal:
            // case getCookies(): DOES NOT COMPILE -> VALUE ITS EVALUATED AT RUNTIME
            // case cookiesTwo: DOES NOT COMPILE -> Duplicate value 2 (applesFinal)
            case cookiesFinal:
            // case cookies: DOES NOT COMPILE -> Constant expression required
            case 3 * 5: // VALID - RESULTS IN A CONSTANT VALUE (15)
        }
    }

    public int getSortOrder(String firstName, final String lastName) {
        String middleName = "Patricia";
        final String suffix = "JR";
        int id = 0;
        switch (firstName) {
            case "Test":
                return 52;
            // case middleName: DOES NOT COMPILE -> Constant expression required
            case suffix:
                id = 10;
                break;
            // case lastName: DOES NOT COMPILE -> Constant expression required
        }
        return id;
    }
}
