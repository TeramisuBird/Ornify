package ornify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A class which manages a single instance of an SQL database.
 * 
 * @author Joseph Blethen
 * @version 1.0
 *
 */
public class SQLDatabase
{
  private final String SECRET_FILEPATH = "sql_secret.txt";
  private Connection connection;
  
  public SQLDatabase() {
    this(false);
  }

  /**
   * You must have a sql_secret.txt in the local path for this to work! The line-by-line order of
   * the file goes:
   * <li>online database password
   * <li>local server name
   * <li>local database name
   * <li>local database url
   * <li>local database username
   * <li>local database password
   */
  public SQLDatabase(boolean isOnline)
  {
    String serverName = "localhost";
    String databaseName = "birds";
    String databaseURL = "jdbc:mysql://";
    String username = "root";
    String password = "root";
    BufferedReader reader;
    try
    {
      reader = new BufferedReader(new FileReader(SECRET_FILEPATH));
      if (isOnline) {
        password = reader.readLine();
        username = "teramisubird";
        serverName = "db4free.net:3306";
        databaseName = "ornify"
            + "?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC"
            + "&useSSL=false";
      } else {
        reader.readLine();
        serverName = reader.readLine();
        databaseName = reader.readLine();
        databaseURL = reader.readLine();
        username = reader.readLine();
        password = reader.readLine();
      }
      String url = databaseURL + serverName + "/" + databaseName;
      System.out.println("Attempting to connect to database: " + url + "\n\nWith password = " + password);
      if (connection==null) {
        this.connection = DriverManager.getConnection(url, username, password);
      }
    }
    catch (IOException e)
    {
      System.out.println("** sql_secret.txt Format Error"
          + "\nEnsure your database info or file location of sql_secret.txt is correct.");
    }
    catch (SQLException e)
    {
      System.out.println("** SQL Error" + "\nThis database does not exist.");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public SQLDatabase(String url, String username, String password)
  {
    try
    {
      this.connection = DriverManager.getConnection(url, username, password);
    }
    catch (SQLException e)
    {
      System.out.println("SQL Error");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Gets an ArrayList of result strings from the given SQL query.
   * 
   * @param column
   *          - The column to make a list of.
   * @param query
   *          - The SQL query itself.
   * @return An ArrayList of type String
   */
  public ArrayList<String> getListFromQuery(int column, String query)
  {
    ResultSet results = getResultsFromQuery(query);
    ArrayList<String> list = new ArrayList<String>();
    try
    {
      while (results.next())
      {
        list.add(results.getString(column));
      }
    }
    catch (SQLException e)
    {
      System.out.println("SQL Error: " + e.getErrorCode());
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Will attempt to print a column from a given query. If failure happens, prints error code to
   * console.
   * 
   * @param column
   *          The column number in the database.
   * @param query
   *          The query to search for.
   */
  public void printColumnFromQuery(int column, String query)
  {
    ResultSet results = getResultsFromQuery(query);
    try
    {
      while (results.next())
      {
        System.out.println(results.getString(column));
      }
    }
    catch (SQLException e)
    {
      System.out.println("SQL Error: " + e.getErrorCode());
      e.printStackTrace();
    }
  }

  /**
   * Will attempt to print a column from a given query. If failure happens, prints error code to
   * console.
   * 
   * @param column
   *          The column number in the database.
   * @param query
   *          The query to search for.
   */
  public void printColumnsFromQuery(int columnA, int columnB, String query)
  {
    ResultSet results = getResultsFromQuery(query);
    try
    {
      while (results.next())
      {
        System.out.println(results.getString(columnA) + " " + results.getString(columnB));
      }
    }
    catch (SQLException e)
    {
      System.out.println("SQL Error: " + e.getErrorCode());
      e.printStackTrace();
    }
  }

  /**
   * Will attempt to execute a given query String on the database. If failure happens, prints error
   * code to console.
   * 
   * @param query
   *          The SQL command string to search the database with.
   * @return A ResultSet object which can be looped through using next() to see the results, null if
   *         error.
   */
  public ResultSet getResultsFromQuery(String query)
  {
    try
    {
      Statement statement = getConnection().createStatement(
          ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      return statement.executeQuery(query);
    }
    catch (SQLException e)
    {
      System.out.println("SQL Error: " + e.getErrorCode());
      return null;
    }
  }

  /**
   * Retrieves this connection to the database.
   * 
   * @return A connection to the SQL database.
   */
  public Connection getConnection()
  {
    return this.connection;
  }

  /**
   * Will attempt to close this database
   * 
   * @return True if successful, otherwise false.
   */
  public boolean closeConnection()
  {
    try
    {
      connection.close();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
  }

}
