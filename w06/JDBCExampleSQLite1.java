import java.sql.*;

/**
 * This example illustrates the most straightforward use of JDBC,
 * with SQLite where the exact SQL commands are known statically (at the time
 * of writing the program).
 */
public class JDBCExampleSQLite1 {

	public static void main(String[] args) throws SQLException {
		if (args.length < 1) {
			System.out.println("Usage: java JDBCExampleSQLite1 <database_file_name>");
			System.exit(0);
		}
		JDBCExampleSQLite1 myExample = new JDBCExampleSQLite1();
		myExample.tryToAccessDB(args[0]);
	}


	private void tryToAccessDB(String dbFileName) throws SQLException {
		Connection connection = null;
		try {
			// Connect to the Database Management System
			String dbUrl = "jdbc:sqlite:" + dbFileName;
			connection = DriverManager.getConnection(dbUrl);


			// Now we can access the database
			// For example, we could see if a person table exists in the database, if it does, delete it and re-create it
			// We're only doing this here because we want to start with a new clean table each time we run the program
			// However, normally, you might not wish to delete the table
			Statement statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE IF EXISTS person");
			statement.executeUpdate("CREATE TABLE person (name VARCHAR(100), age int)");
			statement.close();

			// Now we can insert some values into the table
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO person VALUES ('al',61)");
			statement.executeUpdate("INSERT INTO person VALUES ('oz',28)");
			statement.close();

			// Finally, we can query the database to see if the values have been inserted correctly
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

			System.out.println("\ncontents of table:");
			while (resultSet.next()) {

				// can access ResultSet columns by index
				String name = resultSet.getString(1);

				// can also access ResultSet columns by column name
				int age = resultSet.getInt("age");

				System.out.println("name: " + name);
				System.out.println("age: " + age);
				System.out.println();
			}
			statement.close();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// Regardless of whether an exception occurred above or not,
			// make sure we close the connection to the Database Management System
			if (connection != null) connection.close();
		}

	}

}
