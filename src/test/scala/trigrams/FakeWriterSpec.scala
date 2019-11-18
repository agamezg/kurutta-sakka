package trigrams

import org.scalatest.FlatSpec

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
    val spectedMap = Map("si quiero," -> List("entonces"))
    val map = FakeWriter.read(text)

    assert(map.size == 1)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo;'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo;"
    val spectedMap = Map("si quiero," -> List("entonces"),
                        "quiero, entonces" -> List("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 2)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo entonces"
    val spectedMap = Map("si quiero," -> List("entonces"),
                          "quiero, entonces" -> List("puedo;"),
                          "entonces puedo;" -> List("entonces"))
    val map = FakeWriter.read(text)

    assert(map.size == 3)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo;'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo;"
    val spectedMap = Map("si quiero," -> List("entonces"),
                        "quiero, entonces" -> List("puedo;"),
                        "entonces puedo;" -> List("entonces"),
                        "puedo; entonces" -> List("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 4)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo; pues'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo; pues"
    val spectedMap = Map("si quiero," -> List("entonces"),
                        "quiero, entonces" -> List("puedo;"),
                        "entonces puedo;" -> List("entonces", "pues"),
                        "puedo; entonces" -> List("puedo;"))
    val map = FakeWriter.read(text)

    assert(map.size == 4)
    assert(map == spectedMap)
  }

  "A text: 'si quiero, entonces puedo; entonces puedo; pues quiero'" should "return a map with their respective trigrams" in {
    val text = "si quiero, entonces puedo; entonces puedo; pues quiero"
    val spectedMap = Map("si quiero," -> List("entonces"),
                        "quiero, entonces" -> List("puedo;"),
                        "entonces puedo;" -> List("entonces", "pues"),
                        "puedo; entonces" -> List("puedo;"),
                        "puedo; pues" -> List("quiero"))
    val map = FakeWriter.read(text)

    assert(map.size == 5)
    assert(map == spectedMap)
  }

}
