package org.dsnkostic.discmaster.testutils;

import static org.dsnkostic.discmaster.testutils.TestTools.collectionFromModel;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

@DisplayName("TestTools Unit Test")
public class TestToolsTest {
  @Nested
  @DisplayName("collectionFromModel static method testing")
  class collectionFromModelTesting {

    private Model model;

    @BeforeEach
    void setUp() {
      this.model = new ExtendedModelMap();
    }

    private List<Integer> createTestIntegerArray() {
      return ImmutableList.of(1, 2, 3, 4);
    }

    @Test
    @DisplayName("Return null if there is no attribute in model")
    void missingAttribute_ReturnNull() {
      assertNull(collectionFromModel(model, "TEST"));
    }

    @Test
    @DisplayName("Return null if attribute is not collection in model")
    void attributeNotCollection_ReturnNull() {
      model.addAttribute("TEST", "SomethingElse");

      assertNull(collectionFromModel(model, "TEST"));
    }

    @Test
    @DisplayName("Return integer collection from model")
    void collectionInteger_ReturnIntegerIterable() {
      model.addAttribute("TEST", createTestIntegerArray());

      assertIterableEquals(createTestIntegerArray(), collectionFromModel(model, "TEST"));
    }

  }
}
