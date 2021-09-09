package com.hiperium.java.cert.practice.tests;

import org.junit.Assert;
import org.junit.Test;

/**
 * Working with Java Data Types
 *     Use primitives and wrapper classes, including, operators, parentheses, type promotion and casting.
 *     Handle text using String and StringBuilder classes.
 *     Use local variable type inference, including as lambda parameters.
 */
public class Chapter1Test {

    /**
     * Which of the following are not valid variable names? (Choose two.)
     */
    @Test
    public void question1() {
        // int _;
        // int 2blue;
        int blue, Blue, blue2;
        int blue$, $blue, $, $$, $blue$;
        int _blue, blue_, __, _blue_;
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following?
     */
    @Test
    public void question4() {
        var b = "12";
        b += "3";
        // b.reverse(); ERROR: Cannot resolve method 'reverse' in 'String'.
        System.out.println(b.toString());
        this.question3Fixed();
        Assert.assertTrue(true);
    }

    private void question3Fixed() {
        var b = new StringBuilder("12");
        b.append("3");
        b.reverse();
        System.out.println(b.toString());
    }

    /**
     * What is the output of the following?
     */
    @Test
    public void question5() {
        var line = new StringBuilder("-");
        var anotherLine = line.append("-");
        System.out.print(line == anotherLine);
        System.out.print(" ");
        System.out.println(line.length());  // PRINT: true 2
        this.question5Practice(line);
        Assert.assertTrue(true);
    }

    private void question5Practice(StringBuilder line) {
        StringBuilder anotherLine = new StringBuilder(line.toString());
        System.out.println("anotherLine: " + anotherLine);
        System.out.println(line == anotherLine);         // false
        System.out.println(line.equals(anotherLine));    // false: equals() do not compare the values on StringBuilder objects.
        System.out.println(line.compareTo(anotherLine)); // 0    : Compares the objects lexicographically.
    }

    @Test
    public void question7() {
        var line = new String("-");
        var anotherLine = line.concat("-");
        System.out.print(line == anotherLine);
        System.out.print(" ");
        System.out.print(line.length());    // PRINT: false 1
        Assert.assertTrue(true);
    }
}
