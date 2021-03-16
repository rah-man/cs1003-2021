import java.util.HashMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;

class MapSet {
  public void mapOperation(){
    // create a hashmap of key string and integer value
    HashMap<String, Integer> housePoint = new HashMap<String, Integer>();
    housePoint.put("Griffyndor", 95);
    housePoint.put("Hufflepuff", 99);
    housePoint.put("Ravenclaw", 97);
    housePoint.put("Slytherin", 95);

    System.out.println(housePoint);

    // getting a value of a key using .get()
    System.out.println(housePoint.get("Ravenclaw"));

    // iterating a map

    // by key only
    // the order depends on the hash key of the key
    // change to TreeMap if we want it to be in order of some sort
    for(String house : housePoint.keySet()){
      System.out.println(house);
    }

    // by value only
    for(Integer point : housePoint.values()){
      System.out.println(point);
    }

    // by key and value
    for (Map.Entry<String, Integer> entry : housePoint.entrySet()) {
      String house = entry.getKey();
      Integer point = entry.getValue();
      System.out.println(house + " got " + point + " pts");
    }

    // updating an entry by putting it with a new value
    housePoint.put("Slytherin", 96);
    System.out.println(housePoint);

    // removing an entry by its key
    housePoint.remove("Slytherin");
    System.out.println(housePoint);

    // check if a key is in a map
    if(housePoint.containsKey("Beauxbatons")){
      System.out.println("Beauxbatons is in the map");
    }else{
      System.out.println("Nope...");
    }
  }

  public void setOperation(){
    // create a treeset of string, i.e. the data is stored in binary searh tree order of their ASCII representation. compared it to hashmap above
    Set<String> houses = new TreeSet<String>();
    
    // add elements to a set using .add(), like ArrayList
    houses.add("Slytherin");
    houses.add("Gryffindor");
    houses.add("Ravenclaw");
    houses.add("Hufflepuff");

    System.out.println(houses);

    // check if an element is in a set
    if(houses.contains("Ravenclaw")){
      System.out.println("Ravenclaw is in the set");
    }

    // iterating a set
    for(String house : houses){
      System.out.println(house);
    }

    // removing an element
    houses.remove("Slytherin");
    houses.remove("Durmstrang");  // removing a non-existent element is not an error. it just has no effect
    System.out.println(houses);
  }

  public void getTokensAndVocabulary(){
    /*
    in language processing, from the text input, 
    we usually want to have a list of tokens (i.e. words) in all texts 
    and a set of vocabulary (i.e. the unique words)

    a common way to store the tokens is using a map
    and using a set to store the vocabulary
    */

    TreeMap<String, Integer> tokens = new TreeMap<String, Integer>();
    Set<String> vocabulary = null;

    String text = "The high amount of energy required to vaporize water has a wide range of effects. On a global scale, for example, it helps moderate Earth's climate. A considerable amount of solar heat absorbed by tropical seas is consumed during the evaporation of surface water. Then, as moist tropical air circulates poleward, it releases heat as it condenses and forms rain. On an organismal level, water's high heat of vaporization accounts for the severity of steam burns. These burns are caused by the heat energy released (during formation of hydrogen bonds) when steam condenses into liquid on the skin.";

    System.out.println(text);

    // normalise the text by lowering the letters and adding space around non alpha-numeric symbols
    String normalisedText = text.replaceAll("'s", " 's").replaceAll("\\.", " \\.").replaceAll("\\(", "\\( ").replaceAll("\\)", " \\)").replaceAll(",", " ,").toLowerCase();

    System.out.println("\nNormalised text:\n" + normalisedText);

    String[] tokens_ = normalisedText.split(" "); // split text by space into tokens
    for(String token : tokens_){

      if(tokens.containsKey(token)){
        int currentCount = tokens.get(token);
        tokens.put(token, ++currentCount);
      }else{
        tokens.put(token, 1);
      }
    }

    vocabulary = tokens.keySet();

    System.out.println("\nAll tokens:");
    System.out.println(tokens);

    System.out.println("\nVocabulary:");
    System.out.println(vocabulary);    
  }

  public static void main(String[] args) {
    MapSet app = new MapSet();

    // app.mapOperation();
    // app.setOperation();
    // app.getTokensAndVocabulary();
  }
}