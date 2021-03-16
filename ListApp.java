import java.util.ArrayList;
import java.util.Scanner;

class ListApp {
  public void arrayListOperation(){
    // create an empty list of string
    ArrayList<String> names = new ArrayList<String>();
    names.add("Harry"); // add/append an element to the end of the list
    names.add("Hermione");
    names.add("Ron");

    System.out.println(names);

    names.add(0, "Draco");  // add(index, element) will add element at that particular index
    System.out.println(names);

    // it is size() not length()
    names.remove(names.size()-1); // remove element at index
    System.out.println(names);

    names.set(0, "Ron");  // set(index, value) change the element at index
    System.out.println(names);

    String name = names.get(1); // get an element at index
    String last = names.get(names.size() - 1);
    System.out.println(name + " and " + last);

    // check if a list contains an element
    if(names.contains("Ron")){
      System.out.println("Ron is already in the list");
    }
  }

  public void createCube(int n){
    ArrayList<Integer> cube = new ArrayList<Integer>();
    for(int i = 0; i < n; i++){
      cube.add(i * i * i);
    }

    System.out.println(cube);
  }

  public void passArrayListToMethod(){
    ArrayList<String> people = new ArrayList<String>();
    people.add("Harry");
    people.add("Hermione");
    people.add("Ron");
    people.add("Draco");

    System.out.println("Before calling doSomething()");
    System.out.println(people);

    doSomething(people);
    System.out.println("After calling doSomething()");
    System.out.println(people);
  }

  private void doSomething(ArrayList<String> names){
    names.add("Fred");
    names.add("George");
    names.add("Snape");
    names.remove(0);
  }

  public void arrayListCopy(){
    ArrayList<String> names = new ArrayList<String>();
    names.add("Harry");
    names.add("Hermione");
    names.add("Ron");
    names.add("Draco");

    System.out.println("Original list:");
    System.out.println(names);

    // shallow copy
    ArrayList<String> people = names;
    people.add("Snape");

    System.out.println("After shallow copy:");
    System.out.println(names);
    System.out.println("People:");
    System.out.println(people);

    // deep copy
    ArrayList<String> friends = new ArrayList<String>(names);
    friends.add("Fred");
    System.out.println("After deep copy:");
    System.out.println(names);
    System.out.println("Friends:");
    System.out.println(friends);    
  }

  public void calculateMean(){
    // use System.in to get input from standard input, i.e. the command line
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> numbers = new ArrayList<Integer>();

    System.out.println("Enter a number, type -1 to quit:");
    int number = scanner.nextInt();

    while(number != -1){
      numbers.add(number);

      System.out.println("Enter a number, type -1 to quit:");
      number = scanner.nextInt();
    }

    double average = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
    System.out.println("The average of " + numbers + " is: " + average);
  }

  public static void main(String[] args) {
    ListApp main = new ListApp();

    // main.arrayListOperation();
    // main.createCube(5);
    // main.passArrayListToMethod();
    // main.arrayListCopy();
    // main.calculateMean();
  }
}