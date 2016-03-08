package MultiObjectiveOptimizaton

/**
  * Created by alifar on 2/11/16.
  */

abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next: T
}

trait RichIterator extends AbsIterator{
  def forEach(f: T => Unit){while (hasNext) f(next)}
}

class ListIterator(l: List[Double]) extends AbsIterator{
  override type T = Double

  private var i: Int = 0

  override def next: T = {val item = l(i); i+=1; item}

  override def hasNext: Boolean = i < l.length
}
