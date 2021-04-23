package org.birchavenue.zendesk2021

import io.circe._, io.circe.generic.semiauto._, io.circe.parser._
import org.birchavenue.zendesk2021.Model._

object Loader {
  implicit val orgDecoder: Decoder[Organization] = deriveDecoder[Organization]
  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val ticketDecoder: Decoder[Ticket] = deriveDecoder[Ticket]

  def load(path: String): Either[Error, (List[Organization], List[User], List[Ticket])] = {
    for {
      orgs <- decode[List[Organization]](loadFile(path +"//" + "organizations.json"))
      users <- decode[List[User]](loadFile(path +"//" + "users.json"))
      tickets <- decode[List[Ticket]](loadFile(path +"//" + "tickets.json"))
    } yield (orgs, users, tickets)
  }

  private def loadFile(filePath: String): String = {
    // TODO any sort of error checking
    val bufferedSource = scala.io.Source.fromFile(filePath)
    val text = bufferedSource.getLines().mkString
    bufferedSource.close()
    text
  }
}
