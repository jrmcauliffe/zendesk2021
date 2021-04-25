package org.birchavenue.zendesk2021

import Model.{Entity, Organization, Ticket, User}


class Searcher(orgs: List[Organization], users: List[User], tickets: List[Ticket]) {
  //TODO change this to regex to differentiate between bad commands and missing fields

  val fields = for {
    orgFields <- orgs.headOption.map(_.fieldNames.map("organization." + _).mkString("\n"))
    userFields <- users.headOption.map(_.fieldNames.map("user." + _).mkString("\n"))
    ticketFields <- tickets.headOption.map(_.fieldNames.map("ticket." + _).mkString("\n"))
  } yield List(orgFields, userFields, ticketFields).mkString("\n")

  def search(entity: String, field: String, value: String): Either[String, List[Entity]] = {
    entity match {
      case "ticket" => Right(tickets.filter(_.matches(field, value)))
      case "organization" => Right(orgs.filter(_.matches(field, value)))
      case "user" => Right(users.filter(_.matches(field, value)))
      case _ => Left("Unknown entity")
    }
  }
}
