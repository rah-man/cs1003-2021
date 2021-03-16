// Ozgur, March 2021

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.json.*;

class Composition {

    public static void main(String[] args) throws Exception {

        // Very minimal error checking and exception handling here, don't do what I do in a practical :)
        String filePath = args[0];

        // I am not suggesting this is the best way of reading the contents of a file, but hey, the topic is Java stream API...
        String fileContents = Files.readAllLines(Paths.get(filePath)).stream().collect(Collectors.joining());

        // Let's use an object style API to parse the JSON
        JSONArray quotesJSON = new JSONArray(fileContents);

        // finally, let's convert to a list of objects
        List<Quote> quotes = new ArrayList<Quote>();

        for (int i = 0; i < quotesJSON.length(); i++) {
            Quote quote = Quote.fromJSON(quotesJSON.getJSONObject(i));
            quotes.add(quote);
        }

        // search for author name "O...e"
        // quotes.stream()
        //     .filter(q -> q.getAuthor().startsWith("O"))
        //     .filter(q -> q.getAuthor().endsWith("e"))
        //     .forEach(q -> System.out.println(q));

        System.out.println("\n ---------- \n");

        // do the same, but with named predicates and function composition
        Predicate<Quote> start = q -> q.getAuthor().startsWith("O");
        Predicate<Quote> end = q -> q.getAuthor().endsWith("e");
        // Predicate<Quote> composed = start.and(end);

        quotes.stream()
            .filter(start)
            .forEach(quote -> System.out.println(quote));

        // quotes.stream()
        //     .filter(composed)
        //     .forEach(q -> System.out.println(q));

        System.out.println("\n ---------- \n");

        // not just Oscar Wilde, let's see all whose name starts with O or end in e
        // Predicate<Quote> disjunct = start.or(end);
        // quotes.stream()
        //     .filter(disjunct)
        //     .forEach(q -> System.out.println(q));


    }
}

