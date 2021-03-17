// Ozgur, March 2021

import java.io.*;
import java.nio.charset.*;
import java.util.stream.*;
import org.apache.commons.csv.*;
import java.util.*;

// To compile
// javac -cp commons-csv-1.8.jar: W08Exercise.java

// To run
// time java -cp commons-csv-1.8.jar: W08Exercise data/pp-complete.csv

public class W08Exercise {
    public static void main(String args[]) throws Exception {

        // The input file given as a command line argument
        File inputFile = new File(args[0]);

        // Use Apache Commons CSV to create a parser
        CSVParser parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);

        // Create a generator stream from the CSVParser
        // CSVParser implements Iterable, so it supports spliterator()
        // Check the documentation of StreamSupport.stream to work out what the second (Boolean) argument is
        Stream<CSVRecord> stream = StreamSupport.stream(parser.spliterator(), false);

        // A sample stream computation.
        // Takes less than 1 second for either input file since it doesn't have to parse the entire file...
        // stream.limit(10).forEach(r -> System.out.println(r));

        // findCheapestProperty(inputFile);
        // findAverageInTown(inputFile);
        top10Max(inputFile);
    }

    public static void findCheapestProperty(File inputFile) throws Exception{
        // USING min() with lambda because we don't have the class representation
        System.out.println("\n===================\n");
        CSVParser parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);
        Stream<CSVRecord> stream = StreamSupport.stream(parser.spliterator(), false);

        CSVRecord minProperty = stream
                                    .limit(1000)
                                    .min((prop1, prop2) -> Integer.parseInt(prop1.get(1)) - Integer.parseInt(prop2.get(1)))
                                    .get();
        System.out.println(minProperty);


        // using Collectors.minBy() with lambda, again because we don't have the class representation
        System.out.println("\n===================\n");
        parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);
        stream = StreamSupport.stream(parser.spliterator(), false);
        minProperty = stream
                        .limit(1000)
                        .collect(Collectors.minBy((prop1, prop2) -> Integer.parseInt(prop1.get(1)) - Integer.parseInt(prop2.get(1))))
                        .get();
        System.out.println(minProperty);

        // using reduce() with lambda, for the same reason
        System.out.println("\n===================\n");
        parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);
        stream = StreamSupport.stream(parser.spliterator(), false);
        minProperty = stream
                        .limit(1000)
                        .reduce((prop1, prop2) -> Integer.parseInt(prop1.get(1)) < Integer.parseInt(prop2.get(1)) ? prop1 : prop2)
                        .get();
        System.out.println(minProperty);
    }

    public static void findAverageInTown(File inputFile) throws Exception{
        System.out.println("\n===================\n");
        CSVParser parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);
        Stream<CSVRecord> stream = StreamSupport.stream(parser.spliterator(), true);

        // get average for GLOUCESTER
        String townCity = "GLOUCESTER";
        // double avgPrice = stream
        //                     .filter(prop -> prop.get(11).equals(townCity))
        //                     .mapToDouble(prop -> Double.parseDouble(prop.get(1)))
        //                     .average()
        //                     .orElse(Double.NaN);

        double avgPrice = stream
                            .filter(prop -> prop.get(11).equals(townCity))
                            .mapToInt(prop -> Integer.parseInt(prop.get(1)))
                            .average()
                            .getAsDouble();

        System.out.println("Average in " + townCity + " is: " + avgPrice);
    }

    public static void top10Max(File inputFile) throws Exception{
        System.out.println("\n===================\n");
        CSVParser parser = CSVParser.parse(inputFile, Charset.forName("UTF-8"), CSVFormat.RFC4180);
        Stream<CSVRecord> stream = StreamSupport.stream(parser.spliterator(), true);

        // group by town/city
        // map<groupname, groupvalue>
        Map<String, Double> result = stream
                                        .collect(Collectors.groupingBy(prop -> prop.get(11),
                                        Collectors.averagingDouble(prop -> Double.parseDouble(prop.get(1)))));

        result
            .entrySet()
            .stream() // k, v
            .sorted(Map.Entry.<String, Double>comparingByValue())
            .limit(10)
            .forEachOrdered(c -> System.out.println(c.getKey() + ": " + c.getValue()));

    }
}
