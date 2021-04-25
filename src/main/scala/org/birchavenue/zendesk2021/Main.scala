package org.birchavenue.zendesk2021

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

object Main {

  def parseOptions(args: Array[String]): Option[Config] = {
    import scopt.OParser
    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._
      OParser.sequence(
        programName("zendesk2021"),
        head("zendesk2021", "1.0"),
      )
    }
    OParser.parse(parser1, args, Config("", ""))
  }

  def main(args: Array[String]): Unit = {
    val config: Option[Config] = parseOptions(args)
    val data = Loader.load("data")
    val searcher: Option[Searcher] = data match {
      case Right((orgs, users, tickets)) => Some(new Searcher(orgs, users, tickets))
      case _ => None
    }

    searcher match {
      case Some(s) => val interpreter = new Interpreter(s); interpreter.run
      case None =>
    }
  }
}
