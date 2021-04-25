package org.birchavenue.zendesk2021

import io.circe.Decoder
import io.circe.generic.semiauto._
import io.circe.parser._
import org.birchavenue.zendesk2021.Model.{Organization, Ticket, User}

object EntityDecoder {
  implicit val orgDecoder: Decoder[Organization] = deriveDecoder[Organization]
  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val ticketDecoder: Decoder[Ticket] = deriveDecoder[Ticket]

  def decodeOrgs(s: String): Either[String, List[Organization]] = decode[List[Organization]](s) match {
    case Left(e) => Left("Error Decoding Orgs \n" + e.toString)
    case Right(v) => Right(v)
  }
  def decodeUsers(s: String): Either[String, List[User]] = decode[List[User]](s) match {
    case Left(e) => Left("Error Decoding Users \n" + e.toString)
    case Right(v) => Right(v)
  }
  def decodeTickets(s: String): Either[String, List[Ticket]] = decode[List[Ticket]](s) match {
    case Left(e) => Left("Error Decoding Tickets \n" + e.toString)
    case Right(orgList) => Right(orgList)
  }

}
