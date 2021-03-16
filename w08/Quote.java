import org.json.*;

class Quote {

    private String author;
    private String text;

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    // the factory design pattern, sort of.
    // however it means this class depends on the particular JSON library. may not be ideal for all use cases.
    public static Quote fromJSON(JSONObject object) {
        return new Quote(object.getString("author"), object.getString("text"));
    } 

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return text + " (by " + author + ")";
    }
}
