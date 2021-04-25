package org.birchavenue.zendesk2021.Model

case class Organization(_id: Long, url: String, external_id: String, name: String, domain_names: List[String],
                        created_at: String, details: String, shared_tickets: Boolean, tags: List[String]) extends Entity {

  val fieldNames = this.productElementNames.toList
  val fieldValues = this.productIterator.toList.map(_.toString)

  // column output offset
  val offset = fieldNames.map(_.length).sorted.reverse.headOption.map(_ + 3).getOrElse(20)

  // human readable output for User
  override val toString = fieldNames.map(s => s + (" " * (offset - s.length))).zip(fieldValues).map(s => s._1 + s._2).mkString("\n")

  def matches(field: String, value: String): Boolean = field match {
    case "_id" =>  value.toLongOption.map(_ == this._id).getOrElse(value == "")
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "name" => value == this.name
    case "domain_names" => this.domain_names.contains(value)
    case "created_at" => value == this.created_at
    case "details" => value == this.details
    case "shared_tickets" => value.toBooleanOption.map(_ == this.shared_tickets).getOrElse(value == "")
    case "tags" => this.tags.contains(value)
    case _ => false
  }
}

