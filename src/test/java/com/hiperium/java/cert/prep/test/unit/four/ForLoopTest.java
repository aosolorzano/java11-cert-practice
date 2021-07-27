package com.hiperium.java.cert.prep.test.unit.four;

import org.junit.Assert;
import org.junit.Test;

public class ForLoopTest {

    /**
     * Which statements, when inserted independently into the following blank, will cause the code to print
     * 2 at runtime?
     */
    @Test
    public void question6() {
        int count = 0;
        BUNNY:
        for (int row = 1; row <= 3; row++)
            RABBIT:for (int col = 0; col < 3; col++) {
                System.out.println("row = " + row + ", col = " + col);
                if ((col + row) % 2 == 0) {
                    System.out.println("BREAK");
                    break RABBIT;   // OPTION 1
                    // continue BUNNY; // OPTION 2
                    // break;          // OPTION 3
                }
                System.out.println("count++");
                count++;
            }
        System.out.println("count = " + count);
        Assert.assertTrue(true);
    }

    /**
     * Which statements, when inserted into the following blanks, allow the code to compile and run without
     * entering an infinite loop?
     */
    @Test
    public void question15() {
        int height = 1;
        L1:
        while (height++ < 10) {
            long humidity = 12;
            L2:
            do {
                if (humidity-- % 12 == 0) break L2; // VALID OPTIONS -> BREAK L2 and CONTINUE L2
                System.out.println("humidity = " + humidity);

                int temperature = 30;
                L3:
                for (; ; ) { // INFINITY LOOP
                    temperature++;
                    if (temperature > 50) continue L2; // VALID OPTIONS -> CONTINUE L2
                    System.out.println("temperature = " + temperature);
                }
            } while (humidity > 4);
        }
        Assert.assertTrue(true);
    }
}
