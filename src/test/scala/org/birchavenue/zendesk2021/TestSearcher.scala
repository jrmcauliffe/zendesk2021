package org.birchavenue.zendesk2021

import org.birchavenue.zendesk2021.Model.{Organization, Ticket, User}
import org.scalatest.funsuite.AnyFunSuite

class TestSearcher extends AnyFunSuite {

  private val orgs = List(Organization(105,"http://initech.zendesk.com/api/v2/organizations/105.json",
    "52f12203-6112-4fb9-aadc-70a6c816d605","Koffee",
    List("farmage.com", "extrawear.com", "bulljuice.com", "enaut.com"),
    "2016-06-06T02:50:27 -10:00","MegaCorp",false,List("Jordan", "Roy", "Mckinney", "Frost")))

  private val tickets = List(Ticket("436bf9b0-1147-4c0a-8439-6f79833bff5b", "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
    "9210cdc9-4bee-485f-a078-35396cd74063", "2016-04-28T11:19:34 -10:00",Some("incident"),"A Catastrophe in Korea (North)",
    Some("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."),
    "high","pending",38,Some(24),Some(116),List("Ohio", "Pennsylvania", "American Samoa", "Northern Mariana Islands"),
    false,Some("2016-07-31T02:37:50 -10:00"),"web"))

  private val users = List(User(1,"http://initech.zendesk.com/api/v2/users/1.json","74341f74-9c79-49d5-9611-87ef9b6eb75f",
    "Francisca Rasmussen",Some("Miss Coffey"),"2016-04-15T05:19:46 -10:00",true,None,false,
    None,Some("Sri Lanka"),"2013-08-04T01:03:27 -10:00",Some("coffeyrasmussen@flotonic.com"),
    "8335-422-718","Don't Worry Be Happy!",Some(119),
    List("Springville", "Sutton", "Hartsville/Hartley", "Diaperville"),true,"admin"))

  val searcher = new Searcher(orgs, users, tickets)

  test("Invalid search field returns error") {
    assert(searcher.search("notentity", "id", "220").isLeft)
  }
  test("Valid org search field returns correct value") {
    assert(searcher.search("organization", "_id", "105") == Right(orgs.map((_, Nil))))
  }
  test("Valid ticket search field returns correct value") {
    assert(searcher.search("ticket", "_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b") == Right(tickets.map((_, Nil))))
  }
  test("Valid user search field returns correct value") {
    assert(searcher.search("user", "_id", "1") == Right(users.map((_, Nil))))
  }
  test("Valid search on empty field returns correct value") {
    assert(searcher.search("user", "locale", "") == Right(users.map((_, Nil))))
  }
}

