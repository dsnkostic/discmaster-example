package org.dsnkostic.discmaster.integration;

import static org.dsnkostic.discmaster.Constants.GameConstants.*;
import static org.dsnkostic.discmaster.Constants.ModelConstants.*;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.UUID;
import org.dsnkostic.discmaster.entity.Game;
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
  private UUID game1id;
  private UUID game2id;
  private UUID game3id;
  private UUID game4id;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MockMvc mvc;

  void prefillDatabase() {
    game1id = entityManager.persist(new Game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1)).getUuid();
    game2id = entityManager.persist(new Game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)).getUuid();
    game3id = entityManager.persist(new Game(GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3)).getUuid();
    game4id = entityManager.persist(new Game(GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4)).getUuid();
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
              game(game1id, GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1),
              game(game2id, GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2),
              game(game3id, GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3),
              game(game4id, GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4))));
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
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAME, game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)));
    }
  }
}
