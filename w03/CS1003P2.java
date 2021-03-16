import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
public class  CS1003P2 {
    public static void main(String[] args)throws Exception{
        final String APIKEY = "a653e0f";
        String title = "harry+potter";
        String urlString =  "https://www.omdbapi.com/?apikey="+APIKEY+"&r=json&s="+title;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(br);


        //list to store the title of movies
        List<String> titles = new ArrayList<String>();
        List<String> idList = new ArrayList<String>();

        while(parser.hasNext()){
            JsonParser.Event e = parser.next();
            if(e == JsonParser.Event.KEY_NAME){
                String name = parser.getString();
                if(name.equals("Title")){
                    e = parser.next();
                    if(e == JsonParser.Event.VALUE_STRING)
                        titles.add(parser.getString());
                }
                if(name.equals("imdbID")){
                  e = parser.next();
                  if(e == JsonParser.Event.VALUE_STRING){
                    idList.add(parser.getString());
                  }
                }
            }
        }

        for(String t : titles){
            System.out.println(t);
        }

        for(String id : idList){
            System.out.println(id);
        }

        // for(String plot : plotList){
        //     System.out.println(plot);
        // }







        br.close();


    }
}

