package org.dsnkostic.discmaster.controller;

import static org.dsnkostic.discmaster.testutils.TestTools.collectionFromModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

@DisplayName("GameListController Unit Test")
class GameListControllerTest {

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

      assertEquals("game-list", gameListController.index(model));
      Collection<Game> games = collectionFromModel(model, "games");
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
    private static final String GAME_TITLE_1 = "title1";
    private static final String GAME_TITLE_2 = "title2";
    private static final String GAME_DESCRIPTION_1 = "description1";
    private static final String GAME_DESCRIPTION_2 = "description2";

    private Game game1 = createGame(GAME_TITLE_1, GAME_DESCRIPTION_1);
    private Game game2 = createGame(GAME_TITLE_2, GAME_DESCRIPTION_2);
    private Collection<Game> games = ImmutableList.of(game1, game2);

    private Game createGame(String title, String description) {
      Game game = new Game();
      game.setTitle(title);
      game.setDescription(description);

      return game;
    }

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

      assertEquals("game-list", gameListController.index(model));
      List<Game> gamesReturned = getGamesFromModel(model);
      assertNotNull(gamesReturned);
      assertEquals(2, gamesReturned.size());
      assertGame(GAME_TITLE_1, GAME_DESCRIPTION_1, gamesReturned.get(0));
      assertGame(GAME_TITLE_2, GAME_DESCRIPTION_2, gamesReturned.get(1));
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
    @DisplayName("Non existing game")
    void gameFound() {
      Model model = new ExtendedModelMap();

      assertEquals("game-details", gameListController.getGame(1L, model));
      Game gameReturned = (Game) model.getAttribute("game");
      assertNotNull(gameReturned);
      assertGame(GAME_TITLE_1, GAME_DESCRIPTION_1, gameReturned);
    }

    private List<Game> getGamesFromModel(Model model) {
      return new ArrayList<>(Objects.requireNonNull(collectionFromModel(model, "games")));
    }

    private void assertGame(String expectedTitle, String expectedDescription, Game actual) {
      assertEquals(expectedTitle, actual.getTitle());
      assertEquals(expectedDescription, actual.getDescription());
    }
  }
}
