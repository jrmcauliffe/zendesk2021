package org.birchavenue.zendesk2021

import Model.{Entity, Organization, Ticket, User}

class Searcher(orgs: List[Organization], users: List[User], tickets: List[Ticket]) {

  // Provide a list of all fields available for search
  val fields: Option[String] = for {
    orgFields <- orgs.headOption.map(_.fieldNames.map("organization." + _).mkString("\n"))
    userFields <- users.headOption.map(_.fieldNames.map("user." + _).mkString("\n"))
    ticketFields <- tickets.headOption.map(_.fieldNames.map("ticket." + _).mkString("\n"))
  } yield List(orgFields, userFields, ticketFields).mkString("\n")

  // Implement actual search
  def search(entity: String, field: String, value: String): Either[String, List[(Entity, List[Entity])]] = {
    entity match {
      case "ticket" => Right(tickets.filter(_.matches(field, value)).map(t => (t, Nil)))
      case "organization" => Right(orgs.filter(_.matches(field, value)).map(o =>
        (o, tickets.filter(_.matches("organization_id", o._id.toString)))))  // Suppliment with org tickets
      case "user" => Right(users.filter(_.matches(field, value)).map(u=>
        (u, tickets.filter(_.matches("submitter_id", u._id.toString)))))     // Suppliment with user tickets
      case _ => Left("Unknown entity")
    }
  }
}
