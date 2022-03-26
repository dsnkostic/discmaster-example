package org.dsnkostic.discmaster.repository;

import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.util.Lists;
import org.dsnkostic.discmaster.entity.Game;
import org.dsnkostic.discmaster.testutils.dbutils.BasicDBDataSet;
import org.dsnkostic.discmaster.testutils.dbutils.DBDataSet;
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
      assertTrue(gameRepository.findById(UUID.randomUUID()).isEmpty());
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
    private DBDataSet dbDataSet;

    @BeforeEach
    void setUp() {
      dbDataSet = new BasicDBDataSet(entityManager);
    }

    @Test
    @DisplayName("Find all should return all games")
    void findGamesByIdShouldReturnAllGames() {
      List<Game> games = Lists.newArrayList(gameRepository.findAll());

      assertThat(games, contains(
          dbDataSet.getGameMatchers().get(0),
          dbDataSet.getGameMatchers().get(1),
          dbDataSet.getGameMatchers().get(2),
          dbDataSet.getGameMatchers().get(3)));
    }

    @Test
    @DisplayName("Find By Id With existing id should find one game")
    void findGameByIdShouldReturnOneGame() {
      Optional<Game> game = gameRepository.findById(dbDataSet.getGameUUIDS().get(0));

      assertTrue(game.isPresent());
      assertThat(game.get(), dbDataSet.getGameMatchers().get(0));
    }

    @Test
    @DisplayName("Find by Id Without existing id should not find any game")
    void findGameByIdShouldReturnNone() {
      assertTrue(gameRepository.findById(UUID.randomUUID()).isEmpty());
    }

    @Test
    @DisplayName("Find By Short Url With existing short url should find one game")
    void findGameByShortUrlShouldReturnOneGame() {
      Optional<Game> game = gameRepository.findOptionalByShortUrl(GAME_SHORT_URL_2);

      assertTrue(game.isPresent());
      assertThat(game.get(), dbDataSet.getGameMatchers().get(1));
    }

    @Test
    @DisplayName("Find By Short Url Without existing short url should return none")
    void findGameByShortUrlShouldReturnNone() {
      assertTrue(gameRepository.findOptionalByShortUrl("asdasd").isEmpty());
    }
  }

}
