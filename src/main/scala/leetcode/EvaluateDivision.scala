package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object EvaluateDivision {
  def calcEquation(equations: List[List[String]], values: Array[Double], queries: List[List[String]]): Array[Double] = {
    val parents = mutable.Map[String, (Double, String)]()
    val results = ArrayBuffer[Double]()

    val validVariables = equations.flatten.distinct

    equations.zip(values) foreach {
      case (equation, value) =>
        if (value > 1) {
          if (parents.get(equation(0)).isEmpty) {
            parents += (equation(0) -> (value, equation(1)))
          } else {
            union(equation(0), equation(1), value, parents)
          }
        } else {
          if (parents.get(equation(1)).isEmpty) {
            parents += (equation(1) -> (1.0 / value, equation(0)))
          } else {
            union(equation(1), equation(0), 1.0 / value, parents)
          }
        }

    }

    queries foreach { query =>
      results.addOne(calculate(query, parents, validVariables))
    }

    results.toArray
  }

  def calculate(query: List[String], parents: mutable.Map[String, (Double, String)], validVariables: List[String]): Double = {
    val evaluation1 = eval(query(0), 1.0, parents)
    val evaluation2 = eval(query(1), 1.0, parents)

    if (evaluation1._2 != evaluation2._2) {
      -1.0
    } else {
      if (!validVariables.contains(evaluation1._2) || !validVariables.contains(evaluation2._2)) {
        -1.0
      } else {
        evaluation1._1 / evaluation2._1
      }
    }
  }

  def eval(variable: String, multiplier: Double, parents: mutable.Map[String, (Double, String)]): (Double, String) = {
    parents.get(variable) match {
      case Some((multiply, parent)) =>
        eval(parent, multiplier * multiply, parents)
      case None =>
        (multiplier, variable)
    }
  }

  def union(variable1: String, variable2: String, multiplier: Double, parents: mutable.Map[String, (Double, String)]): Unit = {
    val eval1 = eval(variable1, 1, parents)
    val eval2 = eval(variable2, 1, parents)

    if (eval1._2 != eval2._2) {
      parents += (eval2._2 -> ((eval2._1 / multiplier) * eval1._1, eval1._2))
    }
  }


}
