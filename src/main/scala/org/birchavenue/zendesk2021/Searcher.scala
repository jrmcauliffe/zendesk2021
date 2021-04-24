package org.birchavenue.zendesk2021

import Model.{Entity, Organization, Ticket, User}


class Searcher(orgs: List[Organization], users: List[User], tickets: List[Ticket]) {
  def search(dataType: String, field: String, value: String): Either[String, List[Entity]] = {

    dataType match {
      case "ticket" => Right(tickets.filter(_.matches(field, value)))
      case "organization" => Right(orgs.filter(_.matches(field, value)))
      case "user" => Right(users.filter(_.matches(field, value)))
      case _ => Left("Unknown entity")
    }
  }
}
