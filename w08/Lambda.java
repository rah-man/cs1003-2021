import java.util.*;

class Lambda {
        
    public static void main(String[] args) {

        Person alice = new Person("alice", 20);
        Person bob = new Person("bob", 25);
        Person chris = new Person("chris", 23);
            
        List<Person> people = new ArrayList<Person>(Arrays.asList(alice, bob, chris));

        // with loops
        // int totalAge = 0;
        // for (Person p : people) {
        //     totalAge += p.getAge();
        // }

        int totalAge = people.stream()
                             .mapToInt(p -> p.getAge())
                             .sum();

        System.out.println(people);
        System.out.println(totalAge);
    }
}

