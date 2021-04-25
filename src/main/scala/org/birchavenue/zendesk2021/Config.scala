package org.birchavenue.zendesk2021

case class Config(dataPath: String)

object Config {
  def parseOptions(args: Array[String]): Option[Config] = {
    import scopt.OParser
    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._
      OParser.sequence(
      programName("zendesk2021"),
      head("zendesk2021", "1.0"),
      opt[String]('d', "datapath")
      .action((x, c) => c.copy(dataPath = x))
      .text("relative path of data, defaults to ./data"),
      )
    }
    OParser.parse(parser1, args, Config(".//data"))
  }
}
