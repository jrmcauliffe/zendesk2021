package org.birchavenue.zendesk2021

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

class Interpreter(searcher: Searcher) {

  val welcomeText =
    """Welcome to Zendesk Search
      |Type 'quit' to exit at any time, type '?' to see help text
      |> """.stripMargin

  val helpText =
    """
      |
      |To see available fields to search type 'fields'
      |
      |To search on a specific field type 'search entity.field search-term' or 'search entity.field "search-term"'
      |e.g.
      |> search organization._id 103
      |> search ticket.subject "A Catastrophe in Singapore"
      |
      |To search for empty fields, use the empty string ""
      |e.g.
      |> search organization.locale ""
      |
      |To quit the search tool, type 'quit'
      |
      |Type '?' at any point to reshow this help text
      |
      |""".stripMargin

  // Input token matching Regexs
  val searchRE = "search (\\w*)\\.(\\w*) *\"?([^\"]*)\"?".r // Allow search term to be quoted or unquoted
  val fieldsRE = "(fields)".r
  val quitRE = "(quit|exit)".r
  val helpRE = "(\\?|help)".r


  def run {
    print(welcomeText)

    breakable {
      while (true) {
        readLine() match {
          case searchRE(entity, field, value) =>
            searcher.search(entity, field, value) match {
              case Right(entites) => println(entites.mkString("\n----------------------\n"))
              case Left(err) => println("Error: " + err)
            }
          case fieldsRE(_) => searcher.fields match {
            case Some(fields) => println(fields)
            case None => println("Error, no data found to search")
          }
          case helpRE(_) => println(helpText)
          case quitRE(_) => println("bye"); break
          case _ => println("unknown command, type '?' for help")
        }
        print("> ")
      }
    }
  }

}
