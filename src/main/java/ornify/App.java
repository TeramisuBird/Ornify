package ornify;

/**
 * Hello world!
 *
 */
public class App
{
  public static void main(String[] args)
  {
    System.out.println("If you can read this, your pull was successful! - Joey :)\n\n");
    System.out.println("*** Test for SQLDatabase connection ***\n");
    SQLDatabase connection = new SQLDatabase();
    System.out.println("The following are all birds in database thus far:");
    connection.printColumnFromQuery(2, "SELECT * FROM BIRD");
  }
}
