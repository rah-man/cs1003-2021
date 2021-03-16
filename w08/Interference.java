// Ozgur, March 2021

// import java.io.*;
// import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Interference {

    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");

        Stream<String> stream = list.stream();
        list.add("three");

        stream.forEach(x -> System.out.println(x)); // prints all 3.

        list.stream().forEach(s -> System.out.println(s));
    }
}



