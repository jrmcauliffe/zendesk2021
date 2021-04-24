package org.birchavenue.zendesk2021

object Main {

  def parseOptions(args: Array[String]): Option[Config] = {
    import scopt.OParser
    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._
      OParser.sequence(
        programName("zendesk2021"),
        head("zendesk2021", "1.0"),
        arg[String]("field")
          .action((x, c) => c.copy(field = x))
          .text("Field to search on e.g. organization._id"),
        arg[String]("value")
          .action((x, c) => c.copy(value = x))
          .text("Value to search on e.g. 103"),
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
    val result = for {
      s <- searcher
      c <- config
    } yield s.search(c.field, c.value)
    println(result)
  }
}
