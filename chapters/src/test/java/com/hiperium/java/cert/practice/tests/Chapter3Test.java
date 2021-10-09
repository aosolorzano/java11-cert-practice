package com.hiperium.java.cert.practice.tests;

import com.hiperium.java.cert.practice.util.Hooper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * QUESTION 1
 *
final */ class Story {
    void recite(int chapter) throws Exception {}
}
class Adventure extends Story {
    @Override
    final void recite(final int chapter) {
        switch (chapter) {
            case 2: System.out.println(9);
            default: System.out.println(3);
        }
    }
}

/**
 * QUESTION 9
 */
class Phone {
    private int size;
    public Phone(int size) { this.size = size; }
    public static void sendHome(/* final */ Phone p, int newSize) {
        p = new Phone(newSize);                  // We create a new object, but the original reference 'p' it keeps.
        p.size = 4;
    }
    public static void main() {
        final var phone = new Phone(3);     // final only applies in this method scope.
        // phone = new Phone(5);                // ERROR: Cannot assign a value to final variable 'phone'
        sendHome(phone,7);
        System.out.print(phone.size);
    }
}

/**
 * QUESTION 13
 */
class Car {
    private static void drive() {
        // static { System.out.println("static"); }  ERROR: Not allowed.
        System.out.println("fast");
        { System.out.println("faster"); }
    }
    public static void main() {
        drive();
        drive();
    }
}

/**
 * QUESTION 24
 */
interface Pump { void pump(double psi); }
interface Bend /* extends Pump */ { void bend(double tensileStrength); }
class Robot {
    public static final void apply(Bend instruction, double input) {  // 'static final' combination are allowed in class methods.
        instruction.bend(input);
    }
}

/**
 * QUESTION 45
 */
class Bottle {
    static class Ship {
        private enum Sail {             // w1
            TALL { protected int getHeight() { return 100; }},
            SHORT { protected int getHeight() { return 2; }};
            protected abstract int getHeight();
        }
        public Sail getSail() {
            return Sail.TALL;
        }
    }
    public static void main() {
        var bottle = new Bottle();
        // Ship q = bottle.new Ship();     w2: A static nested class must be instantiated in a static manner.
        Ship q = new Bottle.Ship();
        System.out.print(q.getSail());
    }
}

/**
 * QUESTION 55
 */
abstract interface CanSwim {
    public void swim(final int distance);
}
class Turtle {
    final int distance = 2;
    public static void main() {
        final int distance = 3;
        // CanSwim seaTurtle = { ... };         SYNTAX'S ERROR
        CanSwim fixed = new CanSwim() {
            final int distance = 5;
            @Override
            public void swim(final int distance) {
                System.out.println(distance);
            }
        };
        fixed.swim(7);
    }
}

/**
 * QUESTION 67
 */
class Cinema {
    private String name = "Sequel";
    public Cinema(String name) {
        this.name = name;
    }
}
class Movie extends Cinema {
    private String name = "adaptation";
    public Movie(String movie) {
        super(movie);           // Cinema does not have a default constructor. We must specify one here to compile.
        this.name = "Remake";
    }
    public static void main() {
        System.out.print(new Movie("Trilogy").name);
    }
}

/**
 * QUESTION 81
 */
class Ship {
    protected int weight = 3;
    private int height = 5;
    public int getWeight() { return weight; }
    public int getHeight() { return height; }
}
class Rocket extends Ship {
    public int weight = 2;
    public int height = 4;
    public void printDetails() {
        // System.out.print(super.getWeight() + ", " + super.height);    ERROR: 'height' has private access in class Ship.
        System.out.print(super.getWeight() + ", " + super.getHeight());
    }
    public static final void main() {
        new Rocket().printDetails();
    }
}

/**
 * QUESTION 83
 */
interface Speak {
    public default int getVolume() { return 20; }
}
interface Whisper {
    public default int getVolume() { return 10; }
}
class Debate implements Speak, Whisper {
    // public int getVolume() { return Whisper.super.getVolume(); }
    public int getVolume() { return 30; }
    public static void main() {
        var d = new Debate();
        // System.out.println(Whisper.super.getVolume()); ERROR: Whisper is not an enclosing class.
        d.getVolume();
    }
}

/**
 * QUESTION 85
 */
class Bici {
    static {
        System.out.println("static");
    }
    private static void drive() {
        System.out.println("fast");
    }
    { System.out.println("faster"); }
    public static void main() {
        drive();
        drive();
    }
}

/**
 * QUESTION 92
 */
class Matrix {
    private int level = 1;
    class Deep {
        private int level = 2;
        class Deeper {
            private int level = 5;
            public void printReality(int level) {
                System.out.print(this.level + " ");                 // PRINT: 5
                System.out.print(Matrix.Deep.this.level + " ");     // PRINT: 2
                System.out.print(Deep.this.level);                  // PRINT: 2
            }
        }
    }
    public static void main() {
        Matrix.Deep.Deeper simulation = new Matrix().new Deep().new Deeper();
        simulation.printReality(6);
    }
}

/**
 * QUESTION 105
 */
class Dolls {
    // public int num() { return 3.0; }                 ERROR: Required type: int - Provided: double.
    // public int size() { return 5L; }                 ERROR: Required type: int - Provided: long.
    public int size() { return 5; }
    public int num() { return 3; }

    public void nested() { nested(2, true); }
    public int nested(int w, boolean h) { return 0; }
    public int nested(int level) { return level + 1; }

    public static void main() {
        // System.out.println(new Dolls().nested());    ERROR: Cannot resolve method 'println(void)'.
        System.out.println(new Dolls().nested(5));
    }
}

/**
 * QUESTION 116
 */
class Woods {
    static class Tree {}
    public static void main() {
        int heat = 2;
        int water = 10 - heat;
        final class Oak extends Tree {      // p1: Valid operation.
            public int getWater() {
                // return water;            // p2 ERROR: Variable 'water' needs to be final or effectively final.
                return 5;
            }
        }
        Oak oak = new Oak();
        System.out.print(oak.getWater());
        water = 0;                          // This line makes variable 'water' does not be effectively final.
    }
}

/**
 * QUESTION 121
 */
class Problem extends Exception {}
abstract class Danger {
    protected abstract void isDanger() throws Problem;  // m1
}
class SeriousDanger extends Danger {                    // m2
    // protected void isDanger() throws Exception {}       m3 ERROR: overridden method does not throw 'Exception'.
    protected void isDanger() throws Problem {
        throw new RuntimeException("Is Danger...");     // m4 VALID: We can throw RTE despite the method can throw checked ones.
    }
    public static void main() throws Throwable {        // m5 VALID: Throwable is the superclass of all errors and exceptions.
        // var sd = new SeriousDanger().isDanger();        m6 ERROR: variable initializer is 'void'.
        new SeriousDanger().isDanger();
    }
}

/**
 * QUESTION 125
 */
interface Drive {
    int SPEED = 5;
    default int getSpeed() { return SPEED; }
}
interface Hover {
    int MAX_SPEED = 10;
    default int getSpeed() { return MAX_SPEED; }
}
// class CarTest implements Drive, Hover {}  ERROR: Class 'Car' inherits unrelated defaults types for getSpeed() from Drive and Hover.
class CarTest implements Drive, Hover {
    @Override
    public int getSpeed() { return Drive.super.getSpeed() + Hover.super.getSpeed(); }
    public static void main() {
        class RaceCar extends CarTest {
            @Override public int getSpeed() { return 15; }
        };
        System.out.print(new RaceCar().getSpeed());
    }
}

/**
 * QUESTION 129
 */
abstract class Trapezoid {
    private int getEqualSides() { return 0; }
}
abstract class Rectangle extends Trapezoid {
    public static int getEqualSides() { return 2; } // x1
}
final class Square extends Rectangle {
    // public int getEqualSides() { return 4; }        x2 ERROR: Instance method cannot override static method 'getEqualSides()'.
    public static void main() {
        final Square myFigure = new Square();       // x3
        System.out.print(myFigure.getEqualSides());
    }
}

/**
 * QUESTION 134
 */
class Gift {
    // private final Object contents;                   ERROR: Variable 'contents' might not have been initialized.
    private final Object contents = new Object();
    protected Object getContents() {
        return contents;
    }
    protected void setContents(Object contents) {
        // this.contents = contents;                    ERROR: Cannot assign a value to final variable 'contents'.
    }
    public void showPresent() {
        System.out.print("Your gift: " + contents);
    }
    public static void main() {
        Gift gift = new Gift();
        gift.setContents(gift);
        gift.showPresent();
    }
}

/**
 * QUESTION 140
 */
class MathQ {
    public final double secret = 2;
}
class ComplexMath extends MathQ {
    public final double secret = 4;
}
class InfiniteMath extends ComplexMath {
    public final double secret = 8;
    public static void main() {
        MathQ math = new InfiniteMath();
        System.out.print(math.secret);
    }
}

/**
 * QUESTION 142
 */
class Penguin {
    private int volume = 1;
    private class Chick {
        // private static int volume = 3;   ERROR: Static declarations in inner classes are not supported.
        private int volume = 3;
        void chick() {
            System.out.print("Honk(" + Penguin.this.volume + ")!");  // PRINT: Honk(1)!
        }
    }
    public static void main() {
        Penguin pen = new Penguin();
        final Penguin.Chick littleOne = pen.new Chick();
        littleOne.chick();
    }
}

/**
 * QUESTION 162
 */
class Bond {
    private static int price = 5;
    public boolean sell() {
        if (price < 10) {
            price++;
            return true;
        } else // if (price >= 10) { return false; }
            return false;
    }                                                   // ERROR: Missing return statement
    public static void main() {
        new Bond().sell();
        new Bond().sell();
        new Bond().sell();
        System.out.print(price);
    }
}

/**
 * QUESTION 165
 */
abstract class CarsTwo {
    static { System.out.print("1"); }
    public CarsTwo(String name) {   // Abstract classes cannot be instantiated, but child classes can call public constructors.
        super();                    // VALID
        System.out.print("2");
    }
    { System.out.print("3"); }
}
class BlueCar extends CarsTwo {
    { System.out.print("4"); }
    public BlueCar() {
        super("blue");
        System.out.print("5");
    }
    public static void main() {
        new BlueCar();
    }
}

/**
 * QUESTION 178
 */
class Dragon {
    boolean scaly;
    static final int gold;
    Dragon protectTreasure(int value, boolean scaly) {
        scaly = true;
        return this;
    }
    static void fly(boolean scaly) {
        scaly = true;
    }
    int saveTheTreasure(boolean scaly) {
        return this.gold;
    }
    static void saveTheDay(boolean scaly) {
        // this.gold = 0;                       ERROR: 'this' cannot be referenced from a static context.
        // gold = 0;                            ERROR: Cannot assign a value to final variable 'gold'.
    }
    static { gold = 100; }
}

/**
 * QUESTION 182
 */
interface Planet {
    // int circumference;                       ERROR: Variable 'circumference' might not have been initialized.
    //                                                 Remember, interface variables are STATIC FINAL by default.
    public static final int circumference = 100;
    public abstract void enterAtmosphere();
    public default int getCircumference() {
        enterAtmosphere();
        return circumference;
    }
    private static void leaveOrbit() {
        var earth = new Planet() {
            public void enterAtmosphere() {}    // We must override abstract method 'enterAtmosphere()'.
        };
        earth.getCircumference();
    }
}
class PlanetTest implements Planet {
    @Override
    public void enterAtmosphere() {
        System.out.println("Entering to the atmosphere...");
    }
}

/**
 * QUESTION 184
 */
class Husky {
    { this.food = 10; }
    { this.toy = 2; }
    private final int toy;
    private static int food;
    public Husky(int friend) {
        this.food += friend++;
        // this.toy -= friend--;                ERROR: Variable 'toy' might already have been assigned.
    }
    public static void main() {
        var h = new Husky(2);
        System.out.println(h.food + "," + h.toy);
    }
}

/**
 * QUESTION 188
 */
interface Tasty {
    default void eat() { System.out.print("Spoiled!"); }
}
class ApplePicking {
    public static void main() {
        var apple = new Tasty() {
          // void eat() { System.out.print("Yummy!"); }         ERROR: attempting to assign weaker access privileges.
          @Override
          public void eat() { System.out.print("Yummy!"); }
        };
    }
}

/**
 * QUESTION 191
 */
interface Tool {
    void use(int fun);
}
abstract class Childcare {
    abstract void use(int fun);
}
final class Stroller extends Childcare implements Tool {
    final public void use(int fun) {                    // Declaring a method 'final' in a 'final' class is redundant.
        int width = 5;
        class ParkVisit {                               // Declaring a local class 'ParkVisit'.
            int getValue() { return width + fun; }
        }
        System.out.print(new ParkVisit().getValue());   // Creating an instance of 'ParkVisit'.
    }
}

/**
 * QUESTION 194
 */
class RainForest extends Forest {
    // public RainForest(long treeCount) {}      ERROR: There is no default constructor in 'Forest'.
    public RainForest(long treeCount) {
        super(treeCount);                     // FIXED: We need to call a no default constructor on 'Forest'.
        this.treeCount = treeCount + 1;
    }
    public static void main() {
        System.out.print(new RainForest(5).treeCount);
    }
}
class Forest {
    public long treeCount;
    public Forest(long treeCount) {
        this.treeCount = treeCount + 2;
    }
}

/**
 * QUESTION 196
 *
 * Given that Short and Integer extend Number directly, what type can be used to fill in the blank in the following
 * class to allow it to compile?
 */
interface Horn {
    public Integer play();
}
abstract class Woodwind {
    public Short play() {
        return 3;
    }
}
final class Saxophone {
// final class Saxophone extends Woodwind implements Horn {
    // public _______ play() { return null; }

    // public Short   play() {}     ERROR: clashes with 'Horn';     attempting to use incompatible return type.
    // public Integer play() {}     ERROR: clashes with 'Woodwind'; attempting to use incompatible return type.
    // public Number  play() {}     ERROR: clashes with 'Woodwind'; attempting to use incompatible return type.
    // public Object  play() {}     ERROR: clashes with 'Woodwind'; attempting to use incompatible return type.
}

/**
 * QUESTION 198
 */
enum Proposition {
    // TRUE(1)       { String getNickName() { return "RIGHT"; }},           ERROR: attempting to assign weaker access.
    TRUE(1)    { public String getNickName() { return "RIGHT"; }},
    FALSE(2)   { public String getNickName() { return "WRONG"; }},
    // UNKNOWN(3)    { public String getNickName() { return "LOST"; }}      ERROR: semicolon ';' required.
    UNKNOWN(3) { public String getNickName() { return "LOST"; }};

    public int value;
    Proposition(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
    protected abstract String getNickName();
}

/**
 * QUESTION 200
 */
class Grasshopper extends Hooper {
    public void move() {
        hop();              // p1 NOTE: Only 'Grasshopper' can call 'hop()' method, because it is extending 'Hooper'.
    }
}
class HopCounter {
    public static void main(String[] args) {
        var hopper = new Grasshopper();
        hopper.move();      // p2
        // hopper.hop();    // p3 ERROR: 'hop()' has protected access in 'com.hiperium.java.cert.practice.aux.Hooper'.
    }
}

/**
 * QUESTION 209
 */
interface Ready {
    static int first = 2;
    final short DEFAULT_VALUE = 10;
    GetSet go = new GetSet();                   // n1 ===>>> OK
}
class GetSet implements Ready {
    int first = 5;
    static int second = DEFAULT_VALUE;          // n2 ===>>> OK
    // static int third = first;                ERROR: Non-static field 'first' cannot be referenced from a static context.
    static int third = Ready.first;
    int fourth = first + 1;

    public static void main() {
        var r = new Ready() {};
        System.out.print(r.first);              // n3 ===>>> OK
        System.out.print(" " + second);         // n4 ===>>> OK
    }
}

/**
 * QUESTION 212
 *
 class Rotorcraft                       ERROR: Class must be declared abstract or implement abstract method 'fly()'. */
abstract class Rotorcraft {
    protected final int height = 5;
    abstract int fly();              // NOTE: Method 'fly()' has package-private access
}
interface CanFly {}
class Helicopter extends Rotorcraft implements CanFly {
    private int height = 10;
    protected int fly() {                                // NOTE: It can be protected, but not private.
        return super.height;
    }
    public static void main() {
        // Helicopter h = (Helicopter) new Rotorcraft();    ERROR: 'Rotorcraft' is abstract; cannot be instantiated.
    }
}

/**
 * QUESTION 213
 */
interface Alex {
    default void write() { System.out.print("1"); }     // NOTE: Method with 'public' access by default.
    static void publish() {}
    void think();                                       // NOTE: Method with 'public' access and 'abstract' by default.
    private int process() { return 80; }
}
interface Michael {
    default void write() { System.out.print("2"); }     // NOTE: Method with 'public' access by default.
    static void publish() {}
    void think();                                       // NOTE: Method with 'public' access and 'abstract' by default.
    private int study() { return 100; }
}
class Twins implements Alex, Michael {
    // void write() { System.out.print("3"); }          ERROR: attempting to assign weaker access privileges.
    public void write() { System.out.print("3"); }
    static void publish() {}
    // void think() {                                   ERROR: attempting to assign weaker access privileges.
    public void think() {
        System.out.print("Thinking...");
    }
}

/**
 * QUESTION 214
 */
class Electricity {
    interface Power {}
    public static void main() {
        class Source implements Power {};
        final class Super extends Source {};
        // var start = new Super() {};                  ERROR: Cannot inherit from final.
        var end = new Source() {
            // static boolean t = true;                 ERROR: Static declarations in inner classes are not supported.
        };
    }
}

/**
 * QUESTION 215
 */
class ReadyQuestion {
    protected static int first = 2;
    private final short DEFAULT_VALUE = 10;             // NOTE: Non-static field variable declaration.
    private static class GetSet {
        int first = 5;
        // static int second = DEFAULT_VALUE;           ERROR: 'DEFAULT_VALUE' cannot be referenced from a static context.
        static int second = 20;
    }
    private GetSet go = new GetSet();
    public static void main() {
        ReadyQuestion r = new ReadyQuestion();
        System.out.print(r.go.first);
        System.out.print(", " + r.go.second);
    }
    public void ReadyQuestion() {
        // super();                                     ERROR: Call to 'super()' must be first statement in constructor body.
    }
}

/**
 * QUESTION 219
 */
abstract class Ball {
    protected final int size;
    public Ball(int size) {
        this.size = size;
    }
}
interface Equipment {}
class SoccerBall extends Ball implements Equipment {
    public SoccerBall() {
        super(5);
    }
    public Ball get() { return this; }
    public static void main() {
        var equipment = (Equipment) (Ball) new SoccerBall().get();
        System.out.println("Var equipment class: " + equipment.getClass().getSimpleName());  // PRINT: SoccerBall.
        System.out.println(((SoccerBall) equipment).size);                                   // PRINT: 5.
    }
}

/**
 * QUESTION 220
 */
interface LongQuestion{     // It can be named Long, but the Chapter3Test class will not compile.
    Number length();
}
class Elephant {
    public class Trunk implements LongQuestion {
        public Number length() { return 6; }    // k1
    }
    public class MyTrunk extends Trunk {        // k2:  NOTE THAT THIS IS AN INNER CLASS, NOT A STATIC ONE.
        public Integer length() { return 9; }   // k3
    }
    public static void charge() {
        // System.out.print(new MyTrunk().length());    ERROR: 'this' cannot be referenced from a static context.
        // MyTrunk obj = new MyTrunk();                 NOTE: If 'MyTrunk' was declared static, then this line compiles.
    }
    public static void main() {
        new Elephant().charge();                // k4
    }
}

/**
 * **********************************   **********************************   **********************************
 * **********************************   **********************************   **********************************
 * **********************************   **********************************   **********************************
 *
 * Java Object‐Oriented Approach:
 *      - Declare and instantiate Java objects including nested class objects, and explain objects' lifecycles
 *        (including creation, de-referencing by reassignment, and garbage collection).
 *      - Define and use fields and methods, including instance, static and overloaded methods.
 *      - Initialize objects and their members using instance and static initializer statements and constructors.
 *      - Understand variable scopes, apply encapsulation and make objects immutable.
 *      - Create and use subclasses and superclasses, including abstract classes.
 *      - Utilize polymorphism and casting to call methods, differentiate object type versus reference type.
 *      - Create and use interfaces, identify functional interfaces, and utilize private, static, and default methods.
 *      - Create and use enumerations.
 *
 * ***********************************   **********************************   **********************************
 * ***********************************   **********************************   **********************************
 * ***********************************   **********************************   **********************************
 */
public class Chapter3Test {

    /**
     * What is the output of the following application?
     * <p>
     * R./ The code does not compile because Story class its final and cannot be extended by Adventure class.
     */
    @Test
    public void question1() {
        var bedtime = new Adventure();
        bedtime.recite(2);
        Assert.assertTrue(true);
    }

    @Test
    public void question9() {
        Phone.main();
        Assert.assertTrue(true);
    }

    @Test
    public void question13() {
        Car.main();
        Assert.assertTrue(true);
    }

    /**
     * Which statements about static interface methods are correct? (Choose three.)
     */
    interface Question14 {
                static void defaultAccess()     {}
        private static void privateAccess()     {}
        public  static void publicAccess()      {}
     // public  static final void finalAccess() {}  ERROR: Illegal combination of modifiers: 'static' and 'final'.
     // protected static void protectedAccess() {}  ERROR: Modifier 'protected' not allowed here
    }

    /**
     * What is the output of the following application?
     *
     * R./ The code does not compile because Bend is not a functional interface.
     */
    @Test
    public void question24() {
        final Robot robot = new Robot();
        // robot.apply(x -> System.out.println(x+" bent!"), 5);     WARN: Static member accessed via instance reference.
        Robot.apply(x -> System.out.println(x+" bent!"), 5);  // PRINT: 5.0 bent!
        Assert.assertTrue(true);
    }

    /**
     * Which statement about the following interface is correct?
     *
     * R./ The code does not compile because of line k2.
     */
    public interface Question27 {
        String DEFAULT = "Diving!";     // k1
        abstract int breath();
        private static void stroke() {
            // if (breath() == 1)       // k2: Non-static method 'breath()' cannot be referenced from a static context.
            System.out.print(dive());   // k3
        }
        static String dive() {
            return DEFAULT;             // k4
        }
    }

    /**
     * What is true of the following code? (Choose three.)
     *
     * A. It compiles as is.
     * C. Removing line 2 (1st enum Baby) would create an additional compiler error.
     * E. Removing the static modifier on line 3 (2nd enum Baby) would create an additional compiler error.
     */
    enum Baby { EGG }
    static class Chick {    // E. If we remove static access, then an error appears like follows:
        enum Baby { EGG }   // ERROR: Static declarations in inner classes are not supported.
    }
    @Test
    public void question33() {
        boolean match = false;
        Baby egg = Baby.EGG;
        switch (egg) {
            case EGG: match = true;
        }
        Assert.assertTrue(true);
    }

    /**
     * How many lines will not compile?
     */
    private void printVarargs(String... names) {
        System.out.println(Arrays.toString(names));
    }
    private void printArray(String[] names) {
        System.out.println(Arrays.toString(names));
    }
    @Test
    public void question40() {
        printVarargs("Arlene");         // PRINT: [Arlene]
        printVarargs(new String[]{"Bret"});     // PRINT: [Bret]
        printVarargs(null);             // PRINT: null
        // printArray("Cindy");                    ERROR
        printArray(new String[]{"Don"});        // PRINT: [Don]
        printArray(null);                // PRINT: null
        Assert.assertTrue(true);
    }

    /**
     * What is the minimum number of lines that need to be removed to make this code compile?
     * <p>
     * R./ The code compiles as is.
     */
    @FunctionalInterface
    public interface Question42 {
        public static void baseball() {}
        private static void soccer() {}
        default void play() {}
        void fun();
        // void run();  IF UNCOMMENT: Multiple non-overriding abstract methods found in interface.
    }

    /**
     * Which statement about the following program is correct? (Choose two.)
     * <p>
     * B. The code does not compile because of line u2.
     * D. The code does not compile because of line u4.
     */
    class Leader {}
    class Follower {}
    abstract class Dancer {
        public Leader getPartner() { return new Leader(); }
        abstract public Leader getPartner(int count);               // u1
    }
    public abstract class Question50 extends Dancer {
        @Override public Leader getPartner(int x) { return null; }
        // public Follower getPartner() { return new Follower(); }  u2: Attempting to use incompatible return type.
        public void run() {
            // new SwingDancer().getPartner();                      u4: 'Question50' is abstract; cannot be instantiated.
        }
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question55() {
        Turtle.main();
        Assert.assertTrue(true);
    }

    /**
     * Which is the first declaration to not compile?
     * <p>
     * R./ The DesertTortoise (Question58) interface does not compile.
     */
    interface CanBurrow { public abstract void burrow(); }
    @FunctionalInterface interface HasHardShell extends CanBurrow {}
    abstract class Tortoise implements HasHardShell {
        public abstract int toughness();
    }
    public class Question58 extends Tortoise {
        public int toughness() { return 11; }
        @Override public void burrow() {}       // Class Question58 must implement this method too to compile.
    }

    /**
     * What is the output of the following code?
     *
     * Note: This example deals with method signatures rather than polymorphism. Since hop() methods are static,
     * the precise method called depends on the reference type rather than the actual type of the object.
     */
    static interface Rabbit { }
    static class FlemishRabbit implements Rabbit { }
    private void hop(Rabbit r) {
        System.out.println("hop");
    }
    private void hop(FlemishRabbit r) {
        System.out.println("HOP");
    }
    @Test
    public void question69() {
        Rabbit r1 = new FlemishRabbit();
        FlemishRabbit r2 = new FlemishRabbit();
        hop(r1);                                    // PRINT: hop
        hop(r2);                                    // PRINT: HOP
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     * <p>
     * R./ The code does not compile.
     */
    public void playMusic() {
        System.out.print("Play!");
    }
    // private static void playMusic() { System.out.print("Music!"); }  ERROR: 'playMusic()' is already defined.
    private static void playMusic(String song) {
        System.out.print(song);
    }
    @Test
    public void question72() {
        this.playMusic();
        Assert.assertTrue(true);
    }

    /**
     * How lines of the following code do not compile?
     */
    interface Flavor {
        public default void happy() {
            printFlavor("Rocky road");
        }
        private static void excited() {
            // printFlavor("Peanut butter");        ERROR: Non-static method cannot be referenced from a static context.
        }
        private void printFlavor(String f) {
            System.out.println("Favorite flavor is: " + f);
        }
        public static void sad() {
            // printFlavor("Butter pecan");         ERROR: Non-static method cannot be referenced from a static context.
        }
    }
    public class Question74 implements Flavor {
        @Override
        public void happy() {
            // printFlavor("Cherry chocolate");     ERROR: 'printFlavor()' has private access.
        }
    }

    /**
     * What is the output of the following application?
     *
     * R./ The code does not compile.
     */
    public enum Snow {
        BLIZZARD, SQUALL, FLURRY;   // In this case, this line must ends with a semicolon to compile.
        @Override
        public String toString() {
            return "Sunny";
        }
    }
    @Test
    public void question78() {
        System.out.print(Snow.BLIZZARD.ordinal() + " ");            // PRINT: 0
        System.out.println(Snow.valueOf("flurry".toUpperCase()));   // PRINT: Sunny
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the Rocket program?
     *
     * R./ The code does not compile.
     */
    @Test
    public void question81() {
        Rocket.main();
        Assert.assertTrue(true);
    }

    /**
     * Fill in the blank with the line of code that allows the program to compile and print 10 at runtime.
     *
     * R./ None of the above.
     *
     * Note: If we want resolve this question, we need to change de implementation of overridden method getVolume()
     * on Debate class to call the Whisper version of the method using Whisper.super.getVolume()
     */
    @Test
    public void question83() {
        Debate.main();
        Assert.assertTrue(true);
    }


    /**
     * How many lines does the following code output?
     * R./
     * static.
     * fast.
     * fast.
     */
    @Test
    public void question85() {
        Bici.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     * <p>
     * R./ 5 2 2
     */
    @Test
    public void question92() {
        Matrix.main();
        Assert.assertTrue(true);
    }

    /**
     * Given that Integer and Long are direct subclasses of Number, what type can be used to fill in the blank in the
     * following class to allow it to compile?
     *
     * R./ Long.
     *
     * Note: The play() method is overridden in Question93 for both: MusicCreator and StringInstrument, so the return
     * type must be covariant with both.
     *
     * Integer is a subclass of Number, meaning the override for MusicCreator is valid, but Integer it is not
     * a subclass of Long used in StringInstrument. Therefore, using Integer would cause the code not compile.
     *
     * Number is compatible with the version of the method in MusicCreator, but not with the version in StringInstrument,
     * because Number is a superclass of Long, not a subclass of it.
     */
    interface MusicCreator {
        public Number play();
    }
    abstract class StringInstrument {
        public Long play() { return 3L; }
    }
    public class Question93 extends StringInstrument implements MusicCreator {
        // @Override public Number play() { return null; }      ERROR: attempting to use incompatible return type.
        // @Override public Integer play() { return null; }     ERROR: attempting to use incompatible return type.

        /**
         * Long is a subclass of Number, and therefore, it is covariant with the version in MusicCreator.
         * Since it matches the type in StringInstrument, it can be used here.
         */
        @Override public Long play() { return 1L; }
    }

    /**
     * What is the output of the RightTriangle program?
     * <p>
     * R./ The code does not compile due to line g3.
     */
    abstract class Triangle {
        abstract String getDescription();                   // Package-private abstract method.
    }
    abstract class IsoRightTriangle extends Question94 {    // g1
        public String getDescription() { return "irt"; }    // If getDescription() were declared private here, then an error occur.
    }
    public class Question94 extends Triangle {
        protected String getDescription() { return "rt"; }  // g2: If getDescription() were declared public here, then an error occur.
        public /* static */ void main(String... args) {
            // final var shape = new IsoRightTriangle();    // g3: 'IsoRightTriangle' is abstract; cannot be instantiated.
        }
    }

    /**
     * What is the output of the following program?
     */
    interface Dog {
        private void buryBone() { chaseTail(); }
        // private static void wagTail() { chaseTail(); }   ERROR: Non-static method 'chaseTail()' cannot be referenced from a static context.
        public default String chaseTail() { return "So cute!"; }
    }
    public class Puppy implements Dog {
        public String chaseTail() throws IllegalArgumentException { // We can add RuntimeExceptions, but not checked exceptions.
            throw new IllegalArgumentException("Too little!");
        }
    }
    @Test
    public void question95() {
        var p = new Puppy();
        try {
            System.out.print(p.chaseTail());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        Assert.assertTrue(true);
    }

    /**
     * Given the following structure, which snippets of code return true? (Choose three.)
     */
    interface Friendly {}
    abstract class Dolphin implements Friendly {}
    class Animal implements Friendly {}
    class Whale extends Object {}
    public class Fish {}
    class Coral extends Animal {}

    @Test
    public void question101() {
        System.out.println("new Coral() instanceof Friendly = " + (new Coral() instanceof Friendly));   // TRUE
        System.out.println("null        instanceof Object   = " + (null instanceof Object));            // FALSE
        System.out.println("new Coral() instanceof Object   = " + (new Coral() instanceof Object));     // TRUE
        System.out.println("new Fish()  instanceof Friendly = " + (new Fish() instanceof Friendly));    // FALSE
        System.out.println("new Whale() instanceof Object   = " + (new Whale() instanceof Object));     // TRUE
        Assert.assertTrue(true);
    }

    /**
     * What is true of the following code?
     * <p>
     * R./ 20 20 75
     */
    enum AnimalEnum {
        CHICKEN(21), PENGUIN(75);         // Sets default values.
        private int numDays;
        private AnimalEnum(int numDays) {
            this.numDays = numDays;
        }
        public int getNumDays() { return numDays; }
        public void setNumDays(int numDays) { this.numDays = numDays; }
    }
    @Test
    public void question102() {
        AnimalEnum chicken = AnimalEnum.CHICKEN;
        chicken.setNumDays(20);                             // OK: Sets a new value to an enum variable.
        System.out.print(chicken.getNumDays());             // PRINT: 20
        System.out.print(" ");
        System.out.print(AnimalEnum.CHICKEN.getNumDays());  // PRINT: 20
        System.out.print(" ");
        System.out.print(AnimalEnum.PENGUIN.getNumDays());  // PRINT: 75
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     *
     * R./ The Bank class does not compile.
     */
    enum Currency {
        DOLLAR, YEN, EURO
    }
    abstract class Provider {
        protected Currency c = Currency.EURO;
    }
    public class Bank extends Provider {
        protected Currency c = Currency.DOLLAR;
    }
    @Test
    public void question104() {
        int value = 0;
        switch(new Bank().c) {      // We use the Currency.DOLLAR value defined Bank class.
            // case 0:              ERROR: Required type: Currency - Provided: int. We can use new Bank().c.ordinal() instead.
            case DOLLAR:
                value--; break;
            // case 1:              ERROR: Required type: Currency - Provided: int. We can use new Bank().c.ordinal() instead.
            case EURO:
                value++; break;
        }
        System.out.print(value);    // PRINT: -1
        Assert.assertTrue(true);
    }

    /**
     * How many lines need to be removed for this code to compile?
     *
     * R./ Three.
     */
    @Test
    public void question105() {
        Dolls.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question116() {
        Woods.main();
        Assert.assertTrue(true);
    }

    /**
     * Which statements about the following program are correct? (Choose two.)
     * R./
     * The code does not compile because of line m3.
     * The code does not compile because of line m6.
     */
    @Test
    public void question121() {
        try {
            SeriousDanger.main();
        } catch (Throwable e) {
            System.err.println(e.getMessage());
        }
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     * <p>
     * R./ The code does not compile.
     */
    @Test
    public void question125() {
        CarTest.main();
        Assert.assertTrue(true);
    }


    /**
     * What is the output of the following application? (Choose two.)
     *
     * Note: Java attempts promotion of the primitive types first, before trying to wrap them as a Wrapper objects.
     */
    public class ChooseWisely {
        public ChooseWisely() { super(); }
        public int choose(int choice)    { return 5;  }
        public int choose(short choice)  { return 2;  }
        public int choose(long choice)   { return 11; }
        public int choose(double choice) { return 6;  }
        public int choose(Float choice)  { return 8;  }
    }
    @Test
    public void question126() {
        ChooseWisely c = new ChooseWisely();
        System.out.println(c.choose(2f));           // PRINT: 6 >>> promote to a primitive first.
        System.out.println(c.choose((byte) 2 + 1)); // PRINT: 5 >>> casting first, and then, the addition operation.
        Assert.assertTrue(true);
    }

    /**
     * How many lines of the following program do not compile?
     */
    public enum Question128 {
        RED(1,2) { void toSpectrum() {} },
        BLUE(2)  { void toSpectrum() {}
            // void printColor() {}                 ERROR: Cannot override method 'printColor()', its final.
        },
        ORANGE()        { void toSpectrum() {} },
        GREEN(4) { void toSpectrum() {} };
        // GREEN(4);                                ERROR: Must implement abstract method 'printColor()'.

        private Question128(int... color) {}
        // public Question128(int... color) {}      ERROR: Modifier 'public' not allowed.

        abstract void toSpectrum();
        final void printColor() {}
    }

    /**
     * What is the output of the Square program?
     */
    @Test
    public void question129() {
        Square.main();
        Assert.assertTrue(true);
    }


    /**
     * What is a possible output of the following application?
     *
     * R./ It does not compile.
     */
    @Test
    public void question134() {
        Gift.main();
        Assert.assertTrue(true);
    }


    /**
     * What is the output of the following code?
     */
    static class Rabbits {
        void hop() { System.out.print("hop"); }
    }
    static class FlemishRabbits extends Rabbits {
        void hop() { System.out.print("HOP"); }
    }
    @Test
    public void question138() {
        Rabbits r1 = new FlemishRabbits();
        FlemishRabbits r2 = new FlemishRabbits();
        r1.hop();                                   // PRINT: HOP
        r2.hop();                                   // PRINT: HOP
        Assert.assertTrue(true);
    }

    /**
     * Which of the following are valid class declarations?
     *
     * Notes:
     * 1. Class names follow the same requirements as other identifiers.
     * 2. Underscores (_) and dollar signs ($) are allowed, but no other symbols are allowed.
     * 3. Since Java 9, a single underscore is not permitted as an identifier.
     * 4. Numbers are allowed, but not as the first character.
     */
    class river   {}
    class Pond2$  {}
    class $Pond2  {}
    class $Pond2$ {}
    class var_    {}
    class _var    {}
    class _var_   {}
    // class _ {}       ERROR
    // class Str3@m {}  ERROR
    // class 5Ocean {}  ERROR
    // class var  {}    ERROR: 'var' is a restricted identifier.

    /**
     * What is the output of the InfiniteMath program?
     * <p>
     * Java allows methods to be overridden, but not variables. Therefore, marking them final does not prevent them
     * from being reimplemented in a subclass. Furthermore, polymorphism does not apply in the same way it would to
     * methods as it does to variables. In particular, the reference type determines the version of the secret variable
     * that is selected, making the output 2.0.
     */
    @Test
    public void question140() {
        InfiniteMath.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     *
     * R./ The code does not compile.
     */
    @Test
    public void question142() {
        Penguin.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the ElectricCar program?
     */
    class Automobile {
        private final String drive() { return "Driving vehicle"; }
    }
    class Cars extends Automobile {
        protected String drive() { return "Driving car"; }
    }
    public class ElectricCar extends Cars {
        @Override
        public final String drive() { return "Driving electric car"; }
    }
    @Test
    public void question146() {
        final Automobile car = new ElectricCar();
        var v = (Cars) car;
        System.out.print(v.drive());            // PRINT: Driving electric car.
        Assert.assertTrue(true);
    }

    /**
     * Given the following class declaration, which options correctly declare a local variable containing an instance of
     * the class?
     */
    public class Question148 {
        private abstract class Sky {
            void fall() {
                // var e1 = new Sky();             ERROR: Class 'Sky' is abstract and cannot be instantiated.
                var e2 = new Sky() {};          // VALID: we are extending the class 'Sky'.
                var e3 = new Sky() {            // VALID: we are extending the class 'Sky'.
                    final static int blue = 1;
                };
            }
        }
    }

    /**
     * What is the output of the following program?
     *
     * R./ The code does not compile.
     */
    public class Dwarf {
        // private final String name;          ERROR: Variable 'name' might not have been initialized. If var 'name' was
        //                                            not final, then it defaults value was 'null'.
        private final String name = null;
        public Dwarf() {
            this("Bashful");
        }
        public Dwarf(String name) {
            name = "Sleepy";                // We are not assigning a value to the instance variable 'name'.
            // this.name = "Sleepy";           ERROR: Cannot assign a value to the final variable 'name'.
        }
    }
    @Test
    public void question152() {
        var d = new Dwarf("Doc");
        System.out.println(d.name);         // PRINT: null
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     * <p>
     * R./ 8
     */
    interface AddNumbers {
        int add(int x, int y);
        static int subtract(int x, int y)  { return x - y; }
        default int multiply(int x, int y) { return x * y; }
    }
    public class Calculator {
        protected void calculate(AddNumbers n, int a, int b) {
            System.out.print(n.add(a, b));              // PRINT: 8
        }
    }
    @Test
    public void question153() {
        final var ti = new Calculator() {};             // We are extending the class Calculator. All additional
        //                                                 implementation is assigned to variable 'ti'.
        ti.calculate((k, p) -> p + k + 1, 2, 5);  //  j1: Variable 'ti' can call the inhered method 'calculate()'.
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     * <p>
     * R./ The code does not compile.
     */
    @Test
    public void question162() {
        Bond.main();
        Assert.assertTrue(true);
    }

    /**
     * Question 164: Which statements about static initializers are correct?
     *
     * 1. They cannot be used to create instances of the class they are contained in.
     * 2. They can assign a value to a static final variable.
     * 3. They are executed at most once per program.
     */
    private static final AddNumbers ADD_NUMBERS;
    private Calculator calculator;
    static {
        // calculator = new Calculator();       ERROR: 'this' cannot be referenced from a static context.
        // ADD_NUMBERS = (k, p) -> p + k;       VALID: we are assigned a lambda function.
        ADD_NUMBERS = Integer::sum;
    }

    /**
     * What is the output of the BlueCar program?
     *
     * R./ 13245
     */
    @Test
    public void question165() {
        BlueCar.main();
        Assert.assertTrue(true);
    }

    /**
     * How many lines of the following class contain compilation errors?
     *
     * R./ None.
     */
    class Fly {
        public Fly Fly() { return Fly(); }
        public void Fly(int kite)  {}
        public int  Fly(long kite) { return 1; }
    }
    @Test
    public void question169() {
        var f = new Fly();
        // f.Fly();                                         // WARN: infinite recursion.
        Assert.assertEquals(1, f.Fly(4L));
    }

    /**
     * What is the output of the following application?
     */
    interface Toy { String play(); }
    @Test
    public void question173() {
        abstract class Robot {}
        class Transformer extends Robot implements Toy {
            public String name = "GiantRobot";
            public String play() { return "DinosaurRobot"; }    // y1
        }
        Transformer prime = new Transformer() {
            @Override
            public String play() { return name; }               // y2
        };
        // System.out.print(prime.play() + " " + name);         ERROR: Cannot resolve symbol 'name'.
        System.out.print("prime.play() = " + prime.play());
        Assert.assertEquals("GiantRobot", prime.play());
    }

    /**
     * What is the output of the following application?
     */
    interface Run {
        default CharSequence walk() { return "Walking and running!"; }
    }
    interface Jog {
        default String walk() { return "Walking and jogging!"; }
    }
    public class Sprint implements Run, Jog {
        public String walk() {
            return "Sprinting!";
        }
    }
    @Test
    public void question175() {
        var s = new Sprint();
        System.out.println(s.walk());                           // PRINT: Sprinting!
        Assert.assertEquals("Sprinting!", s.walk());
    }

    /**
     * Which statement about the following interface is correct?
     *
     * R./ The code does not compile for a different reason.
     */
    @Test
    public void question182() {
        PlanetTest planetTest = new PlanetTest();
        Assert.assertEquals(Planet.circumference, planetTest.getCircumference());
    }

    /**
     * Which statement about the following interface is correct?
     *
     * R./ The code does not compile for a different reason.
     */
    @Test
    public void question184() {
        Husky.main();
        Assert.assertTrue(true);
    }

    /**
     * Which variable declaration is the first line not to compile?
     */
    class Building {}
    class House extends Building {}
    @Test
    public void question187() {
        Building b1 = new Building();
        House    h1 = new House();
        Building b2 = new House();
        // Building b3 = (House) b1;        OK but, it causes a ClassCastException at runtime.
        // House h2 = (Building) h1;        COMPILER ERROR ===>>> Required type: House - Provided: Building.
        Building b4 = (Building) b2;
        House    h3 = (House) b2;
        // House h4 = (House) b1;           OK but, it causes a ClassCastException at runtime.
        Assert.assertTrue(true);
    }

    /**
     * Question 203:
     *
     * Which of the following is a valid method name in Java? (Choose two.)
     *
     */
    void Go_$Outside$2() {}
    void $sprint() {}
    // void have-Fun() {}
    // void 9enjoyTheWeather() {}
    // void walk#() {}
    // void new() {}
    // Chapter3Test new() {}

    /**
     * What is the output of the following application?
     */
    @Test
    public void question209() {
        GetSet.main();
        Assert.assertTrue(true);
    }

    /**
     * What is the output of the following application?
     */
    @Test
    public void question219() {
        SoccerBall.main();
        Assert.assertTrue(true);
    }

}

