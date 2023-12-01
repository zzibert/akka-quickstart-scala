package leetcode

import scala.collection.mutable

object MinimumGeneticMutation {
  def minMutation(startGene: String, endGene: String, bank: Array[String]): Int = {
    val oneLetterDifference = mutable.Map[Int, mutable.ArrayBuffer[Int]]()
    val queue = mutable.Queue[(Int, Int)]()
    val tried = mutable.Map[Int, Boolean]()

    // start gene
    val startBuffer =
      oneLetterDifference.getOrElseUpdate(-1, mutable.ArrayBuffer[Int]())
    for (i <- 0 until bank.length) {
      if (isOneLetterDifference(startGene, bank(i))) {
        startBuffer.addOne(i)
      }
    }

    // all other genes
    for (i <- 0 until bank.length) {
      val firstBuffer =
        oneLetterDifference.getOrElseUpdate(i, mutable.ArrayBuffer[Int]())
      for (j <- i + 1 until bank.length) {
        if (isOneLetterDifference(bank(i), bank(j))) {
          firstBuffer.addOne(j)
          val secondBuffer =
            oneLetterDifference.getOrElseUpdate(j, mutable.ArrayBuffer[Int]())
          secondBuffer.addOne(i)
        }
      }
    }

    queue.enqueue((0, -1))

    while (queue.nonEmpty) {
      val (length, geneIndex) = queue.dequeue()
      val gene =
        if (geneIndex == -1) {
          startGene
        } else {
          bank(geneIndex)
        }
      if (gene == endGene) {
        return length
      } else {
        tried.update(geneIndex, true)
        oneLetterDifference.get(geneIndex) match {
          case Some(buffer) =>
            buffer
              .view
              .filterNot(index => tried.getOrElse(index, false))
              .foreach(index => queue.enqueue((length+1, index)))

          case None =>
        }
      }
    }

    -1
  }

  def isOneLetterDifference(gene1: String, gene2: String): Boolean = {
    val array1 = gene1.toCharArray
    val array2 = gene2.toCharArray

    var difference = 0
    for (i <- 0 until 8) {
      if (array1(i) != array2(i)) {
        difference += 1
        if (difference > 1) {
          return false
        }
      }
    }

    difference == 1
  }
}
