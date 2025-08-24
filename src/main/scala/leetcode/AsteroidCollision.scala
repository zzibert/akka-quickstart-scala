package leetcode

import scala.collection.mutable

object Solution {
  def asteroidCollision(asteroids: Array[Int]): Array[Int] = {
    val rightTravellingAsteroids = mutable.Stack[(Int, Int)]()
    val leftTravellingAsteroids = mutable.ListBuffer[(Int, Int)]()

    for {
      i <- 0 until asteroids.length
      asteroid = asteroids(i)
    } {
      if (asteroid > 0) {
        rightTravellingAsteroids.push((i, asteroid))
      } else {
        val convertedAsteroid = -asteroid
        var destroyed = false
        while (!destroyed && rightTravellingAsteroids.nonEmpty) {
          if (rightTravellingAsteroids.head._2 <= convertedAsteroid) {
            if (rightTravellingAsteroids.head._2 == convertedAsteroid) {
              destroyed = true
            }
            rightTravellingAsteroids.pop()
          } else {
            destroyed = true
          }
        }
        if (!destroyed) {
          leftTravellingAsteroids.addOne((i, asteroid))
        }
      }
    }

    (rightTravellingAsteroids.toArray ++ leftTravellingAsteroids.toArray)
      .sortBy(_._1)
      .map(_._2)
  }
}
