package org.birchavenue.zendesk2021

import Model.{Entity, Organization, Ticket, User}


class Searcher(orgs: List[Organization], users: List[User], tickets: List[Ticket]) {

  def search(field: String, value: String): Either[String, List[Entity]] = {
    field.split('.').toList match {
      case List("ticket", f) => Right(tickets.filter(_.matches(f, value)))
      case List("organization", f)  => Right(orgs.filter(_.matches(f, value)))
      case List("user", f) => Right(users.filter(_.matches(f, value)))
      case _ => Left("Unknown entity")
    }
  }
}
