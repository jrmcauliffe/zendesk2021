package org.birchavenue.zendesk2021

object Main {
  def main(args: Array[String]): Unit = {

    // Parse command line option(s)
    Config.parseOptions(args) match {
      case Some(config) => {
        // Load data
        val data = Loader.load(config.dataPath)

        // Intialise searcher
        val searcher: Option[Searcher] = data match {
          case Right((orgs, users, tickets)) => Some(new Searcher(orgs, users, tickets))
          case _ => None
        }

        // Start text interpreter
        searcher match {
          case Some(s) => val interpreter = new Interpreter(s); interpreter.run
          case None =>
        }
      }
      case None => println("Incorrect command line arguments, exiting")
    }
  }

}
