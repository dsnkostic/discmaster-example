package org.dsnkostic.discmaster.testutils;

import java.util.Collection;
import org.springframework.ui.Model;

public class TestTools {
  @SuppressWarnings("unchecked")
  static public <T> Collection<T> collectionFromModel(Model model, String attribute) {
    if (model.getAttribute(attribute) instanceof Collection)
      return (Collection<T>)model.getAttribute(attribute);
    return null;
  }

}
