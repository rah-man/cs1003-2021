import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class First{
  public static void main(String[] args){
    List<Double> doubleList = new ArrayList<Double>(Arrays.asList(23.44, 26.43, 75.35, 245.767, 398.445, 94.72));

    SparkConf sparkConf = new SparkConf().setAppName("spark_first").setMaster("local[*]");
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    JavaRDD<Double> doubleRDD = sparkContext.parallelize(doubleList);

    // map double to int
    JavaRDD<Integer> intRDD = doubleRDD.map(d -> (int) Math.round(d));
    intRDD.collect().forEach(System.out::println);

    // reduce int into sum
    int reducedSum = intRDD.reduce(Integer::sum);
    System.out.println("Sum of all int: " + reducedSum);

    sparkContext.close();
  }

}
