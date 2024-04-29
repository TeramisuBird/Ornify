package ornify;

/**
 * Builds an SQL Query to search a database with.
 * 
 * This work complies with the JMU Honor Code.
 */
public class QueryBuilder
{

  /**
   * QueryBuilder constructor (purposefully empty).
   */
  public QueryBuilder()
  {

  }

  /**
   * build the Query to search the SQL db with.
   * 
   * @return string of SQL query
   */
  public String buildQuery()
  {
    String start = "select name, image_url, sound_url from ornify.bird";
    int emptySelections = 0;
    boolean firstSelection = true;

    // Checks for zero selections.
    for (int i = 0; i < Model.getSelections().length; i++)
    {
      if (Model.getSelections()[i] == null || Model.getSelections()[i].isBlank())
      {
        emptySelections++;
      }
    }
    if (14 < emptySelections)
    {
      return start;
    }

    // Must have some selection.
    for (String s : Model.getIndicies().keySet())
    {
      int index = Model.getIndicies().get(s);

      if (Model.getPicked()[index])
      {
        String chunk = Model.getSelections()[index];

        if (chunk == null || chunk.isBlank())
        {
          continue;
        }

        if (firstSelection)
        {
          chunk = " where " + chunk + "";
          firstSelection = false;
        }
        else
        {
          chunk = " and " + chunk + "";
        }

        start = start + chunk;
      }
    }

    return start;
  }
}
