package com.hiperium.tests.java.unit.four;

public class ForLoop {

    public static void main(String[] args) {
        int x = 0;
        for (long y = 0, z = 4; x < 5 && y < 10; x++, y++)
            System.out.print(y + " ");
        System.out.println(x + " ");

        // Initialize x again to 0
        for (x = 0; x < 10; ) { // VALID FOR STRUCTURE
            x++;
        }
        System.out.println("x = " + x);

        String[] names = new String[3];
        for (String name : names) {
            System.out.println(name + " ");
        }

        question15();
    }

    /**
     * Which statements, when inserted into the following blanks, allow the code to compile and run without
     * entering an infinite loop?
     */
    static void question15() {
        int height = 1;
        L1:
        while (height++ < 10) {
            long humidity = 12;
            L2:
            do {
                if (humidity-- % 12 == 0) continue L2; // break L2;
                System.out.println("humidity = " + humidity);

                int temperature = 30;
                L3:
                for (; ; ) { // INFINITY LOOP
                    temperature++;
                    if (temperature > 50) continue L2;
                    System.out.println("temperature = " + temperature);
                }
            } while (humidity > 4);
        }

    }
}
