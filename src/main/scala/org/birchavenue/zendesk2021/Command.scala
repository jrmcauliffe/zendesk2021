package org.birchavenue.zendesk2021

sealed trait Command
object QuitCommand extends Command
object HelpCommand extends Command
object FieldsCommand extends Command
object UnknownCommand extends Command
case class SearchCommand(entity: String, field: String, value: String) extends Command