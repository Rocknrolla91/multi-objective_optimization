package MixinClassComposition

/**
  * Created by alifar on 1/17/16.
  */

object StringIteratorTest {
  def main(args: Array[String]){
    class Iter extends StringIterator(args(0)) with RichIterator
    val iter = new Iter
    iter forEach println
  }
}
