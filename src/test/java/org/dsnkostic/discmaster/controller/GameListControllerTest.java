package org.dsnkostic.discmaster.controller;

import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_2;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_2;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_2;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_ATTRIBUTE_GAME;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_ATTRIBUTE_GAMES;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_VIEW_NAME_GAME_LIST;
import static org.dsnkostic.discmaster.Constants.ModelConstants.MODEL_VITE_NAME_GAME_DETAILS;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.hamcrest.Matchers.contains;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.dsnkostic.discmaster.entity.Game;
import org.dsnkostic.discmaster.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

// As we are testing here only the controller, I consider this the actual replacement for the real unit test
@WebMvcTest(GameListController.class)
@DisplayName("GameListController Unit test")
class GameListControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private GameRepository gameRepository;

  @Nested
  @DisplayName("When GameRepository is empty")
  class whenGameRepositoryEmpty {

    @BeforeEach
    void setUp() {
      doReturn(new ArrayList<>()).when(gameRepository).findAll();
      doReturn(Optional.empty()).when(gameRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Show index page with empty game list")
    void indexEmptyList() throws Exception {
      mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VIEW_NAME_GAME_LIST))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAMES, ImmutableList.of()));
    }

    @Test
    @DisplayName("Show error page for non existing game")
    void gameNotFound() throws Exception {
      mvc.perform(get("/game/20"))
          .andExpect(status().isNotFound())
          .andExpect(status().reason("Game with 20 could not be found"));
    }
  }

  @Nested
  @DisplayName("When GameRepository is populated")
  class whenGameRepositoryPopulated {
    private final Game game1 = new Game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1);
    private final Game game2 = new Game(GAME_SHORT_URL_2,  GAME_TITLE_2, GAME_DESCRIPTION_2);
    private final Collection<Game> games = ImmutableList.of(game1, game2);

    @BeforeEach
    void setUp() {
      doReturn(games).when(gameRepository).findAll();
      doReturn(Optional.of(game1)).when(gameRepository).findById(1L);
      doReturn(Optional.of(game2)).when(gameRepository).findById(2L);
    }

    @Test
    @DisplayName("Show index page with populated game list")
    void indexGamesList() throws Exception {
      mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VIEW_NAME_GAME_LIST))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAMES,
              contains(game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1),
                  game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2))));
    }

    @Test
    @DisplayName("Show error page for non existing game")
    void gameNotFound() throws Exception {
      mvc.perform(get("/game/20"))
          .andExpect(status().isNotFound())
          .andExpect(status().reason("Game with 20 could not be found"));
    }

    @Test
    @DisplayName("Show game details page for existing game")
    void gameFound() throws Exception {
      mvc.perform(get("/game/1"))
          .andExpect(status().isOk())
          .andExpect(view().name(MODEL_VITE_NAME_GAME_DETAILS))
          .andExpect(model().attribute(MODEL_ATTRIBUTE_GAME, game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1)));
    }
  }
}
