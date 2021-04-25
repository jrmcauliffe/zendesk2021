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

  def run: Unit = {
    print(welcomeText)

    breakable {
      // Loop until quit
      while (true) {
        Interpreter.interpret(readLine()) match {
          case SearchCommand(entity, field, value) =>
            searcher.search(entity, field, value) match {
              case Right(Nil) => println("No results found")
              case Right(results) => {
                // Output results with supplimentary records
                println(results.map(r => r._1.toString :: r._2.map(_.toShortString)).flatten.mkString("\n"))
              }
              case Left(err) => println("Error: " + err)
            }
          case FieldsCommand => searcher.fields match {
            case Some(fields) => println(fields)
            case None => println("Error, no data found to search")
          }
          case HelpCommand => println(helpText)
          case QuitCommand => println("bye"); break()
          case UnknownCommand => println("Unknown command, type '?' for help")
        }
        print("> ") // Print next cursor and loop
      }
    }
  }

}

object Interpreter {
  def interpret(cmdString: String): Command = {
    // Input token matching Regexs
    val searchRE = "\\s*search\\s*(\\w*)\\.(\\w*)\\s*\"?([^\"]*)\"?".r // Allow search term to be quoted or unquoted
    val fieldsRE = "\\s*(fields)\\s*".r
    val quitRE = "\\s*(quit|exit)\\s*".r
    val helpRE = "\\s*(\\?|help)\\s*".r

    cmdString match {
      case searchRE(entity, field, value) => SearchCommand(entity, field, value)
      case fieldsRE(_) => FieldsCommand
      case helpRE(_) => HelpCommand
      case quitRE(_) => QuitCommand
      case _ => UnknownCommand
    }
  }
}