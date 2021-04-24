package org.birchavenue.zendesk2021.Model

case class Ticket(_id: String, url: String, external_id: String, created_at: String, `type`: Option[String],
                  subject: String, description: Option[String], priority: String, status: String, submitter_id: Long,
                  assignee_id: Option[Long], organization_id: Option[Long], tags: List[String], has_incidents: Boolean,
                  due_at: Option[String], via: String) extends Entity
{
  def matches(field: String, value: String): Boolean = field match {
    case "_id" =>  value == this._id
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "created_at" => value == this.created_at
    case "type" => this.`type`.map(_ == value).getOrElse(false)
    case "subject" => value == this.subject
    case "description" => this.description.map(_ == value).getOrElse(false)
    case "priority" => value == this.priority
    case "status" => value == this.status
    case "submitter_id" => value.toLongOption.map(_ == this.submitter_id).getOrElse(false)
    case "assignee_id" => value.toLongOption == this.assignee_id
    case "organization_id" => value.toLongOption == this.organization_id
    case "tags" => this.tags.contains(value)
    case "has_incidents" => value == this.has_incidents
    case "due_at" => this.due_at.map(_ == value).getOrElse(false)
    case "via" => value == this.via
    case _ => false
  }
}
