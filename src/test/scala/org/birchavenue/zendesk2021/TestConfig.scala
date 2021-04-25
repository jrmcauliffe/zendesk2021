package org.birchavenue.zendesk2021

import org.scalatest.funsuite.AnyFunSuite

class TestConfig extends AnyFunSuite {

  test("Config Object loads default path with no arguments") {
    assert(Config.parseOptions(Array.empty) == Right(Config(".//data")))
  }

  test("Config Object loads supplied path with short argument") {
    assert(Config.parseOptions(Array("-d", ".//mypath")) == Right(Config(".//mypath")))
  }

  test("Config Object loads supplied path with long argument") {
    assert(Config.parseOptions(Array("--datapath", ".//mypath")) == Right(Config(".//mypath")))
  }

  test("Config Object Fails when supplied invalid arguments") {
    assert(Config.parseOptions(Array("NOTREALARGUMENTS")).isLeft)
  }

}
