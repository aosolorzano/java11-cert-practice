package com.hiperium.java.cert.miscellaneous;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MiscTest {

    @Test
    public void streamPipelineFlow() {
        System.out.println("First stream using string values: \n");
        var list = List.of("Toby", "Anna", "Leroy", "Alex");
        list.stream()
                .filter(n -> n.length() == 4)
                .peek(s -> System.out.println("Peak value after filter = " + s))        // Print all 3 values filtered.
                .sorted()                                                               // Sorted values and pass it 1-1.
                .peek(s -> System.out.println("Peak value after sorted = " + s))
                .limit(2)                                                               // Pass only 2 values to next step.
                .forEach(s -> System.out.println("ForEach value after limit = " + s));  // Print the 2 limited values.

        // ANOTHER STREAM WITH NUMBERS
        System.out.println("\nSecond stream using integer values: \n");
        var infinite = Stream.iterate(1, x -> x + 1);
        infinite.filter(x -> x % 2 == 1)                                                // Filter odd numbers only.
                .peek(s -> System.out.println("Peak value after filter = " + s))        // PRINT: 1 3 5 7 9
                .limit(5)
                .forEach(s -> System.out.println("ForEach value after limit = " + s));  // PRINT: 1 3 5 7 9

        // LIKE LAST ONE BUT USING SKIP() METHOD
        System.out.println("\nThird stream using integer values with skip(): \n");
        var infinite2 = Stream.iterate(1, x -> x + 1);
        infinite2.filter(x -> x % 2 == 1)                                               // Filter odd numbers only.
                .peek(s -> System.out.println("Peak value after filter = " + s))        // PRINT: 1 3 5 7 9
                .limit(5)
                .peek(s -> System.out.println("Peak value after limit = " + s))         // PRINT: 5 7 9
                .skip(2)                                                                // Skip the 2 first elements.
                .forEach(s -> System.out.println("ForEach value after limit = " + s));  // PRINT: 5 7 9
        Assert.assertTrue(true);
    }

    @Test
    public void streamReduce() {
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        var result1 = stream1.reduce("",
                (s, c) -> {
                    String accumulator = s + c;
                    System.out.println("s = " + s + ", c = " + c + " -->> " + accumulator);
                    return accumulator;
        });
        System.out.println("result1 = " + result1 + "\n");

        // ANOTHER EXAMPLES WITH INTEGER VALUES
        var result2 = Stream.of(3,5,6).reduce(1,
                (a, b) -> {
                    int accumulator = a * b;
                    System.out.println("a = " + a + ", b = " + b + " -->> " + accumulator);
                    return accumulator;
        });
        System.out.println("result2 = " + result2 + "\n");

        // When we do not provide an identity, an Optional is returned
        BinaryOperator<Integer> op = (a, b) -> {
            int accumulator = a * b;
            System.out.println("a = " + a + ", b = " + b + " -->> " + accumulator);
            return accumulator;
        };
        Stream<Integer> stream3 = Stream.of(3,5,6);
        stream3.reduce(op)
                .ifPresent((result3) -> System.out.println("result3 = " + result3 + "\n"));

        // USING A DIFFERENT RETURN TYPE
        Stream<String> stream4 = Stream.of("w", "o", "l", "f!");
        int result4 = stream4.parallel().reduce(0,
                (i, s) -> {
                    int accumulator = i + s.length();
                    System.out.println("i = " + i + ", s = " + s + ", accumulator -->> " + accumulator);
                    return accumulator;
                },
                (a, b) -> {                 // NOTE: Called if the stream is parallel.
                    int combiner = a + b;
                    System.out.println("a = " + a + ", b = " + b + ", combiner -->> " + combiner);
                    return combiner;
        });
        System.out.println("result4 = " + result4);
        Assert.assertTrue(true);
    }

    @Test
    public void streamCollect() {
        System.out.println("Using StringBuilder");
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        StringBuilder result1 = stream1.collect(
                StringBuilder::new,
                (a, b) -> {
                    System.out.println("a = " + a + ", b = " + b);
                    a.append(b);
                    System.out.println("accumulator -->> " + a);
                }, (x, y) -> {                                          // NOTE: Called if the stream is parallel.
                    System.out.println("x = " + x + ", y = " + y);
                    x.append(y);
                    System.out.println("combiner -->> " + x);
                }
        );
        /* Same as before but using method reference:
        StringBuilder result1 = stream2.collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append
        ); */
        System.out.println("result1 = " + result1 + "\n");

        // USING TREE-SET
        System.out.println("Using TreeSet");
        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        TreeSet<String> result2 = stream2.parallel().collect(
                TreeSet::new,
                (a, b) -> {
                    System.out.println("a = " + a + ", b = " + b);
                    a.add(b);
                    System.out.println("accumulator -->> " + a);
                }, (x, y) -> {                                          // NOTE: Called if the stream is parallel.
                    System.out.println("x = " + x + ", y = " + y);
                    x.addAll(y);
                    System.out.println("combiner -->> " + x);
                }
        );
        /* Same as before but using method reference:
        TreeSet<String> result2 = stream2.collect(
                TreeSet::new,
                TreeSet::add,
                TreeSet::addAll
        ); */
        System.out.println("result2 = " + result2);
        Assert.assertTrue(true);
    }

    /**
     * partitioningBy(): Returns a Collector which partitions the input elements according to a Predicate, and organizes
     * them into a Map<Boolean, List<T>>. The returned Map always contains mappings for both false and true keys.
     */
    @Test
    public void usingByPartitioningCollector() {
        Stream.of("eeny", "meeny", "miny", "moe")
                .collect(Collectors.partitioningBy(x -> x.charAt(0) == 'e'))
                .get(false)
                .stream()
                .peek(x -> System.out.println("Second stream value = " + x))        // meeny miny moe
                .collect(Collectors.partitioningBy(x -> x.length() == 4))
                .get(true)
                .forEach(x -> System.out.println("Third stream value = " + x));     // miny
        Assert.assertTrue(true);
    }

    /**
     * groupingBy(): The collector produces a Map<K, List<T>> whose keys are the values resulting from applying the
     * classification function to the input elements, and whose corresponding values are Lists containing the input
     * elements which map to the associated key under the classification function.
     */
    @Test
    public void usingGroupingByCollector() {
        Stream.of("eeny", "meeny", "miny", "moe")
                .collect(Collectors.groupingBy(x -> x.startsWith("e")))
                .get(false)
                .stream()
                .peek(x -> System.out.println("Second stream value = " + x))        // meeny miny moe
                .collect(Collectors.groupingBy(String::length))
                .get(4)
                .forEach(x -> System.out.println("Third stream value = " + x));     // miny
        Assert.assertTrue(true);
    }

    @Test
    public void primitiveFunctionalInterfaces() {
        IntFunction<Integer> if1 = (v) -> null;
        IntFunction<Integer> if2 = (s) -> s;
        IntFunction<Double>  if3 = (int d) -> (double) d + 1;
        IntFunction<Long>    if4 = (int l) -> (long) l;

        ToIntFunction<Integer> tif1 = (v) -> 3;                         // Cannot return null. Must return an "int".
        ToIntFunction<Integer> tif2 = (Integer s) -> s;
        ToIntFunction<Double>  tif3 = (Double d) -> (int) (d + 1);
        // ToIntFunction<Long> tif4 = (l) -> l.intValue();              Lambda can be replaced with method reference.
        ToIntFunction<Long>    tif4 = Long::intValue;

        Assert.assertTrue(true);
    }

    @Test
    public void arrayDeque() {

    }

    @Test
    public void linkedList() {

    }

    // TODO: Check this code.
    @Test
    public void lambdaWithEffectivelyFinalVariable() {
        var s = "fish";
        Predicate<String> pred = s::contains;               // NOTE: This code is valid.
        // Predicate<String> pred2 = x -> s.contains(x);    ERROR: Variable "s" should be final or effectively final.
        s = "turtle";
        System.out.println("pred.test() = " + pred.test("is"));
    }

    @Test
    public void deleteDirectoryTree() {

    }
}
