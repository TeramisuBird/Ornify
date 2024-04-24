package ornify;

public class Pair<T1,T2>
{
  public T1 first;
  public T2 second;
  
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
