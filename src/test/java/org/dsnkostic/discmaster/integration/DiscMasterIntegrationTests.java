package org.dsnkostic.discmaster.integration;

import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_2;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_ATTRIBUTE_GAME;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_ATTRIBUTE_GAMES;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_VIEW_NAME_GAME_LIST;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_VITE_NAME_GAME_DETAILS;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.dsnkostic.discmaster.testutils.dbutils.BasicDBDataSet;
import org.dsnkostic.discmaster.testutils.dbutils.DBDataSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureMockMvc
@Transactional
@DisplayName("DiscMaster main integration test where all features will be tested")
class DiscMasterIntegrationTests {
  private DBDataSet dbDataSet;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MockMvc mvc;

  void prefillDatabase() {
    dbDataSet = new BasicDBDataSet(entityManager);
  }

  @Nested
  @DisplayName("Index page tests")
  class IndexPageTests {

    @Test
    @DisplayName("Show index page with empty game list")
    void emptyIndexPage() throws Exception {
      mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VIEW_NAME_GAME_LIST))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAMES, ImmutableList.of()));
    }

    @Test
    @DisplayName("Show index page with populated game list")
    void populatedGamesList() throws Exception {
      prefillDatabase();

      mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VIEW_NAME_GAME_LIST))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAMES, contains(
              dbDataSet.getGameMatchers().get(0),
              dbDataSet.getGameMatchers().get(1),
              dbDataSet.getGameMatchers().get(2),
              dbDataSet.getGameMatchers().get(3))));
    }
  }

  @Nested
  @DisplayName("Game Details page tests")
  class GameDetailsPageTests {
    @Test
    @DisplayName("Show error page for non existing game")
    void gameNotFound() throws Exception {
      mvc.perform(get("/game/some"))
          .andExpect(status().isNotFound())
          .andExpect(status().reason("Game some could not be found"));
    }

    @Test
    @DisplayName("Show game details page for existing game")
    void gameFound() throws Exception {
      prefillDatabase();

      mvc.perform(get("/game/" + GAME_SHORT_URL_2))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VITE_NAME_GAME_DETAILS))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAME, dbDataSet.getGameMatchers().get(1)));
    }
  }
}
