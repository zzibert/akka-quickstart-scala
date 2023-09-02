package leetcode

import scala.collection.mutable
import scala.collection.mutable.{ ArrayBuffer, ListBuffer }

class AccountsMerge {
  def accountsMerge(accounts: List[List[String]]): List[List[String]] = {
    val emailToIndex = mutable.Map[String, ListBuffer[Int]]()
    val result = mutable.ArrayBuffer[List[String]]()

    accounts.zipWithIndex foreach {
      case (account, index) =>
        account.tail foreach { email =>
          val buffer = emailToIndex.getOrElseUpdate(email, ListBuffer[Int]())
          buffer.addOne(index)
        }
    }

    val emailLists = mutable.ArrayBuffer.from(emailToIndex.values)
    var _changed = true

    while(_changed) {
      _changed = mergeLists(emailLists)
    }

    val accountsArray = accounts.toArray

    emailLists foreach { emailList =>
      var emails = Array[String]()
      val name = accountsArray(emailList(0))(0)

      emailList foreach { index =>
        emails = (emails ++ accountsArray(index).tail).distinct
      }

      result.addOne((name +: emails.sorted).toList)
    }

    result.toList
  }

  def mergeLists(emailLists: ArrayBuffer[ListBuffer[Int]]): Boolean = {
    var i = 0
    var _changed = false

    while (i < (emailLists.length - 1)) {
      var j = i + 1
      while (j < emailLists.length) {
        if (emailLists(i).intersect(emailLists(j)).length > 0) {
          emailLists(i) = (emailLists(i) ++ emailLists(j)).distinct
          emailLists.remove(j)
          _changed = true
        } else {
          j += 1
        }
      }
      i += 1
    }

    _changed
  }
}
