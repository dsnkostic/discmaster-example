package org.dsnkostic.discmaster.testutils.dbutils;

import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_2;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_3;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_DESCRIPTION_4;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_2;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_3;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_SHORT_URL_4;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_1;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_2;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_3;
import static org.dsnkostic.discmaster.Constants.GameConstants.GAME_TITLE_4;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_KEY_ARTISTIC;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_KEY_EXTRA;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_KEY_GENRE;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_4X;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_BESTSELLER;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_CONTEMPORARY;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_FPS;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_GAMEOFTHEYEAR;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_INTERWAR;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_REVOLUTIONARY;
import static org.dsnkostic.discmaster.Constants.GameTagConstants.GAMETAG_VALUE_SCIFI;
import static org.dsnkostic.discmaster.entity.Game.GameBuilder.gameBuilder;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.gameTag;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.UUID;
import org.dsnkostic.discmaster.entity.Game;
import org.hamcrest.Matcher;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class BasicDBDataSet implements DBDataSet {

  final private List<Game> games;
  final private List<UUID> gameUUIDS;
  final private List<Matcher<Game>> gameMatchers;

  public BasicDBDataSet(TestEntityManager entityManager) {
    this.games = ImmutableList.of(
        entityManager.persist(gameBuilder(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1)
            .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_4X)
            .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_SCIFI)
            .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER)
            .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR)
            .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_REVOLUTIONARY).build()),
        entityManager.persist(gameBuilder(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)
            .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS)
            .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_INTERWAR).build()),
        entityManager.persist(gameBuilder(GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3)
            .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS)
            .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_CONTEMPORARY)
            .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER)
            .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR).build()),
        entityManager.persist(gameBuilder(GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4).build()));
    this.gameUUIDS = this.games.stream().map(Game::getUuid).collect(ImmutableList.toImmutableList());
    this.gameMatchers = ImmutableList.of(
        game(gameUUIDS.get(0), GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1,
            contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_4X),
                gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_SCIFI),
                gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER),
                gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR),
                gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_REVOLUTIONARY)))),
        game(gameUUIDS.get(1), GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2,
            contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS),
                gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_INTERWAR)))),
        game(gameUUIDS.get(2), GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3,
            contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS),
                gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_CONTEMPORARY),
                gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER),
                gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR)))),
        game(gameUUIDS.get(3), GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4, empty()));
  }

  @Override
  public List<Game> getGames() {
    return games;
  }

  @Override
  public List<UUID> getGameUUIDS() {
    return gameUUIDS;
  }

  @Override
  public List<Matcher<Game>> getGameMatchers() {
    return this.gameMatchers;
  }
}
