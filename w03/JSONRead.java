import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.math.BigDecimal;

public class JSONRead {
  public static void main( String args[] ) throws Exception {
    JsonParserFactory factory = Json.createParserFactory(null);
    Reader r = new FileReader("input.json");
    JsonParser parser = factory.createParser(r);

    JsonGeneratorFactory factory2 = Json.createGeneratorFactory(null);
    Writer w = new FileWriter( "output.json");
    JsonGenerator generator = factory2.createGenerator(w);

    String name=null;
    while (parser.hasNext()) {
      JsonParser.Event e = parser.next();
      System.out.println(e);
      switch (e) {
        case KEY_NAME:
          name = parser.getString(); break;
        case VALUE_STRING:
          generator.write(name, "XXX>"+parser.getString()+"<XXX"); break;
        case VALUE_NUMBER:
          generator.write(name, parser.getBigDecimal().add(new BigDecimal(1.0))); break;
        case START_OBJECT:
          if (name != null)
              generator.writeStartObject(name);
          else
              generator.writeStartObject(); break;
        case END_OBJECT:
          generator.writeEnd(); break;
        default:
          System.out.println();
      }
    }
  generator.close();
  }
}
