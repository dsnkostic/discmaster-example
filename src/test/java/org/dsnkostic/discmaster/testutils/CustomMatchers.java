package org.dsnkostic.discmaster.testutils;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import org.hamcrest.Matcher;

public class CustomMatchers {

  private CustomMatchers() {}

  static public <E> Matcher<? super E> game(String expectedTitle, String expectedDescription) {
    return allOf(
        hasProperty("title", equalTo(expectedTitle)),
        hasProperty("description", equalTo(expectedDescription)));
  }

  static public <E> Matcher<? super E> game(Long expectedId, String expectedTitle, String expectedDescription) {
    return allOf(
        hasProperty("id", equalTo(expectedId)),
        hasProperty("title", equalTo(expectedTitle)),
        hasProperty("description", equalTo(expectedDescription)));
  }
}
