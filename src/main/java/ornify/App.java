package ornify;

/**
 * This is a test driver class. Run this to test your database connection and settings.
 *
 */
public class App
{
  /**
   * Warning: You must have an sql_secret.txt file in the eclipse Ornify workspace folder.
   * This must contain your local database information line by line.
   * <p>
   * 
   * sql_secret.txt must have the following information in this order:
   * 
   * <li> Online database password
   * <li> Local server name
   * <li> Local database name
   * <li> Local database url
   * <li> Local database username
   * <li> Local database password
   * <p>
   * 
   * @param args
   *          command-line arguments
   */
  public static void main(String[] args)
  {
    System.out.println("If you can read this, your pull was successful! - Joey :)\n\n");
    System.out.println("*** Test for SQLDatabase connection ***\n");

    SQLDatabase connection = new SQLDatabase(false); // Set to true for online database connection.

    // Please be careful debugging with this set to true, 
    // we can only make so many connections per day.
    //
    // If false, you must have both MySQL Workbench and MySQL Server installed.
    // Configure your local database info with sql_secret.txt

    System.out.println("The following are all birds in database thus far:");
    connection.printColumnFromQuery(2, "SELECT * FROM bird");
    connection.closeConnection(); // Always close your connection when finished with the SQL
                                  // database!
  }
}
