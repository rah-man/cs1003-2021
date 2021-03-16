import java.io.*;
import java.util.*;

public class Ex1{
  public static void main(String[] args){
    Ex1 app = new Ex1();
    app.wordCount(args[0], "Boatswain");
  }

  public void wordCount(String filePath, String query){
    try{
      FileReader fr = new FileReader(filePath);
      BufferedReader br = new BufferedReader(fr);
      ArrayList<String> words = new ArrayList<>();
      int match = 0;

      String line = br.readLine();
      while(line != null){
        String[] tokens = line.split(" ");
        if(tokens.length != 0){
          for(int i = 0; i < tokens.length; i++){
            if(tokens[i].length() != 0){
              if(query.equals(tokens[i])){
                match++;
              }

              words.add(tokens[i]);
            }
          }
        }
        // System.out.println(line);
        line = br.readLine();
      }

      System.out.println("Words size: " + words.size());
      System.out.println("Query: " + query + "\nFound occurence: " + match);
    }catch(Exception e){
      System.err.println(e);
    }
  }
}
