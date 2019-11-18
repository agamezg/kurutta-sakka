package trigrams

import scala.collection.mutable.ListBuffer

object FakeWriter {

  def read(text: String): Map[String, ListBuffer[String]] = {
    var trigramsMap = Map.empty[String, ListBuffer[String]]
    val wordsArray = text.split(" ")
    val wordsArrayLenght = wordsArray.length
    var i = 0

    var key: String = ""
    while (i < wordsArrayLenght - 2) {
      key = "%s %s".format(wordsArray(i), wordsArray(i + 1))
      if (trigramsMap.contains(key)) {
        trigramsMap(key) += wordsArray(i + 2)
      }
      else {
        trigramsMap += (key -> ListBuffer(wordsArray(i + 2)))
      }
      i += 1
    }
    trigramsMap
  }
}

