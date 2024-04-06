package ornify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class which manages a single instance of an SQL database.
 * 
 * @author Joseph Blethen
 * @version 1.0
 *
 */
public class SQLDatabase
{
  public String url = "jdbc:mysql://new";
  public String username = "root";
  public String password = "root";
  private Connection connection;

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
      Statement statement = getConnection().createStatement();
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
