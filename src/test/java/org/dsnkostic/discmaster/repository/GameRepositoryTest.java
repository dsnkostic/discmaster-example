package org.dsnkostic.discmaster.repository;

import static org.dsnkostic.discmaster.Constants.GameConstants.*;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.dsnkostic.discmaster.entity.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@DisplayName("GameRepository JPA Test")
class GameRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private GameRepository gameRepository;

  @Nested
  @DisplayName("When GameRepository empty")
  class whenEmptyGameList {

    @Test
    @DisplayName("Find All should return 0 games")
    void findGamesByIdShouldReturnNone() {
      List<Game> games = Lists.newArrayList(gameRepository.findAll());
      assertEquals(0, games.size());
    }

    @Test
    @DisplayName("Find By ID should not find any game")
    void findGameByIdShouldReturnNone() {
      assertTrue(gameRepository.findById(1L).isEmpty());
    }

    @Test
    @DisplayName("Find By Short Url should noy find any game")
    void findGameByShortUrlShouldReturnNone() {
      assertTrue(gameRepository.findOptionalByShortUrl("something").isEmpty());
    }
  }
  @Nested
  @DisplayName("When GameRepository populated")
  class whenGameListPopulated {
    private long game1id;
    private long game2id;
    private long game3id;
    private long game4id;

    @BeforeEach
    void setUp() {
      game1id = entityManager.persist(new Game(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1)).getId();
      game2id = entityManager.persist(new Game(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)).getId();
      game3id = entityManager.persist(new Game(GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3)).getId();
      game4id = entityManager.persist(new Game(GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4)).getId();
    }

    @Test
    @DisplayName("Find all should return all games")
    void findGamesByIdShouldReturnAllGames() {
      List<Game> games = Lists.newArrayList(gameRepository.findAll());

      assertThat(games, contains(
          game(game1id, GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1),
          game(game2id, GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2),
          game(game3id, GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3),
          game(game4id, GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4)));
    }

    @Test
    @DisplayName("Find By Id With existing id should find one game")
    void findGameByIdShouldReturnOneGame() {
      Optional<Game> game = gameRepository.findById(1L);

      assertTrue(game.isPresent());
      assertThat(game.get(), game(game1id, GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1));
    }

    @Test
    @DisplayName("Find by Id Without existing id should not find any game")
    void findGameByIdShouldReturnNone() {
      assertTrue(gameRepository.findById(1000L).isEmpty());
    }

    @Test
    @DisplayName("Find By Short Url With existing short url should find one game")
    void findGameByShortUrlShouldReturnOneGame() {
      Optional<Game> game = gameRepository.findOptionalByShortUrl(GAME_SHORT_URL_2);

      assertTrue(game.isPresent());
      assertThat(game.get(), game(game2id, GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2));
    }

    @Test
    @DisplayName("Find By Short Url Without existing short url should return none")
    void findGameByShortUrlShouldReturnNone() {
      assertTrue(gameRepository.findOptionalByShortUrl("asdasd").isEmpty());
    }
  }

}
