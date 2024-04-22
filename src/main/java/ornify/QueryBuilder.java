package ornify;

import java.util.HashMap;

public class QueryBuilder
{
  HashMap<String, String> choices;
  
  public QueryBuilder(HashMap<String, String> choices)
  {
    this.choices = choices;
  }
  
  public String buildQuery()
  {
    String start = "select name, image_url, sound_url from bird";
    
    if (choices.size() == 1)
    {
      String[] set = (String[]) choices.keySet().toArray();
      String key = set[0];
      
      String cond = key + "='" + choices.get(key) + "'";
      start = start + " where " + cond;
    }
    else if (choices.size() > 1)
    {
      String[] set = (String[]) choices.keySet().toArray();
      start = start + " where ";
      
      for (int i = 0; i < set.length - 2; i++)
      {
        String key = set[i];
        start = start + key + "='" + choices.get(key) + "' and";
      }
      
      String finalKey = set[set.length - 1];
      start = start + finalKey + "='" + choices.get(finalKey) + "'";
    }
    
    return start;
  }
}
