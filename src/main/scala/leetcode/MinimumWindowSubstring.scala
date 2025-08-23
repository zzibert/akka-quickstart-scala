package leetcode

object Solution {
  def minWindow(s: String, t: String): String = {
    var solution = ""
    var targetCharacters = Map[Char, Int]()
    val length = s.length
    var slidingWindowSize = t.length
    var found = false

    t foreach { character =>
      targetCharacters += (character, targetCharacters.getOrElse(character, 0) + 1)
    }

    while (!found && slidingWindowSize <= length) {
      var currentCharacters = Map[Char, Int]()
      for (j <- 0 until slidingWindowSize) {
        currentCharacters += (s(j), currentCharacters.getOrElse(s(j), 0) + 1)
      }

      for {
        i <- 0 until length - (slidingWindowSize - 1)
        if !found
      } {
        if (isSolution(targetCharacters, currentCharacters)) {
          found = true
          solution = s.drop(i).take(slidingWindowSize)
        } else {
          val oldCharacter = s(i)
          currentCharacters.get(oldCharacter) foreach { count =>
            currentCharacters += (oldCharacter, count - 1)
          }
          if (i + slidingWindowSize < length) {
            val newCharacter = s(i + slidingWindowSize)
            currentCharacters += (newCharacter, currentCharacters.getOrElse(
              newCharacter,
              0) + 1)
          }
        }
      }

      slidingWindowSize += 1
    }

    solution
  }

  def isSolution(target: Map[Char, Int], current: Map[Char, Int]): Boolean = {
    target forall {
      case (character, count) =>
        current.getOrElse(character, 0) >= count
    }
  }
}
