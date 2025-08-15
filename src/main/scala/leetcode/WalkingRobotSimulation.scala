package leetcode

object Solution {
  def robotSim(commands: Array[Int], obstacles: Array[Array[Int]]): Int = {
    var result = 0
    var position = Position(0, 0)
    var direction = 'N'

    val obstaclesPosition =
      obstacles map { arr =>
        Position(arr(0), arr(1))
      }

    commands foreach { command =>
      if (command < 0) {
        direction = changeDirection(direction, command)
      } else {
        val relevantObstacles =
          position.filterObstacles(direction, obstaclesPosition)
        position = move(position, direction, command, relevantObstacles)

        val distance = position.distance

        if (distance > result) {
          result = distance
        }
      }
    }

    result
  }

  def move(position: Position,
           direction: Char,
           units: Int,
           obstacles: Array[Position]): Position = {
    if (units == 0) {
      position
    } else {
      val nextPosition =
        direction match {
          case 'N' =>
            Position(position.x, position.y + 1)

          case 'E' =>
            Position(position.x + 1, position.y)

          case 'S' =>
            Position(position.x, position.y - 1)

          case 'W' =>
            Position(position.x - 1, position.y)
        }

      if (obstacles.contains(nextPosition)) {
        position
      } else {
        move(nextPosition, direction, units - 1, obstacles)
      }
    }
  }

  def changeDirection(direction: Char, instruction: Int): Char = {
    (direction, instruction) match {
      case ('N', -1) =>
        'E'

      case ('N', -2) =>
        'W'

      case ('E', -1) =>
        'S'

      case ('E', -2) =>
        'N'

      case ('S', -1) =>
        'W'

      case ('S', -2) =>
        'E'

      case ('W', -1) =>
        'N'

      case ('W', -2) =>
        'S'
    }
  }

  def obstaclesByX(obstacles: Array[Position], x: Int): Array[Position] = {
    obstacles.filter(_.x == x)
  }

  def obstaclesByY(obstacles: Array[Position], y: Int): Array[Position] = {
    obstacles.filter(_.y == y)
  }

  case class Position(x: Int, y: Int) {

    def filterObstacles(direction: Char, obstacles: Array[Position]) = {
      direction match {
        case 'N' | 'S' =>
          obstaclesByX(obstacles, x)

        case 'W' | 'E' =>
          obstaclesByY(obstacles, y)
      }
    }

    def distance: Int =
      x * x + y * y
  }
}
