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
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_1_01;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_1_02;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_1_03;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_1_04;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_2_01;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_2_02;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_2_03;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_2_04;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_3_01;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_3_02;
import static org.dsnkostic.discmaster.Constants.ScreenshotConstants.SCREENSHOT_FILENAME_3_03;
import static org.dsnkostic.discmaster.Constants.ThumbnailConstants.THUMBNAIL_FILENAME_1;
import static org.dsnkostic.discmaster.Constants.ThumbnailConstants.THUMBNAIL_FILENAME_2;
import static org.dsnkostic.discmaster.Constants.ThumbnailConstants.THUMBNAIL_FILENAME_3;
import static org.dsnkostic.discmaster.entity.Game.GameBuilder.gameBuilder;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.game;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.gameTag;
import static org.dsnkostic.discmaster.testutils.CustomMatchers.imageBase;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;

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

  private Game createGame01() {
    return gameBuilder(GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1)
        .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_4X)
        .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_SCIFI)
        .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER)
        .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR)
        .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_REVOLUTIONARY)
        .withThumbnail(THUMBNAIL_FILENAME_1)
        .withScreenshot(SCREENSHOT_FILENAME_1_01)
        .withScreenshot(SCREENSHOT_FILENAME_1_02)
        .withScreenshot(SCREENSHOT_FILENAME_1_03)
        .withScreenshot(SCREENSHOT_FILENAME_1_04)
        .build();
  }

  private Game createGame02() {
    return gameBuilder(GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2)
        .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS)
        .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_INTERWAR)
        .withThumbnail(THUMBNAIL_FILENAME_2)
        .withScreenshot(SCREENSHOT_FILENAME_2_01)
        .withScreenshot(SCREENSHOT_FILENAME_2_02)
        .withScreenshot(SCREENSHOT_FILENAME_2_03)
        .withScreenshot(SCREENSHOT_FILENAME_2_04)
        .build();
  }

  private Game createGame03() {
    return gameBuilder(GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3)
        .withGameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS)
        .withGameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_CONTEMPORARY)
        .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER)
        .withGameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR)
        .withThumbnail(THUMBNAIL_FILENAME_3)
        .withScreenshot(SCREENSHOT_FILENAME_3_01)
        .withScreenshot(SCREENSHOT_FILENAME_3_02)
        .withScreenshot(SCREENSHOT_FILENAME_3_03)
        .build();
  }

  private Game createGame04() {
    return gameBuilder(GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4).build();
  }


  private Matcher<Game> gameMatcher01() {
    return game(gameUUIDS.get(0), GAME_SHORT_URL_1, GAME_TITLE_1, GAME_DESCRIPTION_1,
        contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_4X),
            gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_SCIFI),
            gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER),
            gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR),
            gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_REVOLUTIONARY))),
        imageBase(THUMBNAIL_FILENAME_1),
        contains(ImmutableList.of(imageBase(SCREENSHOT_FILENAME_1_01),
            imageBase(SCREENSHOT_FILENAME_1_02),
            imageBase(SCREENSHOT_FILENAME_1_03),
            imageBase(SCREENSHOT_FILENAME_1_04))));
  }

  private Matcher<Game> gameMatcher02() {
    return game(gameUUIDS.get(1), GAME_SHORT_URL_2, GAME_TITLE_2, GAME_DESCRIPTION_2,
        contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS),
            gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_INTERWAR))),
        imageBase(THUMBNAIL_FILENAME_2),
        contains(ImmutableList.of(imageBase(SCREENSHOT_FILENAME_2_01),
            imageBase(SCREENSHOT_FILENAME_2_02),
            imageBase(SCREENSHOT_FILENAME_2_03),
            imageBase(SCREENSHOT_FILENAME_2_04))));
  }

  private Matcher<Game> gameMatcher03() {
    return game(gameUUIDS.get(2), GAME_SHORT_URL_3, GAME_TITLE_3, GAME_DESCRIPTION_3,
        contains(ImmutableList.of(gameTag(GAMETAG_KEY_GENRE, GAMETAG_VALUE_FPS),
            gameTag(GAMETAG_KEY_ARTISTIC, GAMETAG_VALUE_CONTEMPORARY),
            gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_BESTSELLER),
            gameTag(GAMETAG_KEY_EXTRA, GAMETAG_VALUE_GAMEOFTHEYEAR))),
        imageBase(THUMBNAIL_FILENAME_3),
        contains(ImmutableList.of(imageBase(SCREENSHOT_FILENAME_3_01),
            imageBase(SCREENSHOT_FILENAME_3_02),
            imageBase(SCREENSHOT_FILENAME_3_03))));
  }

  private Matcher<Game> gameMatcher04() {
    return game(gameUUIDS.get(3), GAME_SHORT_URL_4, GAME_TITLE_4, GAME_DESCRIPTION_4, empty(), nullValue(), empty());
  }

  public BasicDBDataSet(TestEntityManager entityManager) {
    this.games = ImmutableList.of(
        entityManager.persist(createGame01()),
        entityManager.persist(createGame02()),
        entityManager.persist(createGame03()),
        entityManager.persist(createGame04()));
    this.gameUUIDS = this.games.stream().map(Game::getUuid).collect(ImmutableList.toImmutableList());
    this.gameMatchers = ImmutableList.of(
        gameMatcher01(),
        gameMatcher02(),
        gameMatcher03(),
        gameMatcher04());
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
