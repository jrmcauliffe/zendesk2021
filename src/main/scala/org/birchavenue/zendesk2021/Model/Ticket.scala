package org.birchavenue.zendesk2021.Model

case class Ticket(_id: String, url: String, external_id: String, created_at: String, `type`: Option[String],
                  subject: String, description: Option[String], priority: String, status: String, submitter_id: Long,
                  assignee_id: Option[Long], organization_id: Option[Long], tags: List[String], has_incidents: Boolean,
                  due_at: Option[String], via: String)
