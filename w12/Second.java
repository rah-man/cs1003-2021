// https://www.codota.com/code/java/methods/org.apache.spark.api.java.JavaRDD/flatMap

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import scala.Tuple2;

public class Second{
  public static void main(String[] args){
    // List<String> harryPotter = new ArrayList<String>(Arrays.asList("Mr and Mrs Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal, thank you very much. They were the last people you'd expect to be involved in anything strange or mysterious, because they just didn't hold with such nonsense."));
    List<String> harryPotter = new ArrayList<String>(Arrays.asList("Mr and Mrs Dursley, of number four, Privet Drive,"));

    SparkConf sparkConf = new SparkConf().setAppName("spark_second").setMaster("local[*]");
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    JavaRDD<String> stringRDD = sparkContext.parallelize(harryPotter);
    JavaRDD<String> wordsRDD = stringRDD.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
    JavaRDD<String> wordsCleanRDD = wordsRDD.map(s -> cleanString(s));

    System.out.println("ALL TOKENS");
    wordsRDD.collect().forEach(System.out::println);

    System.out.println("\nCLEAN TOKENS");
    wordsCleanRDD.foreach(w -> System.out.println(w));

    System.out.println("\nTOKEN LENGTH");
    JavaPairRDD<String, Integer> wordLength = wordsCleanRDD.mapToPair(w -> new Tuple2<String, Integer>(w, w.length()));
    wordLength.foreach(wl -> System.out.println(wl));

    System.out.println("\nWORD JACCARD");
    JavaPairRDD<String, Double> wordJaccard = wordsCleanRDD.mapToPair(w -> new Tuple2<String, Double>(w, jaccardIndex(w, "random target string here")));
    wordJaccard.collect().forEach(System.out::println);

    System.out.println("\nFILTERED RESULT AND VALUE");
    JavaPairRDD<String, Double> jaccardFiltered = wordJaccard.filter(wj -> wj._2() >= 0.75);
    jaccardFiltered.foreach(jf -> System.out.println(jf));

    System.out.println("\nFILTERED RESULT");
    jaccardFiltered.foreach(jf -> System.out.println(jf._1()));
  }

  public static String cleanString(String s){
    return s.replaceAll("[^a-zA-Z0-9]", "");
  }

  public static Double jaccardIndex(String source, String target){
    return Math.random();
  }
}


