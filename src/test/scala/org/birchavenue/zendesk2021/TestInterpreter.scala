package org.birchavenue.zendesk2021

import org.scalatest.funsuite.AnyFunSuite

class TestInterpreter extends AnyFunSuite {

  test("Interpreter correctly interprets quit command") {
    assert(Interpreter.interpret("quit") == QuitCommand)
  }

  test("Interpreter correctly interprets help command") {
    assert(Interpreter.interpret("?") == HelpCommand)
  }

  test("Interpreter correctly interprets fields command") {
    assert(Interpreter.interpret("fields") == FieldsCommand)
  }

  test("Interpreter correctly interprets unknown command") {
    assert(Interpreter.interpret("NOT A COMMAND") == UnknownCommand)
  }

  test("Interpreter correctly interprets search command") {
    assert(Interpreter.interpret("search organization._id 100") == SearchCommand("organization", "_id", "100"))
  }

  test("Interpreter correctly interprets search command with extra whitespace") {
    assert(Interpreter.interpret(" search   organization._id    100") == SearchCommand("organization", "_id", "100"))
  }

}
