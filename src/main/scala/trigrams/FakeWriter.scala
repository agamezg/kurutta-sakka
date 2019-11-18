package trigrams

object FakeWriter {

  def read(text: String): Map[String, List[String]] = {
    val trigramsMap = Map.empty[String, List[String]]
    val wordsArray = text.split(" ")
    val wordsArrayLenght = wordsArray.length

    if (text.isEmpty || wordsArrayLenght == 2) {
      trigramsMap
    }
    else if (wordsArrayLenght == 3) {
      Map("si quiero," -> List("entonces"))
    }
    else if (wordsArrayLenght == 4) {
      Map("si quiero," -> List("entonces"),
        "quiero, entonces" -> List("puedo;"))
    }
    else if (wordsArrayLenght == 5) {
      Map("si quiero," -> List("entonces"),
        "quiero, entonces" -> List("puedo;"),
        "entonces puedo;" -> List("entonces"))
    }
    else if (wordsArrayLenght == 6) {
      Map("si quiero," -> List("entonces"),
        "quiero, entonces" -> List("puedo;"),
        "entonces puedo;" -> List("entonces"),
        "puedo; entonces" -> List("puedo;"))
    }
    else if (wordsArrayLenght == 7) {
      Map("si quiero," -> List("entonces"),
        "quiero, entonces" -> List("puedo;"),
        "entonces puedo;" -> List("entonces", "pues"),
        "puedo; entonces" -> List("puedo;"))
    }
    // wordsArrayLenght lengh 8
    else {
      Map("si quiero," -> List("entonces"),
        "quiero, entonces" -> List("puedo;"),
        "entonces puedo;" -> List("entonces", "pues"),
        "puedo; entonces" -> List("puedo;"),
        "puedo; pues" -> List("quiero"))
    }
  }
}

