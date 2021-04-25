package org.birchavenue.zendesk2021.Model

case class Ticket(_id: String, url: String, external_id: String, created_at: String, `type`: Option[String],
                  subject: String, description: Option[String], priority: String, status: String, submitter_id: Long,
                  assignee_id: Option[Long], organization_id: Option[Long], tags: List[String], has_incidents: Boolean,
                  due_at: Option[String], via: String) extends Entity
{
  val fieldNames = this.productElementNames.toList
  val fieldValues = this.productIterator.toList.map(_.toString)

  // column output offset
  val offset = fieldNames.map(_.length).sorted.reverse.headOption.map(_ + 3).getOrElse(20)

  // human readable output for User
  override val toString = fieldNames.map(s => s + (" " * (offset - s.length))).zip(fieldValues).map(s => s._1 + s._2).mkString("\n")

  val toStringShort = subject

  def matches(field: String, value: String): Boolean = field match {
    case "_id" => value == this._id
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "created_at" => value == this.created_at
    case "type" => this.`type`.map(_ == value).getOrElse(value == "")
    case "subject" => value == this.subject
    case "description" => this.description.map(_ == value).getOrElse(value == "")
    case "priority" => value == this.priority
    case "status" => value == this.status
    case "submitter_id" => value.toLongOption.map(_ == this.submitter_id).getOrElse(value == "")
    case "assignee_id" => value.toLongOption == this.assignee_id
    case "organization_id" => value.toLongOption == this.organization_id
    case "tags" => this.tags.contains(value)
    case "has_incidents" => value == this.has_incidents
    case "due_at" => this.due_at.map(_ == value).getOrElse(value == "")
    case "via" => value == this.via
    case _ => false
  }
}
