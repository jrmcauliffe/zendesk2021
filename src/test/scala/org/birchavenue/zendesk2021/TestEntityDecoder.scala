package org.birchavenue.zendesk2021

import org.birchavenue.zendesk2021.Model.{Organization, Ticket, User}
import org.scalatest.funsuite.AnyFunSuite

class TestEntityDecoder extends AnyFunSuite {

  private val validOrg =
    """[{
      |  "_id": 105,
      |    "url": "http://initech.zendesk.com/api/v2/organizations/105.json",
      |    "external_id": "52f12203-6112-4fb9-aadc-70a6c816d605",
      |    "name": "Koffee",
      |    "domain_names": [
      |      "farmage.com",
      |      "extrawear.com",
      |      "bulljuice.com",
      |      "enaut.com"
      |    ],
      |    "created_at": "2016-06-06T02:50:27 -10:00",
      |    "details": "MegaCorp",
      |    "shared_tickets": false,
      |    "tags": [
      |      "Jordan",
      |      "Roy",
      |      "Mckinney",
      |      "Frost"
      |    ]
      |  }]""".stripMargin

  private val invalidOrg =
    """[{
      |    "url": "http://initech.zendesk.com/api/v2/organizations/105.json",
      |    "external_id": "52f12203-6112-4fb9-aadc-70a6c816d605",
      |    "name": "Koffee",
      |    "domain_names": [
      |      "farmage.com",
      |      "extrawear.com",
      |      "bulljuice.com",
      |      "enaut.com"
      |    ],
      |    "created_at": "2016-06-06T02:50:27 -10:00",
      |    "details": "MegaCorp",
      |    "shared_tickets": false,
      |    "tags": [
      |      "Jordan",
      |      "Roy",
      |      "Mckinney",
      |      "Frost"
      |    ]
      |  }]""".stripMargin

  private val expectedOrgs = List(Organization(105,"http://initech.zendesk.com/api/v2/organizations/105.json",
                         "52f12203-6112-4fb9-aadc-70a6c816d605","Koffee",
                          List("farmage.com", "extrawear.com", "bulljuice.com", "enaut.com"),
                         "2016-06-06T02:50:27 -10:00","MegaCorp",false,List("Jordan", "Roy", "Mckinney", "Frost")))

  private val validTicket = """[{
                      |    "_id": "436bf9b0-1147-4c0a-8439-6f79833bff5b",
                      |    "url": "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
                      |    "external_id": "9210cdc9-4bee-485f-a078-35396cd74063",
                      |    "created_at": "2016-04-28T11:19:34 -10:00",
                      |    "type": "incident",
                      |    "subject": "A Catastrophe in Korea (North)",
                      |    "description": "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.",
                      |    "priority": "high",
                      |    "status": "pending",
                      |    "submitter_id": 38,
                      |    "assignee_id": 24,
                      |    "organization_id": 116,
                      |    "tags": [
                      |      "Ohio",
                      |      "Pennsylvania",
                      |      "American Samoa",
                      |      "Northern Mariana Islands"
                      |    ],
                      |    "has_incidents": false,
                      |    "due_at": "2016-07-31T02:37:50 -10:00",
                      |    "via": "web"
                      |}] """.stripMargin

  private val invalidTicket = """[{
                        |    "url": "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
                        |    "external_id": "9210cdc9-4bee-485f-a078-35396cd74063",
                        |    "created_at": "2016-04-28T11:19:34 -10:00",
                        |    "type": "incident",
                        |    "subject": "A Catastrophe in Korea (North)",
                        |    "description": "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.",
                        |    "priority": "high",
                        |    "status": "pending",
                        |    "submitter_id": 38,
                        |    "assignee_id": 24,
                        |    "organization_id": 116,
                        |    "tags": [
                        |      "Ohio",
                        |      "Pennsylvania",
                        |      "American Samoa",
                        |      "Northern Mariana Islands"
                        |    ],
                        |    "has_incidents": false,
                        |    "due_at": "2016-07-31T02:37:50 -10:00",
                        |    "via": "web"
                        |  }] """.stripMargin

  private val expectedTickets = List(Ticket("436bf9b0-1147-4c0a-8439-6f79833bff5b", "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
                                   "9210cdc9-4bee-485f-a078-35396cd74063", "2016-04-28T11:19:34 -10:00",Some("incident"),"A Catastrophe in Korea (North)",
                                   Some("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."),
                                   "high","pending",38,Some(24),Some(116),List("Ohio", "Pennsylvania", "American Samoa", "Northern Mariana Islands"),
                                   false,Some("2016-07-31T02:37:50 -10:00"),"web"))

  private val validUser = """[{
                    |    "_id": 1,
                    |    "url": "http://initech.zendesk.com/api/v2/users/1.json",
                    |    "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
                    |    "name": "Francisca Rasmussen",
                    |    "alias": "Miss Coffey",
                    |    "created_at": "2016-04-15T05:19:46 -10:00",
                    |    "active": true,
                    |    "verified": true,
                    |    "shared": false,
                    |    "locale": "en-AU",
                    |    "timezone": "Sri Lanka",
                    |    "last_login_at": "2013-08-04T01:03:27 -10:00",
                    |    "email": "coffeyrasmussen@flotonic.com",
                    |    "phone": "8335-422-718",
                    |    "signature": "Don't Worry Be Happy!",
                    |    "organization_id": 119,
                    |    "tags": [
                    |      "Springville",
                    |      "Sutton",
                    |      "Hartsville/Hartley",
                    |      "Diaperville"
                    |    ],
                    |    "suspended": true,
                    |    "role": "admin"
                    |  }]""".stripMargin

  private val invalidUser = """[{
                    |    "url": "http://initech.zendesk.com/api/v2/users/1.json",
                    |    "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
                    |    "name": "Francisca Rasmussen",
                    |    "alias": "Miss Coffey",
                    |    "created_at": "2016-04-15T05:19:46 -10:00",
                    |    "active": true,
                    |    "verified": true,
                    |    "shared": false,
                    |    "locale": "en-AU",
                    |    "timezone": "Sri Lanka",
                    |    "last_login_at": "2013-08-04T01:03:27 -10:00",
                    |    "email": "coffeyrasmussen@flotonic.com",
                    |    "phone": "8335-422-718",
                    |    "signature": "Don't Worry Be Happy!",
                    |    "organization_id": 119,
                    |    "tags": [
                    |      "Springville",
                    |      "Sutton",
                    |      "Hartsville/Hartley",
                    |      "Diaperville"
                    |    ],
                    |    "suspended": true,
                    |    "role": "admin"
                    |  }]""".stripMargin

  private val expectedUsers = List(User(1,"http://initech.zendesk.com/api/v2/users/1.json","74341f74-9c79-49d5-9611-87ef9b6eb75f",
                          "Francisca Rasmussen",Some("Miss Coffey"),"2016-04-15T05:19:46 -10:00",true,Some(true),false,
                          Some("en-AU"),Some("Sri Lanka"),"2013-08-04T01:03:27 -10:00",Some("coffeyrasmussen@flotonic.com"),
                          "8335-422-718","Don't Worry Be Happy!",Some(119),
                          List("Springville", "Sutton", "Hartsville/Hartley", "Diaperville"),true,"admin"))

  test("EntityLoader Object decodes valid org data") {
    assert(EntityDecoder.decodeOrgs(validOrg) == Right(expectedOrgs))
  }
  test("EntityLoader Object fails when decoding invalid org data") {
    assert(EntityDecoder.decodeOrgs(invalidOrg).isLeft)
  }

  test("EntityLoader Object decodes valid ticket data") {
    assert(EntityDecoder.decodeTickets(validTicket) == Right(expectedTickets))
  }
  test("EntityLoader Object fails when decoding invalid ticket data") {
    assert(EntityDecoder.decodeTickets(invalidTicket).isLeft)
  }

  test("EntityLoader Object decodes valid user data") {
    assert(EntityDecoder.decodeUsers(validUser) == Right(expectedUsers))
  }
  test("EntityLoader Object fails when decoding invalid user data") {
    assert(EntityDecoder.decodeUsers(invalidUser).isLeft)
  }
}
