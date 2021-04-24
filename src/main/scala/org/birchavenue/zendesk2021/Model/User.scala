package org.birchavenue.zendesk2021.Model

case class User(_id: Long, url: String, external_id: String, name: String, alias: Option[String], created_at: String,
                active: Boolean, verified: Option[Boolean], shared: Boolean, locale: Option[String], timezone: Option[String],
                last_login_at: String, email: Option[String], phone: String, signature: String, organization_id: Option[Long],
                tags: List[String], suspended: Boolean, role: String) extends Entity
{
  def matches(field: String, value: String): Boolean = field match {
    case "_id" => value.toLongOption.map(_ == this._id).getOrElse(false)
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "name" => value == this.name
    case "alias" => this.alias.map(_ == value).getOrElse(false)
    case "created_at" => value == this.created_at
    case "active" => value == this.active
    case "verified" => this.verified == value.toBooleanOption
    case "shared" => value == this.shared
    case "locale" => this.locale.map(_ == value).getOrElse(false)
    case "timezone" => this.timezone.map(_ == value).getOrElse(false)
    case "last_login_at" => value == this.last_login_at
    case "email" => this.email.map(_ == value).getOrElse(false)
    case "phone" => value == this.phone
    case "signature" => value == this.signature
    case "oranization_id" => value.toLongOption == this.organization_id
    case "tags" => this.tags.contains(value)
    case "suspended" => value == this.suspended
    case "role" => value == this.role
    case _ => false
  }
}
