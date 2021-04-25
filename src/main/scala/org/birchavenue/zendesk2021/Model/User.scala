package org.birchavenue.zendesk2021.Model

case class User(_id: Long, url: String, external_id: String, name: String, alias: Option[String], created_at: String,
                active: Boolean, verified: Option[Boolean], shared: Boolean, locale: Option[String], timezone: Option[String],
                last_login_at: String, email: Option[String], phone: String, signature: String, organization_id: Option[Long],
                tags: List[String], suspended: Boolean, role: String) extends Entity
{
  val fieldNames: List[String] = this.productElementNames.toList
  private val fieldValues = this.productIterator.toList.map(_.toString)

  // column output offset
  private val offset = fieldNames.map(_.length).sorted.reverse.headOption.map(_ + 3).getOrElse(20)

  // human readable output for User
  override val toString: String = fieldNames.map(s => s + (" " * (offset - s.length))).zip(fieldValues).map(s => s._1 + s._2).mkString("\n")

  override def toShortString: String = name

  def matches(field: String, value: String): Boolean = field match {
    case "_id" => value.toLongOption.map(_ == this._id).getOrElse(value == "")
    case "url" => value == this.url
    case "external_id" => value == this.external_id
    case "name" => value == this.name
    case "alias" => this.alias.map(_ == value).getOrElse(value == "")
    case "created_at" => value == this.created_at
    case "active" => value.toBooleanOption.map(_ == this.active).getOrElse(false)
    case "verified" => this.verified == value.toBooleanOption
    case "shared" => value.toBooleanOption.map(_ == this.shared).getOrElse(false)
    case "locale" => this.locale.map(_ == value).getOrElse(value == "")
    case "timezone" => this.timezone.map(_ == value).getOrElse(value == "")
    case "last_login_at" => value == this.last_login_at
    case "email" => this.email.map(_ == value).getOrElse(value == "")
    case "phone" => value == this.phone
    case "signature" => value == this.signature
    case "oranization_id" => value.toLongOption == this.organization_id
    case "tags" => this.tags.contains(value)
    case "suspended" => value.toBooleanOption.map(_ == this.suspended).getOrElse(false)
    case "role" => value == this.role
    case _ => false
  }
}
