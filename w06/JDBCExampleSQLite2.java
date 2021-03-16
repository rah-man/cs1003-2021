import java.sql.*;

/**
 * This example shows how SQL commands can be constructed at run-time, to deal with
 * cases where the details are not known at the time of writing the program.
 *
 * The simplest approach is just to use string concatenation, but as illustrated this
 * has problems where data values include characters with special meaning to SQL,
 * principally the single quote character. The problems can be addressed by using
 * escape characters, but it is easier to use prepared statements, which do this
 * automatically.
 */
public class JDBCExampleSQLite2 {

	public static void main(String[] args) throws SQLException {

		if (args.length < 1) {
			System.out.println("Usage: java JDBCExampleSQLite2 <database_file_name>");
			System.exit(0);
		}
		JDBCExampleSQLite2 myExample = new JDBCExampleSQLite2();
		myExample.tryToAccessDB(args[0]);
	}


	private void tryToAccessDB(String dbFileName) throws SQLException {

		Connection connection = null;
		try {
			String dbUrl = "jdbc:sqlite:" + dbFileName;
			connection = DriverManager.getConnection(dbUrl);

			createTable(connection);
			addDataToTable(connection);
			printTable(connection);

			connection.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (connection != null) connection.close();
		}
	}



	private void createTable(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();

		statement.executeUpdate("DROP TABLE IF EXISTS person");
		statement.executeUpdate("CREATE TABLE person (name VARCHAR(100), age int)");

		statement.close();
	}

	private void addDataToTable(Connection connection) throws SQLException {

		// The following method call doesn't work. Un-comment and run to see how it goes wrong.
		// The problem is that the DBMS interprets the single quote character as indicating the end of the name.

//		 insertPersonUsingStatement("O'Kelly", 54, connection);

		// This works but is confusing to look at, and tricky to do for a name
		// that isn't known until run-time.
		// MariaDB use backslash as the escape character, for SQLite, single quote is escaped using two single quotes ''.
		insertPersonUsingStatement("O''Kelly", 54, connection);

		// This works, and is much more straightforward and can deal with input you have got from a file or user input.
		insertPersonUsingPreparedStatement("O'Reilly", 32, connection);

		// using prepared statements also guards against SQL injection attacks

	}

	private void insertPersonUsingStatement(String name, int age, Connection connection) throws SQLException {

		Statement statement = connection.createStatement();

		String update_string = "INSERT INTO person VALUES ('" + name + "'," + age + ")";

		statement.executeUpdate(update_string);
		statement.close();
	}

	private void insertPersonUsingPreparedStatement(String name, int age, Connection connection) throws SQLException {

		PreparedStatement statement = connection.prepareStatement("INSERT INTO person VALUES (?, ?)");

		statement.setString(1, name);
		statement.setInt(2, age);

		statement.executeUpdate();
		statement.close();

	}

	private void printTable(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

		System.out.println("\ncontents of table:");
		while (resultSet.next()) {

			String name = resultSet.getString(1);
			int age = resultSet.getInt("age");

			System.out.println("name: " + name);
			System.out.println("age: " + age);
			System.out.println();

		}
		statement.close();
	}
}
