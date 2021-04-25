package org.birchavenue.zendesk2021

import org.birchavenue.zendesk2021.Model._
import io.circe.Error

import scala.util.{Try, Success, Failure}

object Loader {

  def load(path: String): Either[String, (List[Organization], List[User], List[Ticket])] = {
    for {
      orgs <- loadFile(path +"//" + "organizations.json").flatMap(EntityDecoder.decodeOrgs(_))
      users <- loadFile(path +"//" + "users.json").flatMap(EntityDecoder.decodeUsers(_))
      tickets <- loadFile(path +"//" + "tickets.json").flatMap(EntityDecoder.decodeTickets(_))
    } yield (orgs, users, tickets)
    }

  private def loadFile(filePath: String): Either[String, String] = {
    Try {
      val bufferedSource = scala.io.Source.fromFile(filePath)
      val text = bufferedSource.getLines().mkString
      bufferedSource.close()
      text
    } match {
      case Success(s) => Right(s)
      case Failure (_) => Left("Failed to open " + filePath)
    }
  }
}
