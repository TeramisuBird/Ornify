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
  public F first;
  public S second;

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
}
