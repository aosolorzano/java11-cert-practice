package com.hiperium.java.cert.practice.tests;

import com.hiperium.java.cert.prep.chapter._16.AutomaticResourceMgmt;
import org.junit.Assert;
import org.junit.Test;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * QUESTION 7:
 *
 * How many constructors in WhaleSharkException compile in the following class?
 * D. Three.
 */
class WhaleSharkException extends Exception {
    public WhaleSharkException() {
        super("Friendly shark!");
    }
    public WhaleSharkException(String message) {
        super(new Exception(new WhaleSharkException()));    // VALID: We pass a Throwable nested exceptions.
    }
    public WhaleSharkException(Exception cause) {}
}

/**
 * QUESTION 11
 */
class PrintCompany {
    class Printer implements Closeable {            // r1
        public void print() {
            System.out.println("This just in!");
        }
        public void close() {}
    }
    public void printHeadlines() {
        // try {Printer p = new Printer()} {        r2 - ERROR: Syntax error. Use parenthesis instead of brackets.
        try (Printer p = new Printer()) {
            p.print();
        }
    }
    public static void main() {
        new PrintCompany().printHeadlines();        // r3
    }
}

/**
 * QUESTION 13
 */
class Light {
    public static void run(String[] v) throws Exception {
        try {
            new Light().turnOn();
        // } catch (RuntimeException v)         y1 ERROR: Variable 'v' is already defined in the scope.
        } catch (RuntimeException e) {
            System.out.println(e);
            throw new IOException();            // y2
        } finally {
            System.out.println("complete");
        }
    }
    public void turnOn() throws IOException {
        new IOException("Not ready");           // y3
    }
}

/**
 * QUESTION 15
 */
class SpellingException extends RuntimeException {}
class SpellChecker {
    public final static void run(String... participants) {
        try {
            if(!"cat".equals("kat")) {
                new SpellingException();            // NOTE: This line does not throw an exception.
            }
        } catch (SpellingException | NullPointerException e) {
            System.out.println("Spelling problem!");
        } catch (Exception e) {
            System.out.println("Unknown Problem!");
        } finally {
            System.out.println("Done!");
        }
    }
}

/**
 * QUESTION 18
 */
class ProblemException extends Exception {
    ProblemException(Exception e) { super(e); }
}
class MajorProblemException extends ProblemException {
    // MajorProblemException(String message) { super(message); }    ERROR: Required type: Exception -- Provided: String.
    // MajorProblemException(String message) { }                    ERROR: There is no default constructor available in 'ProblemException'.
    MajorProblemException(String message) {                         // FIXING ERROR
        super(new Exception(message));
    }
    MajorProblemException(Exception e) { super(e); }
}
class Unfortunate {
    public static void main() throws Exception {
        try {
            System.out.print(1);
            throw new MajorProblemException("Uh oh");
        } catch (ProblemException | RuntimeException e) {
            System.out.print(2);
            try {
                throw new MajorProblemException("yikes");
            } finally {
                System.out.print(3);
            }
        } finally {
            System.out.println(4);
        }
    }

    /**
     * FOR QUESTION 37
     */
    public static void anotherMain() throws Exception {
        try {
            System.out.print(1);
            throw new MajorProblemException(new IllegalStateException());
        } catch (ProblemException | RuntimeException e) {
            System.out.print(2);
            try {
                throw new MajorProblemException(e);
            } finally {
                System.out.print(3);
            }
        } finally {
            System.out.println(4);
        }
    }
}

/**
 * QUESTION 21:
 *
 * Which exception classes, when inserted into the blank in the Problems class, allow the code to compile?
 */
class MissingMoneyException {}
class MissingFoodException {}
class Problems {
    // public void doIHaveAProblem()                                    1. ERROR: Required: Throwable
    //      throws MissingMoneyException, MissingFoodException {}                 Provided: MissingMoneyException
    public void doIHaveAProblem() {
        System.out.println("No problems");
    }
    public static void main() {                                         // throws ___________________
        try {
            final Problems p = new Problems();
            p.doIHaveAProblem();
        // catch (MissingMoneyException | MissingFoodException e) {}    2. ERROR: Required: Throwable
        //                                                                        Provided: MissingMoneyException
        } catch (Exception e) {
            // IMPORTANT: Note that compiler does not complain to add a throw declaration in the main method.
            // It knows beforehand that method 'doIHaveAProblem()' does not throw any checked exception.
            // But, if we add a throw declaration in 'doIHaveAProblem()' method with a checked exception, then
            // we need to add a throw declaration in the main method too, to avoid compilation errors.
            throw e;
        }
    }
}

/**
 * QUESTION 23:
 *
 * Which expressions, when inserted into the blank in the following class, allow the code to compile?
 */
class Beach {
    class TideException extends Exception {}
    public void surf() throws RuntimeException {
        try {
            throw new TideException();
        }
        // catch (___________) {}
        // catch (Exception z) {}                               OK.
        // catch (TideException | IOException z) {}             ERROR: IOException is never thrown in the try block.
        // catch (TideException | Exception z)   {}             ERROR: Types in multi-catch must be disjoint.
        catch (IllegalStateException | TideException z) {}
    }
}

/**
 * QUESTION 25
 */
class Fetch {
    public int play(String name) throws RuntimeException {  // NOTE: Method does not complain to return a value.
        try {
            throw new RuntimeException(name);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public static final void main() throws RuntimeException {
        new Fetch().play("Webby");
        new Fetch().play("Georgette");
    }
}

/**
 * QUESTION 26:
 *
 * What is the output of the following application?
 *
 * D. The code does not compile.
 */
class Organ {
    public void operate() throws IOException {
        throw new RuntimeException("Not supported");
    }
}
class Heart extends Organ {
    // public void operate() throws Exception {}                ERROR: overridden method does not throw 'Exception'.
    public void operate() throws FileNotFoundException {
        System.out.print("beat");
    }
    public static void main() throws Exception {
        try {
            new Heart().operate();
        } finally {
            System.out.print("!");
        }
    }
}

/**
 * QUESTION 28
 */
class Storm {
    public static void main() throws Exception {
        var weatherTracker = new AutoCloseable() {
            public void close() throws RuntimeException {
                System.out.println("Thunder");
            }
        };
        try (weatherTracker) {
            System.out.println("Tracking");
        } catch (Exception e) {
            System.out.println("Lightning");
        } finally {
            System.out.println("Storm gone");
            weatherTracker.close();
        }
    }
}

/**
 * QUESTION 32
 */
class BasketBall {
    public static void main() {
        try {
            System.out.print(1);
            throw new ClassCastException();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.print(2);
        } catch (Throwable ex) {
            System.out.print(3);
        } finally {
            System.out.print(4);
        }
        System.out.print(5);
    }
}

/**
 * QUESTION 34
 */
class ReadSign implements Closeable {
    public void close() {}
    public String get() { return "Hello"; }
}
class MakeSign implements AutoCloseable {
    public void close() {}
    public void send(String message) {
        System.out.print(message);
    }
}
class Translate {
    public static void main() {
        try (ReadSign r = new ReadSign();
             MakeSign w = new MakeSign();) {
            w.send(r.get());
        }
    }
}

/**
 * QUESTION 41:
 *
 * Which statement about the following application is correct?
 *
 * C. The code does not compile because of line w3.
 */
class CarCrash extends RuntimeException {
    CarCrash(Exception e) {}                                // w1
}
class Cars {
    public static void main() throws Exception {            // w2
        try {
            throw new IOException("Auto-pilot error");
        // } catch (Exception | CarCrash e) {}              w3 ERROR: Types in multi-catch must be disjoint: 'CarCrash'
        //                                                            is a subclass of 'Exception'.
        } catch (CarCrash e) {
            throw e;
        } catch (Exception a) {                             // w4
            throw a;
        }
    }
}

/**
 * QUESTION 47:
 *
 * What is the output of the following?
 *
 * D. The code does not compile due to the declaration of IncidentReportException.
 */
class IncidentReportException extends RuntimeException {
    public static void main() throws Exception {
        try {
            // throw new IncidentReportException(new IOException());    ERROR: No constructor found for this sentence.
            throw new IncidentReportException();
        } catch (RuntimeException e) {
            System.out.println(e.getCause());
        }
    }
}

/**
 * QUESTION 48
 */
class Bells {
    class Player implements AutoCloseable {
        @Override public void close() throws RingException {}
    }
    class RingException extends Exception {
        public RingException(String message) {}
    }
    public static void main() throws Throwable {
        try (Player p = null) {                     // VALID: No instance method 'close()' will be called.
            throw new Exception();
        } catch (Exception e) {}
        // catch (_______________________) {}
        // catch (IllegalStateException e) {}       ERROR: Exception 'IllegalStateException' has already been caught.
        // catch (RuntimeException e) {}            ERROR: Exception 'RuntimeException' has already been caught.
        // catch (RingException e) {}               ERROR: Exception 'RingException' has already been caught.
        // catch (SQLException e) {}                ERROR: Exception 'SQLException' has already been caught.
        catch (Error e) {}
    }
}

/**
 * QUESTION 49
 */
class BigCatQ {
    // void roar(int level) throw RuntimeException {}           // ERROR: Unexpected token 'throw'.
    void roar(int level) throws RuntimeException {
        if (level < 3) throw new IllegalArgumentException();
        System.out.print("Roar!");
    }
}
class Lion extends BigCatQ {
    public void roar() {
        System.out.print("Roar!!!");
    }
    void roar(int sound) {
        System.out.print("Meow");
    }
    public static void main() {
        final BigCatQ kitty = new Lion();
        kitty.roar(2);
    }
}

/**
 * QUESTION 52
 */
class Chair {
    public void sit() throws IllegalArgumentException {
        System.out.print("creek");
        throw new RuntimeException();               // IMPORTANT: This sentence is valid.
    }
}
class Stool extends Chair {
    public void sit() throws RuntimeException {     // IMPORTANT: This override is valid.
        System.out.print("thud");
    }
    public static void main() throws Exception {
        try {
            new Stool().sit();
        } finally {
            System.out.print("?");
        }
    }
}

/**
 * QUESTION 50:
 *
 * Which statement about the following program is true?
 *
 * D. The code does not compile.
 */
class MissedCallException extends Exception {}
class PhoneQ {
    static void makeCall() throws RuntimeException {
        throw new ArrayIndexOutOfBoundsException("Call");
    }
    public static void main() {
        try {
            makeCall();
        // } catch (MissedCallException e) {}       ERROR: Exception 'MissedCallException' is never thrown in the try block.
        } finally {
            throw new RuntimeException("Text");
        }
    }
}

/**
 * QUESTION 55
 */
class Pizza {
    Exception order(RuntimeException e) {               // h1: Is not necessary to return an Exception if we throw one.
        throw e;                                        // h2: This is a valid sentence.
    }
    public static void main() {
        var p = new Pizza();
        try {
            p.order(new IllegalArgumentException());    // h3
        } catch(RuntimeException e) {                   // h4
            System.out.print(e);
        }
    }
}

/**
 * QUESTION 59
 */
final class FallenException extends Exception {}
final class HikingGear implements AutoCloseable {
    @Override public void close() throws Exception {
        throw new FallenException();
    }
}
class Cliff {
    public final void climb() throws Exception {
        try (HikingGear gear = new HikingGear()) {
            throw new RuntimeException();
        }
    }
    public static void main() {
        try {
            new Cliff().climb();
        } catch (Throwable t) {
            System.out.println(t);                              // e1: java.lang.RuntimeException
            System.out.println(t.getSuppressed().length);       // PRINT: 1
        }
    }
}

/**
 * QUESTION 60
 */
class WhackAnException {
    public static void main() {
        try {
            throw new ClassCastException();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            throw new NullPointerException();
        } finally {
            throw new RuntimeException();
        }
    }
}



/**
 * Exception Handling:
 *      - Handle exceptions using try/catch/finally clauses, try‐with‐resource, and multi‐catch statements.
 *      - Create and use custom exceptions.
 */
public class Chapter4Test {

    /**
     * Assuming Scanner is a valid class that implements AutoCloseable, what is the expected output of the following
     * code?
     */
    public void question6() {
        AutomaticResourceMgmt.usingScanner();
    }

    /**
     * What is the output of the following application?
     *
     * D. ABD followed by a stack trace.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void question8() {
        try {
            System.out.print('A');
            throw new ArrayIndexOutOfBoundsException();
        } catch (RuntimeException r) {
            System.out.print('B');
            throw r;
        } catch (Exception e) {
            System.out.print('C');
        } finally {
            System.out.println('D');
        }
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question11() {
        PrintCompany.main();                // Fixing the error, it prints: This just in!.
        Assert.assertTrue(true);
    }

    /**
     * How many lines of text does the following program print?
     *
     * C. The code does not compile because of line y1.
     */
    @Test
    public void question13() {
        try {
            Light.run(null);                // Fixing the error, it prints: complete.
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    /**
     * How many lines of text does the following program print?
     *
     * A. One.
     */
    @Test
    public void question15() {
        SpellChecker.run();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     *
     * A. One.
     */
    @Test
    public void question18() {
        try {
            Unfortunate.main();     // Fixing the error, it prints: 1234 plus the exception message.
        } catch (Exception e) {
            System.out.println(e.getMessage());     // PRINT: yikes
        }
        Assert.assertTrue(true);
    }

    /**
     * Which statement about the following program is correct?
     *
     * A. One exception is thrown to the caller at runtime.
     */
    @Test(expected = RuntimeException.class)
    public void question25() {
        Fetch.main();
    }

    /**
     * What is the result of compiling and executing the following class?
     *
     * D. It prints four lines.
     */
    @Test
    public void question28() throws Exception {
        Storm.main();                               // PRINT: 'Tracking', 'Thunder', 'Storm gone' and 'Thunder'.
        Assert.assertTrue(true);
    }

    /**
     * QUESTION 29:
     *
     * How many of the following are valid exception declarations?
     *
     * C. Two.
     */
    class ErrorQ extends Exception {}
    class _X  extends IllegalArgumentException {}
    class _X_ extends IllegalArgumentException {}
    class X_  extends IllegalArgumentException {}
    // class 2BeOrNot2Be extends RuntimeException {}                    ERROR: Syntax error.
    // class NumberException<Integer> extends NumberFormatException {}  ERROR: Generic class may not extend 'Throwable'.
       class NumberException<Integer> {}
    // interface Worry implements NumberFormatException {}              ERROR: No 'implements' clause allowed for interfaces.

    /**
     * What is the output of the following application?
     */
    @Test
    public void question32() {
        BasketBall.main();                  // PRINT: 1345
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question34() {
        Translate.main();                   // PRINT: Hello
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question37() {
        try {
            Unfortunate.anotherMain();      // PRINT: 1234 followed by an exception stack trace.
        } catch (Exception e) {
            e.printStackTrace();            // Print the MajorProblemException followed by 2 'Caused By' blocks.
        }
        Assert.assertTrue(true);
    }

    /**
     * Which expression, when inserted into the blank in the following class, allows the code to compile?
     *
     * A. Error r.
     */
    @Test
    public void question48() {
        try {
            Bells.main();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     *
     * F. None of the above.
     */
    @Test
    public void question49() {
        Lion.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question52() throws Exception {
        Stool.main();                               // PRINT: thud?
        Assert.assertTrue(true);
    }

    /**
     * What is the result of compiling and running the following application?
     */
    @Test
    public void question55() {
        Pizza.main();                       // PRINT: java.lang.IllegalArgumentException
        Assert.assertTrue(true);
    }

    /**
     * Given the following application, what is the name of the class printed at line e1?
     */
    @Test
    public void question59() {
        Cliff.main();                       // PRINT: java.lang.RuntimeException
        Assert.assertTrue(true);
    }

    /**
     * Given the following application, which specific type of exception will be printed in the stack trace at runtime?
     *
     * D. RuntimeException
     */
    @Test(expected = RuntimeException.class)
    public void question60() {
        WhackAnException.main();
    }
}
