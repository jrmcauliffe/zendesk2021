package org.birchavenue.zendesk2021

object Main {
  def main(args: Array[String]): Unit = {

    // Attempt to bootstrap App with data
    val interpreter = for {
      config <- Config.parseOptions(args) // Load Config
      data <- Loader.load(config.dataPath) // Load Data
    } yield new Interpreter(new Searcher(data._1, data._2, data._3)) // Create search and interpreter

    // Quit with errors or launch search interface
    interpreter match {
      case Left(err) => println("Error starting search program\n" + err)
      case Right(i) => i.run
    }
  }
}
