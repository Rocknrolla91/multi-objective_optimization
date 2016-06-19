package MixinClassComposition

/**
  * Created by alifar on 1/17/16.
  */

abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next: T
}
