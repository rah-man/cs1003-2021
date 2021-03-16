import javax.json.*;
import javax.json.stream.*;
import java.math.BigDecimal;

import java.io.*;

public class SimpleJSONWriter {
    public static void main(String [] argv) {
  try {
      // Create a JSON generator
        // This class will help us output valid JSON step-by-step
      JsonGeneratorFactory factory2 = Json.createGeneratorFactory(null);
      Writer w = new FileWriter("simpleoutput.json");
      JsonGenerator generator = factory2.createGenerator(w);

        // Start an object
      generator.writeStartObject();

        // Write a name-value pair
        generator.write("some","thing");
        generator.write("more","stuff");

        // create a named object
    generator.writeStartObject("phone");
        generator.write("mobile","123456");
    generator.writeEnd();

        // create a named array
      generator.writeStartArray("faveBands");
        generator.write("Metallica");
        generator.write("Jedward");
        generator.write("Wagner");
        generator.writeEnd();

        // Close the outside object
        generator.writeEnd();
      generator.close();

  } catch (Exception e) {
    e.printStackTrace();
  }
    }
}
