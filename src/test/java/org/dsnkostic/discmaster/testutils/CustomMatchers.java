package org.dsnkostic.discmaster.testutils;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.util.UUID;
import org.hamcrest.Matcher;

public class CustomMatchers {

  private CustomMatchers() {}

  static public <E> Matcher<? super E> game(String expectedShortUrl, String expectedTitle, String expectedDescription) {
    return allOf(
        hasProperty("shortUrl", equalTo(expectedShortUrl)),
        hasProperty("title", equalTo(expectedTitle)),
        hasProperty("description", equalTo(expectedDescription)));
  }

  static public <E> Matcher<? super E> game(UUID expectedId, String expectedShortUrl,
      String expectedTitle, String expectedDescription) {
    return allOf(
        hasProperty("id", equalTo(expectedId)),
        hasProperty("shortUrl", equalTo(expectedShortUrl)),
        hasProperty("title", equalTo(expectedTitle)),
        hasProperty("description", equalTo(expectedDescription)));
  }
}
