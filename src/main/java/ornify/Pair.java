package ornify;

/**
 * Pair class to assist other classes where HashMaps are not entirely ideal.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @param <T1> for variable type 1
 * @param <T2> for variable type 2
 */
public class Pair<T1,T2>
{
  public T1 first;
  public T2 second;
  
  /**
   * Pair constructor.
   * 
   * @param first for first value
   * @param second for second value
   */
  public Pair(T1 first, T2 second)
  {
    this.first = first;
    this.second = second;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    boolean result = false;
    if (obj == this)
    {
      result = true;
    }
    
    if (obj instanceof Pair && this.first != null && this.second != null)
    {
      @SuppressWarnings("unchecked")
      Pair<T1,T2> other = (Pair<T1,T2>) obj;
      result = this.first.equals(other.first) && this.second.equals(other.second);
    }
     
    return result;
  }
}
