import java.io.*;
import javax.json.*;
import javax.json.stream.*;

public class Hogwarts{
  private class Student{
    public String name;
    public String house;
  }

  private BufferedReader br;
  private Student student = null;

  public Hogwarts() throws FileNotFoundException{
    br = new BufferedReader(new FileReader("hogwarts.dat"));  // hardcoded the filename because lazy
  }

  private void parseNextStudent() throws IOException{
    String line = br.readLine();
    if(line != null){
      student = new Student();
      String[] data = line.split(" "); // the values are separated by space
      student.name = data[0];
      student.house = data[1];
    }
  }

  public boolean hasNextStudent() throws IOException{
    if(student == null)
      parseNextStudent();
    return (student != null);
  }

  public Student getNextStudent(){
    Student s = student;
    student = null; // so the next turn it's null again
    return s;
  }

  public static void main(String[] args){
    JsonGeneratorFactory factory = null;
    JsonGenerator generator = null;

    try {
      factory = Json.createGeneratorFactory(null);
      Writer w = new FileWriter("hogwarts.json");
      Hogwarts h = new Hogwarts();

      generator = factory.createGenerator(w);
      generator.writeStartObject();
      generator.writeStartArray("students");

      while (h.hasNextStudent()) {
        Hogwarts.Student s = h.getNextStudent();

        generator.writeStartObject();
        generator.write("name", s.name);
        generator.write("house", s.house);
        generator.writeEnd();

        System.out.println("Name: " + s.name + "\tHouse: " + s.house);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally{
      generator.writeEnd();
      generator.writeEnd();
      generator.close();
    }
  }
}
