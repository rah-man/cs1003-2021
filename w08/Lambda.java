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

        int max = people.stream()
                    .mapToInt(p -> p.getAge())
                    .max()
                    .getAsInt();

        double avg = people.stream()
                        .mapToInt(p -> p.getAge())
                        .average()
                        .getAsDouble();

        System.out.println(people);
        System.out.println(totalAge);
        System.out.println(max);
        System.out.println(avg);
    }
}

