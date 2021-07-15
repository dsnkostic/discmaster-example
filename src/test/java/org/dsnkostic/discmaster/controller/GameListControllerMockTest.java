package org.dsnkostic.discmaster.controller;

import static org.dsnkostic.discmaster.Constants.GameConstants.*;
import static org.dsnkostic.discmaster.Constants.ModelConstants.*;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.dsnkostic.discmaster.testutils.TestTools.collectionFromModel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.dsnkostic.discmaster.entity.Game;
import org.dsnkostic.discmaster.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

// Although this is the real unit test, I do not see its usage as
// WebMockMvc provides much better tools for this purpose
@DisplayName("GameListController Mock Unit Test")
class GameListControllerMockTest {
  @Mock
  private GameRepository gameRepository;

  private GameListController gameListController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.gameListController = new GameListController(this.gameRepository);
  }

  @Nested
  @DisplayName("When GameRepository is empty")
  class whenGameRepositoryEmpty {

    @BeforeEach
    void setUp() {
      doReturn(new ArrayList<>()).when(gameRepository).findAll();
      doReturn(Optional.empty()).when(gameRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Return empty list")
    void indexEmptyList() {
      Model model = new ExtendedModelMap();

      assertEquals(MODEL_VIEW_NAME_GAME_LIST, gameListController.index(model));
      Collection<Game> games = collectionFromModel(model, MODEL_ATTRIBUTE_GAMES);
      assertNotNull(games);
      assertEquals(0, games.size());
    }

    @Test
    @DisplayName("Non existing game")
    void gameNotFound() {
      Model model = new ExtendedModelMap();

      ResponseStatusException exception = assertThrows(ResponseStatusException.class,
          () -> gameListController.getGame(20, model));

      assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
      assertEquals("Game with 20 could not be found", exception.getReason());
    }
  }

  @Nested
  @DisplayName("When GameRepository is empty")
  class whenGameRepositoryPopulated {
    private final Game game1 = new Game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1);
    private final Game game2 = new Game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2);
    private final Collection<Game> games = ImmutableList.of(game1, game2);

    @BeforeEach
    void setUp() {
      doReturn(games).when(gameRepository).findAll();
      doReturn(Optional.of(game1)).when(gameRepository).findById(1L);
      doReturn(Optional.of(game2)).when(gameRepository).findById(2L);
    }

    @Test
    @DisplayName("Return games list")
    void indexGamesList() {
      Model model = new ExtendedModelMap();

      assertEquals(MODEL_VIEW_NAME_GAME_LIST, gameListController.index(model));
      assertThat(collectionFromModel(model, MODEL_ATTRIBUTE_GAMES),
          contains(game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1),
              game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)));
    }

    @Test
    @DisplayName("Non existing game")
    void gameNotFound() {
      Model model = new ExtendedModelMap();

      ResponseStatusException exception = assertThrows(ResponseStatusException.class,
          () -> gameListController.getGame(20, model));

      assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
      assertEquals("Game with 20 could not be found", exception.getReason());
    }

    @Test
    @DisplayName("Existing game")
    void gameFound() {
      Model model = new ExtendedModelMap();

      assertEquals(MODEL_VITE_NAME_GAME_DETAILS, gameListController.getGame(1L, model));
      assertThat(model.getAttribute(MODEL_ATTRIBUTE_GAME), game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1));
    }
  }
}
