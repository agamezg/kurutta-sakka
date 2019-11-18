package trigrams

import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer


class FakeWriterSpec extends FlatSpec {

  "si quiero, entonces puedo; entonces puedo; pues quiero."

  "An empty text" should "return an empty map" in {
    val emptyText = ""
    val emptyMap = FakeWriter.read(emptyText)

    assert(emptyMap.isEmpty)
  }

  "A text: 'si quiero,'" should "return an empty map" in {
    val text = "si quiero,"
    val emptyMap = FakeWriter.read(text)

    assert(emptyMap.isEmpty)
  }

  "A text: 'si quiero, entonces'" should "return a map with a single key value set" in {
    val text = "si quiero, entonces"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"))
    val map = FakeWriter.read(text)

    assert(map.size == 1)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo;'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo;"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"),
                        "quiero, entonces" -> ListBuffer("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 2)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"),
                          "quiero, entonces" -> ListBuffer("puedo;"),
                          "entonces puedo;" -> ListBuffer("entonces"))
    val map = FakeWriter.read(text)

    assert(map.size == 3)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo;'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo;"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"),
                        "quiero, entonces" -> ListBuffer("puedo;"),
                        "entonces puedo;" -> ListBuffer("entonces"),
                        "puedo; entonces" -> ListBuffer("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 4)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo; pues'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo; pues"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"),
                        "quiero, entonces" -> ListBuffer("puedo;"),
                        "entonces puedo;" -> ListBuffer("entonces", "pues"),
                        "puedo; entonces" -> ListBuffer("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 4)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo; pues quiero'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo; pues quiero"
    val spectedMap = Map("si quiero," -> ListBuffer("entonces"),
                        "quiero, entonces" -> ListBuffer("puedo;"),
                        "entonces puedo;" -> ListBuffer("entonces", "pues"),
                        "puedo; entonces" -> ListBuffer("puedo;"),
                        "puedo; pues" -> ListBuffer("quiero"))
    val map = FakeWriter.read(text)

    assert(map.size == 5)
    assert(map == spectedMap)
  }
}
