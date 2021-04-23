package org.birchavenue.zendesk2021

import Model.{Organization, User, Ticket}

class Searcher(orgs: List[Organization], users: List[User], tickets: List[Ticket]) {
  def search(field: String, value: String): List[Organization] =
  Nil
}
