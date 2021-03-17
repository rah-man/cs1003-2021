import java.util.*;

public class RandomStuff{
  public static void main(String[] args){
    String[] data = new String[5];
    data[0] = "Sunday;Hiking";
    data[1] = "Sunday;Sleeping";
    data[2] = "Sunday;Eating";
    data[3] = "Monday;BeingGrumpy";
    data[4] = "Monday;StayingAlive";

    LinkedHashMap<String, ArrayList<String>> days = new LinkedHashMap<String, ArrayList<String>>();
    for(String s: data){
      String[] temp = s.split(";");
      ArrayList<String> activities = null;

      if(days.containsKey(temp[0])){
        activities = days.get(temp[0]);
      }else{
        activities = new ArrayList<String>();
      }

      activities.add(temp[1]);
      days.put(temp[0], activities);
    }

    System.out.println(days);
  }
}
