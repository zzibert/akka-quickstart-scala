package leetcode

import scala.collection.mutable

class AuthenticationManager(_timeToLive: Int) {
  val tokens = mutable.Map[String, Int]()

  def generate(tokenId: String, currentTime: Int) {
    removeExpiredTokens(currentTime)

    tokens.get(tokenId) match {
      case Some(_) =>
      case None =>
        tokens += (tokenId -> (currentTime + _timeToLive))
    }
  }

  def renew(tokenId: String, currentTime: Int) {
    removeExpiredTokens(currentTime)

    tokens.get(tokenId) match {
      case Some(timestamp) =>
        tokens += (tokenId -> (currentTime + _timeToLive))
      case None =>
    }
  }

  def countUnexpiredTokens(currentTime: Int): Int = {
    removeExpiredTokens(currentTime)

    tokens.size
  }

  def removeExpiredTokens(currentTime: Int) = {
    tokens foreach {
      case (tokenId, timestamp) =>
        if (timestamp <= currentTime) {
          tokens.remove(tokenId)
        }
    }
  }

}
