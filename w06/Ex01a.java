import java.sql.*;

public class Ex01a{
  public static void main(String[] args){
    if(args.length != 1){
      System.out.println("Usage: java Ex01a <database_file_name>");
      System.exit(0);
    }

    Ex01a app = new Ex01a();
    app.accessDatabase(args[0]);
  }

  private void accessDatabase(String dbName){
    System.out.println("inside accessDatabase");

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

  }


}
