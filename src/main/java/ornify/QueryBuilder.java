package ornify;


public class QueryBuilder
{
  
  
  public QueryBuilder()
  {

  }
  
  public String buildQuery()
  {
    String start = "select name, image_url, sound_url from bird";
    
    int selections = 0;
    for (String s : Model.selectionIndicies.keySet())
    {
      int index = Model.selectionIndicies.get(s);
      
      if (Model.picked[index])
      {
        String chunk = s + "=" + Model.selections[index];
        
        if (selections == 0)
        {
          chunk = " where " + chunk;
        }
        else
        {
          chunk = " and " + chunk;
        }
        
        start.concat(chunk);
      }
    }
    
    return start;
  }
}
