import scala.collection.mutable

object Solution {
  def accountsMerge(accounts: List[List[String]]): List[List[String]] = {
    var namesToEmailLists: Map[String, List[List[String]]] = Map.empty
    var result: List[List[String]] = List.empty

    for {
      account <- accounts
    } {
      val name = account.head
      val emails = account.tail

      namesToEmailLists.get(name) match {
        case Some(emailLists) =>
          namesToEmailLists = namesToEmailLists + (name -> (emails :: emailLists))

        case None =>
          namesToEmailLists = namesToEmailLists + (name -> List(emails))
      }
    }

    namesToEmailLists foreach {
      case (name, emailLists) =>
        val uniqueUsers = deduplicate(name, emailLists)
        result = result.concat(uniqueUsers)
    }

    result
  }

  def deduplicate(name: String,
                  emailLists: List[List[String]]): List[List[String]] = {
    val emailListArray = mutable.ArrayBuffer.from(emailLists)
    var changed = true

    while (changed) {
      changed = false
      var i = 0
      while (i < emailListArray.length) {
        var j = i + 1
        while (j < emailListArray.length) {
          if (emailListArray(i).exists(emailListArray(j).contains)) {
            emailListArray(i) =
              (emailListArray(i) ++ emailListArray(j)).distinct
            emailListArray.remove(j)
            changed = true
          } else {
            j += 1
          }
        }
        i += 1
      }
    }

    (emailListArray map { emailList =>
      name :: emailList.distinct.sorted
    }).toList
  }
}
