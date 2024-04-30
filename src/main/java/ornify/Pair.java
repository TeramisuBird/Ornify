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
