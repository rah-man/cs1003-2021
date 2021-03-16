import java.sql.*;

public class Ex01 {

  public static void main(String[] args) throws SQLException {
    if (args.length < 1) {
      System.out.println("Usage: java Ex01 <database_file_name>");
      System.exit(0);
    }
    Ex01 myExample = new Ex01();
    myExample.tryToAccessDB(args[0]);
  }

  private void insertArtist(String name, String label, int chart, Connection conn) throws SQLException{
    String query = "insert into artist values('" + name + "', '" + label + "', " + chart + ")";
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  private void printArtistWithLabel(String label, Connection conn) throws SQLException{
    String query = "select * from artist where record_label='"+label+"'";
    Statement stmt = conn.createStatement();
    ResultSet resultSet = stmt.executeQuery(query);

    System.out.println("Artist under '" + label + "' label:");
    while (resultSet.next()) {
      String name = resultSet.getString("name");
      int chart = resultSet.getInt("highest_chart");

      System.out.println("\tname: " + name);
      System.out.println("\thighest chart: " + chart);
      System.out.println();
    }
    stmt.close();
  }

  private void printAllArtists(Connection conn) throws SQLException{
    String query = "select * from artist";
    Statement stmt = conn.createStatement();
    ResultSet resultSet = stmt.executeQuery(query);

    System.out.println("Artists in our database:");
    while(resultSet.next()){
      String name = resultSet.getString("name");
      String label = resultSet.getString("record_label");
      int chart = resultSet.getInt("highest_chart");

      System.out.println("\tname: " + name);
      System.out.println("\tlabel: " + label);
      System.out.println("\tchart: " + chart);
      System.out.println();
    }
    stmt.close();
  }

  private void deleteUnpopularArtists(int limit, Connection conn) throws SQLException{
    String query = "delete from artist where highest_chart > " + limit;
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  private void createAlbumTable(Connection conn) throws SQLException{
    String query = "create table album ("+
                    "id integer primary key autoincrement, " +
                    "title varchar(100), " +
                    "artist varchar(100), " +
                    "foreign key(artist) references artist(name))";

    Statement stmt = conn.createStatement();
    stmt.executeUpdate("drop table if exists album");
    stmt.executeUpdate(query);
    stmt.close();

  }

  private void insertAlbum(String title, String artist, Connection conn) throws SQLException{
    String query = "insert into album (title, artist) values('"+title+"', '"+artist+"')";
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
  }

  private void printAlbum(String label, Connection conn) throws SQLException{
    String query = "select al.title, al.artist, ar.record_label "+
                    "from album al, artist ar "+
                    "where al.artist = ar.name and ar.record_label='"+label+"'";

    Statement stmt = conn.createStatement();
    ResultSet resultSet = stmt.executeQuery(query);

    System.out.println("Albums from '" + label + "':");
    while(resultSet.next()){
      String album = resultSet.getString("title");
      String artist = resultSet.getString("artist");
      String lab = resultSet.getString("record_label");

      System.out.println("\talbum: " + album);
      System.out.println("\tartist: " + artist);
      System.out.println("\tlabel: " + lab);
      System.out.println();
    }
    stmt.close();
  }

  private void printAlbum(Connection conn) throws SQLException{
    String query = "select al.title, al.artist, ar.record_label "+
                    "from album al, artist ar "+
                    "where al.artist = ar.name";

    Statement stmt = conn.createStatement();
    ResultSet resultSet = stmt.executeQuery(query);

    while(resultSet.next()){
      String album = resultSet.getString("title");
      String artist = resultSet.getString("artist");
      String lab = resultSet.getString("record_label");

      System.out.println("\talbum: " + album);
      System.out.println("\tartist: " + artist);
      System.out.println("\tlabel: " + lab);
      System.out.println();
    }
    stmt.close();
  }

  private void tryToAccessDB(String dbFileName) throws SQLException {

    /*
    Basic steps:
      1. create a string connection for the database "jdbc:sqlite:DBNAME"
      2. create a Connection object by calling DriverManager.getConnection()
        1 & 2 are done just once

      do these steps whenever we want to execute some queries
      3. create statement object by calling connection.createStatement()
      4. call executeUpdate() from statement to create/drop/delete
      5. call executeQuery() for standard queries
      6. close the statement

      7. close the connection at the end of the program

    */

    Connection connection = null;
    try {
      String dbUrl = "jdbc:sqlite:" + dbFileName;
      connection = DriverManager.getConnection(dbUrl);

      String ddl = "create table if not exists artist(" +
                    "name varchar(100) primary key, " +
                    "record_label varchar(100), " +
                    "highest_chart integer)";

      Statement statement = connection.createStatement();
      statement.executeUpdate("DROP TABLE IF EXISTS artist");
      statement.executeUpdate(ddl);
      statement.close();

      System.out.println("Table artist is/has been created");

      statement = connection.createStatement();
      statement.executeUpdate("INSERT INTO artist VALUES ('Nirvana', 'SONY', 2)");
      statement.executeUpdate("INSERT INTO artist VALUES ('The Beatles', 'SONY', 1)");
      statement.close();
      System.out.println("Two artists have been added");

      insertArtist("ABC", "Indy", 1, connection);
      insertArtist("Apple", "Indy", 2, connection);
      insertArtist("Banana", "ORANGE", 3, connection);
      insertArtist("Orange", "ORANGE", 5, connection);
      System.out.println("Four artists have been added by calling insertArtist()");

      printArtistWithLabel("SONY", connection);
      System.out.println("Querying successful");

      printAllArtists(connection);

      System.out.println("Removing artists under 3");
      deleteUnpopularArtists(3, connection);
      System.out.println();
      printAllArtists(connection);

      createAlbumTable(connection);
      System.out.println("Album table is created");

      insertAlbum("Abbey Road", "The Beatles", connection);
      insertAlbum("Yellow Submarine", "The Beatles", connection);
      insertAlbum("Nevermind", "Nirvana", connection);
      insertAlbum("ABC Rules", "ABC", connection);
      insertAlbum("An Apple a day!", "Apple", connection);
      System.out.println("Five albums are added");
      printAlbum(connection);

      printAlbum("SONY", connection);
      printAlbum("Indy", connection);

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (connection != null)
        connection.close();
    }

  }

}
