package org.birchavenue.zendesk2021

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

class Interpreter(searcher: Searcher) {

def run {
  println("Welcome to Zendesk Search")
  println("Type 'quit' to exit at any time, type '?' to see help text")
  print("> ")

  val searchRE = "search (\\w*)\\.(\\w*) (\\w*)".r
  val quitRE = "(quit)".r
  val helpRE = "(\\?)".r

  breakable {
    while (true) {
      readLine() match {
        case searchRE(entity, field, value) => println(searcher.search(entity, field, value).toString)
        case helpRE(x) => println("helptest")
        case quitRE(x) => println("bye"); break
        case _ => println("unknown command, type '?' for help")
      }
      print("> ")
    }
  }}

}
