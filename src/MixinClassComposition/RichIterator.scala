package MixinClassComposition

/**
  * Created by alifar on 1/17/16.
  */
trait RichIterator extends AbsIterator{
  def forEach(f: T => Unit){while (hasNext) f(next)}
}
