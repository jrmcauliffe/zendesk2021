package org.birchavenue.zendesk2021.Model

case class Organization(_id: Long, url: String, external_id: String, name: String, domain_names: List[String],
                        created_at: String, details: String, shared_tickets: Boolean, tags: List[String]) extends Entity {

  def matches(field: String, value: String): Boolean = field match {
    case "_id" =>  value.toLongOption.map(_ == this._id).getOrElse(false)
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "name" => value == this.name
    case "domain_names" => this.domain_names.contains(value)
    case "created_at" => value == this.created_at
    case "details" => value == this.details
    case "shared_tickets" => value.toBooleanOption.map(_ == this.shared_tickets).getOrElse(false)
    case "tags" => this.tags.contains(value)
    case _ => false
  }
}

