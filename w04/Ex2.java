import java.util.*;
import java.io.*;

public class Ex2{
  public static void main(String[] args) throws Exception{
    FileReader fr = new FileReader("ground_truth.csv");
    BufferedReader br = new BufferedReader(fr);
    HashMap<String, Integer> fruits = new HashMap<>();
    HashMap<String, Integer> query = new HashMap<>();

    String line = br.readLine();
    while(line != null){
      // System.out.println(line);
      String[] fruit = line.split("\t");
      fruits.put(fruit[0], Integer.parseInt(fruit[1]));

      line = br.readLine();
    }

    System.out.println(fruits);

    fr = new FileReader("results.csv");
    br = new BufferedReader(fr);

    // Query Apple Ball  Orange  Fish  Peas  Chips
    // Orange  0 0 5 0 0 0
    line = br.readLine();
    line = br.readLine();
    while(line != null && line.length() != 0){
      String[] q = line.split("\t");
      query.put("Apple", Integer.parseInt(q[1]));
      query.put("Ball", Integer.parseInt(q[2]));
      query.put("Orange", Integer.parseInt(q[3]));
      query.put("Fish", Integer.parseInt(q[4]));
      query.put("Peas", Integer.parseInt(q[5]));
      query.put("Chips", Integer.parseInt(q[6]));

      int allOrange = fruits.get(q[0]);
      int truePositive = query.get(q[0]);
      int falsePositive = 0;

      Set<String> keys = query.keySet();
      for(String key: keys){
        if(!key.trim().equals(q[0])){

          falsePositive += query.get(key);
        }
      }

      int falseNegative = allOrange - truePositive;

      System.out.println("Query: " + q[0]);
      System.out.println("Ground: " + allOrange);
      System.out.println("truePositive: " + truePositive);
      System.out.println("falsePositive: " + falsePositive);
      System.out.println("falseNegative: " + falseNegative);

      double prec = ((double) truePositive) / ((double) (truePositive + falsePositive));
      double rec = ((double) truePositive) / ((double) (falseNegative + truePositive));
      double f1 = 2 * prec * rec / (prec + rec);

      System.out.println("prec: " + prec);
      System.out.println("rec: " + rec);
      System.out.println("F1: " + f1);
      System.out.println();

      line = br.readLine();
    }
  }
}
