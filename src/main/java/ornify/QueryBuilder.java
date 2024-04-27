package ornify;

/**
 * Builds an SQL Query to search a database with.
 * 
 * @author Grayson Diehl
 */
public class QueryBuilder
{

  public QueryBuilder()
  {

  }

  public String buildQuery()
  {
    String start = "select name, image_url, sound_url from ornify.bird";
    int emptySelections = 0;
    boolean firstSelection = true;

    // Checks for zero selections.
    for (int i = 0; i < Model.selections.length; i++)
    {
      if (Model.selections[i] == null || Model.selections[i].isBlank())
      {
        emptySelections++;
      }
    }
    if (14 < emptySelections)
    {
      return start;
    }

    // Must have some selection.
    for (String s : Model.selectionIndicies.keySet())
    {
      int index = Model.selectionIndicies.get(s);

      if (Model.picked[index])
      {
        String chunk = Model.selections[index];

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
