package org.birchavenue.zendesk2021

import io.circe.Error
import org.birchavenue.zendesk2021.Model.Organization

object Main {
  def main(args: Array[String]): Unit = {
    val data = Loader.load("data")
    val searcher: Option[Searcher] = data match {
      case Right((orgs, users, tickets)) => Some(new Searcher(orgs, users, tickets))
      case _ => None
    }
    println(searcher.map(_.search("organization", "shared_tickets", "false")))
  }
}
