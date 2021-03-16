import java.io.*;
import javax.json.*;
import javax.json.stream.*;

public class HIP2Writer{
  public static void main(String[] args){
    // JsonGeneratorFactory factory = null;
    JsonGenerator generator = null;
    // JsonGenerator generator = Json.createGenerator(null);

    try {
      // factory = Json.createGeneratorFactory(null);
      Writer w = new FileWriter("hip2.json");
      HIP2Reader h = new HIP2Reader( args[0] );

      // generator = factory.createGenerator(w);
      generator = Json.createGeneratorFactory(null).createGenerator(w);
      generator.writeStartObject();
      generator.writeStartArray("stars");

      while (h.hasNextStar()) {
        HIP2Reader.HIP2Star s = h.getNextStar();

        generator.writeStartObject();
        generator.write("StarID", s.StarID);
        generator.write("right-ascension", s.RA);
        generator.write("declination", s.Decl);
        generator.write("parallax", s.parallax);
        generator.write("magnitude", s.magnitude);
        generator.writeEnd();

        System.out.println("ID "+s.StarID+" Coords "+s.RA+":"+s.Decl+" Parallax "+ s.parallax + " milliarcsecs  magnitude "+ s.magnitude);
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
