package leetcode

import scala.collection.mutable

object LetterCombinationsOfAPhoneNumber {
  def letterCombinations(digits: String): List[String] = {
    if (digits.isEmpty) {
      return Nil
    }
    val symbols = mutable.Map[Char, List[Char]]()

    symbols += ('2' -> List('a', 'b', 'c'))

    symbols += ('3' -> List('d', 'e', 'f'))

    symbols += ('4' -> List('g', 'h', 'i'))

    symbols += ('5' -> List('j', 'k', 'l'))

    symbols += ('6' -> List('m', 'n', 'o'))

    symbols += ('7' -> List('p', 'q', 'r', 's'))

    symbols += ('8' -> List('t', 'u', 'v'))

    symbols += ('9' -> List('w', 'x', 'y', 'z'))

    letterCombinationsHelper(digits, symbols)
  }

  def letterCombinationsHelper(digits: String, symbols: mutable.Map[Char, List[Char]]): List[String] = {
    if (digits.isEmpty) {
      List("")
    } else {
      val combinations = symbols.getOrElse(digits.head, Nil)
      for {
        previous <- letterCombinationsHelper(digits.tail, symbols)
        char <- combinations
      } yield {
        char +: previous
      }
    }
  }
}
