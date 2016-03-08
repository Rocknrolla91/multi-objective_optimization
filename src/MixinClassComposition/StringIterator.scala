package MixinClassComposition

/**
  * Created by alifar on 1/17/16.
  */

class StringIterator(s: String) extends AbsIterator {
  override type T = Char

  private var i: Int = 0

  override def next: T = {val ch = s charAt i; i+=1; ch}

  override def hasNext: Boolean = i < s.length()
}
