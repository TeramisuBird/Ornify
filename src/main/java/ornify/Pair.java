package ornify;

/**
 * Pair class to assist other classes where HashMaps are not entirely ideal.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @param <F>
 *          for variable type 1
 * @param <S>
 *          for variable type 2
 */
public class Pair<F, S>
{
  private F first;
  private S second;

  /**
   * Pair constructor.
   * 
   * @param first
   *          for first value
   * @param second
   *          for second value
   */
  public Pair(final F first, final S second)
  {
    this.first = first;
    this.second = second;
  }

  /**
   * Method that checks for equality.
   * @param obj
   * @return the equality
   */
  @Override
  public boolean equals(final Object obj)
  {
    boolean result = false;
    if (obj == this)
    {
      result = true;
    }

    if (obj instanceof Pair && this.first != null && this.second != null)
    {
      @SuppressWarnings("unchecked")
      Pair<F, S> other = (Pair<F, S>) obj;
      result = this.first.equals(other.first) && this.second.equals(other.second);
    }

    return result;
  }
  
  @Override
  public int hashCode()
  {
    int result = 23;
    if (first != null)
    {
      result = 17 * result + first.hashCode();
    }
    if (second != null)
    {
      result = 17 * result + second.hashCode();
    }
    
    return result;
  }
  
  /**
   * Method that returns the first obj.
   * 
   * @return first value of type F
   */
  public F getFirst()
  {
    return first;
  }
  
  /**
   * Method that returns the second obj.
   * 
   * @return second value of type S
   */
  public S getSecond()
  {
    return second;
  }
}
